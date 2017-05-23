package uk.ashleybye.sedgewick.graph;

import java.util.Scanner;
import uk.ashleybye.sedgewick.collections.Bag;

/**
 * The Graph type stores a representation of a graph in an adjacency-list. Vertices are represented
 * by their array index, with their adjacent vertices stored in a list referenced by the array
 * index. This provides: space usage proportional to V + E; constant time to add an edge; and, time
 * proportional to the degree of a vertex to iterate through all vertices adjacent to it (i.e.
 * constant time per adjacent vertex processed).
 *
 * Such a data structure means that for any given edge, its incident vertices will be referenced in
 * the adjacency-list for the other vertex in the pair. For example:
 *
 * V   E
 * 12: 25
 * 25: 12
 *
 * Vertices will be processed in the order in which they appear in the adjacency-list.
 *
 * Using a symbol-table in lieu of a vertex-indexed array could be useful to allow adding and
 * deleting of vertices. It would also remove the requirement that vertex names are integer
 * indices. See Sedgewick, Algorithms, p527 for more discussion on this and other possible
 * alternative data structures.
 */
public class Graph {

  /**
   * The number of vertices.
   */
  private final int numVertices;

  /**
   * The number of edges.
   */
  private int numEdges;

  /**
   * The adjacency lists.
   */
  private Bag<Integer>[] adjacencyLists;

  /**
   * Create a V-vertex undirected graph with zero edges.
   *
   * @param numVertices The number of vertices.
   */
  public Graph(int numVertices) {
    this.numVertices = numVertices;
    this.numEdges = 0;
    adjacencyLists = (Bag<Integer>[]) new Bag[numVertices];
    for (int v = 0; v < numVertices; v++) {
      adjacencyLists[v] = new Bag<>();
    }
  }

  /**
   * Create an undirected graph by reading a file from a Scanner.
   *
   * It expects an input format consisting of 2E + 2 integer values each separated by a newline.
   * First, the number of vertices, next the number of edges, followed by pairs of vertex numbers
   * of edges. For example:
   *
   * 250
   * 1273
   * 244 246
   * 239 240
   * 238 245 ...
   *
   * @param scanner The {@code Scanner} containing the source data.
   */
  public Graph(Scanner scanner) {
    this(scanner.nextInt());
    this.numEdges = scanner.nextInt();

    for (int e = 0; e < numEdges; e++) {
      int v = scanner.nextInt();
      int w = scanner.nextInt();
      addEdge(v, w);
    }
  }

  /**
   * Gets the number of vertices in this graph.
   *
   * @return The number of vertices.
   */
  public int getNumVertices() {
    return numVertices;
  }

  /**
   * Gets the number of edges in this graph.
   *
   * @return The number of edges.
   */
  public int getNumEdges() {
    return numEdges;
  }

  /**
   * Add an undirected edge from vertex u to vertex v in this graph.
   *
   * @param u Vertex incident to edge.
   * @param v Vertex incident to edge.
   */
  public void addEdge(int u, int v) {
    adjacencyLists[u].add(v);
    adjacencyLists[v].add(u);
  }

  /**
   * Gets the vertices adjacent to the specified vertex.
   *
   * @param vertex The vertex to get adjacent vertices for.
   *
   * @return The vertices adjacent to the specified vertex.
   */
  public Iterable<Integer> adjacentTo(int vertex) {
    return adjacencyLists[vertex];
  }

  /**
   * Gets a {@code String} representation of this graph, in the following format:
   *
   * V vertices, E edges
   * v: e e e
   * v: e e e e e
   * v: e
   * ...
   *
   * @return A {@code String} representation of this graph.
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(numVertices).append(" vertices, ").append(numEdges).append(" edges")
        .append("\n");
    for (int v = 0; v < numVertices; v++) {
      stringBuilder.append(v).append(": ");
      for (int w : this.adjacentTo(v)) {
        stringBuilder.append(w).append(" ");
      }
      stringBuilder.append("\n");
    }

    return stringBuilder.toString();
  }
}
