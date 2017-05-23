package uk.ashleybye.sedgewick.graph;

public class DepthFirstSearch {

  private boolean[] markedVertices;
  private int markedVerticesCount;

  /**
   * Construct a new instance of the DepthFirstSearch class, which searches the graph to find
   * vertices connected to the source vertex.
   *
   * @param graph The graph for this instance to search.
   * @param sourceVertex The source vertex.
   */
  public DepthFirstSearch(Graph graph, int sourceVertex) {
    markedVertices = new boolean[graph.getNumVertices()];
    depthFirstSearch(graph, sourceVertex);
  }

  /**
   * Search the graph to find vertices connected to the source vertex, by following paths and
   * marking each vertex encountered.
   *
   * Conducts a depth first search of the graph, beginning at the source node. Visits each vertex
   * twice: first when getting to a vertex that is unmarked, and second when returning from a marked
   * vertex. Thus, a connected graph with only 8 edges will result in 16 edge traversals, two for
   * each edge, i.e. the number of entries in the adjacency lists. It follows that for a
   * disconnected graph, each edge will still result in two traversals, although the total number of
   * traversals will not be equal to the total number of entries in the adjacency lists.
   *
   * @param graph The graph to search.
   * @param vertex the vertex from which to conduct this recursive search step.
   */
  private void depthFirstSearch(Graph graph, int vertex) {
    markedVertices[vertex] = true;
    markedVerticesCount++;
    for (int adjacentVertex : graph.adjacentTo(vertex)) {
      if (!markedVertices[adjacentVertex]) {
        depthFirstSearch(graph, adjacentVertex);
      }
    }
  }

  /**
   * Returns true if the specified vertex was marked during the search; false, otherwise.
   *
   * @param vertex The vertex.
   *
   * @return true if the vertex is marked; false, otherwise.
   */
  public boolean isMarked(int vertex) {
    return markedVertices[vertex];
  }

  /**
   * Returns the number of vertices that are connected to the source vertex specified in the
   * constructor. If, and only if, the count matches the number of vertices in the graph, the graph
   * is connected.
   *
   * @return The number of vertices marked during the search.
   */
  public int count() {
    return markedVerticesCount;
  }
}
