package uk.ashleybye.sedgewick.graph;

public class DirectedDepthFirstSearch {

  private boolean[] markedVertices;

  /**
   * Construct a new instance of the DirectedDepthFirstSearch class, which searches the digraph to
   * find vertices connected to the source vertex.
   *
   * @param digraph The digraph for this instance to search.
   * @param sourceVertex The source vertex.
   */
  public DirectedDepthFirstSearch(Digraph digraph, int sourceVertex) {
    markedVertices = new boolean[digraph.getNumVertices()];
    depthFirstSearch(digraph, sourceVertex);
  }

  /**
   * Construct a new instance of the DirectedDepthFirstSearch class, which searches the digraph to
   * find vertices connected to each the source vertices.
   *
   * @param digraph The digraph for this instance to search.
   * @param sourceVertices An iterable list of source vertices.
   */
  public DirectedDepthFirstSearch(Digraph digraph, Iterable<Integer> sourceVertices) {
    markedVertices = new boolean[digraph.getNumVertices()];
    for (int sourceVertex : sourceVertices) {
      depthFirstSearch(digraph, sourceVertex);
    }
  }

  /**
   * Search the digraph to find vertices connected to the source vertex, by following paths and
   * marking each vertex encountered.
   *
   * @param digraph The digraph to search.
   * @param head The vertex from which to conduct this search step.
   */
  private void depthFirstSearch(Digraph digraph, int head) {
    markedVertices[head] = true;
    for (int tail : digraph.adjacentTo(head)) {
      if (!markedVertices[tail]) {
        depthFirstSearch(digraph, tail);
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
}
