package windows.world;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import graphics.shapes.GraphicsImage;
import graphics.shapes.GraphicsObject;
import graphics.shapes.GraphicsPrimitives;
import main.Main;
import main.Registry;
import main.Utils;
import windows.Window;

public class BattleWindow extends World{
	public static int scrollSpeed = 3;
	public static String currMapPath = null;
	
	public static Random rand = new Random();
	
	public static boolean isPlayerTurn = true;
	
	GraphicsPrimitives statsBox  = new GraphicsPrimitives(new Color(234,208,0),0,0,240,Main.height);
	GraphicsImage attackLeft = new GraphicsImage(Registry.imgRes.get("Flag2"),30,75,80,80);
	GraphicsImage attackRight = new GraphicsImage(Registry.imgRes.get("Flag2"),130,75,80,80);
	
	public BattleWindow(String name) {
		super(name, MapLoader.genMapKey);
		if(currMapPath != null){
			MapLoader.load(currMapPath, this);
		}
		else{
			MapLoader.generateMap(this);
		}
	}

	@Override
	public BufferedImage draw(Component mainWindow) {
		
		try{
			if(!currMapPath.equals(MapLoader.loadedMap)){
				MapLoader.load(currMapPath, this); // Load the Current Map
			}
		}
		catch(NullPointerException e){}
		
		// Needed Statements in any draw()
		BufferedImage render = Utils.toBufferedImage(mainWindow.createImage(GraphicsObject.currWidth,GraphicsObject.currHeight)); // Windows are created as bufferedImages so that they can be transformed later
		Graphics g = render.getGraphics();
		Tile.drawTiles(g, this);
		
		statsBox.drawObject(g);
		g.setColor(Color.black);
		g.setFont(new Font("Helvetica", Font.PLAIN, 36));
		g.drawString("Attack", 10, 30);
		attackLeft.drawObject(g);
		attackRight.drawObject(g);
		g.drawString("Abilities", 10, 210);
		
		return render;
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		System.out.println(Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset, this)[0] + ", " + Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset, this)[1]);
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
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
		
	}

	@Override
	public void drawWorld(Graphics g, int width, int height) {
		// TODO Auto-generated method stub
		
	}


}
