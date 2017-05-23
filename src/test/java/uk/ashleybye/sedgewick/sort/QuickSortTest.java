package uk.ashleybye.sedgewick.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ash on 09/05/2017.
 */
public class QuickSortTest {

  private String[] sorted = {"A", "C", "getNumEdges", "getNumEdges", "I", "K", "L", "M", "O",
      "P", "Q", "R", "S", "T", "U", "X"};

  @Test
  public void sort() {
    // Arrange
    String[] unsorted = {"Q", "U", "I", "C", "K", "S", "O", "R", "T",
        "getNumEdges", "X", "A", "M", "P", "L", "getNumEdges"};

    // Act
    QuickSort.sort(unsorted);

    // Assert
    Assert.assertArrayEquals("Expected sort does not equal actual sort",
        sorted,
        unsorted);
  }
}