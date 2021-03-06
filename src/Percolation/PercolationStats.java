/****************************************************************
 * Run Monte Carlo simulation to estimate the percolation threshold, consider the following 
 * computational experiment:
 * 
 * 1. Initialize all sites to be blocked.
 * 2. Repeat the following until the system percolates:
 *  - Choose a site (row i, column j) uniformly at random among all blocked sites.
 *  - Open the site (row i, column j).
 * 
 * The fraction of sites that are opened when the system percolates provides an estimate 
 * of the percolation threshold. For example, if sites are opened in a 20-by-20 lattice 
 * according to the snapshots below, then our estimate of the percolation threshold is 
 * 204/400 = 0.51 because the system percolates when the 204th site is opened.
 * 
 * @author Jinghong Yang
 * 
 * **********************************************************

package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
	
	private double[] threshold;
	
	public PercolationStats(int N, int T) {
		if ( N <= 0 || T <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		// perform T independent experiments on an N-by-N grid
		threshold = new double[T];
		int numSites = (int) Math.pow(N, 2);
		int[] sites = new int[numSites];
		for (int i = 0; i < numSites; i++) sites[i] = i;
		
		for (int t = 0; t < T; t++) {
			int count = 0;
			Percolation per = new Percolation(N);
			StdRandom.shuffle(sites);
			while (!per.percolates()) {
				int i = indr(sites[count], N);
				int j = indc(sites[count], N);
				per.open(i, j);
				count++;
			}
			threshold[t] = (double) count/numSites;
		}
	}
	
	private int indr(int i, int n)  {
		return i/n+1;
	}
	private int indc(int i, int n)  {
		return i % n + 1;
	}
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(threshold);
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(threshold);
	}
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return mean() - 1.96*stddev()/Math.sqrt(threshold.length);
	}
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + 1.96*stddev()/Math.sqrt(threshold.length);
	}

	public static void main(String[] args) {
		// test client (described below)
		int N = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats per = new PercolationStats(N, T);
		StdOut.println("mean = " + per.mean());
		StdOut.println("stddev = " + per.stddev());
		StdOut.println("95% confidence interval = " + per.confidenceLo() + "," + per.confidenceHi());
	}

}
