import java.io.*;
import java.util.*;
    
public class Agent 
{
    // stores x and y coords as an array
    private Random rand;
    private Operator o;
    private Vector velocity;
    private Vector position;
    private double sizeOfBoard;
    
    public Agent (double[] coords, double direction, int speed)
    {
	o = new Operator();
	rand = new Random();
	velocity = new Vector(speed, direction);
	position = new Vector(coords);
    }
    public Agent(int sizeOfGraph, double speed)
    {
	o = new Operator();
	rand = new Random();
	position = new Vector(new double[] {rand.nextDouble() * sizeOfGraph, rand.nextDouble() * sizeOfGraph});
	velocity = new Vector(speed, Math.PI * (1 - 2 * rand.nextDouble()));
    }
    public String toString()
    {
	return "Position: " + position + " Velocity: " + velocity;
    }
    
    // ---------- Get/Set Methods ---------- \\

    // The position vector has polar coords (r, theta) and rectangular coords (rcos(theta), rsin(theta))
    // The velocity vector has polar coords (speed, direction)
    public Vector getVelocity()
    {
	return velocity;
    }
    public void setVelocity(Vector v)
    {
	velocity = v;
    }
    public void setVelocity(double direction, double speed)
    {
	velocity = new Vector(speed, direction);
    }
    public double getDirection()
    {
	return velocity.getTheta();
    }
    public void setDirection(double d)
    {
	velocity.setTheta(d);
    }
    public double getSpeed()
    {
	return velocity.getR();
    }
    public void setSpeed(double s)
    {
	velocity.setR(s);
    }


    public Vector getPosition()
    {
	return position;
    }
    public void setPostion(Vector p)
    {
	position = p;
    }
    public double getR()
    {
	return position.getR();
    }
    public void setR(double d)
    {
	position.setR(d);
    }
    public double getX()
    {
	return position.getX();
    }
    public void setX(double x)
    {
	position.setX(x);
    }
    public double getY()
    {
	return position.getY();
    }
    public void setY(double y)
    {
	position.setY(y);
    }
    public double getTheta()
    {
	return position.getTheta();
    }
    public void setTheta(double t)
    {
	position.setTheta(t);
    }
    public double[] getCoords()
    {
	return position.getCoords();
    }
    // ---------- Vicsek ---------- \\	
    // Finds neighbors in a given arraylist of neighbors
    public Agent[] findNeighbors (Agent[] agents, double radius)
    {
	ArrayList<Agent> out = new ArrayList<Agent>();
	for (int i = 0; i < agents.length; i++)
	    {
		if (o.distance(position, agents[i].getPosition()) <= radius)
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
    public void move()
    {
	position = o.add(position, velocity);

	
    }
    // ---------- Additions to Vicsek ---------- \\	
    public Vector repulsion (Agent a, double range)
    {
	Vector e = o.normalize(o.difference(position, a.getPosition()));
	double c = Math.pow((1 + Math.pow(Math.E, o.norm(o.difference(position, a.getPosition()))/range - 2)), -1);
	return o.scalarMultiplication(e, c);
    }



	    
	
				
      
}
