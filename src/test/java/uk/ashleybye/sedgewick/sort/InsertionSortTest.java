package uk.ashleybye.sedgewick.sort;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ash on 09/05/2017.
 */
public class InsertionSortTest {

  private String[] sorted;
  private String[] unsorted;

  @Before
  public void before() {
    sorted = new String[]{"E", "I", "I", "N", "N", "O", "O", "R", "R", "S", "S", "T", "T"};
    unsorted = new String[] {"I", "N", "S", "E", "R", "T", "I", "O", "N", "S", "O", "R", "T"};
  }

  @Test
  public void sort() {
    // Arrange

    // Act
    InsertionSort.sort(unsorted);

    // Assert
    Assert.assertArrayEquals("Expected sort does not equal actual sort",
        sorted,
        unsorted);
  }

  @Test
  public void sortSpecifyLowHigh() {
    // Arrange

    // Act
    InsertionSort.sort(unsorted, 0, 13);

    // Assert
    Assert.assertArrayEquals("Expected sort does not equal actual sort",
        sorted,
        unsorted);
  }

}