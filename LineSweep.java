import java.util.TreeSet;
import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        simpleCanvas T = new simpleCanvas(500, 500);
        Rasterizer R = new Rasterizer (500);


        T.setColor (0.0f, 0.0f, 0.0f);
        T.clear();
        T.setColor (1.0f, 1.0f, 1.0f);

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

        for(Point point : eventQueue) {
            System.out.println(point);
        }

        R.drawLine(250, 0, 250, 499, T);
        R.drawLine(0, 250, 499, 250, T);
        System.out.println(sweepStatus.size());
        for(Line line : sweepStatus) {
            System.out.println(line);
            R.drawLine((250 + 15*line.p.x), (250 + 15*line.p.y), (250 + 15*line.q.x), (250 + 15*line.q.y), T );
        }

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
