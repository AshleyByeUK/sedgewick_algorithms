package uk.ashleybye.sedgewick.graph.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import uk.ashleybye.sedgewick.collections.Bag;
import uk.ashleybye.sedgewick.graph.ConnectedComponents;
import uk.ashleybye.sedgewick.graph.Graph;

public class ConnectedComponentsTest {
  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Usage: java ConnectedComponentsTest sourcefile");
    }

    try {
      Scanner scanner = new Scanner(new FileInputStream(new File(args[0])));
      Graph graph = new Graph(scanner);
      ConnectedComponents connectedComponents = new ConnectedComponents(graph);

      int numComponents = connectedComponents.getComponentCount();
      System.out.println(numComponents + " components");

      Bag<Integer>[] components = (Bag<Integer>[]) new Bag[numComponents];
      for (int i = 0; i < numComponents; i++) {
        components[i] = new Bag<>();
      }

      for (int v = 0; v < graph.getNumVertices(); v++) {
        components[connectedComponents.getComponentId(v)].add(v);
      }

      for (int i = 0; i < numComponents; i++) {
        for (int v : components[i]) {
          System.out.print(v + " ");
        }
        System.out.println();
      }
    } catch (FileNotFoundException exception) {
      System.out.println("File '" + args[0] + "' not found.");
    }
  }
}
