import java.io.*;
import java.util.*;
    
public class Agent extends Vector
{
    // stores x and y coords as an array
    private Random r;
    private Vector velocity;
    
    public Agent (double[] coords, double direction, int speed)
    {
	super(coords);
	r = new Random();
	velocity = new Vector(speed, direction);
    }
    public Agent(int sizeOfGraph, double speed)
    {
	super();
	r = new Random();
	setCoords(new double[] {r.nextDouble() * sizeOfGraph, r.nextDouble() * sizeOfGraph});
	velocity = new Vector(speed, Math.PI * (1 - 2 * r.nextDouble()));
	
    }
    

    public double getDirection()
    {
	return velocity.getTheta();
    }

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
    // Finds neighbors in a given arraylist of neighbors
    public Agent[] findNeighbors (Agent[] agents, double radius)
    {
	ArrayList<Agent> out = new ArrayList<Agent>();
	for (int i = 0; i < agents.length; i++)
	    {
		if (this.distance(agents[i]) < radius)
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
    public Vector repulsion (Agent a, double range)
    {
	Vector e = scalarMultiplication(difference(a), 1/norm(difference(a)));
	double c = Math.pow((1 + Math.pow(Math.E, norm(e)/range - 2)), -1);
	return scalarMultiplication(e, c);
    }



	    
	
				
      
}
