package uk.ashleybye.sedgewick.graph.apps;

import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.BellmanFordShortestPath;
import uk.ashleybye.sedgewick.graph.DirectedEdge;
import uk.ashleybye.sedgewick.graph.EdgeWeightedDigraph;

/**
 * Example to show how use of negative cycles can be used in arbitrage applications. See page 679
 * for explanation.
 *
 * Usage: java Arbitrage < rates.txt
 */
public class Arbitrage {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numVertices = scanner.nextInt();
    String[] name = new String[numVertices];
    EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numVertices);

    for (int u = 0; u < graph.getNumVertices(); u++) {
      name[u] = scanner.next();
      for (int v = 0; v < numVertices; v++) {
        double rate = scanner.nextDouble();
        graph.addEdge(new DirectedEdge(u, v, -Math.log(rate)));
      }
    }

    BellmanFordShortestPath shortestPathTree = new BellmanFordShortestPath(graph, 0);
    if (shortestPathTree.getNegativeCycle().isPresent()) {
      double stake = 1000.0;
      for (DirectedEdge edge : shortestPathTree.getNegativeCycle().get()) {
        System.out.printf("%10.5f %s ", stake, name[edge.from()]);
        stake *= Math.exp(-edge.getWeight());
        System.out.printf("%10.5f %s\n", stake, name[edge.to()]);
      }
    } else {
      System.out.println("No arbitrage opportunity.");
    }
  }
}
