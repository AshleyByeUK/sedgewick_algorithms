package uk.ashleybye.sedgewick.collections;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by ash on 06/05/2017.
 * From Sedgewick, page 151.
 */
public class Queue<T> implements Iterable<T> {

  private Node front;
  private Node back;
  private int nodeCount;

  private class Node {

    T item;
    Node next;
  }

  public boolean isEmpty() {
    return front == null;
  }

  public int size() {
    return nodeCount;
  }

  public void enqueue(T item) {
    Node temp = back;
    back = new Node();
    back.item = item;
    back.next = null;

    if (isEmpty()) {
      front = back;
    } else {
      temp.next = back;
    }

    nodeCount++;
  }

  public T dequeue() {
    T item = front.item;
    front = front.next;

    if (isEmpty()) {
      back = null;
    }

    nodeCount--;

    return item;
  }

  @Override
  public Iterator<T> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<T> {

    private Node current = front;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      T item = current.item;
      current = current.next;
      return item;
    }

    @Override
    public void remove() {
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Queue<String> queue = new Queue<>();

    while (scanner.hasNext()) {
      String item = scanner.next();
      if (!item.equals("-")) {
        queue.enqueue(item);
      } else if (!queue.isEmpty()) {
        System.out.print(queue.dequeue() + " ");
      }
    }

    System.out.println("(" + queue.size() + " left in queue)");
  }
}
