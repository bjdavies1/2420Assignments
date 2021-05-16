package a04Puzzle;

import edu.princeton.cs.algs4.Queue;

/**
 * I received help from the java tutoring center.
 * @author Brady
 *
 */
public class Board {
	int[][] blocks;
	int[][] inOrder;
	
	public Board(int[][] blocks) {
		this.blocks = new int[blocks.length][blocks.length];
		inOrder = new int[blocks.length][blocks.length];
		int slotsFilled = 1;
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j<blocks.length; j++) {
				this.blocks[i][j] = blocks[i][j];
				inOrder[i][j] = slotsFilled;
				slotsFilled++;
			}
		}
		inOrder[blocks.length - 1][blocks.length-1] = 0;
	}
	
	public int size() {
		return blocks.length;
	}
	
	public int hamming() {
		int out = 0;
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks.length; j++) {
				if(blocks[i][j] != inOrder[i][j]) {
					out++;
				}
			}
		}
		return out;
	}
	
	public int manhattan() {
		int moves = 0;
		int out = 0;
		int horizgood = 0;
		int vertgood = 0;
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks.length; j++) {
				if(blocks[i][j] != inOrder[i][j]) {
					horizgood =  ((blocks[i][j] - 1) % blocks.length);
					vertgood = ((blocks[i][j] - 1) / blocks.length);
					out += Math.abs(j - horizgood);
					out += Math.abs(i - vertgood);
					moves += out;
				}
			}
		}
		return moves;
	}
	//use if sorted
	public boolean isGoal() {
		if(hamming() == 0 || manhattan() == 0) {
			return true;
		}else {
		return false;
		}
	}
	
	public boolean isSolvable() {
		//use two private methods for finding if it is an even or odd case.
		if(!(blocks.length %2 == 0)) {
			if(isOdd(blocks)) {
				return false;
			}
		} else {
			if(isEven(blocks)) {
				return false;
			}	
		}
		
		return true;
	}
	
	private boolean isOdd(int[][] blocks2) {
		int[] sender = make1dArray(blocks2);
		if(inversions(sender) % 2 == 0) {
			return false;
		}
		return true;
	}

	private int[] make1dArray(int[][] blocks2) {
		int[] sender = new int[(blocks2.length * blocks2.length)-1];
		int slot = 0;
		for(int i = 0; i < blocks2.length; i++) {
			for(int j = 0; j < blocks2.length; j++) {
				if(blocks2[i][j] == 0) {
					
				}else {
					sender[slot] = blocks2[i][j];
					slot++;
				}
				
			}
		}
		return sender;
	}

	private int inversions(int[] sender) {
		int inversions = 0;
		for(int i = 0; i < sender.length - 1; i++) {
			for(int j = i+1; j < sender.length; j++ ) {
				if(sender[i] > sender[j]) {
				inversions++;	
				}
			}
		}
		return inversions;
	}

	private boolean isEven(int[][] blocks2) {
		if((inversions(make1dArray(blocks2)) + row(blocks2)) % 2 == 0) {
			return true;
		}
		return false;
	}

	private int row(int[][] blocks2) {
		int row = 0;
		for(int i = 0; i < blocks2.length; i++) {
			for(int j = 0; j < blocks2.length; j++) {
				if(blocks2[i][j] == 0) {
					row = i;
					break;
				}
			}
		}
		return row;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if(y instanceof Board) {
			if(blocks.length != ((Board) y).size()) {
				return false;
			}else {
				for(int i = 0; i < blocks.length; i++) {
					for(int j = 0; j < blocks.length; j++) {
						if(blocks[i][j] != ((Board) y).blocks[i][j]) {
							return false;
						}
					}
				}
				return true;
			}
		}else {
			return false;
		}
	}
	
	// all neighboring boards
	public Iterable<Board> neighbors(){
		//create an ArrayList, add 2 - 4 more boards. Return.
		Queue<Board> nei = new Queue<>();
		int[][] blockscopy = new int[blocks.length][blocks.length];
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks.length; j++) {
				blockscopy[i][j] = blocks[i][j];
			}
		}
		int[][] temp = new int[blockscopy.length][blockscopy.length];
		temp = resync(blockscopy, temp);
		int zerohoriz = 0;
		int zerovert = 0;
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks.length; j++) {
				if(blocks[i][j] == 0) {
					zerohoriz = j;
					zerovert = i;
					break;
				}
			}
		}
		while(!nei.isEmpty()) {
			nei.dequeue();
		}
		calculateNeigbors(nei, temp, zerohoriz, zerovert, blockscopy);
		return nei;
	}

	private int[][] resync(int[][] blockscopy, int[][] temp) {
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks.length; j++) {
				temp[i][j] = blockscopy[i][j];
			}
		}
		return temp;
	}
		
	private void calculateNeigbors(Queue<Board> nei, int[][] temp, int zerohoriz, int zerovert, int[][] blockscopy) {
		int holder = 0;
		if(zerohoriz + 1 < temp.length) {
			holder = temp[zerovert][zerohoriz + 1];
			temp[zerovert][zerohoriz + 1] = 0;
			temp[zerovert][zerohoriz] = holder;
			nei.enqueue(new Board(temp));
			temp = resync(blockscopy, temp);
		}
		if(zerohoriz - 1 >= 0) {
			holder = temp[zerovert][zerohoriz - 1];
			temp[zerovert][zerohoriz - 1] = 0;
			temp[zerovert][zerohoriz] = holder;
			nei.enqueue(new Board(temp));
			temp = resync(blockscopy,temp);
		}
		if(zerovert + 1 < temp.length) {
			holder = temp[zerovert + 1][zerohoriz];
			temp[zerovert + 1][zerohoriz] = 0;
			temp[zerovert][zerohoriz] = holder;
			nei.enqueue(new Board(temp));
			temp = resync(blockscopy, temp);
		}
		if(zerovert - 1 >= 0) {
			holder = temp[zerovert - 1][zerohoriz];
			temp[zerovert - 1][zerohoriz] = 0;
			temp[zerovert][zerohoriz] = holder;
			nei.enqueue(new Board(temp));			
			temp = resync(blockscopy, temp);
		}
	}
	
	public String toString() {
	    StringBuilder s = new StringBuilder();
	    s.append(blocks.length + "\n");
	    for (int i = 0; i < blocks.length; i++) {
	        for (int j = 0; j < blocks.length; j++) {
	            s.append(String.format("%2d ", blocks[i][j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}
	
	public static void main(String[] args) {
		
	}

}
