package a05KDTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
//Only use Point2D(Double x Double y), public double x(), public double y(),
//public  double distanceSquaredTo(Point2D that),  public     int compareTo(Point2D that)
//public boolean equals(Object that), public  String toString()
import edu.princeton.cs.algs4.RectHV;
//public    RectHV(double xmin, double ymin, double xmax, double ymax), contains(Point2D p), xmin(), ymin(),
//xmax(), ymax(), intersects(RectHV that), distanceSquaredTo(Point2D p), equals(Object that), and toString().

public class KdTreeST<Value> {// use worksheet for help
	private int levelHelp = 1;
	private Node root;

	private class Node {
		private Point2D key;
		private Value val;
		private Node left, right;
		private int size;
		private RectHV rect;

		public Node(Point2D key, Value val, int size, RectHV rect) {
			this.key = key;
			this.val = val;
			this.size = size;
			this.rect = rect;
		}
	}

	public KdTreeST() {

	}

	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(KdTreeST<Value>.Node root2) {
		if (root2 == null) {
			return 0;
		} else
			return root2.size;
	}

	public void put(Point2D p, Value val) {
		if (p.equals(null) || val.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		levelHelp = 0;

		RectHV rect = new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
				Double.POSITIVE_INFINITY);
		root = put(root, p, val, rect);
	}

	private Node put(Node x, Point2D key, Value val, RectHV rect) {
		if (x == null)
			return new Node(key, val, 1, rect);
		levelHelp = (levelHelp + 1) % 2;
		double cmp;
		RectHV set;
		if (levelHelp == 0) {
			cmp = key.x() - x.key.x();
		} else {
			cmp = key.y() - x.key.y();
		}

		if (cmp < 0) {
			if (levelHelp == 0) {
				set = new RectHV(rect.xmin(), rect.ymin(), x.key.x(), rect.ymax());
			} else {
				set = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.key.y());
			}
			x.left = put(x.left, key, val, set);
		} else if (cmp > 0) {
			if (levelHelp == 0) {
				set = new RectHV(x.key.x(), rect.ymin(), rect.xmax(), rect.ymax());
			} else {
				set = new RectHV(rect.xmin(), x.key.y(), rect.xmax(), rect.ymax());
			}
			x.right = put(x.right, key, val, set);
		} else {
			x.val = val;
		}

		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Value get(Point2D key) {
		levelHelp = 0;
		return get(root, key);
	}

	private Value get(Node x, Point2D key) {
		if (x == null)
			return null;
		double cmp;
		if (levelHelp == 0) {
			cmp = key.x() - x.key.x();
		} else {
			cmp = key.y() - x.key.y();
		}
		levelHelp = (levelHelp + 1) % 2;
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	public boolean contains(Point2D p) {
		if (p.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		if (get(p) != null) {
			return true;
		}
		return false;
	}

	public Iterable<Point2D> points() {
		Queue<Point2D> sender = new Queue<>();
		Queue<Node> helper = new Queue<>();
		helper.enqueue(root);
		while (!helper.isEmpty()) {
			Node holder = helper.dequeue();
			if (holder.left != null) {
				helper.enqueue(holder.left);
			}
			if (holder.right != null) {
				helper.enqueue(holder.right);
			}
			sender.enqueue(holder.key);
		}
		return sender;
	}

	public Iterable<Point2D> range(RectHV rect) {
		if (rect.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		Queue<Point2D> sender = new Queue<>();
		// search how to find collisons
		// double.infinity

		sender = range(rect, sender, root);

		return sender;
	}

	private Queue<Point2D> range(RectHV rect, Queue<Point2D> sender, KdTreeST<Value>.Node root2) {
//		Queue<Node> helper = new Queue<>();
//		helper.enqueue(root);
		

		while (!sender.isEmpty()) {
//			Node holder = helper.dequeue();

			if (root2.left != null) {
				if (root2.rect.intersects(rect)) {
					
//					helper.enqueue(holder.left);
					range(rect, sender, root2.left);
				}
			}
			if (root2.right != null) {
				if (root2.rect.intersects(rect)) {
//					helper.enqueue(holder.right);
					range(rect, sender, root2.right);
				}
			}
//			sender.enqueue(holder.key);
		}
		return sender;
	}

	public Point2D nearest(Point2D p) {
		if (p.equals(null)) {
			throw new java.lang.NullPointerException();
		}
		double currentDist = p.distanceSquaredTo(root.key);
		Point2D closest = null;
		if (currentDist != 0) {
			closest = root.key;
			if (root.left != null && root.left.rect.distanceSquaredTo(p) < currentDist) {
				currentDist = p.distanceSquaredTo(root.left.key);
				closest = nearest(p, root.left, currentDist, closest);
			}
			if (root.right != null && root.right.rect.distanceSquaredTo(p) < p.distanceSquaredTo(closest)) {
				currentDist = p.distanceSquaredTo(root.right.key);
				closest = nearest(p, root.right, currentDist, closest);
			}
		} else {
			if (root.left != null && root.left.rect.distanceSquaredTo(p) < currentDist) {
				closest = nearest(p, root.left, currentDist, closest);
			}
			if (root.right != null && root.right.rect.distanceSquaredTo(p) < currentDist) {
				closest = nearest(p, root.right, currentDist, closest);
			}
		}
		return closest;
	}

	private Point2D nearest(Point2D p, KdTreeST<Value>.Node node, double currentCloseDist, Point2D closestPoint) {
		if(node == null) {
			return closestPoint;
		}
		if(p.distanceSquaredTo(node.key) < currentCloseDist) {
			closestPoint = node.key;
		}
		if (currentCloseDist != 0) {
			currentCloseDist = p.distanceSquaredTo(closestPoint);
			if (node.left != null && node.left.rect.distanceSquaredTo(p) < currentCloseDist) {
				currentCloseDist = p.distanceSquaredTo(node.left.key);
				closestPoint = nearest(p, node.left, currentCloseDist, closestPoint);
			}
			if (node.right != null && node.right.rect.distanceSquaredTo(p) < currentCloseDist) {
				currentCloseDist = p.distanceSquaredTo(node.right.key);
				closestPoint = nearest(p, node.right, currentCloseDist, closestPoint);
			}
		} else {
			currentCloseDist = p.distanceSquaredTo(closestPoint);
			if (node.left != null && node.left.rect.distanceSquaredTo(p) < currentCloseDist) {
				closestPoint = nearest(p, node.left, currentCloseDist, closestPoint);
			}
			if (node.right != null && node.right.rect.distanceSquaredTo(p) < currentCloseDist) {
				closestPoint = nearest(p, node.right, currentCloseDist, closestPoint);
			}
		}
		return closestPoint;
	}

	public static void main(String[] args) {

	}

}
