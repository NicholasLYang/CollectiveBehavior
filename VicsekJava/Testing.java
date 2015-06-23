import java.io.*;
import java.util.*; 

public class Testing
{
    public static void main(String[] args)
    {
	Agent[] a = new Agent[100];
	for (int i = 0; i < 100; i++)
	    {
		a[i] = new Agent(10, 1);
		System.out.println(a[i]);
	    }
	for (int j = 0; j < 100; j++)
	    {
		System.out.println(a[j].repulsion(a, 2));
	    }
				   

    }

}
