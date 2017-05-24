package uk.ashleybye.sedgewick.graph;

import uk.ashleybye.sedgewick.collections.Queue;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * The class conducts a depth first search ordering of vertices in a directed acyclic graph.
 *
 * Vertex ordering is available in:
 *
 * - Pre-order.
 * - Post-order.
 * - Reverse post-order.
 */
public class DepthFirstOrder {

  private boolean[] markedVertices;

  private Queue<Integer> preOrder;
  private Queue<Integer> postOrder;
  private Stack<Integer> reversePostOrder;

  /**
   * Constructs an instance of DepthFirstOrder and computes the pre-, post- and reverse post-order
   * orderings for the given directed acyclic graph.
   *
   * @param digraph The directed acyclic graph.
   */
  public DepthFirstOrder(Digraph digraph) {
    preOrder = new Queue<>();
    postOrder = new Queue<>();
    reversePostOrder = new Stack<>();

    markedVertices = new boolean[digraph.getNumVertices()];

    for (int vertex = 0; vertex < digraph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        directedDepthFirstSearch(digraph, vertex);
      }
    }
  }

  /**
   * Conducts a depth first search of the digraph and constructs the pre-, post-, and reverse
   * post-order orderings.
   *
   * @param digraph The digraph to search.
   * @param head The vertex to search from.
   */
  private void directedDepthFirstSearch(Digraph digraph, int head) {
    preOrder.enqueue(head);

    markedVertices[head] = true;
    for (int tail : digraph.adjacentTo(head)) {
      if (!markedVertices[tail]) {
        directedDepthFirstSearch(digraph, tail);
      }
    }

    postOrder.enqueue(head);
    reversePostOrder.push(head);
  }

  /**
   * Vertices ordered in pre-order or being visited. That is, the order in which they are visited.
   *
   * @return Vertices in pre-order.
   */
  public Iterable<Integer> preOrder() {
    return preOrder;
  }

  /**
   * Vertices ordered in post-order or being visited.
   *
   * @return Vertices in post-order.
   */
  public Iterable<Integer> postOrder() {
    return postOrder;
  }

  /**
   * Vertices ordered in reverse post-order of being visited.
   *
   * @return Vertices in reverse post-order.
   */
  public Iterable<Integer> reversePostOrder() {
    return reversePostOrder;
  }
}
