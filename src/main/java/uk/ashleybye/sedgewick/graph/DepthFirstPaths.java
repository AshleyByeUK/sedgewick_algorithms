package uk.ashleybye.sedgewick.graph;

import java.util.Optional;
import uk.ashleybye.sedgewick.collections.Stack;

/**
 * Uses Depth First Search to visit all getNumVertices connected to a given source vertex. The end vertex
 * of each edge (v, w) traversed for the first time is recorded so that the path from the source
 * vertex to any other given vertex can be determined.
 */
public class DepthFirstPaths {

  private boolean[] markedVertices;
  private int[] edgeTo;
  private final int sourceVertex;

  public DepthFirstPaths(Graph graph, int sourceVertex) {
    markedVertices = new boolean[graph.getNumVertices()];
    edgeTo = new int[graph.getNumVertices()];
    this.sourceVertex = sourceVertex;
    depthFirstSearch(graph, sourceVertex);
  }

  private void depthFirstSearch(Graph graph, int vertex) {
    markedVertices[vertex] = true;
    for (int adjacentVertex : graph.adjacentTo(vertex)) {
      if (!markedVertices[adjacentVertex]) {
        edgeTo[adjacentVertex] = vertex;
        depthFirstSearch(graph, adjacentVertex);
      }
    }
  }

  private boolean hasPathTo(int vertex) {
    return markedVertices[vertex];
  }

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
