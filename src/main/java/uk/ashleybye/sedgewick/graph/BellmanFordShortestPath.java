package uk.ashleybye.sedgewick.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Implementation of the Bellman-Ford algorithm for determining shortest paths. Uses the presence of
 * a negative loop in the edge-weighted directed graph as a terminal condition.
 */
public class BellmanFordShortestPath {

  /**
   * Vertex-indexed array containing length of path from source to vertex at index.
   */
  private double[] distanceTo;

  /**
   * Vertex-indexed array containing the last edge on the path from source to vertex at index.
   */
  private DirectedEdge[] edgeTo;

  /**
   * Vertex-indexed array indicating whether the vertex at index is on the queue for relaxation.
   */
  private boolean[] onQueue;

  /**
   * Queue containing index of vertices to be relaxed.
   */
  private Deque<Integer> queue;

  /**
   * The number of method calls made to {@code relax()}.
   */
  private int cost;

  /**
   * If there are any negative cycles in {@code edgeTo[]}, store one of them.
   */
  private Iterable<DirectedEdge> negativeCycle;

  /**
   * Constructs a new instance of {@code BellmanFordShortestPath} for the specified {@code graph}
   * and {@code sourceVertex}. Puts the {@code sourceVertex} on a queue, then enters a loop which
   * takes a vertex off of the queue and relaxes the edges leaving from it. The algorithm must
   * terminate after V passes of the loop. Rather than keep a count of passes, this implementation
   * checks for the presence of negative cycles and terminates if it finds one (avoids an infinite
   * loop).
   *
   * @param graph the graph
   * @param sourceVertex the source vertex
   */
  public BellmanFordShortestPath(EdgeWeightedDigraph graph, int sourceVertex) {
    distanceTo = new double[graph.getNumVertices()];
    edgeTo = new DirectedEdge[graph.getNumVertices()];
    onQueue = new boolean[graph.getNumVertices()];
    queue = new ArrayDeque<>();

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      distanceTo[vertex] = Double.POSITIVE_INFINITY;
    }

    distanceTo[sourceVertex] = 0.0;
    queue.offer(sourceVertex);
    onQueue[sourceVertex] = true;

    while (!queue.isEmpty() && !this.hasNegativeCycle()) {
      int vertex = queue.remove();
      onQueue[vertex] = false;
      relax(graph, vertex);
    }
  }

  /**
   * Relaxes the specified {@code vertx} for the given {@code graph}. If the distance from the
   * source of any vertices adjacent to the {@code vertx} is greater than the distance to the {@code
   * vertex} plus the weight of the edge from the {@code vertex} to the adjacent vertex, then this
   * edge provides a shorter path from the source to the adjacent vertex and the edge pointing to it
   * and the corresponding distance to it are updated accordingly. This is a successful relaxation.
   *
   * A successful relaxation, as shown, results in a change of the distance to the adjacent vertex.
   * This could impact the distance to the vertices incident to the edges leaving from the adjacent
   * vertex, and so the adjacent vertex is added to the {@code queue} so that its edges can be
   * relaxed in the next round. To ensure that no vertex appears in the {@code queue} more than once
   * at a time, it is makred as being {@code onQueue}.
   *
   * Periodically call {@code findNegativeCycle} to check for negative cycles. This is done every
   * Vth pass, because the directed graph will have a negative cycle if, and only if, the {@code
   * queue} is non-empty after the Vth pass (see p677).
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

        if (!onQueue[adjacentVertex]) {
          queue.offer(adjacentVertex);
          onQueue[adjacentVertex] = true;
        }
      }

      if (cost++ % graph.getNumVertices() == 0) {
        findNegativeCycle();
      }
    }
  }

  /**
   * Creates a shortest path tree and checks to see whether a negative cycle that is reachable from
   * the source vertex has formed. This negative cycle must, as per p677, exist in the sub-graph
   * contained in {@code edgeTo[]}.
   */
  private void findNegativeCycle() {
    int numVertices = edgeTo.length;
    EdgeWeightedDigraph shortestPathTree = new EdgeWeightedDigraph(numVertices);

    for (int vertex = 0; vertex < numVertices; vertex++) {
      if (edgeTo[vertex] != null) {
        shortestPathTree.addEdge(edgeTo[vertex]);
      }
    }

    EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(shortestPathTree);
    negativeCycle = cycleFinder.getCycle().orElse(null);
  }

  /**
   * Returns true if the graph has a negative cycle; false, otherwise.
   *
   * @return true if the graph has a negative cycle; false, otherwise
   */
  private boolean hasNegativeCycle() {
    return negativeCycle != null;
  }

  /**
   * Get a negative cycle, if one exists.
   *
   * @return a negative cycle or empty
   */
  public Optional<Iterable<DirectedEdge>> getNegativeCycle() {
    return Optional.ofNullable(negativeCycle);
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
    Stack<DirectedEdge> path = new Stack<>();
    for (DirectedEdge edge = edgeTo[vertex]; edge != null; edge = edgeTo[edge.from()]) {
      path.push(edge);
    }

    return Optional.ofNullable(path);
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
