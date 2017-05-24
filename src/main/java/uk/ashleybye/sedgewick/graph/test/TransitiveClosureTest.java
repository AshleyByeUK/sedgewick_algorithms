package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.Digraph;
import uk.ashleybye.sedgewick.graph.TransitiveClosure;

public class TransitiveClosureTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 3) {
      System.out.println("Usage: java TransitiveClosureTest sourceFile fromVertex toVertex");
      System.exit(0);
    }

    Digraph digraph = new Digraph(args[0]);
    TransitiveClosure transitiveClosure = new TransitiveClosure(digraph);

    boolean isPath = transitiveClosure.isReachable(Integer.parseInt(args[1]), Integer.parseInt(args[2]));

    System.out.print("Vertex " + args[2] + " is ");
    if (!isPath) {
      System.out.print("NOT ");
    }
    System.out.println("reachable from vertex " + args[1]);
  }

}
