package uk.ashleybye.sedgewick.sort;

import java.util.Scanner;

/**
 * Created by ash on 07/05/2017.
 * From Sedgewick, page 22.
 */
public class UnionFind {

  private int[] vertexParent;   // Access to component ID (vertex indexed).
  private int[] pathLength;     // Length of path from vertex to root (vertex indexed).
  private int numComponents;    // Number of components.

  public UnionFind(int numVertices) {
    // Initialise vertexParent and pathLength arrays.
    numComponents = numVertices;
    vertexParent = new int[numVertices];
    pathLength = new int[numVertices];   // For weighted quick-union.
    for (int vertexId = 0; vertexId < numVertices; vertexId++) {
      vertexParent[vertexId] = vertexId;
      pathLength[vertexId] = 1;
    }
  }

  public int count() {
    return numComponents;
  }

  public boolean connected(int u, int v) {
    return find(u) == find(v);
  }

  public int find(int u) {
    return findWeightedQuickUnion(u);
  }

  public void union(int u, int v) {
    unionWeightedQuickUnion(u, v);
  }

  /*
   * Quick-find, page 222.
   * ~ N^2 => not great with large data-sets.
   */
  private int findQuickFind(int u) {
    return vertexParent[u];
  }

  private void unionQuickFind(int u, int v) {
    // Put u and v into the same component.
    int uComponent = find(u);
    int vComponent = find(v);

    if (uComponent == vComponent) {
      // Nothing to do if u and v are already in the same component.
      return;
    } else {
      // Assign ID of uComponent to all vertices with ID of vComponent.
      for (int vertexId = 0; vertexId < vertexParent.length; vertexId++) {
        if (vertexParent[vertexId] == vComponent) {
          vertexParent[vertexId] = uComponent;
        }
      }
      numComponents--;
    }
  }

  /*
   * Quick-union, page 224.
   * ~ N -> ~ N^2 depending on input => still not great, although potential improvement
   *    over quick-find.
   */
  private int findQuickUnion(int u) {
    while (u != vertexParent[u]) {
      // Follow each edge from current vertex to root vertex for component.
      u = vertexParent[u];
    }
    return u;
  }

  private void unionQuickUnion(int u, int v) {
    // Give u and v the same root vertex.
    int uRoot = find(u);
    int vRoot = find(v);

    if (uRoot == vRoot) {
      // Nothing to do if u and v already have the same root vertex.
      return;
    } else {
      vertexParent[vRoot] = uRoot;
      numComponents--;
    }
  }

  /*
   * Weighted quick-union, page 228.
   * logN => good choice.
   *
   * Can also be implemented with path compression, which gives almost
   * constant time (1, but not quite) per operation. To implement, add
   * another loop to find() which sets vertexParent[vertex] to the root
   * vertex for each of the vertices on the path from u to uRoot. However,
   * for most practical situations, improvements in performance are
   * unlikely to be discerned.
   */
  private int findWeightedQuickUnion(int u) {
    validate(u);
    int root = u;
    while (root != vertexParent[root]) {
      // Follow each edge from current vertex to root vertex for component.
      root = vertexParent[root];
    }
    while (u != root) {
      // Compress paths to all point directly to the root vertex.
      int temp = vertexParent[u];
      vertexParent[u] = root;
      u = temp;
    }
    return root;
  }

  private void validate(int u) {
    if (u < 0 || u >= vertexParent.length) {
      throw new IndexOutOfBoundsException("Index "
          + u + " is not between 0 and " + (vertexParent.length - 1));
    }
  }

  private void unionWeightedQuickUnion(int u, int v) {
    int uRoot = find(u);
    int vRoot = find(v);

    if (uRoot == vRoot) {
      // Nothing to do if u and v already have the same root vertex.
      return;
    } else {
      // Make the smaller root point to the larger one.
      if (pathLength[uRoot] < pathLength[vRoot]) {
        vertexParent[uRoot] = vRoot;
        pathLength[vRoot] += pathLength[uRoot];
      } else {
        vertexParent[vRoot] = uRoot;
        pathLength[uRoot] += pathLength[vRoot];
      }

      numComponents--;
    }
  }

  // java uk.ashleybye.sedgewick.sort.UnionFind < tinyUF.txt
  // java uk.ashleybye.sedgewick.sort.UnionFind < mediumUF.txt
  // java uk.ashleybye.sedgewick.sort.UnionFind < largeUF.txt
  public static void main(String[] args) {
    // Solve dynamic connectivity problem on System.in.
    Scanner scanner = new Scanner(System.in);

    int numVertices = scanner.nextInt();              // Read number of vertices.
    UnionFind unionFind = new UnionFind(numVertices); // Initialise with numVertices components.

    while (scanner.hasNextInt()) {
      int u = scanner.nextInt();
      int v = scanner.nextInt();    // Read pair of vertices to connect.
      if (!unionFind.connected(u, v)) {
        // If the two vertices are not connected, connect them.
        unionFind.union(u, v);

        System.out.println(u + " " + v);
      }
    }

    System.out.println(unionFind.count() + " components");
  }
}
