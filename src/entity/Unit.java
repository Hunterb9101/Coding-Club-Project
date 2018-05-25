package entity;

import java.awt.Graphics;
import java.awt.Image;

import main.Registry;
import windows.world.Tile;
import windows.world.World;

public class Unit {
	//For an actual unit 
	String name;
	UnitClass unitClass;
	double hp; 
	double atk;
	double mAtk;
	double def;
	double spd;
	double mRes;
	double luck;
	Image src;
	UnitClass c; //to determine how much it gets leveled up
	Perk[]perks;
	boolean AI;
	int[] coords;
	
	
	public Unit(String name, String img, int[] coord, UnitClass c, int lvl, Perk[] perks, World w, boolean isAI) {
		this.name  = name;
		this.hp    = c.hp * lvl;
		this.atk   = c.atk * lvl;
		this.mAtk  = c.mAtk * lvl;
		this.def   = c.def * lvl;
		this.spd   = c.spd * lvl;
		this.mRes  = c.mRes * lvl;
		this.luck  = c.luck * lvl;
		this.c	   = c;
		this.perks = perks;
		this.AI    = isAI;
		this.coords = coord;
		src = Registry.imgRes.get(img);
		w.worldUnits.add(this);
	}
	
	public static void drawAllUnitsInWorld(Graphics g, World w){
		
		for(int i = 0; i<w.worldUnits.size(); i++){
			g.drawImage(w.worldUnits.get(i).src, w.worldUnits.get(i).coords[0]*Tile.tileSize + w.xOffset, w.worldUnits.get(i).coords[1]*Tile.tileSize + w.yOffset, null);
		}
	}
	
}
