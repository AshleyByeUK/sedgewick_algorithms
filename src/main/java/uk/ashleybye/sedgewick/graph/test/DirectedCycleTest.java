package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.Digraph;
import uk.ashleybye.sedgewick.graph.DirectedCycle;

public class DirectedCycleTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 1) {
      System.out.println("Usage: java DirectedCycleTest sourceFile");
      System.exit(0);
    }

    Digraph digraph = new Digraph(args[0]);
    DirectedCycle search = new DirectedCycle(digraph);

    System.out.print("Graph is ");
    if (search.getCycle().isPresent()) {
      System.out.print("NOT ");
    }
    System.out.println("a directed acyclic graph.");

    if (search.getCycle().isPresent()) {
      System.out.print("Cycle: ");
      for (int vertex : search.getCycle().get()) {
        System.out.print(vertex + " ");
      }
      System.out.println();
    }
  }
}
