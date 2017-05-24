package uk.ashleybye.sedgewick.graph.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.BreadthFirstPaths;
import uk.ashleybye.sedgewick.graph.Graph;

public class BfsPathsTest {

  public static void main(String[] args) {

    if (args.length != 2) {
      System.out.println("Usage: java BfsPathsTest sourcefile numberEdges");
      System.exit(0);
    }

    try {
      Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
      Graph graph = new Graph(scanner);
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
    } catch (FileNotFoundException exception) {
      System.out.println("File '" + args[0] + "' not found.");
    }
  }
}