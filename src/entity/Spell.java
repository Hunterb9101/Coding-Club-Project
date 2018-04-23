package entity;

import java.util.ArrayList;

import main.Spells;

public class Spell {
	public static ArrayList<Spells> allSpells = new ArrayList<Spells>();
	public String name;
	public String type;
	public int range;
	public double dmg; //As a percent of the Magic Attack Stat
	public int recharge; //In turns
	
	public String effect;//Like freeze, burn, or increased speed
	public double effectMag;//%Boost or chance of effect
	public boolean friendly;//To be used on enemy or on team?
	
	public double dmgPerTurn; //Starting on the second turn
	public int duration;
	public int splashWidth;
	public int splashLength;//In the direction away from the user
	
	public String terrain;
	
	public String unit;
	public int healthCost;
	
	public int heal;
	public int radius;
	
	public Spell(String name, String type, int range, double dmg, int recharge) {
		//Basic Spell 
		this.name = name;
		this.type = type;
		this.range = range;
		this.dmg = dmg;
		this.recharge = recharge;	
	}
	public Spell(String name, String type, int range, double dmg, int recharge, String effect, double effectMag, boolean friendly, int duration, int healthCost) {
		//Spells that change stats 
		this.name = name;
		this.type = type;
		this.range = range;
		this.dmg = dmg;
		this.recharge = recharge;
		this.effect = effect;
		this.effectMag = effectMag;
		this.friendly = friendly;
		this.duration = duration;
		this.healthCost = healthCost;
	}
	public Spell(String name, String type, int range, double dmg, int recharge, double dmgPerTurn, int duration, int splashWidth, int splashLength) {
		//Lasting, Splash Spells
		this.name = name;
		this.type = type;
		this.range = range;
		this.dmg = dmg;
		this.recharge = recharge;
		this.dmgPerTurn = dmgPerTurn;
		this.duration = duration;
		this.splashWidth = splashWidth;
		this.splashLength = splashLength;	
	}
	public Spell(String name, String type, int range, double dmg, int recharge, double dmgPerTurn, int duration, int splashWidth, int splashLength, String terrain) {
		//Terrain Modifying Spell
		this.name = name;
		this.type = type;
		this.range = range;
		this.dmg = dmg;
		this.recharge = recharge;
		this.dmgPerTurn = dmgPerTurn;
		this.duration = duration;
		this.splashWidth = splashWidth;
		this.splashLength = splashLength;
		this.terrain = terrain;
	}
	public Spell(String name, String type, String unit, int healthCost, int recharge) {
		//Conjuration and maybe resurrect
		this.name = name;
		this.type = type;
		this.unit = unit;
		this.healthCost = healthCost;
		this.recharge = recharge;
	}
	public Spell(String name, String type, int range, int heal, int radius, int recharge ) {
		//Healing spells
		this.name = name;
		this.type = type;
		this.range = range;
		this.heal = heal;
		this.radius = radius;
		this.recharge = recharge;
	}
}
