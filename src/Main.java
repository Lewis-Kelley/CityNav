import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {

		JFrame CityNavFrame = new JFrame();

		JPanel CityNavPanel = new JPanel();
		CityNavFrame.add(CityNavPanel);

		JPanel ControlsPanel = new JPanel();
		CityNavPanel.add(ControlsPanel);

		JPanel PathfinderPanel = new JPanel();
		ControlsPanel.add(PathfinderPanel);

		CityNavFrame.setVisible(true);
	}

	public void demo() {
		Node one = new Node("Node1", new Coordinate(0, 0), 50);
		Node two = new Node("Node2", new Coordinate(0, 50), 75);
		Node three = new Node("Node3", new Coordinate(50, 50), 95);

		Path pathOneTwo = new Path(one, two, 60, 1);
		Path pathOneThree = new Path(one, three, 80, 1.25);
		System.out.println(one.generateChildren(false, 0));
	}

}
