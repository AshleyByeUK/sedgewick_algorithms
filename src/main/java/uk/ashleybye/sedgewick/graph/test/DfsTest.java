package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.DepthFirstSearch;
import uk.ashleybye.sedgewick.graph.Graph;

public class DfsTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: java DfsTest sourceFile sourceVertex");
      System.exit(0);
    }

    Graph graph = new Graph(args[0]);
    int sourceVertex = Integer.parseInt(args[1]);
    DepthFirstSearch search = new DepthFirstSearch(graph, sourceVertex);

    for (int v = 0; v < graph.getNumVertices(); v++) {
      if (search.isMarked(v)) {
        System.out.print(v + " ");
      }
    }
    System.out.println();

    if (search.count() != graph.getNumVertices()) {
      System.out.print("NOT ");
    }
    System.out.println("connected");
  }
}
