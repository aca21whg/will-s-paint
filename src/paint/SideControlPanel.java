package paint;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SideControlPanel extends JPanel{
	
	private ButtonGroup tools;
	
	private JRadioButton[] toolButtons;
	
	public SideControlPanel() {
		JPanel sideControlPanel = new JPanel();
		setSize(100,700);
		setBackground(Color.lightGray);
		
		tools = new ButtonGroup();
		//creates an array of JRadioButtons for easier use
		toolButtons = new JRadioButton[5];
		toolButtons[0] = new JRadioButton("drawing");
		toolButtons[1] = new JRadioButton("eraser");
		toolButtons[2] = new JRadioButton("rectangle");
		toolButtons[3] = new JRadioButton("line");
		toolButtons[4] = new JRadioButton("circle");
		
		sideControlPanel.setLayout(new BoxLayout(sideControlPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < 5; i++) {
			tools.add(toolButtons[i]);
			sideControlPanel.add(toolButtons[i]);
		}
		this.add(sideControlPanel);
	}
	
	public String getSelectedTool() {
		for (int i = 0; i < 5; i ++) {
			if (toolButtons[i].isSelected())
				return toolButtons[i].getText();
			}
		return "drawing";
		}
}
