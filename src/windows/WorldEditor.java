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

import javax.swing.JOptionPane;

import main.Main;
import main.MapLoader;
import main.Overlay;
import main.Registry;
import main.Tile;
import main.Utils;

public class WorldEditor extends Window{
	public static int scrollSpeed = 3;
	public static String currMapPath = "maps/HunterIsFreakingAwesome.txt";
	public static Random rand = new Random();
	
	public static boolean isOverlay = false;
	public static int item = 0;
	
	public WorldEditor(String name) {
		
		super(name,false);
		MapLoader.load(currMapPath);
		
		/*
		for(int x = 0; x < 40; x++){
			for(int y = 0; y < 40; y++){
				if(rand.nextDouble()< .05){
					new Tile(new int[]{x,y},"grass",Overlay.allOverlays.get("tree1"));
				}
				else{
					new Tile(new int[]{x,y},"grass");
				}	
			}
		}
		*/
	}

	@Override
	public BufferedImage draw(Component mainWindow) {
		if(!currMapPath.equals(MapLoader.loadedMap)){
			MapLoader.load(currMapPath); // Load the Current Map
		}
		// Needed Statements in any draw()
		BufferedImage render = Utils.toBufferedImage(mainWindow.createImage(mainWindow.getWidth(),mainWindow.getHeight())); // 200x200 is the window size
		Graphics g = render.getGraphics();
		Tile.drawTiles(g);
		
		
		// This creates the menu for the World Editor //
		g.setColor(Color.darkGray);
		g.fillRect(mainWindow.getWidth() - 100, 20, 86, 166);
		
		if(isOverlay){
			g.drawImage(Registry.overlayRes.get(Overlay.allOverlays.get(Utils.getKeyByValue(Registry.saveOverlayKey,item)).image), mainWindow.getWidth() - 100, 25, null);
		}
		else{
			g.drawImage(Registry.tileRes.get(Utils.getKeyByValue(Registry.saveTileKey,item)), mainWindow.getWidth() - 97, 25, null);
		}
		
		return render;
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int[] coords = Tile.selectTile(evt.getX() - Tile.xOffset, evt.getY() - Tile.yOffset);
		
		System.out.println("Tile Clicked At: " + Tile.selectTile(evt.getX() - Tile.xOffset, evt.getY() - Tile.yOffset)[0] + ", " + Tile.selectTile(evt.getX() - Tile.xOffset, evt.getY() - Tile.yOffset)[1]);
		for(int i = 0; i<Tile.allTiles.size(); i++){
			if(Tile.allTiles.get(i).coords == coords){
				if(isOverlay){
					Tile.allTiles.get(i).overlay = Overlay.allOverlays.get(Utils.getKeyByValue(Registry.saveOverlayKey,item));
				}
				else{
					Tile.allTiles.get(i).baseImage = Utils.getKeyByValue(Registry.saveTileKey,item);
				}
			}
		}
		
		
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
		
		else if(evt.getKeyChar() == 'i'){
			item--;
			if(item < 0){
				item = (isOverlay) ? Overlay.allOverlays.size() - 1:Registry.tileRes.size() - 1;
			}
		}
		else if(evt.getKeyChar() == 'k'){
			item++;
			if((item > Overlay.allOverlays.size() - 1 && isOverlay) || (item > Registry.tileRes.size() - 1 && !isOverlay)){
				item = 0;
			}
		}
		else if(evt.getKeyChar() == 'l'){
			isOverlay = true;
			item = 0;
		}
		else if(evt.getKeyChar() == 'j'){
			isOverlay = false;
			item = 0;
		}
		
		else if(evt.getKeyChar() == 'q'){
			String fileName = JOptionPane.showInputDialog("File Name for Map Save:");
			System.out.println(fileName);
			MapLoader.save(fileName);
		}
	}
}
