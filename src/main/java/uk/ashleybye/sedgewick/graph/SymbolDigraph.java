package uk.ashleybye.sedgewick.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Scanner;

public class SymbolDigraph {

  /**
   * Index of symbol to underlying digraph index.
   */
  //TODO(Ash): This should be a HashMap, HashTable is depricated.
  private Hashtable<String, Integer> symbolTable;

  /**
   * Inverse index of underlying digraph index to symbol.
   */
  private String[] invertedIndex;

  /**
   * The digraph.
   */
  private Digraph digraph;

  /**
   * Constructs an instance of SymbolDigraph from the provided file, which shall contain a list of one
   * vertex followed by all of its adjacent vertices per line. Each vertex per line shall be
   * separated by the delimiter.
   *
   * @param filename The file representing the symbol digraph.
   * @param delimiter The delimiter.
   *
   * @throws IOException If cannot load the file at the given path.
   */
  public SymbolDigraph(String filename, String delimiter) throws IOException {
    symbolTable = new Hashtable<>();

    // Perhaps read into byte[] and use the byte[] for each pass, rather than use two streams?
    Path filePath = Paths.get(filename);
    try (Scanner firstPassScanner = new Scanner(Files.newInputStream(filePath));
        Scanner secondPassScanner = new Scanner(Files.newInputStream(filePath))) {
      // First pass builds the index by reading strings to associate each distinct string with an
      // index. The index is the current size of the symbol table.
      while (firstPassScanner.hasNextLine()) {
        String[] items = firstPassScanner.nextLine().split(delimiter);
        for (int i = 0; i < items.length; i++) {
          if (!symbolTable.containsKey(items[i])) {
            symbolTable.put(items[i], symbolTable.size());
          }
        }
      }

      // Build the inverted index.
      invertedIndex = new String[symbolTable.size()];
      for (String key : symbolTable.keySet()) {
        invertedIndex[symbolTable.get(key)] = key;
      }

      // Second pass builds the digraph by connecting the first vertex on each line to all the others.
      digraph = new Digraph(symbolTable.size());
      while (secondPassScanner.hasNextLine()) {
        String[] items = secondPassScanner.nextLine().split(delimiter);
        int vertex = symbolTable.get(items[0]); // The index of the vertex to connect the others to.
        for (int i = 1; i < items.length; i++) {
          digraph.addEdge(vertex, symbolTable.get(items[i]));
        }
      }
    } catch (IOException exception) {
      throw new IOException("Could not open file: " + filename);
    }
  }

  /**
   * Returns true if the specified key is a vertex in the digraph; false, otherwise.
   *
   * @param key The key for the vertex.
   *
   * @return True if the key matches a vertex in the digraph; false, otherwise.
   */
  public boolean contains(String key) {
    return symbolTable.containsKey(key);
  }

  /**
   * Gets the vertex index of the specified key.
   *
   * @param key The key.
   *
   * @return The index of the specified key.
   */
  public int indexOf(String key) {
    return symbolTable.get(key);
  }

  /**
   * Gets the name of the key for the specified vertex index.
   *
   * @param vertex The vertex.
   *
   * @return The name of the key.
   */
  public String nameOf(int vertex) {
    return invertedIndex[vertex];
  }

  /**
   * Gets the underlying digraph.
   *
   * @return The digraph.
   */
  public Digraph getDigraph() {
    return digraph;
  }
}
