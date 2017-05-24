package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.Bipartite;
import uk.ashleybye.sedgewick.graph.Graph;

public class BipartiteTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 1) {
      System.out.println("Usage: java BipartiteTest sourceFile");
      System.exit(0);
    }

    Graph graph = new Graph(args[0]);
    Bipartite search = new Bipartite(graph);

    System.out.print("Graph is ");
    if (!search.isBipartite()) {
      System.out.print("NOT ");
    }
    System.out.println("bipartite.");
  }
}
