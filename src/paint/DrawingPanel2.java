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

public class DrawingPanel2 extends JPanel {
	//creates a buffered image to draw on
	private BufferedImage drawZone;
	//stores the MainPanel this drawing panel is used to allow the use of button values
	private MainPanel parentPanel;
	//integer arrays used to store mouse coords for certain drawing tools
	private int[] startCoord = new int[2];
	private int[] endCoord = new int[2];
	//used if we want to know where the mouse is right now outside an event
	private int[] curCoord = new int[2];
	//tells you weather the mouse is still pressed
	private boolean mousePressed;
	//stores all objects drawn on the panel
	private ArrayList<Shape> drawnObjects = new ArrayList<Shape>();
	//an array list to store what colour and size objects were drawn in 
	private ArrayList<int[]> drawnObjectParams = new ArrayList<int[]>();
	//2d array to represent the image and used for redrawing
	private int[][] rasterGrid;
	
	public DrawingPanel2(int w, int h, MainPanel main){
		//saves MainPanel object
		parentPanel = main;
		//makes it white
		setBackground(Color.white);
		//makes the size slightly smaller than the main panel to leave room for the control panel
		setSize(800, 600);
		//makes the buffered image
		drawZone = new BufferedImage(800 , 600, BufferedImage.TYPE_INT_ARGB);	
		//set up raster grid array
		int whiteCode = Color.white.getRGB();
		rasterGrid = new int[800][600];
		for (int x = 0; x < rasterGrid.length; x++)
			for (int y = 0; y < rasterGrid[x].length; y++)
				rasterGrid[x][y] = whiteCode;
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
	//used when drawing rectangle, line , ellipse to delete previous version of the
	//shape by redrawing the the background and then all the objects
	public void reDraw() {
		Graphics2D graphicsZone = drawZone.createGraphics();
		for (int x = 0; x < rasterGrid.length; x++)
			for (int y = 0; y < rasterGrid[x].length; y++)
				drawZone.setRGB(x, y, rasterGrid[x][y]);
		for (int i = 0; i < drawnObjects.size(); i++) {
			graphicsZone.setStroke(new BasicStroke(drawnObjectParams.get(i)[0]));
			graphicsZone.setColor(new Color(drawnObjectParams.get(i)[1]));
			graphicsZone.draw(drawnObjects.get(i));
		}
		repaint();
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
            	rasterGrid[usedX][usedY] = c;
                drawZone.setRGB(usedX, usedY, c);
            }
		 }
		//redraws the panel so we can see the new look
		 repaint();
	}
	
	private void createShape() {
		//creates the correct shape object on click and adds it to the array list
		int [] params = new int[2];
		if (parentPanel.getTool().equals("rectangle")) {
			drawnObjects.add(new Rectangle2D.Double(startCoord[0],startCoord[1],0,0));
			params = new int[]{parentPanel.getPenSize() , parentPanel.getSelectedColorType().getRGB()};
			drawnObjectParams.add(params);
		}
		else if (parentPanel.getTool().equals("line")) {
			drawnObjects.add(new Line2D.Double(startCoord[0],startCoord[1],0,0));
			params = new int[]{parentPanel.getPenSize() , parentPanel.getSelectedColorType().getRGB()};
			drawnObjectParams.add(params);
		}
		else if (parentPanel.getTool().equals("circle")) {
			drawnObjects.add(new Ellipse2D.Double(startCoord[0],startCoord[1],0,0));
			params = new int[]{parentPanel.getPenSize() , parentPanel.getSelectedColorType().getRGB()};
			drawnObjectParams.add(params);
		}
	}
	
	private void editShape() {
		//edits current shape object and draws them however does not remove
		//previous shape so slowly fills the screen as you move the cursor
		//possible solution clearing screen and redrawing to only have the 
		//correct things drawn but would require changes to the draw tool 
		if (parentPanel.getTool().equals("rectangle")) {
			int rectWidth = Math.abs(startCoord[0] - curCoord[0]);
			int rectHeight = Math.abs(startCoord[1] - curCoord[1]);
			((Rectangle2D.Double) drawnObjects.get(drawnObjects.size()-1))
				.setRect(startCoord[0],endCoord[1],rectWidth,rectHeight);
			reDraw();
		}
		else if (parentPanel.getTool().equals("line")) {
			((Line2D.Double) drawnObjects.get(drawnObjects.size()-1))
				.setLine(startCoord[0],endCoord[1],curCoord[0],curCoord[1]);
			reDraw();
		}
		else if (parentPanel.getTool().equals("circle")) {
			int ellipseWidth = Math.abs(startCoord[0] - curCoord[0]);
			int ellipseHeight = Math.abs(startCoord[1] - curCoord[1]);
			((Ellipse2D.Double) drawnObjects.get(drawnObjects.size()-1))
				.setFrame(startCoord[0],endCoord[1],ellipseWidth,ellipseHeight);
			reDraw();
		}
	}
	
	private class mouseStationary implements MouseListener{
	
		public void mousePressed(MouseEvent e) {
			//save the starting coords on first click
			startCoord[0] = e.getX();
			startCoord[1] = e.getY();
			if (!mousePressed) {
				createShape();
				mousePressed = true;
			}
		}
	     
		public void mouseReleased(MouseEvent e) {
			//save end coords when the mouse is released
			endCoord[0] = e.getX();
			endCoord[1] = e.getY();
			mousePressed = false;
	   		}
	     
		public void mouseEntered(MouseEvent e) {
	        
	    	}
	     
		public void mouseExited(MouseEvent e) {
	        
	    	}
	     
		public void mouseClicked(MouseEvent e) {
			//only runs the function when specific tools are selected
			startCoord[0] = e.getX();
			startCoord[1] = e.getY();
			if (parentPanel.getTool().equals("drawing") || parentPanel.getTool().equals("eraser"))
				draw(e.getX(),e.getY());
			else 
				createShape();
			}
	}
	private class mouseInMotion implements MouseMotionListener{
		
	 public void mouseDragged(MouseEvent e) {
		//only runs the function when specific tools are selected
		curCoord[0] = e.getX();
		curCoord[1] = e.getY();
		 if (parentPanel.getTool().equals("drawing") || parentPanel.getTool().equals("eraser"))
			draw(e.getX(),e.getY());
		 else
			 editShape();
	 	}
	
	public void mouseMoved(MouseEvent e) {

		//if (mousePressed)
			//editShape();
	 	}
	}
}
