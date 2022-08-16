package paint;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FileChooser  extends JPanel
	implements ActionListener{
	JButton open , save;
	JFileChooser fileChooser;
	BufferedImage image;

	public FileChooser(DrawingPanel2 drawing) {
		
		image = drawing.getImage();
		
		fileChooser = new JFileChooser();
		//sets up file chooser to allow a proper file viewing experience
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.addChoosableFileFilter(new PNGFilter());
		fileChooser.addChoosableFileFilter(new JPEGFilter());
		
		open = new JButton("Open");
		open.addActionListener(this);

		//Create the save button.  We use the image from the JLF
		//Graphics Repository (but we extracted it from the jar).
		save = new JButton("Save");
		save.addActionListener(this);
		
		JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(open);
        buttonPanel.add(save);
 
        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
	}
	
	public void actionPerformed(ActionEvent ev) {
		//Handle open button action.
		if (ev.getSource() == open) {
		        //Handle save button action.
		    } 
		else if (ev.getSource() == save) {      
			int returnVal = fileChooser.showSaveDialog(FileChooser.this);
		          
			if (returnVal == JFileChooser.APPROVE_OPTION) {     
				File file = fileChooser.getSelectedFile();    
				//uses image io to save the image as a png 
				System.out.println(fileChooser.getFileFilter().toString());
		        	try { 
		        		if (ImageIO.write(image, "png", new File(file.getPath() + ".PNG"))) 
		        			System.out.println("-- saved png"); 
		        	} catch (IOException e) {
		                    e.printStackTrace();
		        	}}
			}
	}
		   
		public JButton getOpen() {
			return open;
		}
		
		public JButton getSave() {
			return save;
		}
		 
		
	}
	


