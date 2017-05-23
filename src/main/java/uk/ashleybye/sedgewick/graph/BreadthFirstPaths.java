package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Queue;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Uses a breadth first search to find the shortest path to all vertices from a given source vertex.
 */
public class BreadthFirstPaths {

  private boolean[] markedVertices;
  private int[] edgeTo;
  private final int sourceVertex;

  /**
   * Construct an instance of BreadthFirstPaths for the specified graph and source vertex.
   *
   * @param graph The graph to conduct the breadth first search on.
   * @param sourceVertex The vertex from which to conduct the search.
   */
  public BreadthFirstPaths(Graph graph, int sourceVertex) {
    markedVertices = new boolean[graph.getNumVertices()];
    edgeTo = new int[graph.getNumVertices()];
    this.sourceVertex = sourceVertex;
    breadthFirstSearch(graph, sourceVertex);
  }

  /**
   * Compute the breadth first (shortest) paths from the given source vertex to all other vertices
   * in the graph.
   *
   * @param graph The graph to conduct the breadth first search on.
   * @param sourceVertex The vertex from which to conduct the search.
   */
  private void breadthFirstSearch(Graph graph, int sourceVertex) {
    /*
     * Breadth first search queues the source vertex, then iteratively dequeues the vertex and
     * enqueues its adjacent vertices, until no unvisited connected vertices remain. Each vertex is
     * marked as visited the first time it is encountered, and an edge to the previous vertex
     * recorded.
     */
    Queue<Integer> queue = new Queue<>();
    markedVertices[sourceVertex] = true;
    queue.enqueue(sourceVertex);

    while (!queue.isEmpty()) {
      int nextVertex = queue.dequeue();
      for (int adjacentVertex : graph.adjacentTo(nextVertex)) {
        if (!markedVertices[adjacentVertex]) {
          edgeTo[adjacentVertex] = nextVertex;
          markedVertices[adjacentVertex] = true;
          queue.enqueue(adjacentVertex);
        }
      }
    }
  }

  /**
   * Returns true if there is a path from the source vertex to the specified vertex; false,
   * otherwise.
   *
   * @param vertex The last vertex in the path from the source vertex.
   *
   * @return True if a path exists; false, otherwise.
   */
  private boolean hasPathTo(int vertex) {
    return markedVertices[vertex];
  }

  /**
   * Returns the shortest path from the source vertex to the specified vertex, if the two vertices
   * are connected.
   *
   * @param vertex The last vertex to get the shortest path to.
   *
   * @return Optionally, the shortest path to the specified vertex.
   */
  public Optional<Iterable<Integer>> shortestPathTo(int vertex) {
    if (!hasPathTo(vertex)) {
      return Optional.empty();
    }

    Stack<Integer> path = new Stack<>();
    for (int v = vertex; v != sourceVertex; v = edgeTo[v]) {
      path.push(v);
    }
    path.push(sourceVertex);

    return Optional.of(path);
  }
}
