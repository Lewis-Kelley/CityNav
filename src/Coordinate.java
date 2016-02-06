public class Coordinate {

	private double x;
	private double y;

	/**
	 * Create a new Coordinate at (0,0) if no parameters are supplied
	 */
	public Coordinate() {
		this(0, 0);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	/**
	 * Create a new Coordinate at (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Return the straight-line distance to another coordinate
	 * 
	 * @param other
	 * @return
	 */
	public double distance(Coordinate other) {
		double xdist = Math.abs(this.x - other.x);
		double ydist = Math.abs(this.y - other.y);
		return Math.hypot(xdist, ydist);
	}

	/**
	 * Return the straight-line time estimate to another Coordinate, assuming
	 * 64.4 km/h
	 * 
	 * @param other
	 * @return
	 */
	public double time(Coordinate other) {
		double xdist = Math.abs(this.x - other.x);
		double ydist = Math.abs(this.y - other.y);
		return Math.hypot(xdist, ydist) / 64.4;
	}

	/**
	 * Return (x,y)
	 */
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
