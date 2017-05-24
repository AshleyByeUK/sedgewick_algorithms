package uk.ashleybye.sedgewick.graph.test;

import java.io.IOException;
import uk.ashleybye.sedgewick.collections.Bag;
import uk.ashleybye.sedgewick.graph.Digraph;
import uk.ashleybye.sedgewick.graph.StronglyConnectedComponents;

public class StronglyConnectedComponentsTest {

  public static void main(String[] args) throws IOException {

    if (args.length != 1) {
      System.out.println("Usage: java StronglyConnectedComponentsTest sourceFile");
      System.exit(0);
    }

    Digraph digraph = new Digraph(args[0]);
    StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents(
        digraph);

    int numComponents = stronglyConnectedComponents.getComponentCount();
    System.out.println(numComponents + " components");

    Bag<Integer>[] components = (Bag<Integer>[]) new Bag[numComponents];
    for (int i = 0; i < numComponents; i++) {
      components[i] = new Bag<>();
    }

    for (int v = 0; v < digraph.getNumVertices(); v++) {
      components[stronglyConnectedComponents.getComponentId(v)].add(v);
    }

    for (int i = 0; i < numComponents; i++) {
      for (int v : components[i]) {
        System.out.print(v + " ");
      }
      System.out.println();
    }
  }
}
