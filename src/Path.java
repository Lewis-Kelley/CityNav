public class Path {
	private Node nodeA;
	private Node nodeB;
	private double length;
	private double time;

	/**
	 * Initialize a new Path with: - The two specified nodes as nodeA and nodeB
	 * - The specified length, and time
	 * 
	 * Then, add self to those specified Nodes' neighbors lists
	 * 
	 * @param destination
	 * @param length
	 * @param time
	 */
	public Path(Node nodeA, Node nodeB, double length, double time) {
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.length = length;
		this.time = time;

		// Add to nodes' neighbors lists
		nodeA.addToNeighbors(this);
		nodeB.addToNeighbors(this);

		if (length < (nodeA.getCoordinate().distance(nodeB.getCoordinate())))
			System.out.println("Check length for " + this.toString());
	}

	/**
	 * Constructer for XML storage
	 */
	public Path() {

	}

	/**
	 * 
	 * @param nodeA
	 *            the new left end of the path
	 */
	public void setNodeA(Node nodeA) {
		this.nodeA = nodeA;
	}

	/**
	 * 
	 * @return the left end of the path
	 */
	public Node getNodeA() {
		return this.nodeA;
	}

	/**
	 * 
	 * @param nodeB
	 *            the new right end of the path
	 */
	public void setNodeB(Node nodeB) {
		this.nodeB = nodeB;
	}

	/**
	 * 
	 * @return the right end of the path
	 */
	public Node getNodeB() {
		return this.nodeB;
	}

	/**
	 * 
	 * @param length
	 *            the new length of the path
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * 
	 * @return the length of the path
	 */
	public double getLength() {
		return this.length;
	}

	/**
	 * 
	 * @param time
	 *            the new time taken for the path
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * 
	 * @return the time taken for the path
	 */
	public double getTime() {
		return this.time;
	}

	/**
	 * 
	 * Given one end of a path, returns the other end
	 * 
	 * @param start
	 *            the starting end of the path
	 * @return the other end of the path
	 */
	public Node destination(Node start) {
		if (this.nodeA.equals(start))
			return this.nodeB;
		return this.nodeA;
	}

	/**
	 * Return destination name/length/time as a string
	 */
	@Override
	public String toString() {
		return this.nodeA.toString() + " -> " + this.nodeB.toString() + " ("
				+ this.length + " km, " + this.time + " hr)";
	}

}
