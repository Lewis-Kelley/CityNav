import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Main {

	public static void main(String[] args) {

		JFrame CityNavFrame = new JFrame();
		CityNavFrame.setResizable(false);
		CityNavFrame.setSize(1000, 700);

		JPanel CityNavPanel = new JPanel(new BorderLayout());
		CityNavFrame.add(CityNavPanel);
		{
			GraphAndOutputComponent gr = new GraphAndOutputComponent();
			CityNavPanel.add(gr, BorderLayout.CENTER);
			
			JPanel ControlsPanel = new JPanel(new BorderLayout());
			CityNavPanel.add(ControlsPanel, BorderLayout.WEST);
			{
				JPanel ControlsNorthPanel = new JPanel(new BorderLayout());
				ControlsPanel.add(ControlsNorthPanel, BorderLayout.NORTH);
				{
					JButton InterestButton = new JButton("View Cities By Interest");
					ControlsNorthPanel.add(InterestButton, BorderLayout.NORTH);

					JPanel PathfinderPanel = new JPanel(new BorderLayout());
					ControlsNorthPanel.add(PathfinderPanel, BorderLayout.SOUTH);
					{
						JPanel PathfinderLabelsPanel = new JPanel(new BorderLayout());
						PathfinderPanel.add(PathfinderLabelsPanel, BorderLayout.NORTH);
						{
							JLabel PathfinderStartLabel = new JLabel("Start");
							PathfinderLabelsPanel.add(PathfinderStartLabel, BorderLayout.WEST);

							JLabel PathfinderEndLabel = new JLabel("End");
							PathfinderLabelsPanel.add(PathfinderEndLabel, BorderLayout.EAST);
						}

						JPanel PathfinderInputPanel = new JPanel(new BorderLayout());
						PathfinderPanel.add(PathfinderInputPanel, BorderLayout.CENTER);
						{
							JTextField PathfinderStart = new JTextField(10);
							PathfinderInputPanel.add(PathfinderStart, BorderLayout.WEST);

							JTextField PathfinderEnd = new JTextField(10);
							PathfinderInputPanel.add(PathfinderEnd, BorderLayout.EAST);
						}

						JPanel PathfinderButtonsPanel = new JPanel(new BorderLayout());
						PathfinderPanel.add(PathfinderButtonsPanel, BorderLayout.SOUTH);
						{
							JButton PathfinderTimeButton = new JButton("   Time   ");
							PathfinderButtonsPanel.add(PathfinderTimeButton, BorderLayout.EAST);

							JButton PathfinderDistanceButton = new JButton("Distance");
							PathfinderButtonsPanel.add(PathfinderDistanceButton, BorderLayout.WEST);
						}
					}

				}

				JPanel ControlsSouthPanel = new JPanel(new BorderLayout());
				ControlsPanel.add(ControlsSouthPanel, BorderLayout.SOUTH);
				{
					JPanel PlannerPanel = new JPanel(new BorderLayout());
					ControlsSouthPanel.add(PlannerPanel, BorderLayout.NORTH);
					{
						JPanel PlannerLabelsPanel = new JPanel(new BorderLayout());
						PlannerPanel.add(PlannerLabelsPanel, BorderLayout.NORTH);
						{
							JLabel PlannerStartLabel = new JLabel("Start");
							PlannerLabelsPanel.add(PlannerStartLabel, BorderLayout.WEST);

							JLabel PlannerEndLabel = new JLabel("Amount");
							PlannerLabelsPanel.add(PlannerEndLabel, BorderLayout.EAST);
						}

						JPanel PlannerInputPanel = new JPanel(new BorderLayout());
						PlannerPanel.add(PlannerInputPanel, BorderLayout.CENTER);
						{
							JTextField PlannerStart = new JTextField(10);
							PlannerInputPanel.add(PlannerStart, BorderLayout.WEST);

							JTextField PlannerEnd = new JTextField(10);
							PlannerInputPanel.add(PlannerEnd, BorderLayout.EAST);
						}

						JPanel PlannerButtonsPanel = new JPanel(new BorderLayout());
						PlannerPanel.add(PlannerButtonsPanel, BorderLayout.SOUTH);
						{
							JButton PlannerTimeButton = new JButton("   Time   ");
							PlannerButtonsPanel.add(PlannerTimeButton, BorderLayout.EAST);

							JButton PlannerDistanceButton = new JButton("Distance");
							PlannerButtonsPanel.add(PlannerDistanceButton, BorderLayout.WEST);
						}
					}

					JTextPane OutputText = new JTextPane();
					OutputText.setText("This is where output will go.\n\n\n\n\n\n\n");
					ControlsSouthPanel.add(OutputText, BorderLayout.SOUTH);
				}
			}
		}

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
