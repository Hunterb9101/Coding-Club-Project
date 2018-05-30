package entity.items;

import java.awt.Image;
import java.util.ArrayList;

import entity.Perk;
import main.Registry;

public class Weapon extends InventoryItem{
	public static ArrayList<Weapon> allWeapons = new ArrayList<Weapon>();
	public String name;
	public int damage;
	int cost;
	int speedMod;
	double pierce;
	int darkMod;
	int reload;
	boolean fire;
	int radius;
	int dmgPerTurn;
	int duration;
	Perk [] perks;
	public static Image src = Registry.loadImage("res/Flag2.png");
	public Weapon(String name, int damage, int cost, int speedMod, double pierce, int darkMod, int reload,Perk []perks) {
		super(name,src,cost);
		this.speedMod = speedMod;
		this.name = name;
		this.damage = damage;
		this.cost = cost;
		this.pierce = pierce;
		this.darkMod = darkMod;
		this.reload = reload;
		this.perks = perks;
		allWeapons.add(this);	
	}
	public Weapon(String name, int damage, int cost, int speedMod, double pierce, int darkMod, int reload, boolean fire,
			int radius, int dmgPerTurn, int duration,Perk []perks) {
		super(name,src,cost);
		this.speedMod = speedMod;
		this.name = name;
		this.damage = damage;
		this.cost = cost;
		this.pierce = pierce;
		this.darkMod = darkMod;
		this.reload = reload;
		this.radius = radius;
		this.dmgPerTurn = dmgPerTurn;
		this.duration = duration;
		this.perks = perks;
		allWeapons.add(this);	
	}
	public static Weapon getWeapon(String name){
		Weapon w = allWeapons.get(2);
		for(int i = 0; i < allWeapons.size(); i++){
			if(allWeapons.get(i).name.equalsIgnoreCase(name)){
				w = allWeapons.get(i);
			}
		}
		return w;
	}
	public String parseText(){
		String retString = name + " does " + damage + " damage";
		return retString;
	}
}
