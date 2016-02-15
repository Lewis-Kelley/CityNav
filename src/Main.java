import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		CityNavFrame.setSize(1500, 1000);
		
		// Items that various things need to have access to
		JTextPane OutputText = new JTextPane();
		final GraphAndOutputComponent gr = new GraphAndOutputComponent(OutputText);
		final JTextField PathfinderStart = new JTextField(10);
		final JTextField PathfinderEnd = new JTextField(10);
		final JTextField PlannerStart = new JTextField(10);
		final JTextField PlannerEnd = new JTextField(10);

		JPanel CityNavPanel = new JPanel(new BorderLayout());
		CityNavFrame.add(CityNavPanel);
		{
			CityNavPanel.add(gr, BorderLayout.CENTER);
			
			JPanel ControlsPanel = new JPanel(new BorderLayout());
			CityNavPanel.add(ControlsPanel, BorderLayout.WEST);
			{
				JPanel ControlsNorthPanel = new JPanel(new BorderLayout());
				ControlsPanel.add(ControlsNorthPanel, BorderLayout.NORTH);
				{
					JButton InterestButton = new JButton("View Cities By Interest");
					InterestButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							gr.displayIntCities();
							gr.repaint();
						}
						
					});
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
							PathfinderInputPanel.add(PathfinderStart, BorderLayout.WEST);

							PathfinderInputPanel.add(PathfinderEnd, BorderLayout.EAST);
						}

						JPanel PathfinderButtonsPanel = new JPanel(new BorderLayout());
						PathfinderPanel.add(PathfinderButtonsPanel, BorderLayout.SOUTH);
						{
							JButton PathfinderTimeButton = new JButton("   Time   ");
							PathfinderTimeButton.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									gr.findPath(PathfinderStart.getText(), PathfinderEnd.getText(), true);
									gr.repaint();
								}
								
							});
							PathfinderButtonsPanel.add(PathfinderTimeButton, BorderLayout.EAST);

							JButton PathfinderDistanceButton = new JButton("Distance");
							PathfinderDistanceButton.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									gr.findPath(PathfinderStart.getText(), PathfinderEnd.getText(), false);
									gr.repaint();
								}
								
							});
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
							PlannerInputPanel.add(PlannerStart, BorderLayout.WEST);

							PlannerInputPanel.add(PlannerEnd, BorderLayout.EAST);
						}

						JPanel PlannerButtonsPanel = new JPanel(new BorderLayout());
						PlannerPanel.add(PlannerButtonsPanel, BorderLayout.SOUTH);
						{
							JButton PlannerTimeButton = new JButton("   Time   ");
							PlannerTimeButton.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									gr.planner(PlannerStart.getText(), PlannerEnd.getText(), true);		
									gr.repaint();
								}
								
							});
							PlannerButtonsPanel.add(PlannerTimeButton, BorderLayout.EAST);

							JButton PlannerDistanceButton = new JButton("Distance");
							PlannerDistanceButton.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									gr.planner(PlannerStart.getText(), PlannerEnd.getText(), false);	
									gr.repaint();
								}
								
							});
							PlannerButtonsPanel.add(PlannerDistanceButton, BorderLayout.WEST);
						}
					}
					
					gr.setText("This is where output will go.\n\n\n\n\n\n\n");
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
