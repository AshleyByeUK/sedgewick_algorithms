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
   * @param graph The directed acyclic graph.
   */
  public DepthFirstOrder(Digraph graph) {
    preOrder = new Queue<>();
    postOrder = new Queue<>();
    reversePostOrder = new Stack<>();

    markedVertices = new boolean[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex);
      }
    }
  }

  /**
   * Constructs an instance of DepthFirstOrder and computes the pre-, post- and reverse post-order
   * orderings for the given edge-weighted directed acyclic graph.
   *
   * @param graph The edge-weighted directed graph.
   */
  public DepthFirstOrder(EdgeWeightedDigraph graph) {
    preOrder = new Queue<>();
    postOrder = new Queue<>();
    reversePostOrder = new Stack<>();

    markedVertices = new boolean[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex);
      }
    }
  }

  /**
   * Conducts a depth first search of the directed acyclic graph and constructs the pre-, post-, and
   * reverse post-order orderings.
   *
   * @param graph The directed acyclic graph to search.
   * @param head The vertex to search from.
   */
  private void depthFirstSearch(Digraph graph, int head) {
    preOrder.enqueue(head);

    markedVertices[head] = true;
    for (int tail : graph.adjacentTo(head)) {
      if (!markedVertices[tail]) {
        depthFirstSearch(graph, tail);
      }
    }

    postOrder.enqueue(head);
    reversePostOrder.push(head);
  }

  /**
   * Conducts a depth first search of the edge-weighted directed acyclic graph and constructs the
   * pre-, post-, and reverse post-order orderings.
   *
   * @param graph The edge-weighted directed acyclic graph to search.
   * @param head The vertex to search from.
   */
  private void depthFirstSearch(EdgeWeightedDigraph graph, int head) {
    preOrder.enqueue(head);

    markedVertices[head] = true;
    for (DirectedEdge tail : graph.adjacentTo(head)) {
      if (!markedVertices[tail.to()]) {
        depthFirstSearch(graph, tail.to());
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
