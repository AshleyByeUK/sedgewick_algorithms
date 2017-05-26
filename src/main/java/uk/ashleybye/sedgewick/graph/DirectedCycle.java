package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Detects cycles in the given digraph. It is possible that an exponential number of cycles exist.
 * However, to prove that a digraph is a Directed Acyclic Graph (DAG), it is only necessary that no
 * cycles exist. That is, if there is one or more cycles, the graph is not a DAG. Thus, this
 * implementation only stores the first cycle encountered, which is sufficient to prove that the
 * given digraph is not a DAG.
 */
public class DirectedCycle {

  private boolean[] markedVertices;
  private int[] edgeTo;
  private Stack<Integer> cycle;
  private boolean[] onStack;

  /**
   * Construct an instance of DirectedCycle and compute whether the directed graph contains any
   * cycles.
   *
   * @param graph The directed graph.
   */
  public DirectedCycle(Digraph graph) {
    markedVertices = new boolean[graph.getNumVertices()];
    edgeTo = new int[graph.getNumVertices()];
    onStack = new boolean[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex);
      }
    }
  }

  /**
   * Conducted a depth first search of directed paths. Maintains an array of vertices for which the
   * recursive search has not yet completed. If the vertex being searched has a path to a vertex
   * which has yet to complete its recursive search, a cycle exists. This cycle is stored for later
   * retrieval.
   *
   * @param graph The directed graph.
   * @param head The vertex to search from.
   */
  private void depthFirstSearch(Digraph graph, int head) {
    markedVertices[head] = true;
    onStack[head] = true;

    for (int tail : graph.adjacentTo(head)) {
      if (this.hasCycle()) {
        return;
      } else if (!markedVertices[tail]) {
        edgeTo[tail] = head;
        depthFirstSearch(graph, tail);
      } else if (onStack[tail]) {
        cycle = new Stack<>();
        for (int vertex = head; vertex != tail; vertex = edgeTo[vertex]) {
          cycle.push(vertex);
        }
        cycle.push(tail);
        cycle.push(head);
      }
    }

    onStack[head] = false;
  }

  /**
   * Returns true is a cycle exists; false, otherwise.
   *
   * @return True if a cycle exists; false, otherwise.
   */
  private boolean hasCycle() {
    return cycle != null;
  }

  /**
   * Gets the first cycle found, if one exists.
   *
   * @return A cycle, if one exists.
   */
  public Optional<Iterable<Integer>> getCycle() {
    if (hasCycle()) {
      return Optional.of(cycle);
    }

    return Optional.empty();
  }
}
