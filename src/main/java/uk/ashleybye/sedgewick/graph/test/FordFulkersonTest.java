package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.FlowEdge;
import uk.ashleybye.sedgewick.graph.FlowNetwork;
import uk.ashleybye.sedgewick.graph.FordFulkerson;

public class FordFulkersonTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println("Usage: java FordFulkersonTest sourceFile");
      System.out.println("e.g.: java FordFulkersonTest tinyFN.txt");
      System.exit(0);
    }

    FlowNetwork flowNetwork = new FlowNetwork(args[0]);

    int source = 0;
    int sink = flowNetwork.getNumVertices() - 1;
    FordFulkerson maxFlow = new FordFulkerson(flowNetwork, source, sink);

    System.out.println("Flow Network:\n" + flowNetwork + "\n");
    System.out.println("Max flow from " + source + " to " + sink + ":");
    for (int vertex = 0; vertex < flowNetwork.getNumVertices(); vertex++) {
      for (FlowEdge edge : flowNetwork.getEdgesAdjacentTo(vertex)) {
        if (vertex == edge.from() && edge.getFlow() > 0) {
          System.out.println("    " + edge);
        }
      }
    }
    System.out.println("Max flow value = " + maxFlow.getValue());
  }
}
