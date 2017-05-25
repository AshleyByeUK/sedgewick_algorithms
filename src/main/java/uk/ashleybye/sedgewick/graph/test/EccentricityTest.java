package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.graph.Eccentricity;
import uk.ashleybye.sedgewick.graph.Graph;

public class EccentricityTest {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println("Usage: java EccentricityTest sourceFile");
      System.out.println("e.g.: java EccentricityTest mediumG.txt | more");
      System.exit(0);
    }

    Graph graph = new Graph(args[0]);
    Eccentricity eccentricity = new Eccentricity(graph);

    int[] allEccentricities = eccentricity.getEccentricities();

    System.out.println("Radius: " + eccentricity.getRadius());
    System.out.println("Diameter: " + eccentricity.getDiameter());
    System.out.println("Eccentricities: ");
    for (int v = 0; v < allEccentricities.length; v++) {
      System.out.println(v + ": " + allEccentricities[v]);
    }
  }
}
