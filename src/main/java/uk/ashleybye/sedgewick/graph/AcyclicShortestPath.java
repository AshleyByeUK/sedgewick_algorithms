package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Stack;

public class AcyclicShortestPath {

  private DirectedEdge[] edgeTo;
  private double[] distanceTo;

  public AcyclicShortestPath(EdgeWeightedDigraph graph, int sourceVertex) {
    edgeTo = new DirectedEdge[graph.getNumVertices()];
    distanceTo = new double[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      distanceTo[vertex] = Double.POSITIVE_INFINITY;
    }
    distanceTo[sourceVertex] = 0.0;

    TopologicalSort topologicalSort = new TopologicalSort(graph);

    for (int vertex : topologicalSort.getTopologicalOrder()) {
      relax(graph, vertex);
    }
  }

  /**
   * Relaxes the edges of the specified {@code vertex} in the {@code graph}.
   *
   * @param graph the graph
   * @param vertex the vertex
   */
  private void relax(EdgeWeightedDigraph graph, int vertex) {
    for (DirectedEdge edge : graph.adjacentTo(vertex)) {
      // Assign to variables for clarity:
      int u = edge.from();
      int v = edge.to();
      if (distanceTo[v] > distanceTo[u] + edge.getWeight()) {
        distanceTo[v] = distanceTo[u] + edge.getWeight();
        edgeTo[v] = edge;
      }
    }
  }

  /**
   * Get the distance from the source vertex to the specified {@code vertex}.
   *
   * @param vertex the vertex
   *
   * @return the distance
   */
  public double getDistanceTo(int vertex) {
    return distanceTo[vertex];
  }

  /**
   * Optional path from the source vertex to the specified {@code vertex}. If no path exists, the
   * contained value will not be present.
   *
   * @param vertex the vertex
   *
   * @return the optional shortest path
   */
  public Optional<Iterable<DirectedEdge>> shortestPathTo(int vertex) {
    if (!hasPathTo(vertex)) {
      return Optional.empty();
    }

    Stack<DirectedEdge> path = new Stack<>();
    for (DirectedEdge edge = edgeTo[vertex]; edge != null; edge = edgeTo[edge.from()]) {
      path.push(edge);
    }

    return Optional.of(path);
  }

  /**
   * Returns true if there is a path from the source vertex to the specified {@code vertex}; false,
   * otherwise.
   *
   * @param vertex the vertex
   *
   * @return true if a path exists; false, otherwise
   */
  private boolean hasPathTo(int vertex) {
    return distanceTo[vertex] < Double.POSITIVE_INFINITY;
  }
}
