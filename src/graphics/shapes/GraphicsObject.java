package graphics.shapes;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import main.Main;
import windows.Window;

public abstract class GraphicsObject {
	public static ArrayList<GraphicsObject> allObjects = new ArrayList<GraphicsObject>();
	public static HashMap<String,ArrayList<GraphicsObject>> windowObjects = new HashMap<String,ArrayList<GraphicsObject>>();
	// Basic reference for the interface//
	protected static int defaultWidth = 800;
	protected static int defaultHeight = 800;

	// Current Size of interface//
	public static int currWidth = defaultWidth;
	public static int currHeight = defaultHeight;

	// Current Scalar Values//
	protected static double xScalar = ((double) currWidth / (double) defaultWidth);
	protected static double yScalar = ((double) currHeight / (double) defaultHeight);

	// Positioning in reference to defaults//
	public int x = 0;
	public int y = 0;
	public int width = 0;
	public int height = 0;
	
	public boolean freeMotion = true; // Determines whether the object is
										// scalable

	public GraphicsObject(int iX, int iY, int iWidth, int iHeight) {
		x = iX;
		y = iY;
		width = iWidth;
		height = iHeight;
		allObjects.add(this);
		
		// Get the name of the class that created the object (Should be a window)
		// If any packages are added to the windows, the 1 may need to be changed (as it only gets rid of the 'window.')
		String classReference = ((Thread.currentThread().getStackTrace()[3].getClassName()).split("\\.")[1]).split("\\$")[0];
		
		if(windowObjects.get(classReference) == null){ // Add a new GraphicsObject container
			windowObjects.put(classReference, new ArrayList<GraphicsObject>());
		}
		windowObjects.get(classReference).add(this);
	}

	public abstract void drawObject(Graphics g);

	public abstract void onClick();

	public abstract void onHover(Graphics g);

	public static void setDimens(int cWidth, int cHeight) {
		currWidth = cWidth;
		currHeight = cHeight;

		xScalar = ((double) currWidth / (double) defaultWidth);
		yScalar = ((double) currHeight / (double) defaultHeight);
	}

	public void setDefaultDimens(int defWidth, int defHeight) {
		defaultWidth = defWidth;
		defaultHeight = defHeight;
	}

	public static void checkOnClick(int x, int y) {
		String classReference = ((Thread.currentThread().getStackTrace()[2].getClassName()).split("\\.")[1]).split("\\$")[0]; // Current Window
		System.out.println(classReference);
		GraphicsObject obj;
		for (int i = 0; i < windowObjects.get(classReference).size(); i++) {
			obj = windowObjects.get(classReference).get(i);
			if ((x > obj.x * xScalar && x < (obj.width + obj.x) * xScalar)
					&& (y > obj.y * yScalar && y < (obj.y + obj.height) * yScalar)) {
				windowObjects.get(classReference).get(i).onClick();
			}
		}
	}

	public static void checkOnHover(int x, int y) {
		GraphicsObject obj;
		for (int i = 0; i < allObjects.size(); i++) {
			obj = allObjects.get(i);
			if ((x > obj.x * xScalar && x < (obj.width + obj.x) * xScalar)
					&& (y > obj.y * yScalar && y < (obj.y + obj.height) * yScalar)) {
			}
		}
	}
}
