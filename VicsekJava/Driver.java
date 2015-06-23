import java.io.*;
import java.util.*;
public class Driver
{

    public static void main(String[] args)
    {
	

	//  Board (# of Agents, size of Board, radius, repulsion range, speed, alpha, beta, gamma, eta, lambda, sigma, tau)
	double d = 0;
	double p;
	String[] y = new String[1000];
	String[] x = new String[1000];
	String[] u = new String[1000];
	String[] v = new String[1000];
	for (int i = 0; i < 1000; i++)
	    {
		x[i] = new String();
		y[i] = new String();
		u[i] = new String();
		v[i] = new String();
	    }
	String noise = new String();
       	Board b = new Board(1000, 100, 5, 3, 10, 0.8, 0.4, 0.05, 0.2, 0.01, 0.05, 0.2);
	for (int i = 0; i < 500; i++)
	    {

		Agent[] agents = b.getAgents();
		b.Vicsek2(1);
		b.bounds();
		p = b.polarization();
		d = d + p;
		for (int j = 0; j < 1000; j++)
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
	for (int i = 0; i < 1000; i++)
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

