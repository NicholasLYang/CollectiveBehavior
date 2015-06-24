import java.io.*;
import java.util.*;

public class Board
{
    /*
      The board is the space in which the agents move around. The shape of the board can vary. 
      Right now it is closed circle, but previously it was a square that wrapped around. 
      Thus, size has different meanings depending on the shape. Right now it is the radius of the board.
      Operator is a class that can perform basic vector operations along with a few other tasks such as a modified modulus. 
      Rand generates random variables. Radius is the radius of interaction for the agents. Speed is the speed of all the agents. 
      Eta is the noise parameter, regulating the extent to which noise affects the agents. Beta is 
    */
    private double size;
    private Agent[] agents;
    private Operator o;
    private Random rand;
    private double radius;
    private double speed;
    private double repulsionRange;
    private double alpha, beta, gamma, eta, lambda, sigma, tau;
    private double[] noise;
    public Board (int n, int sizeOfBoard, double radius, double repulsionRange, double speed, double alpha, double beta, double gamma, double eta, double lambda, double sigma, double tau)
    {
	o = new Operator();
	rand = new Random();
	agents = new Agent[n];
	size = sizeOfBoard;
	this.radius = radius;
	this.repulsionRange = repulsionRange;
	this.speed = speed;
	this.alpha = alpha;
	this.beta = beta;
	this.gamma = gamma;
	this.eta = eta;
	this.lambda = lambda;
	this.sigma = sigma;
	this.tau = tau;
       
	for (int i = 0; i < n; i++)
	    {
		agents[i] = new Agent(sizeOfBoard, speed);
	    }
	noise = new double[n];
    }
    public Board()
    {
	this(10, 10, 0, 0, 0, 0, 0, 0.4, 0.5, 0.3, 0, 0);
    }
    public String toString()
    {
	String out = "Size: " + size + "\n";
	for (int i = 0; i < agents.length; i++)
	    {
		out = out + "Agent " + i + "| Position: r = " + String.format("%.5g", agents[i].getR()) + " θ = " + String.format("%.5g", agents[i].getTheta()) + " direction =" + String.format("%.5g", agents[i].getDirection()) +  " (" + String.format("%.5g", agents[i].getX()) + ", " +  String.format("%.5g", agents[i].getY()) + ")" + "\n";

	    }
	return out + "\n";
    }
    //----------- Get/Set Methods -----------\\
    public Agent[] getAgents()
    {
	return agents;
    }
    public void setAgents(Agent[] a)
    {
	agents = a;
    }
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
    public double getNoise(int i)
    {
	return noise[i];
    }
    public double[] getNoise()
    {
	return noise;
    }
    // runs Vicsek for a given time interval. 
    public void Vicsek(double time)
    {
	AdvancedNoise();
	Vector sumDiffVelocity, repulsion, newVelocity;
	for (int i = 0; i < agents.length; i++)
	    {
		sumDiffVelocity = agents[i].sumDiffVelocity(agents, radius);
		repulsion = agents[i].sumOfRepulsion(agents, repulsionRange);
		newVelocity = o.add(sumDiffVelocity, o.scalarMultiplication(repulsion, beta));
		newVelocity = o.scalarMultiplication(o.normalize(newVelocity), speed);
		newVelocity.setTheta(newVelocity.getTheta() + eta * noise[i]);
		newVelocity.setR(speed);
		agents[i].setVelocity(newVelocity);
		agents[i].move(time);
		sumDiffVelocity = new Vector();
		repulsion = new Vector();
		newVelocity = new Vector();
	    }
    }
    public void Vicsek2(double time)
    {
	AdvancedNoise2(time);
        wallAvoidance(time);
	Vector repulsion;
	Vector newVelocity;
	for (int i = 0; i < agents.length; i++)
	    {
		// Gets the sum of the agents' velocity within a certain radius
		newVelocity = agents[i].sumOfVelocity(agents, radius);
		// Gets the sum of the repulsion vectors from agents within a certain radius, multiplies it by beta
		repulsion = o.scalarMultiplication(agents[i].sumOfRepulsion(agents, repulsionRange), beta);
		newVelocity = o.add(newVelocity, repulsion);
		newVelocity = o.rotate(newVelocity, eta * noise[i]);
		newVelocity.setR(speed);
		agents[i].setVelocity(newVelocity);
		agents[i].move(time);
	    }
    }
    public void Vicsek3(double time)
    {
	BasicNoise();
	for (int i = 0; i < agents.length; i++)
	    {
		ArrayList<Agent> a = agents[i].findNeighbors(agents, radius);
		double sum = 0;
		for (int j = 0; j < a.size(); j++)
		    {
			sum = sum + a.get(j).getDirection();
		    }
		agents[i].setDirection(sum * noise[i]/a.size());
		agents[i].move(time);
	    }
    }
    
    //-------- Vicsek Components --------\\
    // Random rotation from 0 to 2πη
    public void BasicNoise ()
    {
	for (int i = 0; i < noise.length; i++)
	    {
		noise[i] = rand.nextDouble() * 2 * Math.PI;

	    }
    }

    public void AdvancedNoise()
    {
	/* 
	   Takes previous noise, multiplies it by 1 - alpha (basically how much the previous noise affects the current noise)
	   then with probability lambda adds gamma. Also adds a random number from 0 to 1
	*/
	for (int i = 0; i < noise.length; i++)
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
		noise[i] = (1 - alpha) * noise[i] + rand.nextDouble() + gamma * r;
	    }
    }
    public void AdvancedNoise2(double time)
    {
	double r = rand.nextDouble();
	if (r > lambda * time)
	    {
		r = 0;
	    }
	else
	    {
		r = 1;
	    }
	for (int i = 0; i < noise.length; i++)
	    {
		noise[i] = noise[i] * Math.exp(-alpha * time) + Math.sqrt(Math.pow(sigma, 2)/(2 * alpha) * (1 - Math.exp(-2 * alpha * time)) * time) * (1 - 2 * rand.nextDouble()) + gamma * r * (1 - 2 * rand.nextDouble());
	    }
    }
	
    public void bounds()
    {
	// for a circle
	for (int i = 0; i < agents.length; i++)
	    {

		if (agents[i].getR() > size)
		    agents[i].setR(size);
		
	    }
    }
    public void bounds2()
    {
	// for a square
	for (int i = 0; i < agents.length; i++)
	    {
		if (agents[i].getX() > size)
		    agents[i].setX(-size);
		if (agents[i].getY() > size)
		    agents[i].setY(-size);
		if (agents[i].getX() < -size)
		    agents[i].setX(size);
		if (agents[i].getY() < -size)
		    agents[i].setY(size);
	    }
    }
			
    public void wallAvoidance(double time)
    {
	// Adds wall avoidance to the agent's noise
	for (int i = 0; i < agents.length; i++)
	    {
		noise[i] = noise[i] + (1 - Math.exp(-time / tau)) * 3 * Math.exp(-1.5 * distanceFromWall(agents[i]));
	    }
    }
    public double distanceFromWall(Agent a)
    {
	// For a circular board, the distance from a wall is simply radius minus the distance from the origin 
	return size - a.getR();
    }
    

							  

    //-------- Measurement --------\\

    
    // Returns the norm of the rotational momentum, basically measuring curvature of the agents' motion
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
	Vector out = new Vector();
	for (int i = 0; i < agents.length; i++)
	    {
		out = o.add(out, o.normalize(agents[i].getVelocity()));
	    }
	return (o.norm(out)/agents.length);
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
	


    
