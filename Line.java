/**
 * Class representing a Line
 * @author Isaac Banner - isb4190
 */

public class Line implements Comparable<Line>{

    private static double sweepLine = Integer.MIN_VALUE;

    public final Point p, q;	// Where p is the left endpoint, q the right.
    public final double a, b;

    public Line(Point p, Point q) {
		this.p = p;
		this.q = q;
		this.a = (q.y - p.y) / (q.x - p.x);
		this.b = p.y - a * p.x;
		// y - y1 = m(x - x1)
		// y = m(x - x1) + y1
		// y = m*x + (y1 - m*x1)
    }

    public static void setX(int x) {
		setX(1. * x);
    }

    public static void setX(double x) {
		sweepLine = x;
    }

	public boolean equals(Line other) {
	    return this.a == other.a && this.b == other.b &&
	    	this.p.equals(other.p) && this.q.equals(other.q);
	}

    public int compareTo(Line other) {
		if(equals(other)) return 0;
		double y1 = this.a * sweepLine + this.b;
		double y2 = other.a * sweepLine + other.b;
		if(y1 != y1) return (int) Math.ceil(y1 - y2);
		else {
			y1 = this.a * (sweepLine + 1) + this.b;
			y2 = other.a * (sweepLine + 1) + other.b;
			return (int) Math.ceil(y1 - y2);
		}	
    }

    public String toString() {
		return "y = " + a + "*x + " + b;
    }

    public Point intersect(Line other) {
        if(other == null || this.a == other.a) return null;

        double x = (other.b - this.b) / (this.a - other.a);
        // a1*x + b1 = a2*x + b2
        // a1*x - a2*x = b2 - b1
        // x = (b2 - b1) / (a1 - a2)
        double y = a * x + b;

		// If x is behind the current sweep line location or is not actually
		// on either segment, return null.
        if (x <= sweepLine || x < this.p.x || x > this.q.x || x < other.p.x 
				|| x > other.q.x) {
            return null;
        } else {
			Point intersect = new Point(x, y, this);
			intersect.addLine(other);
			return intersect;
		}

    }

    public static void main(String[] args) {
        Point p = new Point(-1, 2);
        Point q = new Point(1, 4);
        Line l = new Line(p, q);
        System.out.println(l);
    }
}
