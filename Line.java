/**
 * Class representing a Line
 * @author Isaac Banner - isb4190
 */

public class Line implements Comparable<Line>{

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
	return 0;
    }

    public String toString() {
	return "y = " + a + "*x + " + b;
    }

    public static void main(String[] args) {
	Point p = new Point(-1, 2);
	Point q = new Point(1, 4);
	Line l = new Line(p, q);
	System.out.println(l);
    }
}
