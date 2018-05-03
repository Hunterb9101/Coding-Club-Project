package windows.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import main.Main;
import main.Registry;

public class Tile {	
	public static boolean showBorders = true; //This is a debug tool. If true, borders will show between the tiles.
	
	public static int tileSize = 80;
	public Color textColor = Color.black;
	public Color borderColor = Color.black;
	public String baseImage;
	
	public Overlay overlay = Overlay.allOverlays.get("null");
	
	int x;
	int y;
	public int[] coords;
	
	public static int maxCol = 0;
	public static int maxRow = 0;
	
	public Tile(int[] coords, String baseImage, World w){
		this.baseImage = baseImage;
		this.coords = coords;
		this.x = coords[0]*tileSize;
		this.y = coords[1]*tileSize;
		
		if(coords[0]-1 > maxCol){
			maxCol = coords[0];
		}
		if(coords[1]-1 > maxRow){
			maxRow = coords[1];
		}
		w.worldTiles.add(this);
	}
	
	public Tile(int[] coords, String baseImage, Overlay overlay, World w){
		this(coords,baseImage, w);
		this.overlay = overlay;
		
		try{
			if(overlay.image == null){}
		}
		catch(NullPointerException e){
			System.err.println("Null Reference For Overlay");
		}
	}
	
	public static void drawTiles(Graphics g, World w){
		for(int i = - w.xOffset/80; i<Main.width/tileSize - w.xOffset/tileSize + 2; i++){  // Only loads a portion of the map for improved performance.
			for(int j = - w.yOffset/80; j<Main.height/tileSize - w.yOffset/tileSize + 2; j++){ // Only loads a portion of the map for improved performance.
				try{
					g.drawImage(Registry.tileRes.get(w.worldTiles.get(j*maxCol + i).baseImage),w.worldTiles.get(j*maxCol+i).x + w.xOffset, w.worldTiles.get(j*maxCol+i).y + w.yOffset, null);
					if(w.worldTiles.get(j*maxCol+i).overlay != null){
						g.drawImage(Registry.overlayRes.get(w.worldTiles.get(j*maxCol + i).overlay.image),w.worldTiles.get(j*maxCol+i).x + w.xOffset, w.worldTiles.get(j*maxCol+i).y + w.yOffset - Registry.overlayRes.get(w.worldTiles.get(j*maxCol+i).overlay.image).getHeight(null), null);
					}
					
					if(showBorders){
						g.drawLine(w.worldTiles.get(j*maxCol+i).x + w.xOffset, w.worldTiles.get(j*maxCol+i).y + w.yOffset, w.worldTiles.get(j*maxCol+i).x + tileSize + w.xOffset, w.worldTiles.get(j*maxCol+i).y + w.yOffset);
						g.drawLine(w.worldTiles.get(j*maxCol+i).x + w.xOffset, w.worldTiles.get(j*maxCol+i).y + tileSize + w.yOffset, w.worldTiles.get(j*maxCol+i).x + tileSize + w.xOffset, w.worldTiles.get(j*maxCol+i).y +tileSize + w.yOffset);
						
						g.drawLine(w.worldTiles.get(j*maxCol+i).x + w.xOffset, w.worldTiles.get(j*maxCol+i).y + w.yOffset, w.worldTiles.get(j*maxCol+i).x + w.xOffset, w.worldTiles.get(j*maxCol+i).y + tileSize + w.yOffset);
						g.drawLine(w.worldTiles.get(j*maxCol+i).x + w.xOffset + tileSize, w.worldTiles.get(j*maxCol+i).y + w.yOffset, w.worldTiles.get(j*maxCol+i).x + tileSize + w.xOffset, w.worldTiles.get(j*maxCol+i).y + tileSize + w.yOffset);
					} 
				}
				catch(ArrayIndexOutOfBoundsException e){
					// Some tiles return negative due to the system implemented. This silences all errors
				}	
			}
		}
	}
	
	public static int[][] getDrawBounds(World w){ // Returns tile coordinate bounds [[x,y],[x,y]] of draw function
		return new int[][]{{-w.xOffset/tileSize,-w.yOffset/tileSize},{Main.width/tileSize - w.xOffset/tileSize + 2,Main.height/tileSize - w.yOffset/tileSize + 2}};
	}
	
	public static int[] selectTile(int x, int y, World w){
		for(int i = 0; i<w.worldTiles.size(); i++){
			if(x > w.worldTiles.get(i).x && x < w.worldTiles.get(i).x + Tile.tileSize 
			&& y > w.worldTiles.get(i).y && y < w.worldTiles.get(i).y + Tile.tileSize){
				return w.worldTiles.get(i).coords;
			}
		}
		return new int[]{-1,-1};
		
	}
}