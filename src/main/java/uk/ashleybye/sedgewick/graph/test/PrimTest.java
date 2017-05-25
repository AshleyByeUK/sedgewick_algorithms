package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.Edge;
import uk.ashleybye.sedgewick.graph.EdgeWeightedGraph;
import uk.ashleybye.sedgewick.graph.PrimMinimumSpanningTree;

public class PrimTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println("Usage: java LazyPrimTest sourceFile");
      System.exit(0);
    }

    EdgeWeightedGraph graph = new EdgeWeightedGraph(args[0]);
    PrimMinimumSpanningTree minimumSpanningTree = new PrimMinimumSpanningTree(graph);

    System.out.println("Minimum Spanning Tree Edges:");
    for (Edge edge : minimumSpanningTree.getEdges()) {
      System.out.println(edge);
    }
    System.out.println("Weight: " + minimumSpanningTree.getWeight());
  }
}
