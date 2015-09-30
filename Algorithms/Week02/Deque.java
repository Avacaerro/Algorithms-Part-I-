import java.util.NoSuchElementException;
import java.util.Iterator;

/*------------------------------------------------------
 *  Author:        Vincent Bundage
 *  Written:       9/22/2015
 *  Last updated:  9/29/2015
 *
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque
 *
 *  Creates a double ended queue or deque, that supports
 *  adding and removing items from the front or back of
 *  a linked list.
 *------------------------------------------------------*/
public class Deque<Item> implements Iterable<Item> {
  private int itemCount;
  private Node first, last;

  // list item helper
  private class Node {
    private Item item;
    private Node next, previous;
  }
  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
  }
  // is the deque empty?
  public boolean isEmpty() {
    return itemCount == 0;
  }
  // return the number of items on the deque
  public int size() {
    return itemCount;
  }
  // add the item to the front
  public void addFirst(Item item) {
    nullCheck(item);
    if (first == null) {
      first = new Node();
      itemCount++;
      first.item = item;
      last = first;
    } else {
      Node oldFirst = first;
      first = new Node();
      itemCount++;
      first.item = item;
      first.next = oldFirst;
      if (last == null) last = first;
      oldFirst.previous = first;
    }
  }
  // add the item to the end
  public void addLast(Item item) {
    nullCheck(item);
    if (last == null) {
      last = new Node();
      itemCount++;
      last.item = item;
      first = last;
    } else {
      Node oldLast = last;
      last = new Node();
      itemCount++;
      last.item = item;
      oldLast.next = last;
      if (first == null) first = last;
      last.previous = oldLast;
    }
  }
  // remove and return the item from the front
  public Item removeFirst() {
    noElementsCheck();
    Item item = first.item;
    first = first.next;
    itemCount--;
    if (isEmpty()) resetToNull();
    return item;
  }
  // remove and return the item from the end
  public Item removeLast() {
    noElementsCheck();
    Item item = last.item;
    last = last.previous;
    itemCount--;
    if (isEmpty()) resetToNull();
    return item;
  }
  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }
  private class DequeIterator implements Iterator<Item> {
    private Node current;

    public DequeIterator() {
      current = first;
    }
    public void remove() { throw new UnsupportedOperationException(); }
    public boolean hasNext() { return current != null; }
    public Item next() {
      if (current == null) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }
  // helper function for null checking
  private void nullCheck(Item item) {
    if (item == null) throw new NullPointerException();
  }
  // helper function for checking an empty structure
  private void noElementsCheck() {
    if (isEmpty()) throw new NoSuchElementException();
  }
  // helper function to reset nodes to null on an empty deque
  // should fix loitering
  private void resetToNull() {
    first = null;
    last = null;
  }
  // unit testing
  public static void main(String[] args) {
   Deque<Integer> deque = new Deque();
   deque.addLast(1);
   deque.addFirst(2);
   deque.removeLast();
   System.out.println(deque.size());
// System.out.println(deque.removeFirst());
// System.out.println(deque.removeFirst());
// System.out.println(deque.removeFirst());
// System.out.println(deque.removeFirst());
// System.out.println(deque.removeLast());
// System.out.println(deque.removeLast());
// System.out.println(deque.removeLast());

    // Iterator iterator = deque.iterator();
    // Iterator iterator2 = deque.iterator();
    // for (int i : deque) {
    //   for (int j : deque) {
    //     System.out.println(iterator.next());
    //     System.out.println(iterator2.next());
    //   }
    // }

  }
}
