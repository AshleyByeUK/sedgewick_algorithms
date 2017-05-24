package uk.ashleybye.sedgewick.graph;

/**
 * Calculates whether there is a directed path from a given vertex u to another given vertex v. This
 * computation uses the transitive closure of a digraph to determine whether v is reachable from u.
 * Transitive closure of a digraph G is another digraph with the same set of vertices, but with an
 * edge from u to v in the transitive closure if, and only if, v is reachable from u in G.
 *
 * To explain further, consider a sub-graph of a digraph G with the following vertices and directed
 * edges between them: 0 -> 5, 5 -> 4, 4 -> 2, 2 -> 3. In the transitive closure of G, the following
 * directed edges from the sub-graph of G will be present: 0 ->0, 0 -> 5, 0 -> 4, 0 -> 2, 0 -> 3. By
 * convention, every vertex is reachable from itself, so the transitive closure will have a
 * self-loop for every v in V.
 *
 * Even for relatively small and sparse graphs, the transitive closure is typically dense and can be
 * represented by a matrix of boolean values. However, this means that the constructor used space
 * proportional to V^2 and time proportional to V * (V + E). As such, whilst this solution is
 * suitable for small graphs, it is not recommended for large digraphs that might be encountered in
 * practice.
 *
 * A general solution that supports constant-time transitive closure queries with substantially less
 * than quadratic space is currently (2017) an unsolved research problem.
 */
public class TransitiveClosure {

  /**
   * Matrix containing the paths reachable from each vertex v in G, calculated by conducting a depth
   * first search for each v.
   */
  private DirectedDepthFirstSearch[] transitiveClosure;

  /**
   * Constructs an instance of TransitiveClosure and computes the transitive closure for the given
   * digraph.
   *
   * @param digraph The digraph.
   */
  public TransitiveClosure(Digraph digraph) {
    transitiveClosure = new DirectedDepthFirstSearch[digraph.getNumVertices()];

    for (int vertex = 0; vertex < digraph.getNumVertices(); vertex++) {
      transitiveClosure[vertex] = new DirectedDepthFirstSearch(digraph, vertex);
    }
  }

  /**
   * Returns true if vertex v is reachable from vertex u.
   *
   * @param u From vertex.
   * @param v To vertex.
   *
   * @return True id v is reachable from u; false, otherwise.
   */
  public boolean isReachable(int u, int v) {
    // Is v is marked in the transitive closure for u, a path exists.
    return transitiveClosure[u].isMarked(v);
  }
}
