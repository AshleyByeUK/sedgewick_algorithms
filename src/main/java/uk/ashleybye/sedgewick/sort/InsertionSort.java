package uk.ashleybye.sedgewick.sort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ash on 07/05/2017.
 * From Sedgewick, page 251.
 *
 * Worst case: ~ (N^2)/4 compares and ~ (N^2)/4 exchanges (array in reverse order).
 * Best case: N -1 compares and 0 exchanges (array already in order).
 *
 * Can be sped up by shortening the inner loop to move the larger entries to
 * the right one position instead of doing full exchanges (cuts the number of
 * array accesses in half) - see Ex 2.1.25 (page 267).
 *
 * Good for partially sorted arrays, such as when adding to the end of a larger
 * and already sorted array. Or also a fine method for tiny arrays. Both partially
 * sorted and tiny arrays occur frequently in real life and also as intermediate
 * stages on more advanced sorting algorithms.
 */
public class InsertionSort {

  public static void sort(Comparable[] array) {
    // Sort array[] into increasing order.
    for (int i = 1; i < array.length; i++) {
      // Insert array[i] among array[i-1], array[i-2], array[i-3]...
      for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {
        exchange(array, j, j - 1);
      }
    }
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
