package uk.ashleybye.sedgewick.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>The {@code IndexedPriorityQueue} class represents an indexed minimum priority queue of generic
 * keys. It supports <em>offer</em> and <em>poll</em> operations, along with <em>remove</em> and
 * <em>change the key</em> methods. An integer between {@code 0} and {@code maximumElements - 1} is
 * associated with each key, which is used to specify which key to remove or change. It also
 * supports methods for peeking at the minimum key, testing if the priority queue is empty, and
 * iterating through the keys.</p>
 * <p>This implementation is based on the
 * <a href="http://algs4.cs.princeton.edu/24pq">IndexedMinPQ</a> from
 * <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of <i>Algorithms, 4th Edition</i> by
 * Robert Sedgewick and Kevin Wayne. It uses a binary heap along with an array to associate keys
 * with integers in the given range.</p>
 *
 * @param <K> the generic type of key on this priority queue
 */
public class IndexedPriorityQueue<K extends Comparable<K>> implements Iterable<Integer> {

  /**
   * The maximum number of elements on the priority queue.
   */
  private int maximumElements;

  /**
   * The number of elements currently on priority queue.
   */
  private int numElements;

  /**
   * The binary heap based priority queue using <em>1-based indexing</em>.
   */
  private int[] priorityQueue;

  /**
   * Inverse of priorityQueue:
   * {@code inversePriorityQueue[priorityQueue[i]] = priorityQueue[inversePriorityQueue[i]] = i}.
   */
  private int[] inversePriorityQueue;

  /**
   * The keys associated with entries in the priority queue, such that {@code keys[i]} = priority of
   * i.
   */
  private K[] keys;

  /**
   * Constructs an empty indexed priority queue with capacity to hold the specified number of
   * elements.
   *
   * @param maximumElements upper bound on the capacity of the indexed priority queue.
   *
   * @throws IllegalArgumentException if {@code maximumElements < 0}.
   */
  public IndexedPriorityQueue(int maximumElements) {
    if (maximumElements < 0) {
      throw new IllegalArgumentException();
    }

    this.maximumElements = maximumElements;
    numElements = 0;
    priorityQueue = new int[maximumElements + 1];
    inversePriorityQueue = new int[maximumElements + 1]; // Make this of length maximumElements??
    keys = (K[]) new Comparable[maximumElements + 1]; // Make this of length maximumElements??

    for (int i = 0; i <= maximumElements; i++) {
      inversePriorityQueue[i] = -1;
    }
  }

  /**
   * Returns true if this priority queue is empty.
   *
   * @return {@code true} if this priority queue is empty; {@code false}, otherwise.
   */
  public boolean isEmpty() {
    return numElements == 0;
  }

  /**
   * Returns {@code true} if this indexed priority queue contains the specified {@code index}.
   *
   * @param index {@code index} whose presence in this minimum priority queue is to be tested.
   *
   * @return {@code true} if this minimum priority queue contains the specified {@code index};
   * {@code false} otherwise.
   *
   * @throws IndexOutOfBoundsException unless {@code 0 <= index < maximumElements}.
   */
  public boolean contains(int index) {
    if (index < 0 || index >= maximumElements) {
      throw new IndexOutOfBoundsException();
    }
    return inversePriorityQueue[index] != -1;
  }

  /**
   * Returns the number of keys on this priority queue.
   *
   * @return the number of keys on this priority queue
   */
  public int size() {
    return numElements;
  }

  /**
   * Associates the specified {@code key} with the specified {@code index}.
   *
   * @param index the index to associate with the specified {@code key}.
   * @param key the key to associate with the specified {@code index}
   *
   * @throws IndexOutOfBoundsException unless {@code 0 <= index < maximumElements}.
   * @throws IllegalArgumentException if an item is already associated with {@code index}.
   */
  public void offer(int index, K key) {
    if (index < 0 || index >= maximumElements) {
      throw new IndexOutOfBoundsException();
    }

    if (contains(index)) {
      throw new IllegalArgumentException("Index " + index + " is already in the priority queue");
    }

    numElements++;
    inversePriorityQueue[index] = numElements;
    priorityQueue[numElements] = index;
    keys[index] = key;
    swim(numElements);
  }

