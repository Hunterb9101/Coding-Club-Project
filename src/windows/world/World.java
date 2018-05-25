package windows.world;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import entity.Unit;
import main.Main;
import main.Utils;
import windows.Window;

public abstract class World extends Window{
	public int scrollSpeed = 3;
	
	// Offset and Scrolling Variables //
	public boolean isScrolling = false;
	public int stepsToGo = 0;
	public int stepSizeX = 0;
	public int stepSizeY = 0;
	
	public int xOffset = 0;
	public int yOffset = 0;
	public int origXOffset = 0;
	public int origYOffset = 0;
	public int[] worldSize = new int[]{0,0};
	
	private String currMapPath = ""; // Change this to the file path, or MapLoader.genMapKey
	public ArrayList<Tile> worldTiles = new ArrayList<Tile>(); // Functions as AllTiles used to.
	public ArrayList<Unit> worldUnits = new ArrayList<Unit>(); // Functions as AllEntities did.
	
	public World(String name, String mapPath) {
		super(name,false);
		setMap(mapPath);
	}

	public void setMap(String mapPath){ // The way to change maps for a given World instance at any time.
		if(mapPath.equals(MapLoader.genMapKey)){
			MapLoader.generateMap(this,40,40); // Load the Current Map
		}
		else{
			MapLoader.load(mapPath,this); // Load the Current Map
		}	
	}
	
	@Override
	public BufferedImage draw(Component mainWindow) {
		BufferedImage render = Utils.toBufferedImage(mainWindow.createImage(mainWindow.getWidth(),mainWindow.getHeight())); // 200x200 is the window size
		Graphics g = render.getGraphics();
		Tile.drawTiles(g,this);
		Unit.drawAllUnitsInWorld(g, this);
		drawWorld(g, mainWindow.getWidth(), mainWindow.getHeight());
		return render;
	}
	
	public abstract void drawWorld(Graphics g, int width, int height);

	@Override
	public void mousePressed(MouseEvent evt) {
		// Print out clicked tile
		//System.out.println(Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset,this)[0] + ", " + Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset,this)[1]);
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		
		/*
		if(evt.getKeyChar() == 'd'){
			xOffset -= scrollSpeed;
		}
		else if(evt.getKeyChar() == 'a'){
			xOffset += scrollSpeed;
			
		}
		else if(evt.getKeyChar() == 's'){
			yOffset -= scrollSpeed;
		}
		else if(evt.getKeyChar() == 'w'){
			yOffset += scrollSpeed;
		}
		
		*/
		
		// Basic scrolling ability
	}
	
	public void slowScroll(int duration, int xTarget, int yTarget) {
		System.out.println(isScrolling);
		System.out.println(stepsToGo + " steps @ " + stepSizeX + ", " + stepSizeY);
		if(!isScrolling) {
			isScrolling = true;
			stepsToGo = duration;
			stepSizeX = xTarget/duration;
			stepSizeY = yTarget/duration;
		}
		else {
			if(xOffset != xTarget && yOffset != yTarget) {
				xOffset += stepSizeX;
				yOffset += stepSizeY;
			}
			
			stepsToGo--;
			if(stepsToGo < 0) {
				isScrolling = false;
			}
		}
	}
}
