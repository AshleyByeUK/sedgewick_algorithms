package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.BreadthFirstDirectedPaths;
import uk.ashleybye.sedgewick.graph.Digraph;

public class BfsDirectedPathsTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 2) {
      System.out.println("Usage: java BfsDirectedPathsTest sourceFile sourceVertex");
      System.exit(0);
    }

    Digraph digraph = new Digraph(args[0]);
    int head = Integer.parseInt(args[1]);
    BreadthFirstDirectedPaths search = new BreadthFirstDirectedPaths(digraph, head);

    for (int v = 0; v < digraph.getNumVertices(); v++) {
      System.out.print(head + " to " + v + ": ");
      if (search.shortestPathTo(v).isPresent()) {
        for (int tail : search.shortestPathTo(v).get()) {
          if (tail == head) {
            System.out.print(tail);
          } else {
            System.out.print("-" + tail);
          }
        }
      }
      System.out.println();
    }
  }
}