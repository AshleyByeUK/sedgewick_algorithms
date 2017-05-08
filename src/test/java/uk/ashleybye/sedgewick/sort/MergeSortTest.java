package uk.ashleybye.sedgewick.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ash on 08/05/2017.
 */
public class MergeSortTest {

  private String[] sorted = {"E", "E", "G", "M", "O", "R", "R", "S", "T"};

  @Test
  public void merge() {
    // Arrange
    String[] unmerged = {"E", "E", "G", "M", "R", "O", "R", "S", "T"};

    // Act
    MergeSort.merge(unmerged, 0, 4, 8);

    // Assert
    Assert.assertArrayEquals("Expected merge does not equal actual merge",
        sorted,
        unmerged);
  }

  @Test
  public void sort() {
    // Arrange
    String[] unsorted = { "M", "E", "R", "G", "E", "S", "O", "R", "T" };

    // Act
    MergeSort.sort(unsorted);

    // Assert
    Assert.assertArrayEquals("Expected sort does not equal actual sort",
        sorted,
        unsorted);
  }
}