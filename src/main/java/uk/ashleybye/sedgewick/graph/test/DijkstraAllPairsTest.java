package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.graph.DijkstraAllPairsShortestPath;
import uk.ashleybye.sedgewick.graph.DirectedEdge;
import uk.ashleybye.sedgewick.graph.EdgeWeightedDigraph;

public class DijkstraAllPairsTest {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java DijkstraAllPairsTest sourceFile");
      System.exit(0);
    }

    try {
      EdgeWeightedDigraph graph = new EdgeWeightedDigraph(args[0]);
      DijkstraAllPairsShortestPath allPairs = new DijkstraAllPairsShortestPath(graph);

      System.out.println("Vertices range from 0 to " + (graph.getNumVertices() - 1) + ".");
      System.out.println("Enter pairs of vertices, in form: source-target. Ctrl + D to exit.");
      System.out.println();

      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNextLine()) {
        String[] vertices = scanner.nextLine().split("-");
        try {
          int source = Integer.parseInt(vertices[0]);
          int target = Integer.parseInt(vertices[1]);

          System.out
              .printf("%d to %d (%4.2f): ", source, target, allPairs.distance(source, target));
          if (allPairs.path(source, target).isPresent()) {
            for (DirectedEdge edge : allPairs.path(source, target).get()) {
              System.out.print(edge + "  ");
            }
          }
        } catch (IndexOutOfBoundsException | NumberFormatException exception) {
          System.out.print("Not a valid path.");
        } finally {
          System.out.println("\n");
        }
      }
    } catch (IOException exception) {
      System.out.println("Could not load file: " + args[0]);
      System.exit(0);
    }
  }
}
