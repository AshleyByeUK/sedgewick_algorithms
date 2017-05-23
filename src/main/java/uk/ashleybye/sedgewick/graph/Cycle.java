package uk.ashleybye.sedgewick.graph;

public class Cycle {

  private boolean[] markedVertices;
  private boolean hasCycle;

  /**
   * Constructs an instance of Cycle and pre-computes whether or not the specified graph is acyclic.
   *
   * @param graph The graph.
   */
  public Cycle(Graph graph) {
    markedVertices = new boolean[graph.getNumVertices()];
    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex, vertex);
      }
    }
  }

  /**
   * Uses a depth first search to determine if the specified graph contains any cycles. If any
   * vertex is connected to a previously visited node then that graph is not acyclic. Given that
   * depth first search traverses an edge in both directions, first away from a vertex and then back
   * to it on the return journey, a vertex can be connected to a previously visited vertex and be
   * acyclic if, and only if, that vertex is its immediate parent. This is the purpose of the {@code
   * else if} statement: if a previously visited vertex is not this vertices parent, a cycle
   * exists.
   *
   * @param graph The graph to check for cycles.
   * @param u The vertex from which to make the next traversal.
   * @param v The parent vertex of {@code u}.
   */
  private void depthFirstSearch(Graph graph, int u, int v) {
    markedVertices[u] = true;
    for (int vertex : graph.adjacentTo(u)) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex, u);
      } else if (vertex != v) {
        hasCycle = true;
      }
    }
  }

  /**
   * Returns true if the graph does not contain any cycles; false, otherwise.
   *
   * @return True if the graph has no cycles; false, otherwise.
   */
  public boolean isAcyclic() {
    return !hasCycle;
  }
}
