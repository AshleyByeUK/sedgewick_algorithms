package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.DepthFirstPaths;
import uk.ashleybye.sedgewick.graph.Graph;

public class DfsPathsTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 2) {
      System.out.println("Usage: java DfsPathsTest sourceFile sourceVertex");
      System.exit(0);
    }

    Graph graph = new Graph(args[0]);
    int sourceVertex = Integer.parseInt(args[1]);
    DepthFirstPaths search = new DepthFirstPaths(graph, sourceVertex);

    for (int v = 0; v < graph.getNumVertices(); v++) {
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