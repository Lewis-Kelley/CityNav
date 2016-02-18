import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JComponent;
import javax.swing.JTextPane;

/**
 * 
 * Handles graphics for the map of the city
 * 
 * @author blankesa. Created Feb 11, 2016.
 */
public class GraphAndOutputComponent extends JComponent {
	public Graph cityMap;
	private JTextPane outputText;
	private HashMap<Node, Ellipse2D.Double> imageCircles;
	private Stack<Node> lastPath;
	private static final double scale = 7;
	private static final int cityOffsetX = 100;
	private static final int cityOffsetY = 50;
	private static final int diam = 10;
	private HashMap<Path, Line2D.Double> pathLines;
	private String textToDisplay;

	/**
	 * 
	 * Constructs a new component holding a map of the city. This also
	 * constructs a new graph holding the city
	 * 
	 */
	public GraphAndOutputComponent(JTextPane out) {
		this.cityMap = new Graph();
		this.outputText = out;
		this.imageCircles = new HashMap<Node, Ellipse2D.Double>();
		this.lastPath = new Stack<Node>();
		this.pathLines = new HashMap<Path, Line2D.Double>();

		ArrayList<Node> visitedNodes = new ArrayList<Node>();

		for (String key : this.cityMap.getNodes().keySet()) {
			Node tempNode = this.cityMap.getNodes().get(key);

			Ellipse2D.Double cityEllipse = new Ellipse2D.Double(tempNode
					.getCoordinate().getX() * scale + cityOffsetX, tempNode
					.getCoordinate().getY() * scale + cityOffsetY, diam, diam);

			visitedNodes.add(tempNode);
			this.imageCircles.put(tempNode, cityEllipse);

			for (Path eachPath : tempNode.getNeighbors()) {
				// check to ensure path hasn't already been drawn from other
				// start point
				if (!visitedNodes.contains(eachPath.destination(tempNode))) {
					Line2D.Double pathLine = new Line2D.Double(eachPath
							.getNodeA().getCoordinate().getX()
							* scale + cityOffsetX + diam / 2, eachPath
							.getNodeA().getCoordinate().getY()
							* scale + cityOffsetY + diam / 2, eachPath
							.getNodeB().getCoordinate().getX()
							* scale + cityOffsetX + diam / 2, eachPath
							.getNodeB().getCoordinate().getY()
							* scale + cityOffsetY + diam / 2);
					this.pathLines.put(eachPath, pathLine);
				}
			}
		}
	}

	/**
	 * Redraws the nodes and paths. Nodes are colored RED if part of the
	 * suggested route, and BLUE otherwise
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		super.paintComponent(graphics);

		for (Node key : this.imageCircles.keySet()) {
			Ellipse2D.Double cityEllipse = this.imageCircles.get(key);
			if (key.getisVisited()) {
				graphics.setColor(Color.RED);
				graphics.fill(cityEllipse);
			} else {
				graphics.setColor(Color.BLUE);
				graphics.fill(cityEllipse);
			}

			graphics.setColor(Color.BLACK);
			graphics.drawString(key.getName(), (int) cityEllipse.getX(),
					(int) cityEllipse.getY());
		}

		for (Path key : this.pathLines.keySet()) {
			Line2D.Double pathLine = this.pathLines.get(key);
			if (key.getisOnRoute()) {
				graphics.setColor(Color.RED);
			} else {
				graphics.setColor(Color.BLACK);
			}
			graphics.draw(pathLine);
			graphics.setColor(Color.BLACK);
			graphics.drawString(key.getLength() + "/" + key.getTime(),
					(int) (pathLine.x1 + pathLine.x2) / 2,
					(int) (pathLine.y1 + pathLine.y2) / 2);
		}

		this.outputText.setText(this.textToDisplay);
	}

	/**
	 * Sets the text to the list of cities in order of interest.
	 */
	public void displayIntCities() {
		String res = "";
		Node[] list = cityMap.mostInteresting(0);
		for (int i = list.length - 1; i >= 0; i--) {
			res += list[i].getName() + ": " + list[i].getInterest() + "\n";
		}

		this.textToDisplay = res;
	}

