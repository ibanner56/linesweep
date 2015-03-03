import java.util.TreeSet;
import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author Isaac Banner - isb4190
 * Class to run the line sweep algorithm
 */
public class LineSweep {

    /**
     * Runs the sweep algorithm
     * @param eventQueue - The eventQueue (of points)
     * @param sweepStatus - The initial sweep status
     * @param intersects - The linked list to put the intersect poitns into.
     */
    public static void sweep(TreeSet<Point> eventQueue, 
    	TreeSet<Line> sweepStatus, LinkedList<Point> intersects) {

    }

    /**
     * Processes the file, finds the intersections, and displays them.
     * @param args - the command line arguments (filename!)
     */
    public static void main(String[] args) {
    	if(args.length != 1) {
	    System.err.println("Usage: java LineSweep <filename>");
	    return;
	}

	TreeSet<Point> eventQueue = new TreeSet<Point>();
	TreeSet<Line> sweepStatus = new TreeSet<Line>();
	LinkedList<Point> intersects = new LinkedList<Point>();

	try {
	    LineScanner.read(args[0], eventQueue, sweepStatus);
	} catch (IOException ex) {
	    System.err.println(ex.getMessage());
	    return;
	} catch (NoSuchElementException ex) {
	    System.err.println(ex.getMessage());
	    return;
	}

	for(Point point : points) {
	    System.out.println(point);
	}
	for(Line line : lines) { 
	    System.out.println(line);
	}
    }

}
