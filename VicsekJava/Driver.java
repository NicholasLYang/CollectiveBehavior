public class Driver
{
    public static void main(String[] args)
    {
  // # of agents, area of board, radius of interaction, speed of agents and eta
	
	Board b = new Board(10, 10, 8, 1, 0.2, 1, 0.1);
	System.out.println(b);
	for (int i = 0; i < 1000; i++)
	    {
		b.Vicsek();
		if (i % 250 == 0)
		    {
			System.out.println("Polarization is: " + b.polarization());
			System.out.println("Milling is: " + b.milling());
			System.out.println("Cohesion is: " + b.cohesion());
		    }
	    }
	System.out.println(b);
	/*
	Agent[] a = new Agent[10];
	for (int i = 0; i < 10; i++)
	    {
		a[i] = new Agent(10, 2);
		System.out.println(a[i]);
	    }
	System.out.println();
	Agent[] neighbors = a[5].findNeighbors(a, 8);
	for (int j = 0; j < neighbors.length; j++)
	    {
		System.out.println(neighbors[j]);
	    }
	*/
    }
}
