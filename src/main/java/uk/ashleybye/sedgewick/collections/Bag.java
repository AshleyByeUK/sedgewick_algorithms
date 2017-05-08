package uk.ashleybye.sedgewick.collections;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by ash on 06/05/2017.
 * From Sedgewick, page 155.
 */
public class Bag<T> implements Iterable<T> {

  private Node first;   // First node in the list.

  private class Node {

    T item;
    Node next;
  }

  public void add(T item) {
    Node temp = first;
    first = new Node();
    first.item = item;
    first.next = temp;
  }

  @Override
  public Iterator<T> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<T> {

    private Node current = first;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      T temp = current.item;
      current = current.next;
      return temp;
    }

    @Override
    public void remove() {
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Bag<String> bag = new Bag<>();

    while (scanner.hasNext()) {
      String item = scanner.next();
      if (!item.equals("-")) {
        bag.add(item);
      }
    }

    for (String item : bag) {
      System.out.print(item + " ");
    }
    System.out.println();
  }
}
