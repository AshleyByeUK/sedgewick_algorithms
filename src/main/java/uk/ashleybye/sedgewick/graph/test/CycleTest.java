package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.Cycle;
import uk.ashleybye.sedgewick.graph.Graph;

public class CycleTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 1) {
      System.out.println("Usage: java CycleTest sourceFile");
      System.exit(0);
    }

    Graph graph = new Graph(args[0]);
    Cycle search = new Cycle(graph);

    System.out.print("Graph is ");
    if (!search.isAcyclic()) {
      System.out.print("NOT ");
    }
    System.out.println("acyclic.");
  }
}
