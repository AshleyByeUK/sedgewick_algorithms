package uk.ashleybye.sedgewick.graph;

/**
 * ConnectedComponents uses a depth first search algorithm to determine the number of connected
 * components within a graph. It takes the first vertex and marks all vertices connected to it.
 * Next, it locates the next unmarked vertex and marks all vertices connected to it. This process
 * repeats until all vertices in the graph have been visited. A vertex-index array is maintained,
 * which associates a component identifier with all vertices in that component.
 */
public class ConnectedComponents {

  private boolean[] markedVertices;
  private int[] componentIdentifiers;
  private int componentCount;

  /**
   * Construct a new instance of ConnectedComponents with the specified graph and carry out
   * pre-processing to establish the number of connected components.
   *
   * @param graph The graph.
   */
  public ConnectedComponents(Graph graph) {
    markedVertices = new boolean[graph.getNumVertices()];
    componentIdentifiers = new int[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex);
        componentCount++;
      }
    }
  }

  /**
   * Conduct a depth first search from the given vertex in the specified graph, and assign a
   * component identifier to the visited vertex.
   *
   * @param graph The graph to search.
   * @param vertex The vertex to search from.
   */
  private void depthFirstSearch(Graph graph, int vertex) {
    markedVertices[vertex] = true;
    componentIdentifiers[vertex] = componentCount;

    for (int adjacentVertex : graph.adjacentTo(vertex)) {
      if (!markedVertices[adjacentVertex]) {
        depthFirstSearch(graph, adjacentVertex);
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
  public boolean connected(int u, int v) {
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
