/*
 * I am writing this to test the commit capabilities of GIT!
 * Another Test...
 */
package entity.items;

import java.awt.Image;
import java.util.ArrayList;

import main.Registry;

public class Armor extends InventoryItem{
	public static ArrayList<Armor> allArmor = new ArrayList<Armor>();
	public String name;
	public int defense;
	int cost;
	boolean isShield;
	public static Image src = Registry.loadImage("res/Flag2.png");
	
	public Armor(String name, int defense, int cost, boolean isShield) {
		super("0",src,cost);
		this.defense = defense;
		this.cost = cost;
		this.isShield =isShield;
		allArmor.add(this);
	}
	public static Armor getArmor(String name){
		Armor a = allArmor.get(2);
		for(int i = 0; i < allArmor.size(); i++){
			if(allArmor.get(i).name.equalsIgnoreCase(name)){
				a = allArmor.get(i);
			}
		}
		return a;
	}
	public String parseText(){
		String retString = name + " blocks " + defense + " damage";
		return retString;
	}
}
