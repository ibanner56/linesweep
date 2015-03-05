import java.util.LinkedList;
/**
 * Class representation of an event point
 * @author Isaac Banner - isb4190 
 */
public class Point implements Comparable<Point>{

    public final double x, y;			// The ordered pair for the point
    private LinkedList<Line> lines;		// The lines associated with the point

	/**
	 * Constructor for when you don't yet have the associated line.
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */ 
    public Point(double x, double y) {
		this(x, y, null);
    }
	
	/**
	 * Constructor for when you somehow already have the line associated 
	 * with the point (mostly for testing purposes and copying points).
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @param line - the line associated with the point
	 */ 
    public Point(double x, double y, Line line) {
		this.x = x;
		this.y = y;
		this.lines = new LinkedList<Line>();
		if(line != null) lines.add(line);
    }

	/**
	 * Returns the primary line for this point, since for most cases there'll
	 * only be one line.
	 *
	 * @return the first line associated with this point
	 */
    public Line getLine() {
		return lines.get(0);
    }

	/**
	 * Returns the full list of associated lines. Only necessary for the the 
	 * case where we're working with an intersection event point.
	 *
	 * @return the full list of lines associated with the point. 
	 */ 
	public LinkedList<Line> getLines() {
	    return lines;
	}

	/**
	 * Adds a line to the list of lines associated with this point.
	 * @param line - line to add to the list.
	 */ 
    public void addLine(Line line) {
		this.lines.add(line);
    }

	/**
	 * Checks if the two points are equal.
	 * @param other - the other point.
	 */ 
    public boolean equals(Point other) {
		return this.x == other.x && this.y == other.y 
		    && this.getLine().equals(other.getLine());
    }

	/**
	 * @return Whether the point is the leftmost endpoint of its associated line
	 */ 
    public boolean isLeft() { return this.equals(getLine().p); }
	
	/**
	 * @return Similar to isLeft, but returns if it's a rightmost endpoint
	 */ 
    public boolean isRight() { return this.equals(getLine().q); }
	
	/**
	 * Compares the two points based on x coordinate. Should probably 
	 * make this consistent with equals, but since we know we're working
	 * from the general position, we know it isn't really relevant.
	 *
	 * @param other - the point to compare to.
	 * @return whether this point is earlier or later in terms of x, then y
	 */ 
    public int compareTo(Point other) {
        if (this.x < other.x) return -1;
        else if (this.x > other.x) return 1;
        else if(this.y < other.y) return -1;
        else if(this.y > other.y) return 1;
        else return 0;
    }

	@Override
    public String toString() {
		return "(" + x + ", " + y + ")";
    }
}
