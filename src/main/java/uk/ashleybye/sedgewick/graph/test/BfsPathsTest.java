package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.BreadthFirstPaths;
import uk.ashleybye.sedgewick.graph.Graph;

public class BfsPathsTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 2) {
      System.out.println("Usage: java BfsPathsTest sourceFile sourceVertex");
      System.exit(0);
    }

    Graph graph = new Graph(args[0]);
    int sourceVertex = Integer.parseInt(args[1]);
    BreadthFirstPaths search = new BreadthFirstPaths(graph, sourceVertex);

    for (int v = 0; v < graph.getNumVertices(); v++) {
      System.out.print(sourceVertex + " to " + v + ": ");
      if (search.shortestPathTo(v).isPresent()) {
        for (int vertex : search.shortestPathTo(v).get()) {
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