package a04Puzzle;

public class SearchNode implements Comparable<SearchNode> {
	Board here;
	int moves;
	int order;
	SearchNode last;
	
	
	public SearchNode(Board current, int moves, SearchNode last) {
		here = current;
		this.moves = moves + 1;
		order = current.manhattan() + moves;
//		order = current.hamming() +1 moves;
		this.last = last;
	}

	@Override
	public int compareTo(SearchNode arg0) {
		
		return this.getOrder() - arg0.getOrder();
	}

	public Board getHere() {
		return here;
	}

	public int getMoves() {
		return moves;
	}

	public int getOrder() {
		return order;
	}

	public SearchNode getLast() {
		return last;
	}

}
