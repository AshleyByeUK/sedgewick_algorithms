package uk.ashleybye.sedgewick.graph.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.Cycle;
import uk.ashleybye.sedgewick.graph.Graph;

public class CycleTest {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Usage: java CycleTest sourcefile");
      System.exit(0);
    }

    try {
      Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
      Graph graph = new Graph(scanner);
      Cycle search = new Cycle(graph);

      System.out.print("Graph is ");
      if (!search.isAcyclic()) {
        System.out.print("NOT ");
      }
      System.out.println("acyclic.");
    } catch (FileNotFoundException exception) {
      System.out.println("File '" + args[0] + "' not found.");
    }
  }
}
