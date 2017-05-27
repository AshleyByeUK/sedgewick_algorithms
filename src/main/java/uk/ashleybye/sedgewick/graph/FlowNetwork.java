package uk.ashleybye.sedgewick.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FlowNetwork {

  /**
   * The number of vertices in this {@code FlowNewtrok}.
   */
  private int numVertices;

  /**
   * The number of edges in this {@code FlowNetwork}.
   */
  private int numEdges;

  /**
   * Vertex-indexed adjacency lists of edges.
   */
  private List<FlowEdge>[] adjacencyLists;

  /**
   * Construct a {@code FlowNetwork} with the given number of vertices.
   *
   * @param numVertices the number of vertices
   */
  public FlowNetwork(int numVertices) {
    this.numVertices = numVertices;
    this.numEdges = 0;
    this.adjacencyLists = (List<FlowEdge>[]) new List[numVertices];

    for (int vertex = 0; vertex < this.numVertices; vertex++) {
      adjacencyLists[vertex] = new ArrayList<>();
    }
  }

  /**
   * Construct a {@code FlowNetwork} from the given {@code sourceFile}.
   *
   * @param sourceFile the source file
   *
   * @throws IOException if the source file cannot be read
   */
  public FlowNetwork(String sourceFile) throws IOException {
    Path filePath = Paths.get(sourceFile);
    try (Scanner scanner = new Scanner(Files.newInputStream(filePath))) {
      this.numVertices = scanner.nextInt();
      this.adjacencyLists = (List<FlowEdge>[]) new List[numVertices];

      for (int vertex = 0; vertex < this.numVertices; vertex++) {
        adjacencyLists[vertex] = new ArrayList<>();
      }

      int edges = scanner.nextInt();
      for (int edge = 0; edge < edges; edge++) {
        FlowEdge flowEdge = new FlowEdge(scanner.nextInt(), scanner.nextInt(),
            scanner.nextDouble());
        this.addEdge(flowEdge);
      }
    } catch (IOException exception) {
      throw new IOException("Cannot open " + sourceFile);
    }
  }

  /**
   * Get the number of vertices in this {@code FlowNetwork}.
   *
   * @return the number of vertices
   */
  public int getNumVertices() {
    return numVertices;
  }

  /**
   * Get the number of edges in this {@code FlowNetwork}.
   *
   * @return the number of edges
   */
  public int getNumEdges() {
    return numEdges;
  }

  /**
   * Add the given {@code edge} to this instance.
   *
   * @param edge the edge
   */
  public void addEdge(FlowEdge edge) {
    adjacencyLists[edge.from()].add(edge);
    adjacencyLists[edge.to()].add(edge);  // Correct?
    numEdges++;
  }

  /**
   * Get the edges adjacent to the given {@code vertex}.
   *
   * @param vertex the vertx
   *
   * @return the adjacent edges
   */
  public Iterable<FlowEdge> getEdgesAdjacentTo(int vertex) {
    return adjacencyLists[vertex];
  }

  /**
   * Get all the edges in this {@code FlowNetwork}.
   *
   * @return the edges
   */
  public Iterable<FlowEdge> getEdges() {
    List<FlowEdge> edges = new ArrayList<>();

    for (int vertex = 0; vertex < numVertices; vertex++) {
      for (FlowEdge edge : this.getEdgesAdjacentTo(vertex)) {
        if (edge.to() != vertex) {
          edges.add(edge);
        }
      }
    }

    return edges;
  }

  /**
   * Get a string representation of this {@code FlowNetwork}.
   *
   * @return the string representation
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(numVertices).append(" vertices, ").append(numEdges).append(" edges")
        .append("\n");

    for (int vertex = 0; vertex < numVertices; vertex++) {
      stringBuilder.append(vertex).append(": ");
      for (FlowEdge edge : this.getEdgesAdjacentTo(vertex)) {
        if (edge.to() != vertex) {
          stringBuilder.append(edge).append(" ");
        }
      }
      stringBuilder.append("\n");
    }

    return stringBuilder.toString();
  }
}
