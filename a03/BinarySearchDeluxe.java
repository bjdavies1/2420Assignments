package a03;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.RedBlackBST;
/**
 * I have received help from the Java tutor center, and the professor here.
 * @author Brady
 *
 */
public class BinarySearchDeluxe {
	/**
	 * Return the index of the first key in a[] that equals the search key, or -1 if no such key.
	 * @param a
	 * @param key
	 * @param comparator
	 * @return
	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a.equals(null)|| key.equals(null) || comparator.equals(null)) {
			throw new NullPointerException();// I tried to extract the method, but it kept throwing errors.
		}
		Arrays.sort(a, comparator);//not going to take that it came sorted by default
		int upper = a.length - 1;
		int lower = 0;
		int current = ((a.length - 1)/2);
		while(!(upper < lower)) {
			if(comparator.compare(a[current], key) > 0) {
				upper = current - 1;//We already know that current isn't it, so shorten it by one.
				current = current / 2;
			} else if(comparator.compare(a[current], key) < 0) {
				lower = current + 1;//Same thinking as with upper three lines above, but with a lower bound.
				current = current + (lower/2);
			}
			else if(comparator.compare(a[current], key) == 0) {
				upper = current;
				current = ((upper - lower) / 2) + lower;
				if(upper == lower) {//exit condition, which should happen once we find the first  of what we are looking for.
					return upper;
				}
			}
		}
		return -1;
	}
	/**
	 * Return the index of the last key in a[] that equals the search key, or -1 if no such key.
	 * @param a
	 * @param key
	 * @param comparator
	 * @return
	 */
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a.equals(null)|| key.equals(null) || comparator.equals(null)) {
			throw new NullPointerException();
		}
		Arrays.sort(a, comparator);//not going to take that it came sorted by default
		int upper = a.length - 1;
		int lower = 0;
		int current = ((a.length - 1)/2);
		while(!(upper < lower)) {
			if(comparator.compare(a[current], key) > 0) {
				upper = current - 1;//We already know that current isn't it, so shorten it by one.
				current = (int) Math.ceil(current / 2);
			} else if(comparator.compare(a[current], key) < 0) {
				lower = current + 1;//Same thinking as with upper three lines above, but with a lower bound.
				current = (int) Math.ceil(current + (lower/2));
			}
			else if(comparator.compare(a[current], key) == 0) {
				lower = current;
				current = (int) Math.ceil(((upper - lower) / 2)) + lower;
				if(upper == lower) {//exit condition, which should happen once we find the last  of what we are looking for.
					return upper;
				}
			}
		}
		return -1;
	}
	
}
