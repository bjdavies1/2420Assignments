package a05KDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
//Only use Point2D(Double x Double y), public double x(), public double y(),
//public  double distanceSquaredTo(Point2D that),  public     int compareTo(Point2D that)
//public boolean equals(Object that), public  String toString()
import edu.princeton.cs.algs4.RectHV;
//public    RectHV(double xmin, double ymin, double xmax, double ymax), public  double xmin(),
//public  double ymin(), public  double xmax(), public  double ymax(), contains(Point2D p),
//intersects(RectHV that), distanceSquaredTo(Point2D p), equals(Object that), and toString().
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<Value> {
	private Value val;
	RedBlackBST<Point2D, Value> tree;
	public PointST() {
		tree = new RedBlackBST<>();
	}
	
	public boolean isEmpty() {
		return tree.isEmpty();
	}
	
	public int size() {
		return tree.size();
	}
	
	public void put(Point2D p, Value val) {
		if(p.equals(null) || val.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		tree.put(p, val);
	}
	
	public Value get(Point2D p) {
		if(p.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		return tree.get(p);
	}
	
	public boolean contains(Point2D p) {
		if(p.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		return tree.contains(p);
	}
	
	public Iterable<Point2D> points(){
		
		return tree.keys();
	}
	
	public Iterable<Point2D> range(RectHV rect){
		if(rect.equals(null)) {
			throw new java.lang.NullPointerException();
		}//check if x and y's are in range of rect.
		Queue<Point2D> sender = new Queue<>();
		for(int i = 0; i < tree.size(); i++) {
			Point2D spot = tree.select(i);
			if(spot.x() < rect.xmax() && spot.x() > rect.xmin() && spot.y() > rect.ymin() && spot.y() < rect.ymax()) {
				sender.enqueue(spot);
			}
		}
		return sender;
	}
	
	public Point2D nearest(Point2D p) {
		if(p.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		Point2D sender;
		Point2D higher = tree.ceiling(p);
		Point2D lower = tree.floor(p);
		if(lower == null || p.distanceSquaredTo(higher) < p.distanceSquaredTo(lower)) {
			sender = higher;
		}else {
			sender = lower;
		}
		return sender;	
	}
	
	public static void main(String [] args) {
		
	}

}
