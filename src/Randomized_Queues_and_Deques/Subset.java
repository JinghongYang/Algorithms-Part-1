/*************************************************
 * This program takes a command-line integer k; 
 * reads in a sequence of N strings from standard input using StdIn.readString(); 
 * and prints out exactly k of them, uniformly at random. 
 * Each item from the sequence can be printed out at most once. 
 * We assume that 0 ≤ k ≤ N, where N is the number of string on standard input.
 * 
 * @author Jinghong Yang
 * 
 * ****************************************************************


package Randomized_Queues_and_Deques;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	
	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<>();
		int k = Integer.parseInt(args[0]);
		while (!StdIn.isEmpty()) {
			String str = StdIn.readString();
			q.enqueue(str);
		}
		for (int i = 0; i < k; i++) {
			String s = q.dequeue();
			StdOut.println(s);
		}
	}
}
