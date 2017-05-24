package uk.ashleybye.sedgewick.graph;

/**
 * Class represents an edge, which shall connect two vertices, u and v, incident to it.
 */
public class Edge implements Comparable<Edge> {

  /**
   * A vertex, u.
   */
  private final int u;

  /**
   * A vertex, v.
   */
  private final int v;

  /**
   * Weight of the edge.
   */
  private final double weight;

  /**
   * Construct a new instance of Edge, connecting two vertices, u and v, with a given weight.
   *
   * @param u A vertex.
   * @param v The other vertex.
   * @param weight Weight of the edge.
   */
  public Edge(int u, int v, double weight) {
    this.u = u;
    this.v = v;
    this.weight = weight;
  }

  /**
   * Get the weight of the edge.
   *
   * @return The weight of the edge.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Get either of the vertices incident to this edge. Arbitrarily, return u.
   *
   * @return The vertex.
   */
  public int getEitherVertex() {
    return u;
  }

  /**
   * Get the other vertex incident to this edge.
   *
   * @param vertex The vertex that this method does not return.
   *
   * @return The other vertex.
   */
  public int getOtherVertex(int vertex) {
    if (vertex == u) {
      return v;
    } else if (vertex == v) {
      return u;
    } else {
      throw new RuntimeException("Inconsistent edge");
    }
  }

  /**
   * Compare this edge to the specified edge. Returns the natural ordering based on edge weight.
   *
   * @param other Edge to compare this edge to.
   *
   * @return -1 if this edge has less weight than the other edge, 0 if the two edges are equal, and
   * 1 if this edge has greater weight than the other edge.
   */
  @Override
  public int compareTo(Edge other) {
    if (this.getWeight() < other.getWeight()) {
      return -1;
    } else if (this.getWeight() > other.getWeight()) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * String representation of this edge.
   *
   * @return String representing this edge.
   */
  @Override
  public String toString() {
    return String.format("%d-%d %.2f", u, v, weight);
  }
}
