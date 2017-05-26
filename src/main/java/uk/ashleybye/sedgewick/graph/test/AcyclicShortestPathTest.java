package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.AcyclicShortestPath;
import uk.ashleybye.sedgewick.graph.DirectedEdge;
import uk.ashleybye.sedgewick.graph.EdgeWeightedDigraph;

public class AcyclicShortestPathTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: java AcyclicShortestPathTest sourceFile sourceVertex");
      System.exit(0);
    }

    EdgeWeightedDigraph graph = new EdgeWeightedDigraph(args[0]);
    int sourceVertex = Integer.parseInt(args[1]);
    AcyclicShortestPath paths = new AcyclicShortestPath(graph, sourceVertex);

    for (int vertex = 0; vertex < graph.getNumVertices(); vertex++) {
      System.out.print(sourceVertex + " to " + vertex);
      System.out.printf(" (%4.2f): ", paths.getDistanceTo(vertex));
      if (paths.shortestPathTo(vertex).isPresent()) {
        for (DirectedEdge edge : paths.shortestPathTo(vertex).get()) {
          System.out.print(edge + "  ");
        }
      }
      System.out.println();
    }
  }
}