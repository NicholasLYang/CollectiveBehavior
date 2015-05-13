import java.io.*;
import java.util.*;

public class Vector
{
    // Stores both rectangular and polar version of coords.

    // Coords is an array with the x coord being the first term and the y coord being the second term
    private double[] coords;

    private double theta;
    private double r;
    public Vector (double[] a)
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
	r = norm(coords);
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
	
		

 

    // ---------- Get/Set Methods ---------- \\
    public double getTheta()
    {
	return theta;
    }

    public void setTheta(double d)
    {
	theta = d;
    }
    public double getR()
    {
	return r;
    }

    public void setR(double d)
    {
	r = d;
    }
    public double[] getCoords()
    {
	return coords;
    }

    public void setCoords(double[] d)
    {
	coords = Arrays.copyOfRange(d, 0, 2);
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
	coords[0] = x;
    }
    public void setY(double y)
    {
	coords[1] = y;
    }
    // ---------- Useful Methods ---------- \\

    public double distance (Vector v)
    {
	double[] c2 = v.getCoords();
	return Math.sqrt(Math.pow(coords[1] - c2[1], 2) + Math.pow(coords[0] - c2[0], 2));
    }
    // returns this vector minues v
    public Vector difference (Vector v)
    {
	double[] c2 = v.getCoords();
	Vector out = new Vector(new double[] {coords[0] - c2[0], coords[1] - c2[1]});
	return out;
    }
    public Vector add (Vector v)
    {
	double[] c2 = v.getCoords();
	Vector out = new Vector(new double[] {coords[0] + c2[0], coords[1] + c2[1]});
	return out;
    }
    public Vector scalarMultiplication(Vector v, double d)
    {
	double[] c = v.getCoords();                                          
	c = new double[] {c[0] * d, c[1] * d};
	Vector out = new Vector(c);
	return out; 
    }
    public Vector scalarMultiplication(double d)
    {
	return this.scalarMultiplication(this, d);
    }
    public double norm (double[] a)
    {
	return Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
    }
    public double norm (Vector v)
    {
	return this.norm(v.getCoords());
    }
    public double norm()
    {
	return this.norm(coords);
    }
    // ---------- Main Method ---------- \\
    public static void main(String[] args)
    {
	double[] a = {3.0, 4.0};
	Vector v = new Vector();
	System.out.println(v.getR());

    }
	

}

		   
