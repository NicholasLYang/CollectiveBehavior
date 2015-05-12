public class Driver
{
    public static void main(String[] args)
    {

	Board b = new Board(100, 2, 0.75, 0.4, 0, 0.5, 0.1);
	System.out.println(b);
	for (int i = 0; i < 1000; i++)
	    {
		b.Vicsek();
		if (i % 100 == 0)
		    {
			System.out.println("Polarization is: " + b.polarization());
			System.out.println("Milling is: " + b.milling());
			System.out.println("Cohesion is: " + b.cohesion());
		    }
	    }
	System.out.println(b);
    }
}
