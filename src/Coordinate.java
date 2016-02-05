
public class Coordinate {

	protected double x;
	protected double y;
	
	/**
	 * Create a new Coordinate at (0,0) if no parameters are supplied
	 */
	public Coordinate(){
		this(0,0);
	}
	
	/**
	 * Create a new Coordinate at (x,y)
	 * @param x
	 * @param y
	 */
	public Coordinate(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Return the straight-line distance to another coordinate
	 *
	 * @param other
	 * @return
	 */
	public double distance(Coordinate other){
		double xdist = Math.abs(this.x-other.x);
		double ydist = Math.abs(this.y-other.y);
		return Math.hypot(xdist, ydist);
	}
	
//	public double time(Coordinate other){}
	
}
