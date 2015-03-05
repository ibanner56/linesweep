import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Isaac on 2/19/2015.
 * Used to read from and write to points files.
 */
public class LineScanner {

    /**
     * Reads in a points file and returns it as an unsorted list of points.
     * @param filename - path to the points file
     * @return An ArrayList of points.
     * @throws IOException
     * @throws NoSuchElementException
     */
    public static void read (String filename, TreeSet<Point> points)
            throws IOException, NoSuchElementException{
        
        File pointFile = new File(filename);
        Scanner s = new Scanner(new BufferedReader(new FileReader(pointFile)));

        int n = Integer.parseInt(s.nextLine());

        for(int i = 0; i < n; i++) {
            String[] pointPair = s.nextLine().split(" ");

            if (pointPair.length != 4) {
                throw new IOException("Improperly formatted point - pair not of length 2.");
            }

            Point p = new Point(Integer.parseInt(pointPair[0]),
                        Integer.parseInt(pointPair[1]));
            Point q = new Point(Integer.parseInt(pointPair[2]),
                    Integer.parseInt(pointPair[3]));
            Line line = new Line(p, q);
            p.setLine(line);
            q.setLine(line);

            points.add(p);
            points.add(q);
        }
    }

    /**
     * Default write method - calls write with the output file name "hull"
     * @param intersects - list of intersection points
     * @return Whether or not the write was successful
     */
    public static boolean write (ArrayList<Point> intersects) {
        return write(intersects, "intersects");
    }

    /**
     * Writes the points to an output file
     * @param intersects - list of intersection points
     * @param filename - Output file path
     * @return Whether or not the write was successful
     */
    public static boolean write (ArrayList<Point> intersects, String filename) {
        PrintWriter writer;
        try {
            writer = new PrintWriter("./" + filename + ".out", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        writer.println("" + intersects.size());
        for(int i = 0; i < intersects.size(); i++) {
            writer.println(intersects.get(i));
        }

        writer.close();
        return true;
    }
}
