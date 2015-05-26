public class Operator
{
    public void rotate(Vector v, double d)
    {
	v.setTheta(mod(v.getTheta() + d, 2 * Math.PI));
	
    }
    public double distance (Vector v1, Vector v2)
    {
	double[] c1 = v1.getCoords();
	double[] c2 = v2.getCoords();
	return Math.sqrt(Math.pow(c1[1] - c2[1], 2) + Math.pow(c1[0] - c2[0], 2));
    }
    // returns this vector minus v
    public Vector difference (Vector v1, Vector v2)
    {
	double[] c1 = v1.getCoords();
	double[] c2 = v2.getCoords();
	Vector out = new Vector(new double[] {c1[0] - c2[0], c1[1] - c2[1]});
	return out;
    }
    public Vector add (Vector v1, Vector v2)
    {
	double[] c1 = v1.getCoords();
	double[] c2 = v2.getCoords();
	Vector out = new Vector(new double[] {c1[0] + c2[0], c1[1] + c2[1]});
	return out;
    }
    public Vector scalarMultiplication(Vector v, double d)
    {
	double[] c = v.getCoords();                                          
	c = new double[] {c[0] * d, c[1] * d};
	Vector out = new Vector(c);
	return out; 
    }
    public double length(double[] a)
    {
	return Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
    }
    public double norm (Vector v)
    {
	return this.length(v.getCoords());
    }
    public double mod (double x, double n)
    {
	double r = x % n;
	if (r > 0 && x < 0)
	    {
		r -= n;
	    }
	return r;
    }
    public Vector normalize (Vector v)
    {
	Vector out = new Vector(1, v.getTheta());
	return out;
    }
}
