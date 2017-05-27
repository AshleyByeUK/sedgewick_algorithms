package uk.ashleybye.sedgewick.graph;

/**
 * A {@code FlowEdge} for use in a residual flow {@code FlowNetwork}.
 */
public class FlowEdge {

  /**
   * The head of this {@code FlowEdge}.
   */
  private int head;

  /**
   * The tail of this {@code FlowEdge}.
   */
  private int tail;

  /**
   * The capacity of this {@code FlowEdge}.
   */
  private double capacity;

  /**
   * The current flow of this {@code FlowEdge}.
   */
  private double flow;

  /**
   * Construct a {@code FlowEdge} from {@code head} to {@code tail} with a given {@code capacity}
   * and zero flow.
   *
   * @param head the head
   * @param tail the tail
   * @param capacity the capacity
   */
  public FlowEdge(int head, int tail, double capacity) {
    this.head = head;
    this.tail = tail;
    this.capacity = capacity;
    this.flow = 0.0;
  }

  /**
   * Get the head of this {@code FlowEdge}.
   *
   * @return the head
   */
  public int from() {
    return head;
  }

  /**
   * Get the tail of this {@code FlowEdge}.
   *
   * @return the tail
   */
  public int to() {
    return tail;
  }

  /**
   * Get the vertex of the {@code FlowEdge} that is not represented by the given {@code vertex}.
   *
   * @param vertex the vertex.
   *
   * @return the other vertex
   */
  public int other(int vertex) {
    if (vertex == head) {
      return tail;
    } else if (vertex == tail) {
      return head;
    } else {
      throw new RuntimeException(
          "Inconsistent edge. Vertex " + vertex + " is not incident to edge: " + this);
    }
  }

  /**
   * Get the capacity of this {@code FlowEdge}.
   */
  public double getCapacity() {
    return capacity;
  }

  /**
   * Get the flow of this {@code FlowEdge}.
   *
   * @return the flow
   */
  public double getFlow() {
    return flow;
  }

  /**
   * Get the residual capacity toward the given {@code vertex}.
   *
   * @param vertex the vertex
   *
   * @return the residual capacity
   */
  public double residualCapacityTo(int vertex) {
    /*
     * Because we are working with a residual network, capacity towards the head is the current flow.
     * Conversly, capacity towards the tail is the difference between total capacity and the current
     * flow.
     */
    if (vertex == head) {
      return flow;
    } else if (vertex == tail) {
      return capacity - flow;
    } else {
      throw new RuntimeException(
          "Inconsistent edge. Vertex " + vertex + " is not incident to edge: " + this);
    }
  }

  /**
   * Add flow of {@code delta} in the direction of the given {@code vertex}.
   *
   * @param vertex the vertx
   * @param delta the flow
   */
  public void addResidualFlowTo(int vertex, double delta) {
    /*
     * Because we are working with a residual network, adding flow to the head reduces the available
     * flow. Conversely, adding flow towards the tail increases the available flow.
     */
    if (vertex == head) {
      flow -= delta;
    } else if (vertex == tail) {
      flow += delta;
    } else {
      throw new RuntimeException(
          "Inconsistent edge. Vertex " + vertex + " is not incident to edge: " + this);
    }
  }

  /**
   * Get a string representation of this {@code FlowEdge}.
   *
   * @return string representing this edge
   */
  @Override
  public String toString() {
    return String.format("%d->%d (capacity=%.2f, flow=%.2f)", head, tail, capacity, flow);
  }
}
