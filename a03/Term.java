package a03;

import java.util.Comparator;
/**
 * I received help from Alex at the Java tutor area.
 * @author Brady
 *
 */
public class Term implements Comparable<Term> {
	private final String query;
	private final double weight;
	
	public Term(String query, double weight) {
		if(query.equals(null)) {
			throw new NullPointerException();
		}
		if(weight < 0) {
			throw new IllegalArgumentException();
		}
		this.query = query;
		this.weight = weight;
	}
	
	/**
	 * Compare the terms in descending order by weight.
	 * @return
	 */
	public static Comparator<Term> byReverseWeight(){
		
		return new Reverse();
	}
	
	private static class Reverse implements Comparator<Term>{

		@Override
		public int compare(Term o1, Term o2) {
			
			return (int) (o2.weight - o1.weight);
		}
	}
	
	/**
	 * Compare the terms in lexicographic order but using only the first r characters of each query.
	 * @param r
	 * @return
	 */
	public static Comparator<Term> byPrefixOrder(int r){
		if(r < 0) {
			throw new IllegalArgumentException();
		}
		return new Pre(r);
	}
	
	private static class Pre implements Comparator<Term>{
		int end;
		private Pre(int r) {
			end = r;
		}

		@Override
		public int compare(Term o1, Term o2) {
			return o1.query.substring(0, end).compareToIgnoreCase(o2.query.substring(0, end));
		}
		
	}
	
	/**
	 * // Compare the terms in lexicographic order by query.
	 */
	@Override
	public int compareTo(Term that) {
		return this.query.compareToIgnoreCase(that.query);
	}

	@Override
	public String toString() {
		return weight + "	" + query;
	}
}
