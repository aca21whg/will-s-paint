package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
	//creates a buffered image to draw on
	private BufferedImage drawZone;
	//stores the MainPanel this drawing panel is used to allow the use of button values
	private MainPanel parentPanel;
	//integer arrays used to store mouse coords for certain drawing tools
	private int[] startCoord = new int[2];
	private int[] endCoord = new int[2];
	//used if we want to know where the mouse is right now outside an event
	private int[] curCoord = new int[2];
	
	private ArrayList<Shape> drawnObjects;
	
	public DrawingPanel(int w, int h, MainPanel main){
		//saves MainPanel object
		parentPanel = main;
		//makes it white
		setBackground(Color.white);
		//makes the size slightly smaller than the main panel to leave room for the control panel
		setSize(800, 600);
		//makes the buffered image
		drawZone = new BufferedImage(800 , 600, BufferedImage.TYPE_INT_ARGB);	
		//adds listeners
		addMouseListener(new mouseStationary());
		addMouseMotionListener(new mouseInMotion());
	}
	
	public void paintComponent(Graphics g) {
		//allows us to see the things we draw on the buffered image
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(drawZone, null, null);
    }
	
	private void draw(int drawX, int drawY) {
		double penThickness = parentPanel.getPenSize()/2.0;
		int minThick = (int)Math.floor(penThickness);
		int maxThick = (int)Math.ceil(penThickness);
		//gets the selected colours from the main panel RGB value for later use
		int c = parentPanel.getSelectedColorType().getRGB();
		//can't just use x and y values as the values need to change
		//depending on the x and y values which could cause an infinite loop
		int usedX;
		int usedY;
		//loops through all the pixels around the cursor and colours them
		for (int x = drawX - maxThick; x < drawX + minThick; x++) {
			//if pixels out the area changes there value
			if (x > drawZone.getWidth() - 1)
				usedX = drawZone.getWidth() - 1;
			else if (x < 0)
				usedX = 0;
			else
				usedX = x;
            for (int y = drawY - maxThick; y < drawY + minThick; y++) {
            	//if pixels out the area changes there value
            	if (y > drawZone.getHeight() - 1)
    				usedY = drawZone.getHeight() - 1;
            	else if (y < 0)
    				usedY = 0;
            	else
            		usedY = y;
            	//changes the colour of the pixel
                drawZone.setRGB(usedX, usedY, c);
            }
		 }
		//redraws the panel so we can see the new look
		 repaint();
	}
	
	private void drawRectangle(Graphics2D zone) {
		int rectWidth = Math.abs(startCoord[0] - endCoord[0]);
		int rectHeight = Math.abs(startCoord[1] - endCoord[1]);
		zone.draw(new Rectangle2D.Double(startCoord[0], startCoord[1],
				rectWidth,
                rectHeight));
	}
		
	private void drawCircle(Graphics2D zone) {
		int rectWidth = Math.abs(startCoord[0] - endCoord[0]);
		int rectHeight = Math.abs(startCoord[1] - endCoord[1]);
		zone.draw(new Ellipse2D.Double(startCoord[0], startCoord[1],
				rectWidth,
                rectHeight));
	}
	
	private void drawShape() {
		Graphics2D graphicsZone = drawZone.createGraphics();
		graphicsZone.setColor(parentPanel.getSelectedColorType());
		graphicsZone.setStroke(new BasicStroke(parentPanel.getPenSize()));
		if (parentPanel.getTool().equals("rectangle"))
			drawRectangle(graphicsZone);
		else if (parentPanel.getTool().equals("line"))
			graphicsZone.draw(new Line2D.Double(startCoord[0], startCoord[1], endCoord[0], endCoord[1]));
		else if (parentPanel.getTool().equals("circle"))
			drawCircle(graphicsZone);
		repaint();
	}
	
	private class mouseStationary implements MouseListener{
	
		public void mousePressed(MouseEvent e) {
			//save the starting coords on first click
			startCoord[0] = e.getX();
			startCoord[1] = e.getY();
	    	}
	     
		public void mouseReleased(MouseEvent e) {
			//save end coords when the mouse is released
			endCoord[0] = e.getX();
			endCoord[1] = e.getY();
			drawShape();
	   		}
	     
		public void mouseEntered(MouseEvent e) {
	        
	    	}
	     
		public void mouseExited(MouseEvent e) {
	        
	    	}
	     
		public void mouseClicked(MouseEvent e) {
			//only runs the function when specific tools are selected
			if (parentPanel.getTool().equals("drawing") || parentPanel.getTool().equals("eraser"))
				draw(e.getX(),e.getY());
			}
	}
	private class mouseInMotion implements MouseMotionListener{
		
	 public void mouseDragged(MouseEvent e) {
		//only runs the function when specific tools are selected
		 if (parentPanel.getTool().equals("drawing") || parentPanel.getTool().equals("eraser"))
			draw(e.getX(),e.getY());
	 	}
	
	public void mouseMoved(MouseEvent e) {
		curCoord[0] = e.getX();
		curCoord[1] = e.getY();
	 	}
	}
}
