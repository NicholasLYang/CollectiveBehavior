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
    private double lambda, alpha, gamma;
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
	lambda = 0;
	alpha = 0;
	gamma = 0;
    }
    public void setUpAdvancedNoise (double l, double a, double g)
    {
	lambda = l;
	alpha = a;
	gamma = g;
    }
    public String toString()
    {
	String out = new String();
	for (int i = 0; i < agents.length; i = i + 200)
	    {
		out = out + "Agent " + i + "| Position: r = " + String.format("%.5g", agents[i].getR()) + " θ = " + String.format("%.5g", agents[i].getTheta()) + " direction =" + String.format("%.5g", agents[i].getDirection()) + "\n";

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
			sumOfDirections = sumOfDirections + agents[j].getDirection();
		    }
		double newDirection = sumOfDirections/neighbors.length;
		newDirection = newDirection + rand.nextDouble() * 2 * Math.PI * eta;
		if (neighbors.length == 0)
		    newDirection = 0;
		newDirection = newDirection + BasicNoise();
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
    // Random rotation from 0 to 2πη
    public double BasicNoise ()
    {
	return rand.nextDouble() * 2 * Math.PI * eta;
    }
    
    public double ComplexNoise (double noise)
    {
	double r = rand.nextDouble();
	if (r > lambda)
	    {
		r = 0;
	    }
	else
	    {
		r = 1;
	    }
	return (1 - alpha) * noise + rand.nextDouble() + gamma * r;
    }
    //-------- Measurement --------\\

    
    // Returns the norm of the rotational momentum, basically measing curvature of the agents' motion
    public double milling()
    {
	double out = 0;
	for (int i = 0; i < agents.length; i++)
	    {
		out = out + (agents[i].getX() * Math.sin(agents[i].getDirection()) - agents[i].getY() * Math.cos(agents[i].getDirection()))/(o.norm(agents[i].getPosition()) * speed);
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
	double out = o.length(sum)/agents.length;
	return out;
    }
    public double cohesion()
    {
	double sum = 0;
	for (int i = 0; i < agents.length; i++)
	    {
		sum = sum + Math.pow(Math.E, (-1 * o.length(agents[i].getCoords())/(4 * radius)));
	    }
	return sum/agents.length;
    }

 

}
	


    
