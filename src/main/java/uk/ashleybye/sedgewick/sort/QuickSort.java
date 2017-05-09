package uk.ashleybye.sedgewick.sort;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by ash on 09/05/2017.
 * From Sedgewick, pages 289 & 291.
 *
 * ~ NlogN. Algorithm 2.5 (page 289) without improvements, will have a running
 * time within a constant factor of 1.39NlogN. This is also true of mergesort,
 * although quicksort is typically faster due to it doing much less data movement.
 */
public class QuickSort {

  private static Random random;   // Pseudo-random number generator.
  private static long seed;       // Pseudo-random number generator seed.

  static {
    seed = System.currentTimeMillis();
    random = new Random(seed);
  }

  public static void sort(Comparable[] array) {
    shuffle(array);   // Eliminate dependence on input. An alternative approach is
                      // to randomly choose an element to be the partitioning value
                      // in partition().
    sort(array, 0, array.length - 1);
  }

  /*
   * Recursively call sort after partitioning the array. After each pass, one
   * extra value is in its final position within the array. This by calling
   * partition() and then sort on increasingly smaller sub-arrays.
   */
  private static void sort(Comparable[] array, int low, int high) {
    // For small sub-arrays, use insertion sort. The constant value cutoff is
    // system dependent, but values between 5 and 15 should work well in most
    // cases.
    if (high <= low + 15) {
//    if (high <= low) {
      // Add 1 to high, since InsertionSort parameter is exclusive, yet we have
      // already subtracted 1 from array.length in the public sort() method.
      InsertionSort.sort(array, low, high + 1);
      return;
    }

    // Partition and sort array[] into increasing order.
    int j = partition(array, low, high);
    sort(array, low, j - 1);    // Sort left part.
    sort(array, j + 1, high);   // Sort right part.
  }

  private static void shuffle(Object[] array) {
    if (array == null) {
      throw new IllegalArgumentException("Argument array is null");
    }

    for (int i = 0; i < array.length; i++) {
      int r = i + uniform(array.length - i);     // between i and n-1
      Object temp = array[i];
      array[i] = array[r];
      array[r] = temp;
    }
  }

  private static int uniform(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Argument must be positive");
    }

    return random.nextInt(n);
  }

  /*
   * Here we partition the array by arbitrarily choosing array[low] to be the
   * partitioning item. Next, scan from the left of the array until an entry
   * that is greater than or equal to the partitioning value is found. Repeat
   * this process for the right hand side, until an entry less than or equal to
   * the partitioning value is found. By definition, the values for i and j
   * are on the wrong side of the partitioning value, so swap them around. This
   * is repeated until all entries to the left of the partitioning value are not
   * greater than it, and those to the right are not less than it, i.e. when
   * the i and j indices cross. Finally, swap the partitioning value with the
   * highest entry of the left hand sub-array and return its position index.
   */
  private static int partition(Comparable[] array, int low, int high) {
    // Partition into array[low...i], array[i], array[i + 1...high].
    int i = low;        // Left scan index.
    int j = high + 1;   // Right scan index.
    Comparable v = array[low];    // Partitioning item.

    while (true) {
      // Scan right, scan left, check for scan complete, and exchange.
      while (less(array[++i], v)) {
        // Can be eliminated, see exercise 2.3.17.
        if (i == high) {
          break;
        }
      }
      while (less(v, array[--j])) {
        // Technically redundant, since v is item one and j will never move past it.
        if (j == low) {
          break;
        }
      }
      if (i >= j) {
        break;
      }
      exchange(array, i, j);
    }
    exchange(array, low, j);    // Put v = array[j] into position.
    return j;      // array[low...j - 1] <= array[j] <= array[j + 1...high].
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void exchange(Comparable[] array, int i, int j) {
    Comparable temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private static void show(Comparable[] array) {
    // Print the array on a single line.
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static boolean isSorted(Comparable[] array) {
    // Test whether the array entries are in order.
    for (int i = 1; i < array.length; i++) {
      if (less(array[i], array[i - 1])) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    // Read strings from System.in, sort them, and print.
    Scanner scanner = new Scanner(System.in);

    ArrayList<String> strings = new ArrayList<>();
    while (scanner.hasNext()) {
      strings.add(scanner.next());
    }

    String[] array = strings.toArray(new String[strings.size()]);
    sort(array);
    assert isSorted(array);
    show(array);
  }
}
