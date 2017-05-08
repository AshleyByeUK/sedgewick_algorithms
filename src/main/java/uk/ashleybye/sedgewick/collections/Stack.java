package uk.ashleybye.sedgewick.collections;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by ash on 06/05/2017.
 * From Sedgewick, page 149.
 */
public class Stack<T> implements Iterable<T> {

  private Node top;     // Top of the stack (most recently added).
  private int nodeCount;

  private class Node {

    T item;
    Node next;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public int size() {
    return nodeCount;
  }

  public void push(T item) {
    Node temp = top;
    top = new Node();
    top.item = item;
    top.next = temp;
    nodeCount++;
  }

  public T pop() {
    T temp = top.item;
    top = top.next;
    nodeCount--;

    return temp;
  }

  @Override
  public Iterator<T> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<T> {

    private Node current = top;

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
    Stack<String> stack = new Stack<>();

    while (scanner.hasNext()) {
      String item = scanner.next();
      if (!item.equals("-")) {
        stack.push(item);
      } else if (!stack.isEmpty()) {
        System.out.print(stack.pop() + " ");
      }
    }

    System.out.println("(" + stack.size() + " left on stack)");
  }
}
