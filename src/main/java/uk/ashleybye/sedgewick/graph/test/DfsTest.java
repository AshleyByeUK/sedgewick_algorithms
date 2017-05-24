package uk.ashleybye.sedgewick.graph.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.DepthFirstSearch;
import uk.ashleybye.sedgewick.graph.Graph;

public class DfsTest {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Usage: java DfsTest sourcefile numberEdges");
      System.exit(0);
    }

    try {
      Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
      Graph graph = new Graph(scanner);
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
    } catch (FileNotFoundException exception) {
      System.out.println("File '" + args[0] + "' not found.");
    }
  }
}
