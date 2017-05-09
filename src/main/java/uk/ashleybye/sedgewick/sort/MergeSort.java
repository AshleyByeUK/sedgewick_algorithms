package uk.ashleybye.sedgewick.sort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ash on 08/05/2017.
 * ~ NlogN, thus linearithmic, which is good.
 *
 * Uses an entire second array, so quite space intensive, especially as N grows. This
 * temp[] should be passed as a parameter to merge() rather than be a static field,
 * especially if this is a library function. If not, there is a risk multiple clients
 * will try to access the array at the same time, which would not end well! There
 * are implementations of the algorithm that can be used which use less space (see
 * Ex 2.2.10 and 2.2.11). Performance can be further improved by 10-15% by setting a
 * cutoff for sub-arrays length 15 or less, and using insertion sort on these
 * (see Ex 2.2.23).
 *
 * Testing whether the array is already in order can reduce the running time to be linear
 * for such arrays. This is achieved by testing whether array[mid] <= array[mid + 1] and
 * skipping the call to merge() if it is. All the recursive calls are still performed
 * (see Ex 2.2.8).
 *
 * When using linked lists, the bottom up algorithm should be preferred, because
 * it can sort the list in place (i.e. without creating any new list nodes).
 */
public class MergeSort {

  public static void merge(Comparable[] array,
      int low,
      int mid,
      int high,
      Comparable[] temp) {

    // Skip already sorted sub arrays.
    if (array[mid].compareTo(array[mid + 1]) <= 0) {
      return;
    }

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

  // The following single sort() method uses a pairwise, bottom-up approach.
  public static void sort(Comparable[] array) {
    // Do lgN passes of pairwise merges.
    Comparable[] temp = new Comparable[array.length];  // Initialise just once, hence not in merge().

    for (int size = 1; size < array.length; size *= 2) {
      for (int low = 0; low < (array.length - size); low += (2 * size)) {
        merge(array,
            low,
            low + size - 1,
            Math.min((low + (2 * size) - 1), (array.length - 1)),
            temp);
      }
    }
  }

  // The following two sort() methods utilise a recursive, top-down approach.
//  public static void sort(Comparable[] array) {
//    temp = new Comparable[array.length];  // Initialise just once, hence not in merge().
//    sort(array, 0, array.length - 1);
//  }
//
//  private static void sort(Comparable[] array, int low, int high) {
//    // Sort array[low...high].
//    if (high <= low) {
//      return;
//    } else {
//      int mid = low + ((high - low) / 2); // Low not always zero, e.g. 10 + ((20 - 10) / 2).
//      sort(array, low, mid);          // Sort left half.
//      sort(array, mid + 1, high);     // Sort right half.
//      merge(array, low, mid, high);   // Merge results.
//    }
//  }

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
