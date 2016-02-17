import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

public class Graph {
	private class RankedNode implements Comparable {
		private Node node;
		private Stack<Node> path;
		private double cost;
		private double remCost;

		public RankedNode(Node n, Node goal, Stack<Node> p, double c,
				boolean byTime) {
			node = n;
			path = p;
			cost = c;
			remCost = byTime ? node.time(goal) : node.distance(goal);

			path.push(node);
		}

		public int compareTo(Object o) {
			return (int) (cost + remCost - ((RankedNode) o).cost - ((RankedNode) o).remCost);
		}

	}

	private HashMap<String, Node> nodes;
	private TreeSet<Node> interest;

	public Graph() {
		this.nodes = LoadXMLMap();
		this.interest = new TreeSet<>();
		for (String key : this.nodes.keySet()) {
			this.interest.add(this.nodes.get(key));
		}
	}
	
	public HashMap<String, Node> getNodes(){
		return this.nodes;
	}

	/**
	 * 
	 * Loads the map from the given file, hardcoded to "Cities.xml". Map is set
	 * in XMLFileInput
	 * 
	 * @return the hashmap of names to nodes
	 */
	public HashMap<String, Node> LoadXMLMap() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("Cities.xml");
			BufferedInputStream bis = new BufferedInputStream(fis);
			XMLDecoder xmlDecoder = new XMLDecoder(bis);
			HashMap<String, Node> loadedNodes = (HashMap<String, Node>) xmlDecoder.readObject();

            return loadedNodes;
		} catch (FileNotFoundException exception) {
			// This should never occurr. Map file is hard-coded in
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * Adds the passed Node to the HashMap.
	 * 
	 * @param n
	 *            The Node to add.
	 */
	public void add(Node n) {
		nodes.put(n.getName(), n);
	}

	/**
	 * Searches the nodes for the shortest path to the destination using A*.
	 * 
	 * @param start
	 *            The name of the Node to start at.
	 * @param end
	 *            The name of the goal Node.
	 * @param byTime
	 *            True if searching by time, false if by distance
	 * @return A Stack of Node's representing the shortest path.
	 * @throws Exception 
	 */
	public Object [] search(String start, String end, boolean byTime) throws Exception {
		if(!nodes.containsKey(start) || !nodes.containsKey(end)){
			throw new Exception("Invalid city names");}
		
		Node goal = nodes.get(end);
		RankedNode curr;
		HashMap<Node, Double> children;
		TreeSet<Node> visited = new TreeSet<>();

		PriorityQueue<RankedNode> q = new PriorityQueue<>();
		q.add(new RankedNode(nodes.get(start), goal, new Stack<Node>(), 0,
				byTime));

		while (!q.isEmpty()) {
			curr = q.remove();
			if (curr.node.getName().equals(goal.getName())){ // Success
				Object[] ret =  new Object[2];
				ret[0]= curr.path;
				ret[1]=curr.cost;
				return ret;
			}
			visited.add(curr.node);

			children = curr.node.generateChildren(byTime, curr.cost);
			for (Node n : children.keySet()) {
				if (!visited.contains(n))
					q.add(new RankedNode(n, goal, (Stack<Node>)curr.path.clone(), children.get(n),
							byTime));
			}
		}

		return null;
	}

	/**
	 * Return the "count" most interesting Node's in this Graph. Passing a 0 or
     * a value greater than the size will return all elements.
	 * 
	 * @param count
	 * @return
	 */
	public Node[] mostInteresting(int count) {
        Node[] ret = new Node[0];
		if (count == 0 || count > interest.size())
			return interest.toArray(ret);

		ArrayList<Node> list = new ArrayList<>();
		Iterator<Node> it = interest.iterator();

		for (int i = 0; i < count; i++) {
			list.add(it.next());
		}

		return list.toArray(ret);
	}

	/**
	 * Returns a list of different routes that optimize some combination of
	 * number of cities and total interest of the route.
	 * 
	 * @param byTime
	 *            True if the cost is measured by time, false if by distance
	 * @param maxCost
	 *            The maximum cost for the root
	 * @param start
	 *            The name of the starting Node
	 * @return
	 */
	public ArrayList<Stack<Node>> planner(boolean byTime, double maxCost,
			String start) {
		Node starting = nodes.get(start);

		ArrayList<Stack<Node>> paths = new ArrayList<>();

		// max interest
		paths.add(plannerHelper(new Stack<Node>(), new TreeSet<Node>(), starting, byTime,
                                maxCost, 0, 1, 0));

		// max number of cities
		paths.add(plannerHelper(new Stack<Node>(), new TreeSet<Node>(), starting, byTime,
                                maxCost, 0, 0, 1));

		return paths;

	}

	/**
	 * Helper method for planner that recursively finds the best route given a
	 * certain criteria.
	 * 
	 * @param path
	 *            The path taken to get to the currentCity.
	 * @param visited
	 *            A tree containing the list of visited cities to prevent
	 *            looping.
	 * @param currentCity
	 *            The city currently being recursed from.
	 * @param byTime
	 *            True if the cost is measured by time, false if by distance
	 * @param maxCost
	 *            The maximum cost for the root
	 * @param curCost
	 *            The cost to get from the starting Node to the currentCity
	 * @param intCo
	 *            The coefficient on the interest of each city
	 * @param numCo
	 *            The coefficient on the number of cities visited
	 */
	private Stack<Node> plannerHelper(Stack<Node> path, TreeSet<Node> visited,
			Node currentCity, boolean byTime, double maxCost, double curCost,
			int intCo, int numCo) {

		HashMap<Node, Double> neighbors = currentCity.generateChildren(byTime,
				curCost);

		path.push(currentCity);
		visited.add(currentCity);

		double currentBest = -10000;
		Stack<Node> bestResults = path;

		for (Node city : neighbors.keySet()) {
			// BC 1: already visited
			if (visited.contains(city))
				continue;

			// BC 2: too far
			if (neighbors.get(city) > maxCost)
				continue;

			Stack<Node> results = plannerHelper((Stack<Node>) path.clone(),
					(TreeSet<Node>) visited.clone(), city, byTime, maxCost,
					neighbors.get(city), intCo, numCo);

			double sum = 0;
			for (Node resultCity : results) {
				sum += resultCity.getInterest() * intCo + numCo;
			}

			if (sum > currentBest) {
				currentBest = sum;
				bestResults = results;
			}
		}

		return bestResults;
	}

    /**
     * Prints out an unsorted list of the Node's in this Graph.
     */
    public String toString() {
        String str = "";
        for(Node n : interest) {
            str += n.toString() + "\n";
        }

        return str;
    }
}
