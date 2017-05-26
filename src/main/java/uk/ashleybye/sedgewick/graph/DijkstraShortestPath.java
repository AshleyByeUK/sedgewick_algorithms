package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.IndexedPriorityQueue;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Implements Dijkstra's algorithm for finding shortest paths in an edge weighted digraph.
 *
 * From Sedgewick, Algorithms, p655.
 */
public class DijkstraShortestPath {

  /**
   * Vertex-indexed array of directed edges, populated and updated during edge relaxation, to
   * contain the lowest weight edge that links the vertex at the index to the source vertex.
   */
  private DirectedEdge[] edgeTo;

  /**
   * Vertex-indexed array representing the total distance from the specified vertex to the source
   * vertex. Initialised to {@code Double.POSITIVE_INFINITY}, represents an unvisited or unreachable
   * () vertex. Updated during edge relaxation.
   */
  private double[] distanceTo;

  /**
   * Indexed priority queue ensures that the vertex with the least distance from the source vertex
   * is processed next during relaxation.
   */
  private IndexedPriorityQueue<Double> priorityQueue;

  /**
   * Construct a new isntance of DijkstraShortestPath with the specified {@code graph} and compute
   * the shortest paths tree from the the {@code sourceVertex}.
   *
   * @param graph the graph
   * @param sourceVertex the source vertex
   */
  public DijkstraShortestPath(EdgeWeightedDigraph graph, int sourceVertex) {
    edgeTo = new DirectedEdge[graph.getNumVertices()];
    distanceTo = new double[graph.getNumVertices()];
    priorityQueue = new IndexedPriorityQueue<>(graph.getNumVertices());

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      distanceTo[vertex] = Double.POSITIVE_INFINITY;
    }
    distanceTo[sourceVertex] = 0.0;

    priorityQueue.offer(sourceVertex, 0.0);
    while (!priorityQueue.isEmpty()) {
      relax(graph, priorityQueue.pollMinimum());
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
      int adjacentVertex = edge.to();
      if (distanceTo[adjacentVertex] > distanceTo[vertex] + edge.getWeight()) {
        distanceTo[adjacentVertex] = distanceTo[vertex] + edge.getWeight();
        edgeTo[adjacentVertex] = edge;
        if (priorityQueue.contains(adjacentVertex)) {
          priorityQueue.changeKey(adjacentVertex, distanceTo[adjacentVertex]);
        } else {
          priorityQueue.offer(adjacentVertex, distanceTo[adjacentVertex]);
        }
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
