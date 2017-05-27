package uk.ashleybye.sedgewick.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class FordFulkerson {

  /**
   * Vertex-indexed array representing whether there is an source->vertex path in the residual
   * graph.
   */
  private boolean[] isMarked;

  /**
   * Vertex-indexed array for the last edge on the shortest source->vertex path.
   */
  private FlowEdge[] edgeTo;

  /**
   * The current value of the maximum flow.
   */
  private double value;

  /**
   * Construct an instance of {@code FordFulkerson} and find the maximum flow from {@code source} to
   * {@code sink} in the flow network represented by {@code graph}. {@code source} and {@code sink}
   * represent s and t, respectively, in an st-cut.
   *
   * @param graph the flow network
   * @param source the source vertex
   * @param sink the sink vertex
   */
  public FordFulkerson(FlowNetwork graph, int source, int sink) {
    // While there is an augmenting path, use it.
    while (hasAugmentingPath(graph, source, sink)) {
      // Compute bottleneck capacity.
      double bottleneck = Double.POSITIVE_INFINITY;
      for (int vertex = sink; vertex != source; vertex = edgeTo[vertex].other(vertex)) {
        bottleneck = Math.min(bottleneck, edgeTo[vertex].residualCapacityTo(vertex));
      }

      // Augment flow.
      for (int vertex = sink; vertex != source; vertex = edgeTo[vertex].other(vertex)) {
        edgeTo[vertex].addResidualFlowTo(vertex, bottleneck);
      }

      value += bottleneck;
    }
  }

  /**
   * See Sedgewick, Algorithms, pahe 897.
   *
   * @param graph the graph
   * @param source the source, or s
   * @param sink the sink, or t
   *
   * @return true if augmenting path exists; false, otherwise
   */
  private boolean hasAugmentingPath(FlowNetwork graph, int source, int sink) {
    isMarked = new boolean[graph.getNumVertices()];
    edgeTo = new FlowEdge[graph.getNumVertices()];
    Deque<Integer> queue = new ArrayDeque<>();

    isMarked[source] = true;
    queue.offer(source);

    while (!queue.isEmpty()) {
      int vertex = queue.remove();
      for (FlowEdge edge : graph.getEdgesAdjacentTo(vertex)) {
        int adjacentVertex = edge.other(vertex);
        if (edge.residualCapacityTo(adjacentVertex) > 0 && !isMarked[adjacentVertex]) {
          edgeTo[adjacentVertex] = edge;
          isMarked[adjacentVertex] = true;
          queue.offer(adjacentVertex);
        }
      }
    }

    return isMarked[sink];
  }

  /**
   * Get the maximum flow.
   *
   * @return the maximum flow
   */
  public double getValue() {
    return value;
  }

  /**
   * Returns true if the given {@code vertex} is in the st-cut; false, otherwise.
   *
   * @param vertex the vertex
   *
   * @return true if the vertex is in the cut; false, otherwise
   */
  public boolean isInCut(int vertex) {
    return isMarked[vertex];
  }
}
