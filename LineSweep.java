import java.util.TreeSet;
import java.util.Iterator;
import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Math;

/**
 * @author Isaac Banner - isb4190
 * Class to run the line sweep algorithm
 */
public class LineSweep {

    /**
     * Runs the sweep algorithm
     * @param eventQueue - The eventQueue (of points)
     * @param sweepStatus - The initial sweep status
     * @param intersects - The linked list to put the intersect points into.
     */
    public static void sweep(TreeSet<Point> eventQueue, 
    	TreeSet<Line> sweepStatus, LinkedList<Point> intersects) {
	
	while(eventQueue.size() > 0) {
	    Point current = eventQueue.pollFirst();

		System.out.println("Current Point: " + current);		

	    Line.setX(current.x);
	    if(current.isLeft()) {
	    	Line cline = current.getLine();
			sweepStatus.add(cline);
			Line higher = sweepStatus.higher(cline);
			if(higher != null) {
				Point hintersect = sweepStatus.higher(cline)
									.intersect(sweepStatus.lower(cline));
				if(hintersect != null) eventQueue.remove(hintersect);
			}
			Point upperIntersect = cline.intersect(sweepStatus.higher(cline));
			if(upperIntersect != null) eventQueue.add(upperIntersect);
			Point lowerIntersect = cline.intersect(sweepStatus.lower(cline));
			if(lowerIntersect != null) eventQueue.add(lowerIntersect);
	    } else if(current.isRight()) {
			Line cline = current.getLine();
			Line clineUpper = sweepStatus.higher(cline);
			Line clineLower = sweepStatus.lower(cline);
			sweepStatus.remove(cline);
			if(clineUpper != null) {
				Point intersect = clineUpper.intersect(clineLower);
				if(intersect != null) eventQueue.add(intersect);
			}
	    } else {
			// It's an intersection event.
			intersects.add(current);
			for(Line line : current.getLines()) {
				sweepStatus.remove(line);	// Note, this only works
				sweepStatus.add(line);		// to swap the lines because
			}								// my line comparator will
											// look ahead if they're 
											// equal at the current point

			for(Line line : current.getLines()) {
				Point upperIntersect = line
					.intersect(sweepStatus.higher(line));
				if(upperIntersect != null) eventQueue.add(upperIntersect);
				Point lowerIntersect = line
					.intersect(sweepStatus.lower(line));
				if(lowerIntersect != null) eventQueue.add(lowerIntersect);	
			}
		}
	}

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

        simpleCanvas T = new simpleCanvas(500, 500);
        Rasterizer R = new Rasterizer (500);


        T.setColor (0.0f, 0.0f, 0.0f);
        T.clear();
        T.setColor (1.0f, 1.0f, 1.0f);

        TreeSet<Point> eventQueue = new TreeSet<Point>();
        TreeSet<Line> sweepStatus = new TreeSet<Line>();
        LinkedList<Point> intersects = new LinkedList<Point>();
		LinkedList<Line> lines = new LinkedList<Line>();

        try {
            LineScanner.read(args[0], eventQueue, lines);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return;
        } catch (NoSuchElementException ex) {
            System.err.println(ex.getMessage());
            return;
        }


	sweep(eventQueue, sweepStatus, intersects);

	// Print out the lines and draw them.
	System.out.println(sweepStatus.size());
	R.drawLine(0, 250, 499, 250, T);
	R.drawLine(250, 0, 250, 499, T);
        for(Line line : lines) {
            System.out.println(line);
            R.drawLine((250 + 15*line.p.x), (250 + 15*line.p.y), (250 + 15*line.q.x), (250 + 15*line.q.y), T );
        }

	System.out.println("\nNumber of intersections: " + intersects.size());
	for(Point point : intersects) {
	    System.out.println(point);
		R.drawPoint((250 + 15*point.x), (250 + 15*point.y), T);
	}
	
	LineScanner.write(intersects, "intersections");	

        Frame f = new Frame( "line Test" );
        f.add("Center", T);
        f.pack();
        f.setResizable (false);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}


