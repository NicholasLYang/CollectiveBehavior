import java.io.*;
import java.util.*;
    
public class Agent 
{
    // stores x and y coords as an array
    private Random rand;
    private Operator o;
    private Vector velocity;
    private Vector position;
    private double speed;
    private double sizeOfBoard;
    //  private double noise;
    public Agent (double[] coords, double direction, int s, int size)
    {
	o = new Operator();
	rand = new Random();
	velocity = new Vector(s, direction);
	position = new Vector(coords);
	sizeOfBoard = size;
	speed = s;
    }
    public Agent(int size, double s)
    {
	sizeOfBoard = size;
	o = new Operator();
	rand = new Random();
	position = new Vector(rand.nextDouble() * sizeOfBoard, rand.nextDouble() * ( 1 - 2 * Math.PI));
	velocity = new Vector(s, Math.PI * (1 - 2 * rand.nextDouble()));
	speed = s;
    }
    public Agent()
    {
	this(1, 0.25);
    }
    public String toString()
    {
	return "\n" + "   Position: " + position + "\n" + "   Velocity: " + velocity;
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
    /*
    public double getNoise()
    {
	return noise;
    } 
    public void setNoise(double d)
    {
	noise = d;
    }
    */

    // ---------- Vicsek ---------- \\	
    // Finds the sum of neighbors' directions given array of agents and radius
    public ArrayList<Agent> findNeighbors (Agent[] agents, double radius)
    {
       
	ArrayList<Agent> out = new ArrayList<Agent>();
	for (int i = 0; i < agents.length; i++)
	    {
		if (o.distance(position, agents[i].getPosition()) <= radius)
		    {
			out.add(agents[i]);
		    }
	    }
	return out;
    }
    public Vector sumDiffVelocity(Agent[] agents, double radius)
    {
	ArrayList<Agent> a = findNeighbors(agents, radius);
	Vector out = new Vector();
	for (int i = 0; i < a.size(); i++)
	    {
		out = o.add(o.difference(a.get(i).getVelocity(), velocity), out);
	    }
	return out;
    }
    public Vector sumOfVelocity(Agent[] agents, double radius)
    {
	ArrayList<Agent> a = findNeighbors(agents, radius);
	Vector out = new Vector();
	for ( int i = 0; i < a.size(); i++)
	    {
		out = o.add(out, a.get(i).getVelocity());
	    }
	return out;
    }
	    
    public void move(double time)
    {
	position = o.add(position, o.scalarMultiplication(velocity, time));
	double[] c = position.getCoords();
	
    }
    // Takes an array of agents, runs it through findNeighbors, then calculates the repulsion from all of them.
    public Vector sumOfRepulsion (Agent[] a, double range)
    {
	Vector out = new Vector();
	ArrayList<Agent> agents = findNeighbors(a, range);
	for (int i = 0; i < agents.size(); i++)
	    {
		Vector e = o.normalize(o.difference(position, agents.get(i).getPosition()));
		double c = Math.pow((1 + Math.exp(o.distance(position, agents.get(i).getPosition())/range - 2)), -1);
		out = o.add(out, o.scalarMultiplication(e, c));
	    }
	return out;
    }


	


	    
	
				
      
}
