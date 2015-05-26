import java.io.*;
import java.util.*;

public class Board
{
    private double size;
    private Agent[] agents;
    private Operator o;
    private Random rand;
    private double radius;
    private double speed;
    private double eta;
    private double beta;
    private double repulsionRange;
    // # of agents, area of board, radius of interaction, speed of agents and eta
    public Board (int n, int sizeOfGraph, double r, double s, double e, double b, double rR)
    {
	o = new Operator();
	rand = new Random();
	agents = new Agent[n];
	size = sizeOfGraph;
	speed = s;
	radius = r;
	eta = e;
	beta = b;
	repulsionRange = rR;
	for (int i = 0; i < n; i++)
	    {
		agents[i] = new Agent(sizeOfGraph, s);

	    }
	radius = 0;
    }
    public String toString()
    {
	String out = new String();
	for (int i = 0; i < agents.length; i++)
	    {
		out = out + "Agent " + i + " r = " + String.format("%.5g", agents[i].getR()) + " theta = " + String.format("%.5g", agents[i].getTheta()) + " d =" + String.format("%.5g", agents[i].getDirection()) + "   ";
		if (i % 2 == 0)
		    {
			out = out + "\n";
		    }
	    }
	return out + "\n";
    }
    //----------- Get/Set Methods -----------\\
    public double getRadius()
    {
	return radius;
    }

    public void setRadius(double r)
    {
	radius = r;
    }
    public double getSpeed()
    {
	return speed;
    }

    public void setSpeed(double d)
    {
	speed = d;
    }

    public double getEta()
    {
	return eta;
    }

    public void setEta(double d)
    {
	eta = d;
    }

    public void Vicsek()
    {
	double sumOfDirections;
	Agent[] neighbors;
	for (int i = 0; i < agents.length; i++)
	    {
		sumOfDirections = 0;
		neighbors = agents[i].findNeighbors(agents, radius);
		for (int j = 0; j < neighbors.length; j++)
		    {
			sumOfDirections = sumOfDirections + neighbors[j].getDirection();
		    }
		double newDirection = sumOfDirections/neighbors.length;
		if (neighbors.length == 0)
		    newDirection = 0;
		agents[i].setDirection(newDirection);
		// System.out.println("Agent " + i + " has direction " + sumOfDirections/neighbors.length);
		agents[i].move();
	    }
;
    }

    // Right now doesn't work. One paper claims this is equivalent to the method above.
    public void Vicsek1()
    {

	Vector sumOfVelocity;
	Agent[] neighbors;
	for (int j = 0; j < agents.length; j++)
	    {
		sumOfVelocity = new Vector();
		neighbors = agents[j].findNeighbors(agents, radius);
		for (int c = 0; c < neighbors.length; c++)
		    {
			o.add(sumOfVelocity, neighbors[c].getVelocity());
		    }
		Vector newVelocity = o.scalarMultiplication(o.normalize(sumOfVelocity), speed);
		System.out.println(newVelocity);
		o.rotate(newVelocity, 2 * Math.PI * rand.nextDouble() * eta);
		agents[j].setVelocity(newVelocity);
		Vector newPosition = o.add(agents[j].getPosition(), newVelocity);

			
	    }
	    
    }
    
    //-------- Measurement --------\\

    
    // Returns the norm of the rotational momentum, basically measing curvature of the agents' motion
    public double milling()
    {
	double out = 0;
	for (int i = 0; i < agents.length; i++)
	    {
		out = out + (agents[i].getX() * Math.sin(agents[i].getDirection()) - agents[i].getY() * Math.cos(agents[i].getDirection()))/norm(agents[i].getCoords());
	    }
	return out/agents.length;
    }
    // Measures whether the agents are all going in the same direction
    public double polarization()
    {
	double[] sum = new double[2];
	for (int i = 0; i < agents.length; i++)
	    {
		sum[0] = sum[0] + Math.cos(agents[i].getDirection());
		sum[1] = sum[1] + Math.sin(agents[i].getDirection());
	    }
	double out = norm(sum)/agents.length;
	return out;
    }
    public double cohesion()
    {
	double sum = 0;
	for (int i = 0; i < agents.length; i++)
	    {
		sum = sum + Math.pow(Math.E, (-1 * norm(agents[i].getCoords())/(4 * radius)));
	    }
	return sum/agents.length;
    }
    //------------ Useful Methods ------------\\

// Because the build in java mod doesn't work with negatives. Basically d % m
public double mod (double x, double n)
{
    double r = x % n;
    if (r > 0 && x < 0)
	{
	    r -= n;
	}
    return r;
}
	    
    public double norm (double[] a)
    {
	return Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
    }
 

}
	


    
