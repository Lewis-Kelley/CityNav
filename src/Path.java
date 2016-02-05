
public class Path {
	protected Node destination;
	protected double length;
	protected double time;
	
	/**
	 * Initialize a new Path associated with the specified destination Node, length, and time. 
	 *
	 * @param destination
	 * @param length
	 * @param time
	 */
	public Path(Node destination, double length, double time) {
		this.destination=destination;
		this.length=length;
		this.time=time;
	}

}
