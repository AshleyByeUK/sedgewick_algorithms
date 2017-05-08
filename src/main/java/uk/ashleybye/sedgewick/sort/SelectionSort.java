package uk.ashleybye.sedgewick.sort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ash on 07/05/2017.
 * From Sedgewick, page 249.
 *
 * ~ N^2 for all arrays, whether already sorted, same value or in random order.
 */
public class SelectionSort {

  public static void sort(Comparable[] array) {
    // Sort array[] into increasing order.
    for (int i = 0; i < array.length; i++) {
      // Exchange array[i] with smallest entry in array[i+1...array.length].
      int min = i;    // Index of smallest entry.
      for (int j = i + 1; j < array.length; j++) {
        if (less(array[j], array[min])) {
          min = j;
        }
      }
      exchange(array, i, min);
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
