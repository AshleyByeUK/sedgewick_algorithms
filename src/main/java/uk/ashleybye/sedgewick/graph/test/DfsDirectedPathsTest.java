package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.DepthFirstDirectedPaths;
import uk.ashleybye.sedgewick.graph.Digraph;

public class DfsDirectedPathsTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 2) {
      System.out.println("Usage: java DfsDirectedPathsTest sourceFile sourceVertex");
      System.exit(0);
    }

    Digraph digraph = new Digraph(args[0]);
    int sourceVertex = Integer.parseInt(args[1]);
    DepthFirstDirectedPaths search = new DepthFirstDirectedPaths(digraph, sourceVertex);

    for (int v = 0; v < digraph.getNumVertices(); v++) {
      System.out.print(sourceVertex + " to " + v + ": ");
      if (search.pathTo(v).isPresent()) {
        for (int vertex : search.pathTo(v).get()) {
          if (vertex == sourceVertex) {
            System.out.print(vertex);
          } else {
            System.out.print("-" + vertex);
          }
        }
      }
      System.out.println();
    }
  }
}