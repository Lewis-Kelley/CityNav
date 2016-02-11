import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

/**
 * 
 * Handles graphics for the map of the city
 * 
 * @author blankesa. Created Feb 11, 2016.
 */
public class GraphAndOutputComponent extends JComponent {
	public Graph cityMap;
	private TextArea outputText;
	private HashMap<Node, Ellipse2D.Double> imageCircles;
	private static final double scale = 1.0;
	private static final int cityOffsetX = 0;
	private static final int cityOffsetY = 0;
	private static final int diam = 10;
	private HashMap<Path, Line2D.Double> pathLines;
	private String textToDisplay;

	/**
	 * 
	 * Constructs a new component holding a map of the city. This also
	 * constructs a new graph holding the city
	 * 
	 */
	public GraphAndOutputComponent() {
		this.cityMap = new Graph();
		this.outputText = new TextArea("Welcome to CityNav");
		ArrayList<Node> visitedNodes = new ArrayList<Node>();
		this.imageCircles = new HashMap<Node, Ellipse2D.Double>();
		this.pathLines = new HashMap<Path, Line2D.Double>();
		for (String key : this.cityMap.getNodes().keySet()) {
			Node tempNode = this.cityMap.getNodes().get(key);
			visitedNodes.add(tempNode);
			Ellipse2D.Double cityEllipse = new Ellipse2D.Double(tempNode
					.getCoordinate().getX() * scale + cityOffsetX, tempNode
					.getCoordinate().getY() * scale + cityOffsetY, diam, diam);
			this.imageCircles.put(tempNode, cityEllipse);
			for (Path eachPath : tempNode.getNeighbors()) {
				// check to ensure path hasn't already been drawn from other
				// start point
				if (!visitedNodes.contains(eachPath.destination(tempNode))) {
					Line2D.Double pathLine = new Line2D.Double(eachPath
							.getNodeA().getCoordinate().getX(), eachPath
							.getNodeA().getCoordinate().getY(), eachPath
							.getNodeB().getCoordinate().getX(), eachPath
							.getNodeB().getCoordinate().getY());
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
		}

		for (Path key : this.pathLines.keySet()) {
			Line2D.Double pathLine = this.pathLines.get(key);
			graphics.draw(pathLine);
		}

		this.outputText.setText(this.textToDisplay);
	}

	public void setText(String textToDisplay) {
		this.textToDisplay = textToDisplay;
	}

}
