package paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MainPanel extends JFrame{
	
	private ButtonGroup colourChoice;
	
	private JRadioButton[] colours;
		
	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		
		colourChoice = new ButtonGroup();
		//creates an array of JRadioButtons for easier use
		colours = new JRadioButton[10];
		colours[0] = new JRadioButton("red");
		colours[1] = new JRadioButton("green");
		colours[2] = new JRadioButton("blue");
		colours[3] = new JRadioButton("black");
		colours[4] = new JRadioButton("pink");
		colours[5] = new JRadioButton("yellow");
		colours[6] = new JRadioButton("cyan");
		colours[7] = new JRadioButton("orange");
		colours[8] = new JRadioButton("magenta");
		colours[9] = new JRadioButton("gray");
		
		//adds the buttons to the panel and the group so they functio properly
		controlPanel.setLayout(new FlowLayout());
		for (int i = 0; i < 10; i++) {
			colourChoice.add(colours[i]);
			controlPanel.add(colours[i]);
		}
		
		return controlPanel;
	}
														
	public MainPanel() {
		//creates the panel
		setTitle("A Simple Frame");
		setSize(900, 700);
		//sets the panel as a container for other panels
		Container contentPane = this.getContentPane();
		//creates the drawing panel
		DrawingPanel myDrawing = new DrawingPanel(this.getWidth(),this.getHeight() - 100, this);
		
		//adds the panels in the desired layout
		this.setLayout(new BorderLayout());
		this.add(createControlPanel(), BorderLayout.NORTH);
		this.add(myDrawing, BorderLayout.SOUTH);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		myDrawing.setVisible(true);
	}
	//gets the string value of the selected JRadioButton
	public String getSelectedColour() {
		for (int i = 0; i < 10; i ++) {
			if (colours[i].isSelected())
				return colours[i].getText();
		}
		return "black";
	}
	//uses getSelectedColour to return the correct Color object
	public Color getSelectedColorType() {
		String selectedColour = getSelectedColour();
		if (selectedColour.equals("red"))
			return Color.red;
		else if (selectedColour.equals("green"))
			return Color.green;
		else if (selectedColour.equals("blue"))
			return Color.blue;
		else if (selectedColour.equals("black"))
			return Color.black;
		else if (selectedColour.equals("pink"))
			return Color.pink;
		else if (selectedColour.equals("magenta"))
			return Color.magenta;
		else if (selectedColour.equals("cyan"))
			return Color.cyan;
		else if (selectedColour.equals("yellow"))
			return Color.yellow;
		else if (selectedColour.equals("gray"))
			return Color.gray;
		else if (selectedColour.equals("orange"))
			return Color.orange;
		else
			return Color.black;
	}
	
	public static void main(String[] args) {
		new MainPanel();
	}
	
	 
}
