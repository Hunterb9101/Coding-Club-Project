package entity;

import java.awt.Image;

import entity.items.Weapon;
import main.Registry;
import windows.world.World;

public class WeaponProficiency {
	Weapon weapon;
	boolean [] perks;//list of perks that have been chosen from the perks in the weapon class
	double level;//int is proficiency level decimal is progress to next level
	public WeaponProficiency(Weapon weapon, boolean [] perks, double level) {	
		this.weapon = weapon;
		this.perks = perks;
		this.level = level;
	}
}
