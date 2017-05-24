package uk.ashleybye.sedgewick.graph;

/**
 * This is an implementation of Kosaraju's algorithm for computing strongly connected components in
 * a digraph. To do so, it does a depth-first search in the reverse digraph, which produces a vertex
 * order; the reverse post-order vertex ordering of the search in the reverse digraph is then used
 * in a depth-first search of the given digraph.
 *
 * Once the vertex order has been computed, it takes the first vertex and marks all vertices
 * connected to it. Next, it locates the next unmarked vertex and marks all vertices connected to
 * it. This process repeats until all vertices in the graph have been visited. A vertex-index array
 * is maintained, which associates a component identifier with all vertices in that component.
 */
public class StronglyConnectedComponents {

  private boolean[] markedVertices;
  private int[] componentIdentifiers;
  private int componentCount;

  /**
   * Construct a new instance of StronglyConnectedComponents with the specified digraph and carry
   * out pre-processing to establish the number of strongly connected components.
   *
   * @param digraph The digraph.
   */
  public StronglyConnectedComponents(Digraph digraph) {
    markedVertices = new boolean[digraph.getNumVertices()];
    componentIdentifiers = new int[digraph.getNumVertices()];
    DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph.reverse());

    for (int vertex : depthFirstOrder.reversePostOrder()) {
      if (!markedVertices[vertex]) {
        directedDepthFirstSearch(digraph, vertex);
        componentCount++;
      }
    }
  }

  /**
   * Conduct a depth first search from the given vertex in the specified digraph, and assign a
   * component identifier to the visited vertex.
   *
   * @param digraph The digraph to search.
   * @param head The vertex to search from.
   */
  private void directedDepthFirstSearch(Digraph digraph, int head) {
    markedVertices[head] = true;
    componentIdentifiers[head] = componentCount;

    for (int tail : digraph.adjacentTo(head)) {
      if (!markedVertices[tail]) {
        directedDepthFirstSearch(digraph, tail);
      }
    }
  }

  /**
   * Compares the component identifier for each vertex and returns true if two vertices are in the
   * same component; false, otherwise.
   *
   * @param u The first vertex.
   * @param v The second vertex.
   *
   * @return True if the vertices are connected; false, otherwise.
   */
  public boolean stronglyConnected(int u, int v) {
    return componentIdentifiers[u] == componentIdentifiers[v];
  }

  /**
   * Get the component identifier for the specified vertex.
   *
   * @param vertex The vertex.
   *
   * @return The component id.
   */
  public int getComponentId(int vertex) {
    return componentIdentifiers[vertex];
  }

  /**
   * Get the number of components in the graph.
   *
   * @return The number of components.
   */
  public int getComponentCount() {
    return componentCount;
  }
}
