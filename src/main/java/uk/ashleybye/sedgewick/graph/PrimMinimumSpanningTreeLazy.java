package uk.ashleybye.sedgewick.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Implements a lazy (evaluates whether an edge is eligible only when it has priority in the
 * priority queue) version of Prim's algorithm to find the Minimum Spanning Tree (MST) of a
 * connected graph. This is a greedy algorithm, which starts with vertex of index zero and adds it
 * to the MST, and all of its edges to the priority queue. The vertex incident to the other end of
 * the edge with the least weight in the priority queue is then visited, added to the MST and it's
 * incident edges to the priority queue. If the edge with least priority is ineligible (that is,
 * both of its incident vertices have already been visited, it is ignored and priority given to the
 * edge with the next least weight). This repeats until the MST has been constructed. By definition,
 * the MST has V vertices and V - 1 edges.
 *
 * Effectively starts with a graph with all edges coloured grey. As edges are added to the minimum
 * spanning tree, they are coloured black. This creates two cut sets, one black, the other grey.
 * Crossing edges connect vertices in the black set to vertices in the grey set and are eligible for
 * inclusion in the minimum spanning tree. Edges become ineligible when the addition of another edge
 * to the black set results in the previously eligible edge in the black connecting to a vertex in
 * the grey set, but which is now included in the black set. That is, when an edge no longer crosses
 * from the black set to the grey set due to the addition of a new vertex to the black set, that
 * edge becomes ineligible for inclusion.
 */
public class PrimMinimumSpanningTreeLazy {

  /**
   * Vertex-indexed array of vertices that have been visited.
   */
  private boolean[] markedVertices;

  /**
   * The minimum spanning tree.
   */
  private Deque<Edge> minimumSpanningTree;

  /**
   * Crossing (and ineligible) edges.
   */
  private PriorityQueue<Edge> priorityQueue;

  /**
   * Constructs an instance of PrimMinimumSpanningTreeLazy and compute the minimum spanning tree for
   * the given graph.
   *
   * @param graph The graph.
   */
  public PrimMinimumSpanningTreeLazy(EdgeWeightedGraph graph) {
    markedVertices = new boolean[graph.getNumVertices()];
    minimumSpanningTree = new ArrayDeque<>();
    priorityQueue = new PriorityQueue<>();

    // Assume graph is connected.
    visit(graph, 0);

    while (!priorityQueue.isEmpty()) {
      // Get the lowest priority edge from the priority queue.
      Edge edge = priorityQueue.poll();

      int u = edge.getEitherVertex();
      int v = edge.getOtherVertex(u);

      // Skip edge if it is ineligible for inclusion in the minimum spanning tree.
      if (markedVertices[u] && markedVertices[v]) {
        continue;
      }

      // Add the eligible edge to the minimum spanning tree.
      minimumSpanningTree.offer(edge);

      // Visit either u or v (depending on which vertex is returned by edge.getOtherVertex()) and
      // add it to the the tree.
      if (!markedVertices[u]) {
        visit(graph, u);
      }
      if (!markedVertices[v]) {
        visit(graph, v);
      }
    }
  }

  /**
   * Marks the specified vertex in the graph as visited and adds all edges from the vertex to
   * un-visited incident vertices.
   *
   * @param graph The graph.
   * @param vertex The vertex to visit.
   */
  private void visit(EdgeWeightedGraph graph, int vertex) {
    markedVertices[vertex] = true;
    for (Edge edge : graph.adjacentTo(vertex)) {
      if (!markedVertices[edge.getOtherVertex(vertex)]) {
        priorityQueue.add(edge);
      }
    }
  }

  /**
   * Get the minimum spanning tree.
   *
   * @return The minimum spanning tree.
   */
  public Iterable<Edge> getEdges() {
    return minimumSpanningTree;
  }

  /**
   * Get the total weight of the minimum spanning tree.
   *
   * @return The weight.
   */
  public double getWeight() {
    // Lazy computation iterates through MST and returns sum of weights. Should develop eager
    // version that calculates weight as edges are added to the MST.
    double sum = 0.0;
    for (Edge edge : minimumSpanningTree) {
      sum += edge.getWeight();
    }

    return sum;
  }
}
