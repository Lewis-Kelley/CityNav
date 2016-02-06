public class Path {
	protected Node nodeA;
	protected Node nodeB;
	protected double length;
	protected double time;

	/**
	 * Initialize a new Path with:
	 * - The two specified nodes as nodeA and nodeB
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

		if (length < (nodeA.coord.distance(nodeB.coord)))
			System.out.println("Check length for " + this.toString());
	}

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
