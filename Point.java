/**
 * Class representation of an event point
 * @author Isaac Banner - isb4190 
 */
public class Point implements Comparable<Point>{

    public final double x, y;
    private Line line;

    public Point(double x, double y) {
	this(x, y, null);
    }

    public Point(double x, double y, Line line) {
	this.x = x;
	this.y = y;
	this.line = line;
    }

    public Line getLine() {
	return line;
    }

    public void setLine(Line line) {
	this.line = line;
    }

    public boolean equals(Point other) {
	return this.x == other.x && this.y == other.y;
    }

    public boolean isLeft() { return this.equals(line.p); }

    public boolean isRight() { return this.equals(line.q); }

    public int compareTo(Point other) {
        if (this.x < other.x) return -1;
        else if (this.x > other.x) return 1;
        else if(this.y < other.y) return -1;
        else if(this.y > other.y) return 1;
        else return 0;
    }

    public String toString() {
	return "(" + x + ", " + y + ")";
    }
}
