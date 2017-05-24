package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.Graph;
import uk.ashleybye.sedgewick.graph.SymbolGraph;

public class SymbolGraphTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Uasge: java SymbolTableTest fileName delimiter");
      System.exit(0);
    }

    String fileName = args[0];
    String delimiter = args[1];

    SymbolGraph symbolGraph = new SymbolGraph(fileName, delimiter);
    Graph graph = symbolGraph.getGraph();

    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String source = scanner.nextLine();
      if (symbolGraph.contains(source)) {
        for (int vertex : graph.adjacentTo(symbolGraph.indexOf(source))) {
          System.out.println("    " + symbolGraph.nameOf(vertex));
        }
      } else {
        System.out.println(source + " is not a valid key.");
      }
    }
  }
}
