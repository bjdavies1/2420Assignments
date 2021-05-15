/**
 * Pair programming for this assignment
 * @author William 'Scott' Garcia
 * @author Brady 
 *
 */

package a01;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{

	private int N;
	private int T;
	private double[] threshold;
	
	private Percolation percolation;
	
	/**
	 * 
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T)
	{
		if (N <= 0 || T <= 0) throw new IllegalArgumentException("N and T must be greater than 0");
		
		this.N = N;
		this.T = T;
		
		this.threshold = new double[T];
		
		// run experiments
		for (int i = 0; i < T; i++)
		{
			int sitesOpened = 0; // counter for number of sites opened
			
			this.percolation = new Percolation(N);
			while(!percolation.percolates())
			{
				int r = StdRandom.uniform(N) + 1;
				int c = StdRandom.uniform(N) + 1;
				if (!percolation.isOpen(r, c))
				{
					percolation.open(r, c);
					sitesOpened++;
				}
			}
			threshold[i] = (double)sitesOpened / (double)(N * N); // sets the percolation threshold
		}
	}
	
	/**
	 * 
	 * @return sample mean for the percolation threshold
	 */
	public double mean()
	{
		return StdStats.mean(threshold);
	}
	
	/**
	 * 
	 * @return standard deviation for percolation threshold
	 */
	public double stddev()
	{
		return StdStats.stddev(threshold);
	}
	
	/**
	 * 
	 * @return low confidence level threshold for percolation
	 */
	public double confidenceLow()
	{
		return StdStats.mean(threshold) - 1.96 * StdStats.stddev(threshold) / Math.sqrt(T);
	}
	
	/**
	 * 
	 * @return high confidence level threshold for percolation
	 */
	public double confidenceHigh()
	{
		return StdStats.mean(threshold) + 1.96 * StdStats.stddev(threshold) / Math.sqrt(T);
	}
	
	// Test method for PercolationStats
//	public static void main(String[] args)
//	{
//		int n = 200;
//		int t = 300;
//		
//		PercolationStats stats = new PercolationStats(n, t);
//		
//		StdOut.print("Mean: " + stats.mean());
//		StdOut.println();
//		StdOut.print("Standard deviation: " + stats.stddev());
//		StdOut.println();
//		StdOut.print("Low confidence: " + stats.confidenceLow());
//		StdOut.println();
//		StdOut.print("High confidence: " + stats.confidenceHigh());
//	}
}
