package uk.ashleybye.sedgewick.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ash on 09/05/2017.
 */
public class QuickSortTest {

  private String[] sorted = {"C", "I", "K", "O", "Q", "R", "S", "T", "U"};

  @Test
  public void sort() {
    // Arrange
    String[] unsorted = {"Q", "U", "I", "C", "K", "S", "O", "R", "T"};

    // Act
    QuickSort.sort(unsorted);

    // Assert
    Assert.assertArrayEquals("Expected sort does not equal actual sort",
        sorted,
        unsorted);
  }
}