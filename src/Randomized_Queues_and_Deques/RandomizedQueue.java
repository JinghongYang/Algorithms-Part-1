/***********************************************
 * A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly 
 * at random from items in the data structure.
 * 
 * @author Jinghong Yang
 * 
 * ***********************************************************

package Randomized_Queues_and_Deques;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] s;
	private int N = 0;

	public RandomizedQueue() {
		// construct an empty randomized queue
		s = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		// is the queue empty?
		return N == 0;
	}
	public int size() {
		// return the number of items on the queue
		return N;
	}
	public void enqueue(Item item) {
		// add the item
		if (item == null) throw new java.lang.NullPointerException();
		if (N == s.length) resize(2*s.length);
		s[N++] = item;
	}
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = s[i];
		}
		s = copy;
	}
	public Item dequeue() {
		// remove and return a random item
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int n = StdRandom.uniform(N);
		Item pop = s[n];
		s[n] = s[--N];
		s[N] = null;
		if (N > 0 && N == s.length/4) resize(s.length/2);
		return pop;
	}
	public Item sample() {
		// return (but do not remove) a random item
		if (isEmpty()) throw new java.util.NoSuchElementException();
		int n = StdRandom.uniform(N);
		return s[n];
	}
	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RandomIterator();
	}
	private class RandomIterator implements Iterator<Item> {
		
        private int i = N;
        private int[] order;
        
        public RandomIterator() {
        	if (N != 0) {
        		order = new int[N];
        		for (int i = 0; i < N; i++) order[i] = i;
        		StdRandom.shuffle(order);
        	}
        		
        }
				
		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Item next() {
			if (i == 0) throw new java.util.NoSuchElementException();
			return s[order[--i]];
		}
		public void remove() {
			throw new UnsupportedOperationException("not implemented");
		}
		
	}
	public static void main(String[] args) {
		// unit testing
	}

}
