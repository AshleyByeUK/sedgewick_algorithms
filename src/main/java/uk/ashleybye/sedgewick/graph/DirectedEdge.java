package uk.ashleybye.sedgewick.graph;

public class DirectedEdge {

  /**
   * Edge source.
   */
  private final int u;

  /**
   * Edge target.
   */
  private final int v;

  /**
   * Edge weight.
   */
  private final double weight;

  /**
   * Construct a new instance of DirectedEdge.
   *
   * @param u index of vertex at head of this directed edge (the vertex it points from).
   * @param v index of vertex at tail of this direct edge (the vertex it connects to).
   * @param weight the weight of this vertex.
   */
  public DirectedEdge(int u, int v, double weight) {
    this.u = u;
    this.v = v;
    this.weight = weight;
  }

  /**
   * Get the index of the vertex at the head of this directed edge,
   *
   * @return index of vertex at the head of this directed edge.
   */
  public int from() {
    return u;
  }

  /**
   * Get the index of the vertex at the tail of this directed edge.
   *
   * @return index of vertex at tail of this directed edge.
   */
  public int to() {
    return v;
  }

  /**
   * Get the weight of this directed edge.
   *
   * @return the weight of thi directed edge.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Gets a string representation of this directed edge.
   *
   * @return string representation of this directed edge.
   */
  @Override
  public String toString() {
    return String.format("%d->%d %.2f", u, v, weight);
  }
}
