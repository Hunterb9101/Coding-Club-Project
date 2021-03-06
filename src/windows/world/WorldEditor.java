package windows.world;

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
import main.Registry;
import main.Utils;
import windows.Window;

public class WorldEditor extends World{
	public static boolean isOverlay = false;
	public static int item = 0;
	
	public WorldEditor(String name, String mapPath) {
		super(name,mapPath);
		setMap(mapPath);
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int[] coords = Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset,this);
		for(int i = 0; i<worldTiles.size(); i++){
			if(worldTiles.get(i).coords == coords){
				if(isOverlay){
					worldTiles.get(i+1).overlay = Overlay.allOverlays.get(Utils.getKeyByValue(Registry.saveOverlayKey,item)); //The +maxRow is added so that the tree is on the correct tile.
				}
				else{
					worldTiles.get(i).baseImage = Utils.getKeyByValue(Registry.saveTileKey,item);
				}
			}
		}
		
		
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
			MapLoader.save(fileName,this);
		}
	}

	@Override
	public void drawWorld(Graphics g, int width, int height) {
		// This creates the menu for the World Editor //
				g.setColor(Color.darkGray);
				g.fillRect(width - 100, 20, 86, 166);
				
				if(isOverlay){
					g.drawImage(Registry.overlayRes.get(Overlay.allOverlays.get(Utils.getKeyByValue(Registry.saveOverlayKey,item)).image), width - 100, 25, null);
				}
				else{
					g.drawImage(Registry.tileRes.get(Utils.getKeyByValue(Registry.saveTileKey,item)), width - 97, 25, null);
				}
	}
}
