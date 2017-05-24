package uk.ashleybye.sedgewick.graph.apps;

import java.io.IOException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.BreadthFirstPaths;
import uk.ashleybye.sedgewick.graph.Graph;
import uk.ashleybye.sedgewick.graph.SymbolGraph;

/**
 * Takes a given source file, delimiter and source vertex and uses breadth first search to determine
 * a shortest path between the source vertex and a given sink vertex.
 */
public class DegreesOfSeparation {

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Usage: java DegreesOfSeparation fileName delimiter sourceVertex");
      System.out.println("e.g.: java DegreesOfSeparation movies.txt \"/\" \"Bacon, Kevin\"");
      System.exit(0);
    }

    try {
      SymbolGraph symbolGraph = new SymbolGraph(args[0], args[1]);
      Graph graph = symbolGraph.getGraph();

      String sourceKey = args[2];
      if (symbolGraph.contains(sourceKey)) {
        int source = symbolGraph.indexOf(sourceKey);
        BreadthFirstPaths search = new BreadthFirstPaths(graph, source);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
          String sinkKey = scanner.nextLine();
          if (symbolGraph.contains(sinkKey)) {
            int sink = symbolGraph.indexOf(sinkKey);
            if (search.shortestPathTo(sink).isPresent()) {
              for (int vertex : search.shortestPathTo(sink).get()) {
                System.out.println("    " + symbolGraph.nameOf(vertex));
              }
            } else {
              System.out.println("\"" + sourceKey + "\" is not connected to \"" + sinkKey + "\"");
            }
          } else {
            System.out.println("\"" + sinkKey + "\" is not a valid vertex name.");
          }
        }
      } else {
        System.out.println("\"" + sourceKey + "\" is not a valid vertex name.");
      }
    } catch (IOException exception) {
      System.out.println(exception.getMessage());
    }
  }
}
