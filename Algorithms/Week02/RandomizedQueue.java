import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/*------------------------------------------------------
 *  Author:        Vincent Bundage
 *  Written:       9/23/2015
 *  Last updated:  9/28/2015
 *
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *
 *  Creates a randomized queue that randomizes
 *  dequeue returns.
 *
 *------------------------------------------------------*/

public class RandomizedQueue<Item> implements Iterable<Item> {
  private int itemCount;
  private Item[] qArray;
  private int end;

  // construct an empty randomized queue
  public RandomizedQueue() {
    qArray = (Item[]) new Object[1];
  }
  // is the queue empty?
  public boolean isEmpty() {
    return itemCount == 0;
  }
  // return the number of items on the queue
  public int size() {
    return itemCount;
  }
  // add the item
  public void enqueue(Item item) {
    if (item == null) throw new NullPointerException();
    if (itemCount == qArray.length) resize(2*qArray.length);
    qArray[itemCount++] = item;
    end = itemCount-1;
  }
  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    int rand = StdRandom.uniform(qArray.length);
    while (qArray[rand] == null) {
      rand = StdRandom.uniform(qArray.length);
    }
    // System.out.println(Arrays.toString(qArray));
    Item item = qArray[rand];
    qArray[rand] = null;
    itemCount--;
    end = itemCount;
    swap(rand, end);
    if (itemCount > 0 && itemCount == qArray.length/4) resize(qArray.length/2);
    if (isEmpty()) qArray = (Item[]) new Object[1];
    return item;
  }
  // return (but do not remove) a random item
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();
    int rand = StdRandom.uniform(itemCount);
    return qArray[rand];
  }
  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandQueueIterator();
  }
  private class RandQueueIterator implements Iterator<Item> {
    private int[] itArrayCopy;
    private int n;
    private int a;

    public RandQueueIterator() {
      n = itemCount;
      a = end;
      itArrayCopy = new int[a+1];
      for (int i = 0; i <= a; i++) {
        itArrayCopy[i] = i;
      }
      StdRandom.shuffle(itArrayCopy);
    }
    public void remove() { throw new UnsupportedOperationException(); }
    public boolean hasNext() { return n  > 0; }
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      n--;
      return qArray[itArrayCopy[a--]];
    }
  }
  // helper to resize array when required
  private void resize(int capacity) {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < itemCount; i++) {

      copy[i] = qArray[i];
    }
    qArray = copy;
  }
  private void swap(int x, int y) {
    Item temp = qArray[x];
    qArray[x] = qArray[y];
    qArray[y] = temp;
  }
  // unit testing
  public static void main(String[] args) {
  // RandomizedQueue<String> rQueue = new RandomizedQueue();
  // rQueue.enqueue("A");
  // rQueue.dequeue();
  // rQueue.enqueue("B");
  // rQueue.enqueue("C");
  // rQueue.dequeue();
  // rQueue.enqueue("D");
  // rQueue.enqueue("E");
  // rQueue.enqueue("F");
  // rQueue.enqueue("G");
  // rQueue.enqueue("H");
  // rQueue.dequeue();
  // rQueue.enqueue("I");
  //
  // Iterator iterator = rQueue.iterator();
  // System.out.println(iterator.next());
  // System.out.println(iterator.next());
  // System.out.println(iterator.next());
  // System.out.println(iterator.hasNext());


  }
}
