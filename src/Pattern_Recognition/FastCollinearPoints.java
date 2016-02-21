package Pattern_Recognition;
/******************************************
 * FastCollinear algorithm

 * A faster, sorting-based solution. Remarkably, it is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

 * Think of p as the origin.
 * For each other point q, determine the slope it makes with p.
 * Sort the points according to the slopes they makes with p.
 * Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.

 * @author Jinghong Yang
 *
 **************************************************/


import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class FastCollinearPoints {

	private final Point[] points;
	private final LineSegment[] segments;
	private int numSeg = 0;

	public FastCollinearPoints(Point[] points) {

		if (points == null) throw new java.lang.NullPointerException();
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) throw new java.lang.NullPointerException();
		}
		this.points = points;
		checkDuplicatedEntries(points);


		ArrayList<LineSegment> segList = new ArrayList<>();
		int count = 0;
		Point[] pointsCopy = Arrays.copyOf(points, points.length);
		Arrays.sort(pointsCopy);
		for (int p = 0; p < points.length; p++) {
			Point[] pointsp = Arrays.copyOf(pointsCopy, pointsCopy.length);
			Arrays.sort(pointsp, points[p].slopeOrder());
			for (int q = 1; q < pointsp.length - 1; q++) {
				if (pointsp[0].slopeTo(pointsp[q]) == pointsp[0].slopeTo(pointsp[q+1])) {
					count++;
				}
				else if (count >= 2 && pointsp[0].compareTo(pointsp[q-count]) < 0) {
					segList.add(new LineSegment(pointsp[0], pointsp[q]));
					numSeg++;
					count = 0;
				}
				else count = 0;
			}
			if (count >= 2 && pointsp[0].compareTo(pointsp[pointsp.length - count - 1]) < 0) {
				segList.add(new LineSegment(pointsp[0], pointsp[pointsp.length - 1]));
				numSeg++;
				count = 0;
			}
			else count = 0;
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

		// read the N points from a file
		In in = new In(input48.txt);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.show(0);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
	}



}
