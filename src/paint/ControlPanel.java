package paint;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class ControlPanel extends JPanel{
	
	private ButtonGroup colourChoice;
	
	private JRadioButton[] colours;
	
	private JSpinner penSize;
		
	public ControlPanel(DrawingPanel2 drawing) {
		JPanel controlPanel = new JPanel();
		setSize(900,100);
		setBackground(Color.lightGray);
		
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
		
		//sets up text box for pen thickness
		JLabel penSizeLabel = new JLabel("pen thickness");
		penSize = new JSpinner(new SpinnerNumberModel(10,1,100,1));
		
		//adds the buttons to the panel and the group so they function properly
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(penSizeLabel);
		controlPanel.add(penSize);
		for (int i = 0; i < 10; i++) {
			colourChoice.add(colours[i]);
			controlPanel.add(colours[i]);
		}
		FileChooser fileChooser = new FileChooser(drawing);
		controlPanel.add(fileChooser.getOpen());
		controlPanel.add(fileChooser.getSave());
		this.add(controlPanel);
	}
	
	//gets the string value of the selected JRadioButton
	public String getSelectedColour() {
		for (int i = 0; i < 10; i ++) {
			if (colours[i].isSelected())
				return colours[i].getText();
			}
		return "black";
		}
	
		//gets input pen size
		public int getPenSize(){
			return (int)penSize.getValue();
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
}