  /**
   * Returns an index associated with a minimum key.
   *
   * @return an index associated with a minimum key.
   *
   * @throws NoSuchElementException if this priority queue is empty.
   */
  public int getMinimumIndex() {
    if (numElements == 0) {
      throw new NoSuchElementException("Priority queue underflow");
    }
    return priorityQueue[1];
  }

  /**
   * Returns a minimum key.
   *
   * @return a minimum key.
   *
   * @throws NoSuchElementException if this priority queue is empty.
   */
  public K getMinimumKey() {
    if (numElements == 0) {
      throw new NoSuchElementException("Priority queue underflow");
    }
    return keys[priorityQueue[1]];
  }

  /**
   * Removes a minimum key and returns its associated index.
   *
   * @return an index associated with a minimum key.
   *
   * @throws NoSuchElementException if this priority queue is empty.
   */
  public int pollMinimum() {
    if (numElements == 0) {
      throw new NoSuchElementException("Priority queue underflow");
    }
    int min = priorityQueue[1];
    exchange(1, numElements--);
    sink(1);
    assert min == priorityQueue[numElements + 1];
    inversePriorityQueue[min] = -1; // Remove.
    keys[min] = null; // To help with garbage collection.
    priorityQueue[numElements + 1] = -1; // Not needed.
    return min;
  }

  /**
   * Returns the key associated with {@code index}.
   *
   * @param index the index of the key to return.
   *
   * @return the key associated with {@code index}.
   *
   * @throws IndexOutOfBoundsException unless {@code 0 <= index < maximumElements}.
   * @throws NoSuchElementException no key is associated with {@code index}.
   */
  public K getKeyFor(int index) {
    if (index < 0 || index >= maximumElements) {
      throw new IndexOutOfBoundsException();
    }
    if (!contains(index)) {
      throw new NoSuchElementException("Index " + index + " is not in the priority queue");
    } else {
      return keys[index];
    }
  }

  /**
   * Change the key associated with {@code index} to the specified value.
   *
   * @param index the index of the key to change.
   * @param key change the key associated with {@code index} to this key.
   *
   * @throws IndexOutOfBoundsException unless {@code 0 <= index < maximumElements}.
   * @throws NoSuchElementException no key is associated with {@code index}.
   */
  public void changeKey(int index, K key) {
    if (index < 0 || index >= maximumElements) {
      throw new IndexOutOfBoundsException();
    }
    if (!contains(index)) {
      throw new NoSuchElementException("Index " + index + " is not in the priority queue");
    }
    keys[index] = key;
    swim(inversePriorityQueue[index]);
    sink(inversePriorityQueue[index]);
  }

  /**
   * Decrease the key associated with {@code index} to the specified value.
   *
   * @param index the index of the key to decrease.
   * @param key decrease the key associated with {@code index} to this key.
   *
   * @throws IndexOutOfBoundsException unless {@code 0 <= index < maximumElements}.
   * @throws IllegalArgumentException if {@code key >= getKeyFor(index)}.
   * @throws NoSuchElementException no key is associated with {@code index}.
   */
  public void decreaseKey(int index, K key) {
    if (index < 0 || index >= maximumElements) {
      throw new IndexOutOfBoundsException();
    }
    if (!contains(index)) {
      throw new NoSuchElementException("Index " + index + " is not in the priority queue");
    }
    if (keys[index].compareTo(key) <= 0) {
      throw new IllegalArgumentException(
          "Calling decreaseKey() with given argument would not strictly decrease the key");
    }
    keys[index] = key;
    swim(inversePriorityQueue[index]);
  }

