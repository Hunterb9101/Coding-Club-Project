package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import windows.World;

public class Tile {	
	public static ArrayList<Tile> allTiles = new ArrayList<Tile>();
	public static boolean showBorders = true;
	
	public static int xOffset = 0;
	public static int yOffset = 0;
	public static int origXOffset = 0;
	public static int origYOffset = 0;
	
	static int tileSize = 80;
	public Color textColor = Color.black;
	public Color borderColor = Color.black;
	public String baseImage;
	
	public Overlay overlay = Overlay.allOverlays.get("null");
	
	int x;
	int y;
	public int[] coords;
	
	public static int maxCol = 0;
	public static int maxRow = 0;
	
	public Tile(int[] coords, String baseImage){
		this.baseImage = baseImage;
		this.coords = coords;
		this.x = coords[0]*tileSize;
		this.y = coords[1]*tileSize;
		
		if(coords[0] > maxCol){
			maxCol = coords[0];
		}
		if(coords[1] > maxRow){
			maxRow = coords[1];
		}
		allTiles.add(this);
	}
	
	public Tile(int[] coords, String baseImage, Overlay overlay){
		this(coords,baseImage);
		this.overlay = overlay;
		try{
			if(overlay.image == null){}
		}
		catch(NullPointerException e){
			System.err.println("Null Reference For Overlay");
		}
	}
	
	public static void drawTiles(Graphics g){
		for(int i = 0; i<maxCol; i++){
			for(int j = 0; j<maxRow; j++){
				g.drawImage(Registry.tileRes.get(allTiles.get(j*maxCol + i).baseImage),allTiles.get(j*maxCol+i).x + xOffset, allTiles.get(j*maxCol+i).y + yOffset, null);
				if(allTiles.get(j*maxCol+i).overlay != null){
					g.drawImage(Registry.overlayRes.get(allTiles.get(j*maxCol + i).overlay.image),allTiles.get(j*maxCol+i).x + xOffset, allTiles.get(j*maxCol+i).y + yOffset - Registry.overlayRes.get(allTiles.get(j*maxCol+i).overlay.image).getHeight(null), null);
				}
				
				if(showBorders){
					g.drawLine(allTiles.get(j*maxCol+i).x + xOffset, allTiles.get(j*maxCol+i).y + yOffset, allTiles.get(j*maxCol+i).x + tileSize + xOffset, allTiles.get(j*maxCol+i).y + yOffset);
					g.drawLine(allTiles.get(j*maxCol+i).x + xOffset, allTiles.get(j*maxCol+i).y + tileSize + yOffset, allTiles.get(j*maxCol+i).x + tileSize + xOffset, allTiles.get(j*maxCol+i).y +tileSize + yOffset);
					
					g.drawLine(allTiles.get(j*maxCol+i).x + xOffset, allTiles.get(j*maxCol+i).y + yOffset, allTiles.get(j*maxCol+i).x + xOffset, allTiles.get(j*maxCol+i).y + tileSize + yOffset);
					g.drawLine(allTiles.get(j*maxCol+i).x + xOffset + tileSize, allTiles.get(j*maxCol+i).y + yOffset, allTiles.get(j*maxCol+i).x + tileSize + xOffset, allTiles.get(j*maxCol+i).y + tileSize + yOffset);
				} 
			}
		}
	}
	
	public static int[] selectTile(int x, int y){
		for(int i = 0; i<allTiles.size(); i++){
			if(x > allTiles.get(i).x && x < allTiles.get(i).x + Tile.tileSize 
			&& y > allTiles.get(i).y && y < allTiles.get(i).y + Tile.tileSize){
				return allTiles.get(i).coords;
			}
		}
		return new int[]{-1,-1};
		
	}
}