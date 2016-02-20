package Percolation;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation  {

	private int[][] sites; // =1 is open, =0 if blocked(full)
	private WeightedQuickUnionUF wqu;

	public Percolation(int N)  {
		if (N < 1)  {
			throw new java.lang.IllegalArgumentException(N + " must be larger than 0");  
		}
		// create N-by-N grid, with all sites blocked
		sites = new int[N][N];
		for (int i = 0; i < N; i++)  {
			for (int j = 0; j < N; j++)  {
				sites[i][j] = 0;			
			}
		}
		// Initialize WeightedQuickUnionUF and union the top row sites to a virtual site, same with the bottom sites.
		wqu = new WeightedQuickUnionUF((int) Math.pow(N, 2)+2);
		for (int i = 1; i <= N; i++)  {
			wqu.union(i, 0);
			wqu.union( (int) Math.pow(N, 2)+1-i, (int) Math.pow(N, 2)+1);
		}
	}

	public void open(int i, int j) {
		if (i < 1 || i > sites.length || j < 1 || j > sites.length) {
			throw new IndexOutOfBoundsException("index " + i + " is not between 1 and " + sites.length);  
		}
		// open site (row i, column j) if it is not open already
		sites[i-1][j-1] = 1;
		// connect newly opened site with surrounding opened sites
		if (i > 1 && isOpen(i-1, j)) wqu.union(index(i, j), index(i-1, j)); // connect upwards
		if (i < sites.length && isOpen(i+1, j)) wqu.union(index(i, j), index(i+1, j)); // connect downwards
		if (j > 1 && isOpen(i, j-1)) wqu.union(index(i, j), index(i, j-1)); // connect leftwards
		if (j < sites.length && isOpen(i, j+1)) wqu.union(index(i, j), index(i, j+1)); // connect rightwards
	}

	public boolean isOpen(int i, int j) {
		if (i < 1 || i > sites.length || j < 1 || j > sites.length) {
			throw new IndexOutOfBoundsException("index " + i + " is not between 1 and " + sites.length);  
		}
		// is site (row i, column j) open?
		return (sites[i-1][j-1] == 1);
	}
	public boolean isFull(int i, int j) {
		if (i < 1 || i > sites.length || j < 1 || j > sites.length) {
			throw new IndexOutOfBoundsException("index " + i + " is not between 1 and " + sites.length);  
		}
		// is site (row i, column j) full?
		return ( wqu.connected(index(i, j), 0) && isOpen(i,j) );
	}

	public boolean percolates() {
		// does the system percolate?
		if (sites.length == 1) return isOpen(1,1);
		else return wqu.connected(0, (int) Math.pow(sites.length, 2)+1);
	}

	private int index(int i, int j) {
		// By convention, the row and column indices i and j are integers between 1 and N, where (1, 1) is the upper-left site
		return sites.length*(i-1)+j;
	}

	public static void main(String[] args) {
		// test client (optional)
	}

}