	/**
	 * Finds the path between start and end, updates the visuals of the map, and
	 * stores the path into the output text.
	 * 
	 * @param start
	 * @param end
	 * @param byTime
	 */
	public void findPath(String start, String end, boolean byTime) {
		while (!lastPath.isEmpty()) {
			Node tempNode = lastPath.pop();
			tempNode.setisVisited(false);
			for (Path tempPath : tempNode.getNeighbors()) {
				tempPath.setisOnRoute(false);
			}
		}

		try {
			Object[] searchResults = cityMap.search(start, end, byTime);
			lastPath = (Stack<Node>) searchResults[0];
			String cost = ((Double) searchResults[1]).toString();

			Node[] visited = lastPath.toArray(new Node[0]);

			String res;
			if (byTime) {
				res = cost + " hours:\n";
			} else {
				res = cost + " km:\n";
			}

			for (int i = 0; i < visited.length; i++) {
				visited[i].setisVisited(true);
				res += visited[i].getName() + "\n";
				if (i < visited.length - 1) {
					for (Path tempPath : visited[i].getNeighbors()) {
						if (tempPath.destination(visited[i]).equals(
								visited[i + 1])) {
							tempPath.setisOnRoute(true);
						}
					}
				}
			}

			this.textToDisplay = res;
		} catch (Exception e) {
			System.out.println(e);
			this.textToDisplay = "ERROR: Please check the names of\nthe cities you are entering.";
		}
	}

	public void planner(String start, String amount, boolean byTime) {
		while (!lastPath.isEmpty()) {
			Node tempNode = lastPath.pop();
			tempNode.setisVisited(false);
			for (Path tempPath : tempNode.getNeighbors()) {
				tempPath.setisOnRoute(false);
			}
		}

		try {
			ArrayList<Stack<Node>> paths = cityMap.planner(byTime,
					Double.valueOf(amount), start);
			String res = "";
			String[] pathDescriptions = { "Highest interest (Displayed)", "Most Cities" };
			;
			for (int j = 0; j < paths.size(); j++) {
				Node[] visited = paths.get(j).toArray(new Node[0]);
				res += pathDescriptions[j] + "\n";

				for (int i = 0; i < visited.length; i++) {
					res += "\t" + visited[i].getName() + "\n";
				}
			}

			this.textToDisplay = res;

			lastPath = paths.get(0);

			Node[] visited = lastPath.toArray(new Node[0]);

			for (int i = 0; i < visited.length; i++) {
				visited[i].setisVisited(true);
				res += visited[i].getName() + "\n";
				if (i < visited.length - 1) {
					for (Path tempPath : visited[i].getNeighbors()) {
						if (tempPath.destination(visited[i]).equals(
								visited[i + 1])) {
							tempPath.setisOnRoute(true);
						}
					}
				}
			}

		} catch (Exception e) {
			this.textToDisplay = "ERROR: Please check the names of\n"
					+ "the cities you are entering and that you entered\n"
					+ "a valid amount.";
		}
	}

	public void searchCity(String city) {
		Node c = cityMap.getNodes().get(capitalize(city));

		while (!lastPath.isEmpty()) {
			Node tempNode = lastPath.pop();
			tempNode.setisVisited(false);
			for (Path tempPath : tempNode.getNeighbors()) {
				tempPath.setisOnRoute(false);
			}
		}

		if (c == null) {
			this.textToDisplay = "ERROR: Please check the names of\n"
					+ "the cities you are entering.";
		} else {
			this.textToDisplay = c.toString();
			lastPath.push(c);
			c.setisVisited(true);
		}
	}

	public void setText(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}

	/**
	 * Capitalize first letter of every word in the input string, return
	 * capitalized version
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		return Main.capitalize(str);
	}
}
