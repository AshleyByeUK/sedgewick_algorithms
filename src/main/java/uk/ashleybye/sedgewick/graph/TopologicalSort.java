package uk.ashleybye.sedgewick.graph;

/**
 * Conducts a topological sort on the given digraph. Solves precedence-constrained scheduling
 * problems (see pages 574 and 575). That is, sorting the digraph such that all its directed edges
 * from a vertex earlier in the order point to a vertex later in the order (or report that it is not
 * possible to do so - if the digraph is not acyclic).
 */
public class TopologicalSort {

  private Iterable<Integer> topologicalOrder;

  /**
   * Constructs an instance of TopologicalSort and sorts the given digraph into topological order.
   *
   * @param digraph The digraph.
   */
  public TopologicalSort(Digraph digraph) {
    DirectedCycle cycleFinder = new DirectedCycle(digraph);
    if (!cycleFinder.getCycle().isPresent()) {
      DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
      topologicalOrder = depthFirstOrder.reversePostOrder();
    }
  }

  /**
   * Get the topological ordering of the digraph.
   *
   * @return The digraph.
   */
  public Iterable<Integer> getTopologicalOrder() {
    return topologicalOrder;
  }

  /**
   * Returns true if the digraph is acyclic; false, otherwise.
   *
   * @return True if the digraph is acyclic; false, otherwise.
   */
  public boolean isDirectedAcyclicGraph() {
    return topologicalOrder == null;
  }
}
