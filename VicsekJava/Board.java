import java.io.*;
import java.util.*;

public class Board
{
    private double size;
    private Agent[] agents;
    private Random rand;
    private double radius;
    private double speed;
    private double eta;
    private double beta;
    private double repulsionRange;
    // # of agents, area of board, radius of interaction, speed of agents and eta
    public Board (int n, double a, double r, double s, double e, double b, double rR)
    {
	rand = new Random();
	agents = new Agent[n];
	size = a;
	radius = r;
	eta = e;
	beta = b;
	repulsionRange = rR;
	for (int i = 0; i < n; i++)
	    {
		agents[i] = new Agent(a, s);
	    }
	radius = 0;
    }
    public String toString()
    {
	String out = new String();
	for (int i = 0; i < agents.length; i++)
	    {
		out = out + "Agent " + i + " (" + String.format("%.5g", agents[i].getX()) + ", " + String.format("%.5g", agents[i].getY()) + ") d =" + String.format("%.5g", agents[i].getDirection()) + "   ";
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

	double[] sumOfVelocity;
	Agent[] neighbors;
	for (int j = 0; j < agents.length; j++)
	    {
		sumOfVelocity = new double[2];
		neighbors = agents[j].findNeighbors(agents, radius);
		for (int c = 0; c < neighbors.length; c++)
		    {
			sumOfVelocity = arrayAddition(arrayAddition(sumOfVelocity, neighbors[c].getVelocity()), arrayMultiply(agents[j].repulsion(neighbors[c], repulsionRange), beta));
		    }
		double newDirection = arrayMultiply(sumOfVelocity, 1 / neighbors.length) + eta * Math.PI * (1 - 2 * rand.nextDouble()) % (2 * Math.PI));
		double newX = (agents[j].getX() + speed * Math.cos(agents[j].getDirection())) % size;
		double newY = (agents[j].getY() + speed * Math.sin(agents[j].getDirection())) % size;
		agents[j].setX(newX);
		agents[j].setY(newY);
		agents[j].setDirection(newDirection);
			
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
    public double norm (double[] a)
    {
	return Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
    }
    public double[] arrayAddition(double[] a, double[] b)
    {
	double[] c = new double[Math.max(a.length, b.length)];
	for (int i = 0; i < a.length && i < b.length; i++)
	    {
		c[i] = a[i] + b[i];
	    }
	return c;
    }
    
    public double[] arrayMultiply (double[] a, double b)
    {
	for (int i = 0; i < a.length; i++)
	    {
		a[i] = a[i] * b;
	    }
	return a;
    }
}
	


    
