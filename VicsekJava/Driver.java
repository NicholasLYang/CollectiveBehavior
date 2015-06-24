import java.io.*;
import java.util.*;
public class Driver
{

    public static void main(String[] args)
    {
	
	int n = Integer.parseInt(args[0]);
	int frames = 500;
	//  Board (# of Agents, size of Board, radius, repulsion range, speed, alpha, beta, gamma, eta, lambda, sigma, tau)
	double d = 0;
	double p;
	String[] y = new String[n];
	String[] x = new String[n];
	String[] u = new String[n];
	String[] v = new String[n];
	for (int i = 0; i < n; i++)
	    {
		x[i] = new String();
		y[i] = new String();
		u[i] = new String();
		v[i] = new String();
	    }
	String noise = new String();
       	Board b = new Board(n, 500, 50, 15, 50, 0.8, 0.8, 0.05, 0.2, 0.01, 0.05, 0.2);
	for (int i = 0; i < frames; i++)
	    {

		Agent[] agents = b.getAgents();
		b.Vicsek2(1);
		b.bounds();
		p = b.polarization();
		d = d + p;
		for (int j = 0; j < n; j++)
		    {
			x[j] = x[j] + agents[j].getX() + " ";
			y[j] = y[j] + agents[j].getY() + " ";
			u[j] = u[j] + agents[j].getVelocity().getX() + " ";
			v[j] = v[j] + agents[j].getVelocity().getY() + " ";
		    }
		if (i % 100 == 0)
		    {
			System.out.println(i + " Polarization: " + p);
			
		    }
		/*
		b.AdvancedNoise();
		
		noise = noise + b.getNoise(0) + " ";
		*/

	    }
	System.out.println(noise);
	PrintStream printY, printX, printU, printV;
	for (int i = 0; i < n; i++)
	    {
		try
		    {
	    
		        printY = new PrintStream(new FileOutputStream("y" + (i + 1) +".txt"));
			printY.print(y[i]);
		        printX = new PrintStream(new FileOutputStream("x" + (i + 1) + ".txt"));
			printX.print(x[i]);
			printU = new PrintStream(new FileOutputStream("u" + (i + 1) + ".txt"));
			printU.print(u[i]);
			printV = new PrintStream(new FileOutputStream("v" + (i + 1) + ".txt"));
			printV.print(v[i]);
			/*
			  PrintStream n = new PrintStream(new FileOutputStream("n.txt"));
			  n.print(noise);
			*/
		    }
		catch (Exception e)
		    { }
	    }
    }
    

}

