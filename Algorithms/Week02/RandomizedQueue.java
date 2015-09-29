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
  }
  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    int rand = StdRandom.uniform(qArray.length);
    while (qArray[rand] == null) rand = StdRandom.uniform(qArray.length);
    // System.out.println(Arrays.toString(qArray));
    Item item = qArray[rand];
    qArray[rand] = null;
    itemCount--;
    if (itemCount > 0 && itemCount == qArray.length/4) resize(qArray.length/2);
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
      a = qArray.length-1;
      itArrayCopy = new int[a+1];
      for (int i = 0; i < qArray.length; i++) {
        itArrayCopy[i] = i;
      }
      StdRandom.shuffle(itArrayCopy);
    }
    public void remove() { throw new UnsupportedOperationException(); }
    public boolean hasNext() { return n  > 0; }
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      while (qArray[itArrayCopy[a]] == null) a--;
      n--;
      return qArray[itArrayCopy[a--]];
    }
  }
  // helper to resize array when required
  private void resize(int capacity) {
    int check = 0;
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < itemCount; i++) {
      for (int j = check; j < qArray.length; j++) {
        if (qArray[j] != null) {
          copy[i] = qArray[j];
          check = j+1;
          break;
        }
      }
    }
    qArray = copy;
  }
  // unit testing
  public static void main(String[] args) {
// RandomizedQueue<String> rQueue = new RandomizedQueue();
// rQueue.enqueue("A");
// rQueue.enqueue("B");
// rQueue.enqueue("C");
// rQueue.enqueue("D");
// rQueue.enqueue("E");
// rQueue.enqueue("F");
// rQueue.enqueue("G");
// rQueue.enqueue("H");
// rQueue.enqueue("I");
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());
// System.out.println(rQueue.dequeue());

// System.out.println(rQueue.sample());
// Iterator iterator = rQueue.iterator();
// System.out.println(iterator.next());
// System.out.println(iterator.next());
// System.out.println(iterator.next());
// System.out.println(iterator.hasNext());


  }
}
