package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Uses Depth First Search to visit all vertices connected to a given source vertex. The end vertex
 * of each edge (u, v) traversed for the first time is recorded so that the path from the source
 * vertex to any other given vertex can be determined.
 */
public class DepthFirstDirectedPaths {

  private boolean[] markedVertices;
  private int[] edgeTo;
  private final int sourceVertex;

  /**
   * Construct an instance of DepthFirstDirectedPaths to compute the paths from the given source
   * vertex in the provided digraph.
   *
   * @param digraph The digraph to search for paths.
   * @param sourceVertex The vertex to search for paths from.
   */
  public DepthFirstDirectedPaths(Digraph digraph, int sourceVertex) {
    markedVertices = new boolean[digraph.getNumVertices()];
    edgeTo = new int[digraph.getNumVertices()];
    this.sourceVertex = sourceVertex;
    directedDepthFirstSearch(digraph, sourceVertex);
  }

  /**
   * Searches the given digraph to find paths from the given source vertex. The end vertex of each
   * (u, v) edge is recorded in {@code edgeTo}.
   *
   * @param digraph The digraph to search for paths.
   * @param vertex The vertex to search for paths from.
   */
  private void directedDepthFirstSearch(Digraph digraph, int vertex) {
    markedVertices[vertex] = true;
    for (int adjacentVertex : digraph.adjacentTo(vertex)) {
      if (!markedVertices[adjacentVertex]) {
        edgeTo[adjacentVertex] = vertex;
        directedDepthFirstSearch(digraph, adjacentVertex);
      }
    }
  }

  /**
   * Returns true if there is a path to the specified vertex; false, otherwise.
   *
   * @param vertex The vertex to query.
   *
   * @return True if there is a path to the given vertex; false, otherwise.
   */
  private boolean hasPathTo(int vertex) {
    return markedVertices[vertex];
  }

  /**
   * Returns the path from this instances source vertex to the specified vertex.
   *
   * @param vertex The vertex to retrieve the path to.
   *
   * @return The paths from the source vertex.
   */
  public Optional<Iterable<Integer>> pathTo(int vertex) {
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
