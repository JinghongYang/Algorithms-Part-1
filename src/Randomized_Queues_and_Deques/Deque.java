package Randomized_Queues_and_Deques;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int siz = 0;
	
	private class Node {
		private Item item;
		private Node prev;
		private Node next;
	}

	public Deque() {
		// construct an empty deque
		this.first = null;
		this.last = null;
	}

	public boolean isEmpty() {
		// is the deque empty?
		return siz == 0;
	}

	public int size() {
		// return the number of items on the deque
		return siz;
	}

	public void addFirst(Item item) {
		if (item == null) throw new java.lang.NullPointerException();
		// add the item to the front
		if (isEmpty()) {
			first = new Node();
			first.item = item;
			first.prev = null;
			first.next = null;
			last = first;
		}
		else {
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.prev = null;
			first.next = oldfirst;
			oldfirst.prev = first;
		}		
		siz++;
	}
	public void addLast(Item item) {
		if (item == null) throw new java.lang.NullPointerException();
		// add the item to the end
		if (isEmpty()) {
			addFirst(item);
		}
		else {
			Node oldlast = last;
			last = new Node();
			last.item = item;
			last.next = null;
			last.prev = oldlast;
			oldlast.next = last;
			siz++;
		}

	}
	public Item removeFirst() {
		if (siz == 0) throw new java.util.NoSuchElementException();
		// remove and return the item from the front
		Item item = first.item;
		first = first.next;
//		first.prev = null;
		siz--;
		
		if (isEmpty()) {
			last = null;
		}
		return item;
	}
	public Item removeLast() {
		if (siz == 0) throw new java.util.NoSuchElementException();
		// remove and return the item from the end
		Item item = last.item;
		last = last.prev;
		siz--;
		if (!isEmpty()) last.next = null;
		if (isEmpty()) first = null;
		return item;
	}

	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DqIterator();
	}

	private class DqIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (current == null) throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		// unit testing
		Deque<Integer> q = new Deque<>();
		q.addFirst(1);
		q.addLast(2);
		q.removeLast();
		for (int i : q) StdOut.println(i);
	}











}