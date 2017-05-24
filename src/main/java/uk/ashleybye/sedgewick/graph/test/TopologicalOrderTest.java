package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.SymbolDigraph;
import uk.ashleybye.sedgewick.graph.TopologicalSort;

public class TopologicalOrderTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: java TopologicalOrderTest sourceFile delimiter");
      System.out.println("e.g. java TopologicalOrderTest ./data/jobs.txt \"/\"");
      System.exit(0);
    }

    SymbolDigraph symbolDigraph = new SymbolDigraph(args[0], args[1]);
    TopologicalSort topologicalSort = new TopologicalSort(symbolDigraph.getDigraph());

    for (int vertex : topologicalSort.getTopologicalOrder()) {
      System.out.println(symbolDigraph.nameOf(vertex));
    }
  }
}
