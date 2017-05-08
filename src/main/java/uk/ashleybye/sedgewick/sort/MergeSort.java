package uk.ashleybye.sedgewick.sort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ash on 08/05/2017.
 */
public class MergeSort {

  private static Comparable[] temp;  // Temporary array for merges.

  public static void merge(Comparable[] array, int low, int mid, int high) {
    // Merge array[low...mid] with array[mid + 1...high].
    int i = low;
    int j = mid + 1;

    // Copy array[low...high] to temp[low...high].
    for (int k = low; k <= high; k++) {
      temp[k] = array[k];
    }

    // Merge back to array[low...high]
    for (int k = low; k <= high; k++) {
      if (i > mid) {
        // We've run out of elements below the mid-point, so take from above it.
        array[k] = temp[j++];
      } else if (j > high) {
        // We've run out of elements above the mid-point, so take from below it.
        array[k] = temp[i++];
      } else if (less(temp[j], temp[i])) {
        // Next element above mid-point is less than next element below mid-point.
        array[k] = temp[j++];
      } else {
        // Next element below mid-point is less than or equal to next element above mid-point.
        array[k] = temp[i++];
      }
    }
  }

  public static void sort(Comparable[] array) {
    temp = new Comparable[array.length];  // Initialise just once, hence not in merge().
    sort(array, 0, array.length - 1);
  }

  private static void sort(Comparable[] array, int low, int high) {
    // Sort array[low...high].
    if (high <= low) {
      return;
    } else {
      int mid = low + ((high - low) / 2); // Low not always zero, e.g. 10 + ((20 - 10) / 2).
      sort(array, low, mid);          // Sort left half.
      sort(array, mid + 1, high);     // Sort right half.
      merge(array, low, mid, high);   // Merge results.
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
