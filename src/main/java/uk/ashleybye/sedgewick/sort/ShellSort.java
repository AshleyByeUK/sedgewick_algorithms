package uk.ashleybye.sedgewick.sort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ash on 08/05/2017.
 * From Sedgewick, page 259.
 */
public class ShellSort {

  public static void sort(Comparable[] array) {
    // Sort array[] into increasing order.
    int h = 1;
    while (h < array.length / 3) {
      h = (3 * h) + 1;  // 1, 4, 13, 40, 121, 364, 1093, ...
    }
    while (h >= 1) {
      // h-sort the array.
      for (int i = h; i < array.length; i++) {
        // Insert array[i] among array[i-h], array[i-2*h], array[i=-3*h]...
        for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
          exchange(array, j, j - h);
        }
      }
      h = h / 3;
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
