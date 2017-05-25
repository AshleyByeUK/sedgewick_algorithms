package uk.ashleybye.sedgewick.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import uk.ashleybye.sedgewick.sort.UnionFind;

/**
 * <p>Implements Kruskal's algorithm for determining the minimum spanning tree of a {@code graph}.
 * The algorithm processes edges in ascending order of weight, colouring black all edges that do not
 * form a cycle with edges previously coloured black. There are V - 1 edges in a minimum spanning
 * tree with V vertices, so the algorithm continues until V - 1 edges have been added to the minimum
 * spanning tree.</p>
 * <p>Slower than {@code PrimMinimumSpanningTree} at 1m18 vice 1m16 to process an
 * edge weighted graph with 1,000,00 vertices. Similar time to {@Code PrimMinimumSpanningTreeLazy},
 * which took 1m08.</p>
 */
public class KruskalMinimumSpanningTree {

  /**
   * The minimum spanning tree.
   */
  private Deque<Edge> minimumSpanningTree;

  /**
   * The weight of the minimum spanning tree;
   */
  private double weight;

  /**
   * Constructs a new instance of {@code KruskalMinimumSpanningTree} with the specified {@code
   * graph} and computes the minimum spanning tree using an implementation of Kruskal's algorithm.
   * Assumes the {@code graph} is connected.
   *
   * @param graph The graph.
   */
  public KruskalMinimumSpanningTree(EdgeWeightedGraph graph) {
    minimumSpanningTree = new ArrayDeque<>();
    UnionFind unionFind = new UnionFind(graph.getNumVertices());
    PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.getNumEdges());

    // Can't use Iterable<Edge> in PriorityQueue() constructor or addAll(); add edges individually.
    for (Edge edge : graph.getEdges()) {
      priorityQueue.add(edge);
    }

    while (!priorityQueue.isEmpty() && minimumSpanningTree.size() < graph.getNumVertices() - 1) {
      // Get the minimum weight edge (and its associated vertices) on the priority queue.
      Edge edge = priorityQueue.poll();
      int u = edge.getEitherVertex();
      int v = edge.getOtherVertex(u);

      // Ignore ineligible edges.
      if (unionFind.connected(u, v)) {
        continue;
      }

      // Merge components and add edge to the minimum spanning tree.
      unionFind.union(u, v);
      minimumSpanningTree.offer(edge);
      weight += edge.getWeight();
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
    return weight;
  }
}
