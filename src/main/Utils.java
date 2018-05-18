package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JOptionPane;

//////////////////////////////////////
//			Utils			  		//
//		The knicknack functions		//
//////////////////////////////////////

public class Utils {
	
	// Needed for perspective rendering //
	public static double[][] matrixMultiply(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
	
	public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
	
	
	// Conversion from normal images (bleh) to BufferedImages (Whole bunch of cool features) //
	public static BufferedImage toBufferedImage(Image img){
	    if (img instanceof BufferedImage) {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public static void printBox(String text){
		JOptionPane.showMessageDialog(null, text); //Simple JOption pane dialog box
	}
	
	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public static String getWorkspaceDirectory(){
		String currDir = System.getProperty("user.dir");
		String newFileDir = "";
		String[] elements = currDir.split("\\\\");
		
		for(int i = 0; i<elements.length; i++){
		}
		
		for(int i = 0; i<elements.length - 1; i++){
			newFileDir+= elements[i] + "\\";
		}
		return newFileDir;
	}
	
	public static List<String> readFile(File filePath) { 
		String fLine = "";
		List<String> fDataRaw = new ArrayList<String>(); // All Data in a file
		if (filePath.exists()) {
			try {
				Scanner scan = new Scanner(filePath);
				while (scan.hasNext()) {
					fLine = scan.nextLine(); // This records every line
					fDataRaw.add(fLine); // this adds the string into the entire
											// database
					fLine = "";// resets the line variable so the string doesn't
								// keep concatenating the lines before it.
				}
				scan.close();
			} catch (FileNotFoundException ignored) {
			}
		}

		else {
			System.out.println("ERROR: FileOps can't find the file!");
		}
		return fDataRaw;
	}
	
	public static void writeFile(String path, String[] data){ //Allows the program to write to a file
		try{
			PrintWriter writer = new PrintWriter(new File(path), "UTF-8");
			
			for(int i = 1; i<data.length; i++) {
				writer.println(data[i]);
			}
		
			writer.close();
		}
		
		catch(FileNotFoundException ingore){}
		catch(UnsupportedEncodingException ignore){}
	}
}
