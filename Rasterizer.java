//
//  Rasterizer.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * 
 * A simple class for performing rasterization algorithms.
 *
 */

import java.util.*;

public class Rasterizer {
    
    /**
     * number of scanlines
     */
    int n_scanlines;
    
    /**
     * Constructor
     *
     * @param n - number of scanlines
     *
     */
    Rasterizer (int n)
    {
        n_scanlines = n;
    }

    public void drawLine(double x0, double y0, double x1, double y1, simpleCanvas C) {
        drawLine((int) x0, (int) y0, (int) x1, (int) y1, C);
    }
    
    /**
     * Draw a line from (x0,y0) to (x1, y1) on the simpleCanvas C.
     *
     * Implementation should be using the Midpoint Method
     *
	 * You are to add the implementation here using only calls
	 * to C.setPixel()
     *
     * @param x0 - x coord of first endpoint
     * @param y0 - y coord of first endpoint
     * @param x1 - x coord of second endpoint
     * @param y1 - y coord of second endpoint
     * @param C - The canvas on which to apply the draw command.
	 */
	public void drawLine (int x0, int y0, int x1, int y1, simpleCanvas C)
	{
		int temp;
    		if(x0 > x1){ 
			//If x0 is greater than x1, we need to swap the
			//vertices to make the line left -> right.
			temp = x0;
			x0 = x1;
			x1 = temp;
			temp = y0;
			y0 = y1;
			y1 = temp;
		}

		int dy = y1 - y0;
		int dx = x1 - x0;
		String slope;
		int x = 0, y = 0, end = 0;

		if(dy > dx){ 
			//For large positive, flip the slope variables
			slope = "lp";
			temp = dx;
			dx = dy; 
			dy = temp;
			end = y1;

		}
		else if(dy < 0){
			if(dy < (-1 * dx)){
				//For large negative, negate the y and swap
				slope = "ln";
				temp = dx;
				dx = -1 * dy;
				dy = temp;
				end = y0 + (y0 - y1); //"negates" the low point
			}
			else{
				//For small negative, negate the y
				slope = "sn";
				dy = -1 * dy;
				end = x1;
			}
		}
		else{
		//Our favorite octant. Just set the final value and go.
			slope = "sp";
			end = x1;
		}

		//Generate the inital delta values.
		int dE, dNE, d;
		dE = 2 * dy;
		dNE = 2 * ( dy - dx );
		d = dE - dx;

		//Trying to reduce code space / repeated functionality
		//with this one. This just makes setting the for values
		//easier.
		boolean lrg = slope.contains("l");
		//Using ternary operators to get the inital loop values.
		for( x = lrg?y0:x0, y = lrg?x0:y0; x <= end; x++ ) {
			//I suppose if we were dead set on execution time I 
			//could have used a switch, but honestly, is O(4) 
			//really that much worse than O(1)? 
			if(slope.equals("lp")){
				//Flip the pixel back.
				C.setPixel( y, x );
			}
			else if(slope.equals("sn")){
				//"Negate" the y across the inital point.
				C.setPixel( x, y0 - (y - y0) );
			}
			else if(slope.equals("ln")){
				//Flip and "Negate" the y
				C.setPixel( y, y0 - (x - y0) );
			}
			else{
				//Normal pixel. Carry on.
				C.setPixel( x, y );
			}
			
			//Update delta values.
			if(d <= 0 )
				d += dE;
			else{
				y ++;
				d += dNE;
			}
		}
    	}
      
}
