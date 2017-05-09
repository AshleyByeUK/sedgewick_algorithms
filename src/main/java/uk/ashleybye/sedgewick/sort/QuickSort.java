package uk.ashleybye.sedgewick.sort;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by ash on 09/05/2017.
 * From Sedgewick, pages 289 & 291.
 */
public class QuickSort {

  private static Random random;   // Pseudo-random number generator.
  private static long seed;       // Pseudo-random number generator seed.

  static {
    seed = System.currentTimeMillis();
    random = new Random(seed);
  }

  public static void sort(Comparable[] array) {
    shuffle(array);   // Eliminate dependence on input.
    sort(array, 0, array.length - 1);
  }

  private static void sort(Comparable[] array, int low, int high) {
    // Sort array[] into increasing order.
    if (high <= low) {
      return;
    }

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

  private static int partition(Comparable[] array, int low, int high) {
    // Partition into array[low...i], array[i], array[i + 1...high].
    int i = low;        // Left scan index.
    int j = high + 1;   // Right scan index.
    Comparable v = array[low];    // Partitioning item.

    while (true) {
      // Scan right, scan left, check for scan complete, and exchange.
      while (less(array[++i], v)) {
        if (i == high) {
          break;
        }
      }
      while (less(v, array[--j])) {
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
