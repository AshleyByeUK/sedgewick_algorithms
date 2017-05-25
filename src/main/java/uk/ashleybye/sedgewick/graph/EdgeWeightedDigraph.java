package uk.ashleybye.sedgewick.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import uk.ashleybye.sedgewick.collections.Bag;

/**
 * The class represents a directed graph with weighted edges. Edges are stored in a vertex-indexed
 * array of adjacency-lists of edges, and no assumptions are made about the order in which edges
 * appear in the adjacency-lists. This implementation does not prohibit parallel edges nor
 * self-loops, although the implementation of {@code getEdges()} excludes self-loops even if they
 * are present in the data structure. If self-loops are required, the implementation will need to be
 * altered (see page Algorithms, p612).
 */
public class EdgeWeightedDigraph {

  /**
   * The number of vertices in the graph.
   */
  private final int numVertices;

  /**
   * The number of edges in the graph.
   */
  private int numEdges;

  /**
   * The adjacency-lists of edges.
   */
  private Bag<DirectedEdge>[] adjacencyLists;

  /**
   * Construct a new instance of EdgeWeightedDigraph with the specified number of vertices and zero
   * edges.
   *
   * @param numVertices The number of vertices.
   */
  public EdgeWeightedDigraph(int numVertices) {
    this.numVertices = numVertices;
    this.numEdges = 0;
    adjacencyLists = (Bag<DirectedEdge>[]) new Bag[numVertices];

    for (int vertex = 0; vertex < numVertices; vertex++) {
      adjacencyLists[vertex] = new Bag<>();
    }
  }

  /**
   * Construct a new instance of EdgeWeightedDigraph from the specified source file, which should be
   * in the format total number of vertices and total number of edges on the first line, followed by
   * a list of edges, one per line. Each edge should be specified in order of vertex u, vertex v,
   * weight. An example follows:
   *
   * 123 341
   * 12 76 0.34
   * 12 94 0.75
   * 19 23 0.57
   * ...
   *
   * @param fileName The source file.
   *
   * @throws IOException If the source file cannot be read.
   */
  public EdgeWeightedDigraph(String fileName) throws IOException {
    Path filePath = Paths.get(fileName);

    try (Scanner scanner = new Scanner(Files.newInputStream(filePath))) {
      this.numVertices = scanner.nextInt();
      this.numEdges = scanner.nextInt();
      adjacencyLists = (Bag<DirectedEdge>[]) new Bag[numVertices];

      for (int vertex = 0; vertex < numVertices; vertex++) {
        adjacencyLists[vertex] = new Bag<>();
      }

      for (int e = 0; e < numEdges; e++) {
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        double weight = scanner.nextDouble();
        this.addEdge(new DirectedEdge(from, to, weight));

        // numEdges is incremented in addEdge(), so decrement to keep E constant.
        numEdges--;
      }
    } catch (IOException exception) {
      throw new IOException("Could not open file: " + fileName);
    }
  }

  /**
   * Get the number of vertices in the graph.
   *
   * @return The number of vertices.
   */
  public int getNumVertices() {
    return numVertices;
  }

  /**
   * Get the number of edges in the graph.
   *
   * @return The number of edges.
   */
  public int getNumEdges() {
    return numEdges;
  }

  /**
   * Add an edges to the graph.
   *
   * @param edge The edge to add.
   */
  public void addEdge(DirectedEdge edge) {
    adjacencyLists[edge.from()].add(edge);
    numEdges++;
  }

  /**
   * Get the vertices adjacent to the specified vertex.
   *
   * @param vertex The vertex.
   *
   * @return Adjacent vertices.
   */
  public Iterable<DirectedEdge> adjacentTo(int vertex) {
    return adjacencyLists[vertex];
  }

  /**
   * Get an iterable list of all edges in the graph. Includes parallel edges and excludes
   * self-loops.
   *
   * @return Iterable list of edges.
   */
  public Iterable<DirectedEdge> getEdges() {
    Bag<DirectedEdge> edges = new Bag<>();
    for (int vertex = 0; vertex < numVertices; vertex++) {
      for (DirectedEdge edge : adjacentTo(vertex)) {
        edges.add(edge);
      }
    }

    return edges;
  }
}
