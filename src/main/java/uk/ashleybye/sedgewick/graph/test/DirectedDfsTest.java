package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.collections.Bag;
import uk.ashleybye.sedgewick.graph.Digraph;
import uk.ashleybye.sedgewick.graph.DirectedDepthFirstSearch;

public class DirectedDfsTest {

  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println("Usage: java DirectedDfsTest sourceFile sourceVertex [sourceVertex...]");
      System.exit(0);
    }

    Digraph digraph = new Digraph(args[0]);
    Bag<Integer> sourceVertices = new Bag<>();
    for (int i = 1; i < args.length; i++) {
      sourceVertices.add(Integer.parseInt(args[i]));
    }

    DirectedDepthFirstSearch reachable = new DirectedDepthFirstSearch(digraph, sourceVertices);

    for (int v = 0; v < digraph.getNumVertices(); v++) {
      if (reachable.isMarked(v)) {
        System.out.print(v + " ");
      }
    }
    System.out.println();
  }
}
