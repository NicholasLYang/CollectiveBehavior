import java.io.*;
import java.util.*;
    
public class Agent
{
    // stores x and y coords as an array
    private double[] coords;
    private double direction;
    private double speed;
    private Random r;

    public Agent (int x, int y, int d, int s)
    {
	coords = new double[2];
	coords[0] = x;
	coords[1] = y;
	speed = s;
	direction = d;
    }
    public Agent(double sizeOfGraph, double s)
    {
	speed = s;
	r = new Random();
	coords = new double[2];
	coords[0] = sizeOfGraph * r.nextDouble();
	coords[1] = sizeOfGraph * r.nextDouble();
	direction = (2 * r.nextDouble()) * Math.PI;
    }
    // returns distance from origin
    public double getDistance()
    {
	return Math.sqrt(Math.pow(coords[1], 2) + Math.pow(coords[0], 2));
    }
    public double getX()
    {
	return coords[0];
    }

    public double getY ()
    {
	return coords[1];
    }

    public double[] getCoords()
    {
	return coords;
    }
    public void setX(double x)
    {
	coords[0] = x;
    }
    public void setY(double y)
    {
	coords[1] = y;
    }
    public void setCoords(double[] c)
    {
	try
	    {
		coords[0] = c[0];
		coords[1] = c[1];
	    }
	catch (Exception E)
	    {}

    }
    public double getDirection()
    {
	return direction;
    }
    public void setDirection(double d)
    {
	direction = d % (2 * Math.PI);
    }
    public double[] getVelocity()
    {
	double[] a = {speed * Math.cos(direction), speed * Math.sin(direction)};
	return a;
    }
    // Finds neighbors in a given arraylist of neighbors
    public Agent[] findNeighbors (Agent[] agents, double radius)
    {
	ArrayList<Agent> out = new ArrayList<Agent>();
	for (int i = 0; i < agents.length; i++)
	    {
		double aX = agents[i].getX();
		double aY = agents[i].getY();
		if (Math.sqrt(Math.pow((aX - this.getX()), 2) + Math.pow((aY - this.getY()), 2)) <= radius)
		    {
			out.add(agents[i]);
		    }
	    }
	Agent[] a = new Agent[out.size()];
	for (int j = 0; j < out.size(); j++)
	    {
		a[j] = out.get(j);
	    }
	return a;
    }
    public double[] repulsion (Agent a, double range)
    {
	double[] e = {coords[0] - a.getX(), coords[1] - a.getY()};
	double c = Math.pow((1 + Math.pow(Math.E, norm(e)/range - 2)), -1);
	return arrayMultiply(e, c / norm(e));
    }


    //---------- Useful Stuff ----------\\
    public double[] arrayMultiply (double[] a, double b)
    {
	for (int i = 0; i < a.length; i++)
	    {
		a[i] = a[i] * b;
	    }
	return a;
    }
    public double norm(double[] a)
    {
	return Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
    }
	    
	
				
      
}
