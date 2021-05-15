package a03;

import java.util.ArrayList;

/**
 * I received help from Alex at the Java tutor area.
 * @author Brady
 *
 */
public class Autocomplete {
	

	private ArrayList<Term> terms = new ArrayList<>();
	public Autocomplete(Term[]terms) {
		for(int i = 0; i < terms.length; i++) {
			this.terms.add(terms[i]);
		}
	}
	/**
	 * Return all terms that start with the given prefix, in descending order of weight.
	 * @param prefix
	 * @return
	 */
	public Term[] allMatches(String prefix) {
		ArrayList<Term> b = new ArrayList<>();
//		private Term[] holder = new Term[10];//ask if we are allowed to use ArrayList
		int start = BinarySearchDeluxe.firstIndexOf(terms.toArray(new Term[terms.size()]), new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int end = BinarySearchDeluxe.lastIndexOf(terms.toArray(new Term[terms.size()]), new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		for(int i = start; i <= end; i++) {
			b.add(terms.get(i));
		}
		return (Term[]) b.toArray();
	}
	/**
	 * Return the number of terms that start with the given prefix.
	 * @param prefix
	 * @return
	 */
	public int numberOfMatches(String prefix) {
		int matches = BinarySearchDeluxe.lastIndexOf(terms.toArray(new Term[terms.size()]), new Term(prefix, 0), Term.byPrefixOrder(prefix.length()))
				- BinarySearchDeluxe.firstIndexOf(terms.toArray(new Term[terms.size()]), new Term(prefix, 0), Term.byPrefixOrder(prefix.length()))
				+ 1;
		return matches;//I feel like I could just not use a variable, but I feel better using the variable to 
		//store it, even if it is just for a little bit.
	}
}
