package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Detects cycles in the given edge-weighted directed graph. It is possible that an exponential
 * number of cycles exist. However, to prove that an edge-weighted directed graph is a Directed
 * Acyclic Graph (DAG), it is only necessary that no cycles exist. That is, if there is one or more
 * cycles, the graph is not a DAG. Thus, this implementation only stores the first cycle
 * encountered, which is sufficient to prove that the given digraph is not a DAG.
 */
public class EdgeWeightedDirectedCycle {

  private boolean[] markedVertices;
  private DirectedEdge[] edgeTo;
  private Stack<DirectedEdge> cycle;
  private boolean[] onStack;

  /**
   * Construct an instance of DirectedCycle and compute whether the edge-weighted directed graph
   * contains any cycles.
   *
   * @param graph The edge-weighted directed graph.
   */
  public EdgeWeightedDirectedCycle(EdgeWeightedDigraph graph) {
    markedVertices = new boolean[graph.getNumVertices()];
    edgeTo = new DirectedEdge[graph.getNumVertices()];
    onStack = new boolean[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex);
      }
    }
  }

  /**
   * Conducted a depth first search of edge-weighted directed paths. Maintains an array of vertices
   * for which the recursive search has not yet completed. If the vertex being searched has a path
   * to a vertex which has yet to complete its recursive search, a cycle exists. This cycle is
   * stored for later retrieval.
   *
   * @param graph The directed graph.
   * @param head The vertex to search from.
   */
  private void depthFirstSearch(EdgeWeightedDigraph graph, int head) {
    markedVertices[head] = true;
    onStack[head] = true;

    for (DirectedEdge tail : graph.adjacentTo(head)) {
      if (this.hasCycle()) {
        return;
      } else if (!markedVertices[tail.to()]) {
        edgeTo[tail.to()] = tail;
        depthFirstSearch(graph, tail.to());
      } else if (onStack[tail.to()]) {
        cycle = new Stack<>();
        DirectedEdge edge = tail;
        while (edge.from() != tail.to()) {
          cycle.push(edge);
          edge = edgeTo[edge.from()];
        }
        cycle.push(edge);
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
  public Optional<Iterable<DirectedEdge>> getCycle() {
    if (hasCycle()) {
      return Optional.of(cycle);
    }

    return Optional.empty();
  }
}
