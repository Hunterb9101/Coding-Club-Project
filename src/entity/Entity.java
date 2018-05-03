package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import main.Registry;
import windows.world.Tile;
import windows.world.World;

public class Entity{
	
	public Image src = Registry.imgRes.get("Character");
	public int[] coords = new int[]{0,0}; // Coordinates on playing field
	public boolean isVisible = true; // Tells the drawing mechanism whether or not to draw. (NOT IMPLEMENTED)
	
	public Entity(String name, UnitClass uClass, World w){
		// Basically just a placeholder
		w.worldEntities.add(this);
	}
	
	public static void drawAllEntitiesInWorld(Graphics g, World w){
		for(int i = 0; i<w.worldEntities.size(); i++){
			g.drawImage(w.worldEntities.get(i).src, w.worldEntities.get(i).coords[0]*Tile.tileSize + w.xOffset, w.worldEntities.get(i).coords[1]*Tile.tileSize + w.yOffset, null);
		}
	}
}
