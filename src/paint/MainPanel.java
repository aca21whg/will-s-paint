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
	
	private int drawingSizeX;
	
	private int drawingSizeY;
															
	public MainPanel() {
		//creates the panel
		setTitle("A Simple Frame");
		setSize(950, 700);
		//sets the panel as a container for other panels
		Container contentPane = this.getContentPane();
		//creating a JDialog panel too allow users to select drawing size
		chooseDrawingSize(this);
		setSize(drawingSizeX + 150,drawingSizeY + 100);
		//creates the drawing panel
		DrawingPanel2 myDrawing = new DrawingPanel2(drawingSizeX,drawingSizeY, this);
		//creates control panel
		controlPanel = new ControlPanel(myDrawing);
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
	
	public void chooseDrawingSize(JFrame f) {
		JDialog sizeInputPanel = new JDialog(f , "Dialog Example", true);  
		sizeInputPanel.setLayout( new FlowLayout() ); 
		JSpinner pictureSizeX = new JSpinner(new SpinnerNumberModel(800,40,1400,10));
		JSpinner pictureSizeY = new JSpinner(new SpinnerNumberModel(600,40,900,10));
        JButton b = new JButton ("OK");  
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
            	drawingSizeX = (int)pictureSizeX.getValue();
            	drawingSizeY = (int)pictureSizeY.getValue();
            	sizeInputPanel.setVisible(false);  
            }  
        });  
        sizeInputPanel.add( new JLabel ("drawing width:"));
        sizeInputPanel.add(pictureSizeX);
        sizeInputPanel.add( new JLabel ("drwaing height:"));
        sizeInputPanel.add(pictureSizeY); 
        sizeInputPanel.add(b); 
        sizeInputPanel.add( new JLabel ("Click button to continue.")); 
        sizeInputPanel.add(b);   
        sizeInputPanel.setSize(300,300);    
        sizeInputPanel.setVisible(true);  
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
