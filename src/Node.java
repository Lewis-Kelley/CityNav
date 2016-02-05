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
	 * Find the length associated with the Path to the specified Node
	 *
	 * @param other
	 * @return
	 */
	public double distance(Node other) {
		for (Path neighbor : neighbors) {
			if (neighbor.destination.equals(other)) {
				return neighbor.length;
			}
		}

		return 0;
	}

	/**
	 * Find the time associated with the Path to the specified Node
	 *
	 * @param other
	 * @return
	 */
	public double time(Node other) {
		for (Path neighbor : neighbors) {
			if (neighbor.destination.equals(other)) {
				return neighbor.time;
			}
		}

		return 0;
	}

	public HashMap<Node, Integer> generateChildren(boolean byTime, int currCost) {
		return null;
	}

	public int compareTo(Node other) {
		return 0;
	}

	public void draw(Graphics2D g) {
		//
	}
}
