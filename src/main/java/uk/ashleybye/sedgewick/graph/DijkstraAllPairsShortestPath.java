package uk.ashleybye.sedgewick.graph;

import java.util.Optional;

/**
 * Uses {@code DijkstraShortestPath} to compute shortest path between all pairs of vertices in the
 * graph.
 */
public class DijkstraAllPairsShortestPath {

  /**
   * Source vertex-indexed array of shortest paths.
   */
  private DijkstraShortestPath[] allPairs;

  /**
   * Construct a new instance, computing the shortest paths between every vertex in the {@code
   * graph}.
   */
  public DijkstraAllPairsShortestPath(EdgeWeightedDigraph graph) {
    allPairs = new DijkstraShortestPath[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      allPairs[vertex] = new DijkstraShortestPath(graph, vertex);
    }
  }

  /**
   * Get the path between the {@code source} and {@code target} vertices.
   *
   * @param source the source vertex
   * @param target the target vertex
   *
   * @return the path, if it exists
   */
  public Optional<Iterable<DirectedEdge>> path(int source, int target) {
    return allPairs[source].shortestPathTo(target);
  }

  /**
   * Get the distance between the {@code source} and {@code target} vertices.
   *
   * @param source the source vertex
   * @param target the target vertex
   *
   * @return the distance
   */
  public double distance(int source, int target) {
    return allPairs[source].getDistanceTo(target);
  }
}
