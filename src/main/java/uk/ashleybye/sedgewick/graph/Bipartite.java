package uk.ashleybye.sedgewick.graph;

/**
 * Checks whether a graph is bipartite; that is, whether it is two-colourable. A graph is bipartite
 * if vertices can be assigned one of two colours in such a way that no edge connects vertices of
 * the same colour.
 */
public class Bipartite {

  private boolean[] markedVertices;
  private boolean[] vertexColour;
  private boolean isBipartite = true;

  /**
   * Construct and instance of Bipartite and pre-compute whether or not the specified graph is
   * bipartite.
   *
   * @param graph The graph.
   */
  public Bipartite(Graph graph) {
    markedVertices = new boolean[graph.getNumVertices()];
    vertexColour = new boolean[graph.getNumVertices()];

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      if (!markedVertices[vertex]) {
        depthFirstSearch(graph, vertex);
      }
    }
  }

  /**
   * Performs a depth first traversal of the graph, visiting each adjacent vertex and assigning it
   * the opposite colour of the previous vertex. If an adjacent vertex has already been marked as
   * visited and has the same colour as the current vertex, then the graph is not bipartite.
   *
   * @param graph The graph.
   * @param vertex The vertex to visit.
   */
  private void depthFirstSearch(Graph graph, int vertex) {
    markedVertices[vertex] = true;
    for (int adjacentVertex : graph.adjacentTo(vertex)) {
      if (!markedVertices[adjacentVertex]) {
        vertexColour[adjacentVertex] = !vertexColour[vertex];
        depthFirstSearch(graph, adjacentVertex);
      } else if (vertexColour[adjacentVertex] == vertexColour[vertex]) {
        isBipartite = false;
      }
    }
  }

  /**
   * Returns true if the graph is bipartite; false, otherwise.
   *
   * @return True is the graph is bipartite; false, otherwise.
   */
  public boolean isBipartite() {
    return isBipartite;
  }
}
