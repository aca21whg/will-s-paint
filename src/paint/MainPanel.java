package paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MainPanel extends JFrame{
	
	private ControlPanel controlPanel; 
	
	private SideControlPanel sideControlPanel;
															
	public MainPanel() {
		//creates the panel
		setTitle("A Simple Frame");
		setSize(950, 700);
		//sets the panel as a container for other panels
		Container contentPane = this.getContentPane();
		//creates the drawing panel
		DrawingPanel2 myDrawing = new DrawingPanel2(this.getWidth(),this.getHeight() - 100, this);
		//creates control panel
		controlPanel = new ControlPanel();
		//creates sideControlPanel
		sideControlPanel = new SideControlPanel();
		//adds the panels in the desired layout
		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.NORTH);
		this.add(sideControlPanel, BorderLayout.WEST);
		this.add(myDrawing, BorderLayout.CENTER);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		myDrawing.setVisible(true);
		controlPanel.setVisible(true);
	}
	
	public Color getSelectedColorType() {
		if (getTool().equals("eraser"))
			return(Color.white);
		return controlPanel.getSelectedColorType();
	}
	
	public int getPenSize() {
		return controlPanel.getPenSize();
	}
	
	public String getTool() {
		return sideControlPanel.getSelectedTool();
	}
	
	public static void main(String[] args) {
		new MainPanel();
	}
	
	 
}
