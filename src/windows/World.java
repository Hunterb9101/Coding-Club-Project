package windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import main.Main;
import main.MapLoader;
import main.Overlay;
import main.Tile;
import main.Utils;

public class World extends Window{
	public static int scrollSpeed = 3;
	public static String currMapPath = null;
	
	public static Random rand = new Random();
	
	public World(String name) {
		
		super(name,false);
		if(currMapPath != null){
			MapLoader.load(currMapPath);
		}
		else{
			MapLoader.generateMap();
		}
	}

	@Override
	public BufferedImage draw(Component mainWindow) {
		try{
			if(!currMapPath.equals(MapLoader.loadedMap)){
				MapLoader.load(currMapPath); // Load the Current Map
			}
		}
		catch(NullPointerException e){}
		
		// Needed Statements in any draw()
		BufferedImage render = Utils.toBufferedImage(mainWindow.createImage(mainWindow.getWidth(),mainWindow.getHeight())); // 200x200 is the window size
		Graphics g = render.getGraphics();
		Tile.drawTiles(g);
		
		return render;
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		System.out.println(Tile.selectTile(evt.getX() - Tile.xOffset, evt.getY() - Tile.yOffset)[0] + ", " + Tile.selectTile(evt.getX() - Tile.xOffset, evt.getY() - Tile.yOffset)[1]);
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyChar() == 'd'){
			Tile.xOffset -= scrollSpeed;
		}
		else if(evt.getKeyChar() == 'a'){
			Tile.xOffset += scrollSpeed;
			
		}
		else if(evt.getKeyChar() == 's'){
			Tile.yOffset -= scrollSpeed;
		}
		else if(evt.getKeyChar() == 'w'){
			Tile.yOffset += scrollSpeed;
		}
		
	}
}
