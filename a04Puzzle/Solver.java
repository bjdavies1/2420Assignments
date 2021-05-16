package a04Puzzle;

import java.util.ArrayList;
import java.util.Iterator;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
/**
 * I received help from the java tutoring center.
 * @author Brady
 *
 */
public class Solver {
	private int moves = 0;
	Stack<Board> solution = new Stack<>();
	
	public Solver(Board initial) {
		if(!initial.isSolvable()) {
			throw new IllegalArgumentException(); 
		}
		if(initial.equals(null)) {
			throw new NullPointerException();
		}
		MinPQ<SearchNode> hamming = new MinPQ<>();
		SearchNode start = new SearchNode(initial, 0, null);
		hamming.insert(start);
		
		
		SearchNode present = hamming.delMin();
		ArrayList<Board> helper = new ArrayList<>();
		helper.add(present.getHere());
		Iterator<Board> through = initial.neighbors().iterator();
		while(through.hasNext()) {
			hamming.insert(new SearchNode(through.next(),present.getMoves(), start));
		}
				//make own puzzle real quick. slide 8 block.
		
		do  {
			present = hamming.delMin();
			through = present.getHere().neighbors().iterator();
			while(through.hasNext()) {
				Board next = through.next();
				if(helper.contains(next)) {
					
				}else {
					hamming.insert(new SearchNode(next, present.getMoves(), present));
					helper.add(next);
				}
			}
			
		}while(!present.getHere().isGoal());
		
		SearchNode last = present;
		while(last != null) {
			solution.push(last.getHere());
			last = last.getLast();
		}
		moves = solution.size() - 1;
		while(!solution.isEmpty()) {
			System.out.println(solution.pop());
		}
	}
	
	public int moves() {
		
		return moves;
	}
	
	public Iterable<Board> solution(){
		return solution;
	}
	
	public static void main(String[] args) {
		// create initial board from file
	    In in = new In("C:\\Users\\Brady\\eclipse-workspace\\2420 Assignments\\src\\a04Puzzle\\puzzle30.txt");
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            blocks[i][j] = in.readInt();
	        }
	    }
	    Board initial = new Board(blocks);

	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	        Solver solver = new Solver(initial);
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }

	    // if not, report unsolvable
	    else {
	        StdOut.println("Unsolvable puzzle");
	    }
	}

}
