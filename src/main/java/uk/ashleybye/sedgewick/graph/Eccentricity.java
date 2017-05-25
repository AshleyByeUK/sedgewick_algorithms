package uk.ashleybye.sedgewick.graph;

import uk.ashleybye.sedgewick.collections.Queue;

/**
 * Algorithm to calculate eccentricity, based on BreadthFirstPaths algorithm in Algorithms, 4th
 * Edition, by Robert Sedgewick and Kevin Wayne. For each vertex of the graph, the algorithm
 * conducts a breadth first search for all connected vertices. The maximal shortest path from each
 * vertex to any other vertex, the eccentricity of that vertex, is recorded. Also checks whether the
 * eccentricity for the vertex is less than the current radius (smallest eccentricity in the graph)
 * or longer than the current diameter (largest eccentricity in the graph), and updates them if
 * either is true.
 *
 * Not studied, but quick assessment is that growth factor: ~ V^2.
 */
public class Eccentricity {

  /**
   * Vertex-indexed array of eccentricities for the graph.
   */
  private int[] eccentricities;

  /**
   * Graph radius.
   */
  private int radius;

  /**
   * Graph diameter.
   */
  private int diameter;

  /**
   * Construct a new instance of {@code Eccentricity} and compute the eccentricity of each vertex in
   * the {@code graph}. Calculate {@code radius} and {@code diameter} based on the eccentricity for
   * each vertex.
   *
   * @param graph the graph
   */
  public Eccentricity(Graph graph) {
    eccentricities = new int[graph.getNumVertices()];
    radius = Integer.MAX_VALUE;
    diameter = Integer.MIN_VALUE;

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      eccentricities[vertex] = breadthFirstSearch(graph, vertex);

      if (eccentricities[vertex] < radius) {
        radius = eccentricities[vertex];
      }
      if (eccentricities[vertex] > diameter) {
        diameter = eccentricities[vertex];
      }
    }
  }

  /**
   * Use breadth first search to compute the eccentricity of the {@code sourceVertex} for the
   * specified {@code graph}.
   *
   * @param graph the graph to conduct the breadth first search on
   * @param sourceVertex the vertex from which to conduct the search
   */
  private int breadthFirstSearch(Graph graph, int sourceVertex) {
    /*
     * Breadth first search queues the source vertex, then iteratively dequeues the vertex and
     * enqueues its adjacent vertices, until no unvisited connected vertices remain. Each vertex is
     * marked as visited the first time it is encountered, and a distance to the source vertex
     * recorded.
     */
    Queue<Integer> queue = new Queue<>();
    boolean[] isVisisted = new boolean[graph.getNumVertices()];
    int[] distanceToSource = new int[graph.getNumVertices()];

    isVisisted[sourceVertex] = true;
    distanceToSource[sourceVertex] = 0;
    queue.enqueue(sourceVertex);

    int eccentricity = 0;
    while (!queue.isEmpty()) {
      int thisVertex = queue.dequeue();
      for (int adjacentVertex : graph.adjacentTo(thisVertex)) {
        if (!isVisisted[adjacentVertex]) {
          distanceToSource[adjacentVertex] = distanceToSource[thisVertex] + 1;
          isVisisted[adjacentVertex] = true;
          queue.enqueue(adjacentVertex);

          if (distanceToSource[adjacentVertex] > eccentricity) {
            eccentricity = distanceToSource[adjacentVertex];
          }
        }
      }
    }

    return eccentricity;
  }

  /**
   * Get the eccentricities for each vertex in this graph.
   *
   * @return the eccentricities for each vertex
   */
  public int[] getEccentricities() {
    return eccentricities;
  }

  /**
   * Gets the radius of this graph.
   *
   * @return the radius
   */
  public int getRadius() {
    return radius;
  }

  /**
   * Gets the diameter of this graph.
   *
   * @return the diameter
   */
  public int getDiameter() {
    return diameter;
  }
}
