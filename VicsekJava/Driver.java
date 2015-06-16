import java.io.*;
import java.util.*;
public class Driver
{

    public static void main(String[] args)
    {
	
  // Board(# of agents, size of board, radius, speed, eta, beta, repulsion range);
	double d = 0;
	double p;
	String s1 = new String();
	String s2 = new String();
	Board b = new Board(10000, 100, 8, 1, 0, 1, 0.1);

	for (int i = 1; i < 2001; i++)
	    {
		// n = 10,000 t = 2,000,  
		b.Vicsek();
		p = b.polarization();
		d = d + p;
		if (i % 40 == 0)
		    {
			b.setEta(b.getEta() + 0.02);
			System.out.println(i);
			s1 = s1 + d/40 + " ";
			s2 = s2 + b.getEta() + " ";
			d = 0;
		    }
		/*
		  System.out.println("Milling is: " + b.milling());
		  System.out.println("Cohesion is: " + b.cohesion());
		*/
		    
	    }
	try
	{
	    PrintStream y = new PrintStream(new FileOutputStream("y.txt"));
	    y.print(s1);
	    PrintStream x = new PrintStream(new FileOutputStream("x.txt"));
	    x.print(s2);
	}
	catch (Exception e)
	{ }

	    

    }
	

}

