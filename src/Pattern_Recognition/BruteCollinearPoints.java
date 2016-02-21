package Pattern_Recognition;

/******************************************
 * Brute force. Write a program BruteCollinearPoints.java that examines 4 points at a time and checks whether 
 * they all lie on the same line segment, returning all such line segments. To check whether the 4 points 
 * p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between 
 * p and s are all equal.

 * The method segments() should include each line segment containing 4 points exactly once. If 4 points appear on 
 * a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both) 
 * and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to 
 * BruteCollinearPoints that has 5 or more collinear points.

 * @author Jinghong Yang
 *
 **************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;


public class BruteCollinearPoints {

	private final Point[] points;
	private final LineSegment[] segments;
	private int numSeg = 0;

	public BruteCollinearPoints(Point[] points) {

		if (points == null) throw new java.lang.NullPointerException();
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) throw new java.lang.NullPointerException();
		}
		this.points = points;
		checkDuplicatedEntries(points);
		
		ArrayList<LineSegment> segList = new ArrayList<>();
		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		Arrays.sort(pointsCopy);
		for (int p = 0; p < pointsCopy.length - 3; p++) {
			for (int q = p + 1; q < pointsCopy.length - 2; q++) {
				for (int r = q + 1; r < pointsCopy.length - 1; r++) {
					for (int s = r + 1; s < pointsCopy.length; s++) {
						if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
								pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
							segList.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
							numSeg++;
						}
					}
				}
			}
		}
		segments = segList.toArray(new LineSegment[segList.size()]);
	}

	public int numberOfSegments() {
		return numSeg;
	}

	public LineSegment[] segments() {
		return Arrays.copyOf(segments, numberOfSegments());
	}

	private void checkDuplicatedEntries(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) {
					throw new IllegalArgumentException("Repeated points.");
				}
			}
		}
	}


	public static void main(String[] args) {
		// unit test
		int[] x = {1, 2, 3, 4};
		int[] y = {5, 6, 7, 8};
		int N = x.length;
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			points[i] = new Point(x[i], y[i]);
		}
		
		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		int n = collinear.numberOfSegments();
		StdOut.println(n);
	}




}


