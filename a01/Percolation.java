package a01;
import java.lang.IndexOutOfBoundsException;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * I received help from a Java Tutor and worked with William Garcia on this assignment. I worked on this class, 
 * and he worked on PercolationStats.
 * @author Brady
 * @author William 'Scott' Garcia
 */
public class Percolation {
	boolean[][] perlocates;  
	WeightedQuickUnionUF helper;
	int size;
	int upper;
	int lower;
	
	public Percolation(int N) {
		if(N <=0) {
			throw new IllegalArgumentException("Number has to be greater than zero");
		}
		perlocates = new boolean[N][N];
		for(int i = 0; i < N ; i++) {
			for(int j = 0; j < N; j++) {
				perlocates[i][j] = false;
			}
		}
		size = N*N +2;
		upper = size - 1;
		lower = size-2;
		helper = new WeightedQuickUnionUF(size);
		for(int i = 0; i < N; i++) {
			helper.union(xyTo1d(0, i),upper);
			helper.union(xyTo1d(N-1, i),lower);
		}
	}
	/**
	 * Opens slot and connects any adjacent slots that are open as well.
	 * @param i
	 * @param j
	 */
	public void open(int i, int j) {
		boundCheck(i,j);
		perlocates[i][j] = true;
		
		if(i != 0) {
			checkUp(i,j);
		}
		if(i != perlocates.length - 1) {
			checkDown(i,j);
		}
		if(j != 0) {
			checkLeft(i,j);
		}
		if(j != perlocates.length - 1) {
			checkRight(i,j);
		}
	}
	
	private void checkLeft(int i, int j) {
		if(isOpen(i,j-1)) {
			helper.union(xyTo1d(i,j-1),xyTo1d(i,j) );
		}
	}

	private void checkRight(int i, int j) {
		if(isOpen(i,j+1)) {
			helper.union(xyTo1d(i,j+1),xyTo1d(i,j) );
		}
	}

	private void checkDown(int i, int j) {
		if(isOpen(i+1,j)) {
			helper.union(xyTo1d(i+1,j),xyTo1d(i,j) );
		}
	}

	private void checkUp(int i, int j) {
		if(isOpen(i-1,j)) {
			helper.union(xyTo1d(i-1,j),xyTo1d(i,j) );
		}
	}
	/**
	 * Checks if the slot in question is open.
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		boundCheck(i,j);
		return perlocates[i][j];
	}
	/**
	 * Sees if there is a connection to the top slot.
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) {//If it is open and can be connected to an open site on the top row.
		boundCheck(i,j);
		return helper.connected(xyTo1d(i,j), upper);
	}
	/**
	 * Returns true if the top and bottom are connected.
	 * @return
	 */
	public boolean percolates() {
	return helper.connected(upper,  lower);
	}
	
	private void boundCheck(int i, int j) {
		if (i < 0 || j < 0 || i > perlocates.length - 1 || j > perlocates.length - 1) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private int xyTo1d(int i, int j) {
		return i * perlocates.length + j;
	}
}
