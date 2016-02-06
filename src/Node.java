import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {

	protected String name;
	protected Coordinate coord;
	protected int interest;
	protected ArrayList<Path> neighbors;

	public Node(String name, Coordinate coord, int interest) {
		this.name = name;
		this.coord = coord;
		this.interest = interest;
	}

	
	/**
	 * Return straight line distance to other Node's coordinate
	 *
	 * @param other
	 * @return
	 */
	public double distance(Node other){
		return this.coord.distance(other.coord);
	}
	
	/**
	 * Return straight line time to other Node's coordinate
	 *
	 * @param other
	 * @return
	 */
	public double time(Node other){
		return this.coord.time(other.coord);
	}
	
	/**
	 * Find the length associated with the Path to the specified Node.
	 * Return -1 if other Node is not a neighbor.
	 *
	 * @param other
	 * @return
	 */
	public double pathDistance(Node other) {
		for (Path neighbor : this.neighbors) {
			if (neighbor.destination.equals(other)) {
				return neighbor.length;
			}
		}

		return -1;
	}

	/**
	 * Find the time associated with the Path to the specified Node
	 * Return -1 if other Node is not a neighbor.
	 *
	 * @param other
	 * @return
	 */
	public double pathTime(Node other) {
		for (Path neighbor : this.neighbors) {
			if (neighbor.destination.equals(other)) {
				return neighbor.time;
			}
		}
		return -1;
	}

	/**
	 * Returns a HashMap<Node,Double> linking each neighbor and (the current cost + the cost to go
	 * to that neighbor)
	 *
	 * @param byTime
	 * @param currCost
	 * @return
	 */
	public HashMap<Node, Double> generateChildren(boolean byTime, int currCost) {
		HashMap<Node, Double> children = new HashMap<>();

		if (byTime) {
			// by time: use neighbor.time
			for (Path neighbor : this.neighbors) {
				children.put(neighbor.destination, neighbor.time + currCost);
			}
		} else {
			// else, by dist: use neighbor.length
			for (Path neighbor : this.neighbors) {
				children.put(neighbor.destination, neighbor.length + currCost);
			}
		}

		return children;
	}

	/**
	 * Compares this node's interest to the other node's interest, using the standard .compareTo()
	 * method
	 *
	 * @param other
	 * @return
	 */
	public int compareTo(Node other) {
		return ((Comparable) this.interest)
				.compareTo((Comparable) other.interest);
	}

	/**
	 * Return name
	 *
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Return this Node's name and coordinate as a String
	 */
	@Override
	public String toString(){
		return this.name+" "+this.coord.toString();
	}
	
	public void draw(Graphics2D g) {
		//
	}
}
