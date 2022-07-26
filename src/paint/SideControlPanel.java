package paint;

import java.awt.Color;
import java.awt.FlowLayout;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SideControlPanel extends JPanel{
	
	private ButtonGroup tools;
	
	private JRadioButton[] toolButtons;
	
	public SideControlPanel() {
		JPanel sideControlPanel = new JPanel();
		setSize(150,700);
		setBackground(Color.lightGray);
		//loads images for the buttons icons
		ImageIcon rectangle = new ImageIcon(getClass().getResource("images/rectangle.png"));
		ImageIcon circle = new ImageIcon(getClass().getResource("images/circle.png"));
		ImageIcon line = new ImageIcon(getClass().getResource("images/line.png"));
		ImageIcon eraser = new ImageIcon(getClass().getResource("images/eraser.png"));
		ImageIcon pen = new ImageIcon(getClass().getResource("images/pen.png"));
		ImageIcon fill = new ImageIcon(getClass().getResource("images/fill.png"));
		
		tools = new ButtonGroup();
		//creates an array of JRadioButtons for easier use
		toolButtons = new JRadioButton[6];
		toolButtons[0] = new JRadioButton("drawing",pen);
		toolButtons[1] = new JRadioButton("eraser",eraser);
		toolButtons[2] = new JRadioButton("rectangle", rectangle);
		toolButtons[3] = new JRadioButton("line",line);
		toolButtons[4] = new JRadioButton("circle",circle);
		toolButtons[5] = new JRadioButton("fill",fill);
		
		sideControlPanel.setLayout(new BoxLayout(sideControlPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < 6; i++) {
			tools.add(toolButtons[i]);
			sideControlPanel.add(toolButtons[i]);
		}
		this.add(sideControlPanel);
	}
	
	public String getSelectedTool() {
		for (int i = 0; i < 6; i ++) {
			if (toolButtons[i].isSelected())
				return toolButtons[i].getText();
			}
		return "drawing";
		}
}