  /**
   * Increase the key associated with {@code index} to the specified value.
   *
   * @param index the index of the key to increase.
   * @param key increase the key associated with {@code index} to this key.
   *
   * @throws IndexOutOfBoundsException unless {@code 0 <= index < maximumElements}.
   * @throws IllegalArgumentException if {@code key <= getKeyFor(index)}.
   * @throws NoSuchElementException no key is associated with {@code index}.
   */
  public void increaseKey(int index, K key) {
    if (index < 0 || index >= maximumElements) {
      throw new IndexOutOfBoundsException();
    }
    if (!contains(index)) {
      throw new NoSuchElementException("Index " + index + " is not in the priority queue");
    }
    if (keys[index].compareTo(key) >= 0) {
      throw new IllegalArgumentException(
          "Calling increaseKey() with given argument would not strictly increase the key");
    }
    keys[index] = key;
    sink(inversePriorityQueue[index]);
  }

  /**
   * Remove the key associated with {@code index}.
   *
   * @param index the index of the key to remove.
   *
   * @throws IndexOutOfBoundsException unless {@code 0 <= index < maximumElements}.
   * @throws NoSuchElementException no key is associated with {@code index}.
   */
  public void remove(int index) {
    if (index < 0 || index >= maximumElements) {
      throw new IndexOutOfBoundsException();
    }

    if (!contains(index)) {
      throw new NoSuchElementException("Index " + index + " is not in the priority queue");
    }

    int inverseIndex = inversePriorityQueue[index];
    exchange(inverseIndex, numElements--);
    swim(inverseIndex);
    sink(inverseIndex);
    keys[index] = null;
    inversePriorityQueue[index] = -1;
  }

  /**
   * General helper function to determine if the key for the minimum priority queue index {@code i}
   * is greater than the key for the minimum priority queue index {@code j}.
   *
   * @param i the index to determine whether it is greater.
   * @param j the index to compare to.
   *
   * @return {@code true} if {@code i} is greater than {@code} j.
   */
  private boolean isGreaterThan(int i, int j) {
    return keys[priorityQueue[i]].compareTo(keys[priorityQueue[j]]) > 0;
  }

  /**
   * General helper function to exchange the items at {@code i} and {@code j}.
   *
   * @param i the index to exchange with {@code j}.
   * @param j the index to exchange with {@code i}.
   */
  private void exchange(int i, int j) {
    int temp = priorityQueue[i];
    priorityQueue[i] = priorityQueue[j];
    priorityQueue[j] = temp;
    inversePriorityQueue[priorityQueue[i]] = i;
    inversePriorityQueue[priorityQueue[j]] = j;
  }

  /**
   * Heap helper function to <em>swim</em> {@code index} up the heap.
   *
   * @param index the index to <em>swim</em>.
   */
  private void swim(int index) {
    while (index > 1 && isGreaterThan(index / 2, index)) {
      exchange(index, index / 2);
      index = index / 2;
    }
  }

  /**
   * Heap helper function to <em>sink</em> {@code index} down the heap.
   *
   * @param index the index to <em>sink</em>.
   */
  private void sink(int index) {
    while (2 * index <= numElements) {
      int j = 2 * index;
      if (j < numElements && isGreaterThan(j, j + 1)) {
        j++;
      }
      if (!isGreaterThan(index, j)) {
        break;
      }
      exchange(index, j);
      index = j;
    }
  }

  /**
   * Returns an iterator that iterates over the keys on the minimum priority queue in ascending
   * order. The iterator doesn't implement {@code remove()} since it's optional.
   *
   * @return an iterator that iterates over the keys in ascending order.
   */
  @Override
  public Iterator<Integer> iterator() {
    return new HeapIterator();
  }

  private class HeapIterator implements Iterator<Integer> {

    // Create a new priorityQueue.
    private IndexedPriorityQueue<K> copy;

    // Add all elements to a copy of heap.
    // Takes linear time since already in heap order so no keys move.
    public HeapIterator() {
      copy = new IndexedPriorityQueue<K>(priorityQueue.length - 1);
      for (int i = 1; i <= numElements; i++) {
        copy.offer(priorityQueue[i], keys[priorityQueue[i]]);
      }
    }

    @Override
    public boolean hasNext() {
      return !copy.isEmpty();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

    @Override
    public Integer next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return copy.pollMinimum();
    }
  }
}