package paint;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PNGFilter extends FileFilter{
    @Override
    //created a file filter so you can decide what file to look at
    //and what to save files as
    //but it was being wierd so i gave up
    public boolean accept(File f){
        return f.getName().toLowerCase().endsWith(".png")||f.isDirectory();
    }
    @Override
    public String getDescription(){
        return "PNG files(*.PNG)";
    }
    
    public String toString() {
    	return "png";
    }
}