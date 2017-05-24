package uk.ashleybye.sedgewick.graph.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.Bipartite;
import uk.ashleybye.sedgewick.graph.Graph;

public class BipartiteTest {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Usage: java BipartiteTest sourcefile");
      System.exit(0);
    }

    try {
      Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
      Graph graph = new Graph(scanner);
      Bipartite search = new Bipartite(graph);

      System.out.print("Graph is ");
      if (!search.isBipartite()) {
        System.out.print("NOT ");
      }
      System.out.println("bipartite.");
    } catch (FileNotFoundException exception) {
      System.out.println("File '" + args[0] + "' not found.");
    }
  }
}
