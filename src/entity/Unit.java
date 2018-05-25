package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import main.Registry;
import windows.world.BattleWindow;
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
	int[] coordTarget;
	
	
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
			if(w.worldUnits.get(i) == BattleWindow.selectedUnit) {
				g.setColor(Color.yellow);
				g.drawRect(w.worldUnits.get(i).coords[0]*Tile.tileSize + w.xOffset - 10, w.worldUnits.get(i).coords[1]*Tile.tileSize + w.yOffset - 10, Tile.tileSize + 10, Tile.tileSize + 10);
			}
			g.drawImage(w.worldUnits.get(i).src, w.worldUnits.get(i).coords[0]*Tile.tileSize + w.xOffset, w.worldUnits.get(i).coords[1]*Tile.tileSize + w.yOffset, null);
		}
	}
	
	public void setTarget(int[] coordTarget) {
		this.coordTarget = coordTarget;
		
		
	}
	
	public void moveUnit() {
		System.out.println("MOVING ME: " + name);
		System.out.println("SPEED " + spd);
		
		if(coords[0] != coordTarget[0] && coords[1] != coordTarget[1]) {
			System.out.println("Need to move");
			
			double dx = coordTarget[0] - coords[0];
			double dy = coordTarget[1] - coords[1];
			
			double hypot = Math.hypot(dx, dy);
			double angle = Math.atan2(dy, dx);
			
			System.out.println("cos: " + (Math.cos(angle) * spd));
			System.out.println("sin: " + (Math.sin(angle) * spd));
			
			if(hypot > spd) {
				coords[0] = (int) Math.ceil(Math.cos(angle) * spd + coords[0]);
				coords[1] = (int) Math.ceil(Math.sin(angle) * spd + coords[1]);
			} else {
				coords[0] = coordTarget[0];
				coords[1] = coordTarget[1];
			}
			
			
		}
		
		
	}
	
	public static void moveAllUnits(World w) {
		for(int i = 0; i<w.worldUnits.size(); i++){
			w.worldUnits.get(i).moveUnit();
		}
	}
	
	public static Unit selectUnit(World w, int[] coords) {
		for(int i = 0; i<w.worldUnits.size(); i++){
			if(w.worldUnits.get(i).coords[0] == coords[0] && 
					w.worldUnits.get(i).coords[1] == coords[1]) {
				return w.worldUnits.get(i);
			}
		}
		System.out.println("uh oh, no unit there");
		return null;
	}
	
}
