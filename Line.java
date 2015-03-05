/**
 * Class representing a Line
 * @author Isaac Banner - isb4190
 */

public class Line implements Comparable<Line>{

    private static double sweepLine = Int.

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

    public int compareTo(Line other) {
        if(this.a + b < other.a + b) return -1;
        else if(this.a + b > other.a + b) return 1;
        else if(this.p.compareTo(other.p) < 0) return -1;
        else if(this.p.compareTo(other.p) > 0) return 1;
        else if(this.q.compareTo(other.q) < 0) return -1;
        else if(this.q.compareTo(other.q) > 0) return 1;
        else return 0;
    }

    public String toString() {
	return "y = " + a + "*x + " + b;
    }

    public Point intersect(Line other) {
        if(this.a == other.a) return null;

        double x = (other.b - this.b) / (this.a - other.a);
        // a1*x + b1 = a2*x + b2
        // a1*x - a2*x = b2 - b1
        // x = (b2 - b1) / (a1 - a2)
        double y = a * x + b;

        if (x < this.p.x || x > this.q.x || x < other.p.x || x > other.q.x) {
            return null;
        } else return new Point(x, y, this);

    }

    public static void main(String[] args) {
        Point p = new Point(-1, 2);
        Point q = new Point(1, 4);
        Line l = new Line(p, q);
        System.out.println(l);
    }
}
