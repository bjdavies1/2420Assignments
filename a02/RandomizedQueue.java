package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
/**
 *  I got a good bit of help from the Java Lab, mainly Alex.
 * @author Brady
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	Item[] holder;
	private int occupiedSlots = 0;
	
	public RandomizedQueue() {
		holder = (Item[]) new Object[10];
	}
	
	public boolean isEmpty() {
		if(occupiedSlots == 0) {
			return true;
		}
		return false;
	 }
	
	public int size() {
		return occupiedSlots;
	}
	
	public void enqueue(Item item) {
		nullItemCheck(item);
		if(occupiedSlots == holder.length) {
			holder = largerArray(holder);
		}
		occupiedSlots++;
		holder[occupiedSlots] = item;
		StdRandom.shuffle(holder, 0, occupiedSlots);
	}

	private Item[] largerArray(Item[] holder2) {
		Item[] temp = (Item[]) new Object[holder.length * 2];
		for(int i = 0; i < occupiedSlots; i++) {
			temp[i] = holder2[i];
		}
		return temp;
	}
	
	public Item dequeue() {
		occupiedSlots--;
		Item returner = holder[occupiedSlots];
		holder[occupiedSlots] = null;
		if(occupiedSlots == holder.length/4){
			holder = smallerArray(holder);
	}
		StdRandom.shuffle(holder, 0, occupiedSlots);
		return returner;
	}
	
	private Item[] smallerArray(Item[] holder2) {
		Item[] temp = (Item[]) new Object[holder.length/4];
		for(int i = 0; i < occupiedSlots; i++) {
			temp[i] = holder2[i];
		}
		return temp;
	}

	public Item sample() {
		StdRandom.shuffle(holder, 0, occupiedSlots);
		Item returner = holder[0];
		return returner;
	}

	@Override
	public Iterator<Item> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<Item>{
		int current = occupiedSlots - 1;
		@Override
		public boolean hasNext() {
			if(holder[current + 1].equals(null)) {
				return false;
			}
			return true;
		}

		@Override
		public Item next() {
			if(hasNext()) {
				current++;
			}else {
				throw new NoSuchElementException();
			}
			return holder[current];
		}
	}
	
	private void nullItemCheck(Item item) {
		if(item.equals(null)) {
			 throw new NullPointerException();
		 }
	}
	
	public static void main(String[] args) {
		RandomizedQueue<String> test = new RandomizedQueue<String>();
		test.enqueue("hello");
		test.enqueue("goodbye");
		test.enqueue("hi");
		test.enqueue("bye");
		test.enqueue("middle?");
		System.out.println(test.sample());
//		for(int i = 0; i < 7; i++) {
//			test.enqueue("hi" + i);
//		}
//		for(int i = test.occupiedSlots; i > 0; i--) {
//			System.out.println(test.dequeue());
//			System.out.println(test.occupiedSlots);
//		}
		System.out.println(test.dequeue());
		System.out.println(test.dequeue());

		System.out.println(test.dequeue());//I don't know what's going on to cause this to not work fully, the randomizer is supposed to only go to 
		System.out.println(test.dequeue());//the final occupied slot. It may have something to do with I made my own, and it may have been
		//provided, I'm not sure.

	}
}