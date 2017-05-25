package uk.ashleybye.sedgewick.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import uk.ashleybye.sedgewick.collections.IndexedPriorityQueue;

/**
 * <p>Implementation of Prim's algorithm to compute the minimum spanning tree of an edge weighted
 * graph. This implementation uses an eager version of the algorithm, based on Sedgewick,
 * Algorithms, p622.</p>
 * <p>This is slightly faster than {@code PrimMinimumSpanningTreeLazy}, taking
 * 1m08s vice 1m16s to process an edge weighted graph of 1,000,000 entries.</p>
 */
public class PrimMinimumSpanningTree {

  private Edge[] edgeTo;
  private double[] distanceTo;
  private boolean[] markedVertices;
  private IndexedPriorityQueue<Double> priorityQueue;

  /**
   * Constructs a new instance of PrimMinimumSpanningTree with the given {@code graph} and computes
   * the minimum spanning tree. Assumes that the {@code graph} is connected.
   *
   * @param graph The graph for which to compute the minimum spanning tree.
   */
  public PrimMinimumSpanningTree(EdgeWeightedGraph graph) {
    edgeTo = new Edge[graph.getNumVertices()];
    distanceTo = new double[graph.getNumVertices()];
    markedVertices = new boolean[graph.getNumVertices()];
    priorityQueue = new IndexedPriorityQueue<>(graph.getNumVertices());

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      distanceTo[vertex] = Double.POSITIVE_INFINITY;
    }

    distanceTo[0] = 0.0;
    priorityQueue.offer(0, 0.0);

    while (!priorityQueue.isEmpty()) {
      visit(graph, priorityQueue.pollMinimum());
    }
  }

  /**
   * Visits the specified {@code vertex} in the {@code graph} and marks it as visited. It then
   * iterates over each of the edges for the given {@code vertex} and tests whether the other vertex
   * incident to the edge has been visited previously. For each previously unvisited vertex adjacent
   * to the given {@code vertex}, it checks if the weight of the edge vertex-otherVertex is less
   * than the current best known weight to the other vertex (the distance to the other vertex); if
   * it is, the edge vertex-otherVertex is the new best distance. If there is no previously known
   * distance, the edge is added to the minimum spanning tree; otherwise, the distance to
   * otherVertex in the minimum spanning tree is updated accordingly.
   *
   * @param graph The graph.
   * @param vertex The vertex to visit.
   */
  private void visit(EdgeWeightedGraph graph, int vertex) {
    markedVertices[vertex] = true;
    for (Edge edge : graph.adjacentTo(vertex)) {
      int otherVertex = edge.getOtherVertex(vertex);
      if (markedVertices[otherVertex]) {
        // Edge vertex-otherVertex is ineligible.
        continue;
      }

      if (edge.getWeight() < distanceTo[otherVertex]) {
        // This edge is the new best connection (the one with the least weight) from the minimum
        // spanning tree to the otherVertex.
        edgeTo[otherVertex] = edge;
        distanceTo[otherVertex] = edge.getWeight();
        if (priorityQueue.contains(otherVertex)) {
          priorityQueue.changeKey(otherVertex, distanceTo[otherVertex]);
        } else {
          priorityQueue.offer(otherVertex, distanceTo[otherVertex]);
        }
      }
    }
  }

  /**
   * Get the minimum spanning tree.
   *
   * @return The minimum spanning tree.
   */
  public Iterable<Edge> getEdges() {
    List<Edge> minimumSpanningTree = new ArrayList<>();

    // First entry in edgeTo is null, don't include it.
    for (int edge = 1; edge < edgeTo.length; edge++) {
      minimumSpanningTree.add(edgeTo[edge]);
    }

    return Collections.unmodifiableList(minimumSpanningTree);
  }

  /**
   * Get the total weight of the minimum spanning tree.
   *
   * @return The weight.
   */
  public double getWeight() {
    double sum = 0.0;
    for (double weight : distanceTo) {
      sum += weight;
    }

    return sum;
  }
}
