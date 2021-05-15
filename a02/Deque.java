package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * I got a good bit of help from the Java Lab, mainly Alex.
 * @author Brady
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
	
	Item[] holder;
	private int spacesTaken = 0;
	private int head = 0;
	private int tail = 1;
	public Deque() {
		holder = (Item[]) new Object[10]; 
	}
	
	 public boolean isEmpty() {
		 if(spacesTaken != 0) {
			 return false;
		 }
		 return true;
	 }
	 
	 public int size() {
		 
		 return spacesTaken;
	 }
	 
	 public void addFirst(Item item) {
		 nullItemCheck(item);
		 if(spacesTaken == holder.length) {
				holder = largerArray(holder);
			}
		 
		 holder[head] = item;
		 head--;
		 enlargeCheck();
		 spacesTaken++;
	 }
	 
	 public void addLast(Item item) {
		 nullItemCheck(item);
		 if(spacesTaken == holder.length) {
				holder = largerArray(holder);
			}
		 holder[tail] = item;
		 tail++;
		 enlargeCheck();
		 
		 spacesTaken++;
	 }

	private void enlargeCheck() {
		if(tail > holder.length - 1) {
			 tail = tail % (holder.length);
		 }
	}
	 private Item[] largerArray(Item[] holder2) {
			Item[] temp = (Item[]) new Object[holder.length * 2];
			for(int i = 0; i < spacesTaken; i++) {
				temp[i] = holder2[i];
			}
			tail = spacesTaken;
			head = temp.length - 1;
			return temp;
		}
	 
	 public Item removeFirst() {
		 checkIfEmpty();
		 spacesTaken--;
		 shrinkCheck();
		 Item going = holder[head];
		 holder[head] = null;
		 head++;
		 shrinkCheck();
		 return going;
	 }

	private void checkIfEmpty() {
		if(isEmpty()) {
			 throw new NoSuchElementException();
		 }
	}
	 
	 public Item removeLast() {
		 checkIfEmpty();
		 Item returner = holder[tail];
		 holder[tail] = null;
		 tail--;
		 if(tail < 0) {
			 tail = holder.length - 1;
		 }
		 spacesTaken--;
		 shrinkCheck();
		 return returner;
	 }

	private void shrinkCheck() {
		if(spacesTaken == holder.length/4){
				holder = smallerArray(holder);
		 }
	}
	 
	 private Item[] smallerArray(Item[] holder2) {
			Item[] temp = (Item[]) new Object[holder.length/4];
			for(int i = 0; i < spacesTaken; i++) {
				temp[i] = holder2[i];
			}
			tail = spacesTaken;
			head = temp.length - 1;
			return temp;
	 }

	 
	 private void nullItemCheck(Item item) {
			if(item.equals(null)) {
				 throw new NullPointerException();
			 }
		}
		
	@Override
	public Iterator<Item> iterator() {	
		return new MyIterator();
	}
	
	public static void main(String[] args) {
		 Deque<String> test = new Deque<String>();
		 for(int i = 0; i<10; i++) {
			 test.addFirst("Hi");
		 }
		 for(int i = 0; i<8; i++) {
		 	System.out.println(test.removeLast());
		 }
		 for(int i = 0; i<15; i++) {
			 test.addFirst("Hi");
		 }
		 for(int i = 0; i<9; i++) {
			 	System.out.println(test.removeFirst());
			 }
		 System.out.println(test.size());
	 }
	
	private class MyIterator implements Iterator<Item>{
		int current = head;
		@Override
		public boolean hasNext() {
			if(current + 1 != tail) {
				return true;
			}else {
				return false;
			}
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
}
