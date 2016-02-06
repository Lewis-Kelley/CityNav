import java.util.Stack;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.ArrayList;

public class Graph {
	private class RankedNode implements Comparable {
		private Node node;
		private Stack<Node> path;
		private double cost;
		private double remCost;

		public RankedNode(Node n, Node goal, Stack<Node> p, double c, boolean byTime) {
			node = n;
			path = p;
			cost = c;
			remCost = byTime ? node.time(goal) : node.distance(goal);

			path.push(node);
		}

		public int compareTo(Object o) {
			return (int)(cost + remCost - ((RankedNode)o).cost - ((RankedNode)o).remCost);
		}
	}
	
	private HashMap<String, Node> nodes;
	private TreeSet<Node> interest;
	private OutputTextBox output;

	public Graph(OutputTextBox out) {
		nodes = new HashMap<>();
		interest = new TreeSet<>();
		output = out;
	}

	/**
	 * Adds the passed Node to the HashMap.
	 *
	 * @param n The Node to add.
	 */
	public void add(Node n) {
		nodes.put(n.getName(), n);
	}

	/**
	 * Searches the nodes for the shortest path to the destination using A*.
	 *
	 * @param start The name of the Node to start at.
	 * @param end The name of the goal Node.
	 * @param byTime True if searching by time, false if by distance
	 * @return A Stack of Node's representing the shortest path.
	 */
	public Stack<Node> search(String start, String end, boolean byTime) {
		Node goal = nodes.get(end);
		RankedNode curr;
		HashMap<Node, Double> children;
		TreeSet<Node> visited = new TreeSet<>();

		PriorityQueue<RankedNode> q = new PriorityQueue<>();
		q.add(new RankedNode(nodes.get(start), goal, new Stack<Node>(), 0, byTime));

		while(!q.isEmpty()) {
			curr = q.remove();
			if(curr.node.getName().equals(goal.getName())) //Sucess
				return curr.path;

			visited.add(curr.node);

			children = curr.node.generateChildren(byTime, curr.cost);
			for(Node n : children.keySet()) {
				if(!visited.contains(n))
					q.add(new RankedNode(n, goal, curr.path, (double)children.get(n), byTime));
			}
		}

		return null;
	}

	/**
	 * Return the "count" most interesting Node's in this Graph. Passing a 0 will return all elements.
	 *
	 * @param count
	 * @return
	 */
	public Node[] mostInteresting(int count) {
		if(count == 0 || count > interest.size())
			return (Node[])interest.toArray();
		
		ArrayList<Node> list = new ArrayList<>();
		Iterator<Node> it = interest.iterator();

		for(int i = 0; i < count; i++) {
			list.add(it.next());
		}

		return (Node[])list.toArray();
	}
}
