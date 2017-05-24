package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Queue;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Uses a breadth first search to find the shortest path to all vertices from a given source vertex.
 */
public class BreadthFirstDirectedPaths {

  private boolean[] markedVertices;
  private int[] edgeTo;
  private final int sourceVertex;

  /**
   * Construct an instance of BreadthFirstPaths for the specified digraph and source vertex.
   *
   * @param digraph The digraph to conduct the breadth first search on.
   * @param sourceVertex The vertex from which to conduct the search.
   */
  public BreadthFirstDirectedPaths(Digraph digraph, int sourceVertex) {
    markedVertices = new boolean[digraph.getNumVertices()];
    edgeTo = new int[digraph.getNumVertices()];
    this.sourceVertex = sourceVertex;
    directedBreadthFirstSearch(digraph, sourceVertex);
  }

  /**
   * Compute the breadth first (shortest) paths from the given source vertex to all other vertices
   * in the digraph.
   *
   * @param digraph The digraph to conduct the breadth first search on.
   * @param sourceVertex The vertex from which to conduct the search.
   */
  private void directedBreadthFirstSearch(Digraph digraph, int sourceVertex) {
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
      int head = queue.dequeue();
      for (int tail : digraph.adjacentTo(head)) {
        if (!markedVertices[tail]) {
          edgeTo[tail] = head;
          markedVertices[tail] = true;
          queue.enqueue(tail);
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
