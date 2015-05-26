import java.io.*;
import java.util.*;

public class Vector
{
    // Stores both rectangular and polar version of coords.

    // Coords is an array with the x coord being the first term and the y coord being the second term
    private double[] coords;
    private Operator o;
    private double theta;
    private double r;
    public Vector (double[] a)
    {
	o = new Operator();
	coords = Arrays.copyOfRange(a, 0, 2);
	theta = Math.atan2(a[1], a[0]);
	if (a[0] == 0)
	    {
		if (a[1] > 0)
		    theta = Math.PI/2;
		else
		    theta =  3 * Math.PI/2;
	    }
	r = o.length(coords);
    }
    public Vector (double r1, double d1)
    {
	r = r1;
	theta = d1 % (2 * Math.PI);
	if (theta > 0 && d1 < 0)
	    {
		theta = theta - (2 * Math.PI);
	    }
	coords = new double[] {r * Math.cos(theta), r * Math.sin(theta)};
	
    }
    public Vector ()
    {
	this(0, 0);
    }
	
		
    public String toString()
    {
	return "R = " + r + " Theta = " + theta;
    }
 
    // ---------- Get/Set Methods ---------- \\
    public double getTheta()
    {
	return theta;
    }

    public void setTheta(double d)
    {
	theta = d;
	theta = o.mod(theta, 2 * Math.PI);
	coords = new double[] {r * Math.cos(theta), r * Math.sin(theta)};
    }
    public double getR()
    {
	return r;
    }

    public void setR(double d)
    {
	r = d;
	coords = new double[] {r * Math.cos(theta), r * Math.sin(theta)};
    }
    public double[] getCoords()
    {
	return coords;
    }

    public void setCoords(double[] a)
    {
	coords = Arrays.copyOfRange(a, 0, 2);
	theta = Math.atan2(a[1], a[0]);
	if (a[0] == 0)
	    {
		if (a[1] > 0)
		    theta = Math.PI/2;
		else
		    theta =  3 * Math.PI/2;
	    }
	r = o.length(coords);
    }
    public double getX()
    {
	return coords[0];
    }

    public double getY ()
    {
	return coords[1];
    }
    public void setX(double x)
    {
	
	this.setCoords( new double[] {x, coords[1]});
	r = o.length(coords);
	theta = Math.atan2(coords[0], coords[1]);
    }
    public void setY(double y)
    {
	this.setCoords(new double[] {coords[0], y});
	r = o.length(coords);
	theta = Math.atan2(coords[0], coords[1]);
    }
 
	

}

		   
