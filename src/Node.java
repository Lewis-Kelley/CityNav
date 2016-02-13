import java.util.ArrayList;
import java.util.HashMap;

public class Node implements Comparable {
	public static final double NODE_RADIUS = 10.0;

	private String name;
	private Coordinate coord;
	private int interest;
	private boolean isVisited;
	private ArrayList<Path> neighbors = new ArrayList();

	/**
	 * 
	 * Constructs a new node with a given name, location, and interest level
	 * 
	 * @param name
	 * @param coord
	 * @param interest
	 */
	public Node(String name, Coordinate coord, int interest) {
		this.name = name;
		this.coord = coord;
		this.interest = interest;
		this.isVisited = false;
	}

	/**
	 * Empty constructor for XML input
	 */
	public Node() {

	}

	/**
	 * 
	 * For XML input ONLY
	 * 
	 * @param neighbors
	 *            sets neighbors to given list of paths
	 */
	public void setNeighbors(ArrayList<Path> neighbors) {
		this.neighbors = neighbors;
	}

	/**
	 * 
	 * @return the list of paths away from this point
	 */
	public ArrayList<Path> getNeighbors() {
		return this.neighbors;
	}

	/**
	 * 
	 * @param name
	 *            the new name of the node
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setisVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}

	public boolean getisVisited() {
		return this.isVisited;
	}

	/**
	 * 
	 * @param coord
	 *            the new coordinates of the node
	 */
	public void setCoordinate(Coordinate coord) {
		this.coord = coord;
	}

	/**
	 * 
	 * @param interest
	 *            the new interest level of the node
	 */
	public void setInterest(int interest) {
		this.interest = interest;
	}

	/**
	 * 
	 * @return the interest level of the node
	 */
	public int getInterest() {
		return this.interest;
	}

	/**
	 * 
	 * @return the coordinate of the node
	 */
	public Coordinate getCoordinate() {
		return this.coord;
	}

	/**
	 * Add the Path to neighbors
	 * 
	 * @param path
	 */
	public void addToNeighbors(Path path) {
		this.neighbors.add(path);
	}

	/**
	 * Return straight line distance to other Node's coordinate
	 * 
	 * @param other
	 * @return
	 */
	public double distance(Node other) {
		return this.coord.distance(other.coord);
	}

	/**
	 * Return straight line time to other Node's coordinate
	 * 
	 * @param other
	 * @return
	 */
	public double time(Node other) {
		return this.coord.time(other.coord);
	}

	/**
	 * Find the length associated with the Path to the specified Node. Return -1
	 * if other Node is not a neighbor.
	 * 
	 * @param other
	 * @return
	 */
	public double pathDistance(Node other) {
		for (Path neighbor : this.neighbors) {
			if (neighbor.destination(this).equals(other)) {
				return neighbor.getLength();
			}
		}

		return -1;
	}

	/**
	 * Find the time associated with the Path to the specified Node Return -1 if
	 * other Node is not a neighbor.
	 * 
	 * @param other
	 * @return
	 */
	public double pathTime(Node other) {
		for (Path neighbor : this.neighbors) {
			if (neighbor.destination(this).equals(other)) {
				return neighbor.getTime();
			}
		}
		return -1;
	}

	/**
	 * Returns a HashMap<Node,Double> linking each neighbor and (the current
	 * cost + the cost to go to that neighbor)
	 * 
	 * @param byTime
	 * @param currCost
	 * @return
	 */
	public HashMap<Node, Double> generateChildren(boolean byTime,
                                                  double currCost) {
		HashMap<Node, Double> children = new HashMap();

		if (byTime) {
			// by time: use neighbor.time
			for (Path neighbor : this.neighbors) {
				children.put(neighbor.destination(this), neighbor.getTime()
                             + currCost);
			}
		} else {
			// else, by dist: use neighbor.length
			for (Path neighbor : this.neighbors) {
				children.put(neighbor.destination(this), neighbor.getLength()
                             + currCost);
			}
		}

		return children;
	}

	/**
	 * Compares this node's interest to the other node's interest, using the
	 * standard .compareTo() method
	 * 
	 * @param other
	 * @return
	 */
	public int compareTo(Node other) {
		return this.interest - other.interest;
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
	public String toString() {
        String str = this.name + " <" + this.interest + "> " + this.coord.toString();
        for(Path p : neighbors) {
            str += "\n\t" + p.destination(this).getName() + "(D: " + p.getLength() + ", T: " + p.getTime() + ")";
        }

        return str;
    }

    @Override
    public int compareTo(Object other) {
        return this.interest - ((Node)other).interest;
	}
}
