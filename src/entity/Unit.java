package entity;


public class Unit {
	//For an actual unit 
	String name;
	UnitClass unitClass;
	int hp; 
	int atk;
	int mAtk;
	int def;
	int spd;
	int mRes;
	int luck;
	UnitClass c; //to determine how much it gets leveled up
	Perk[]perks;
	
	
	public Unit(String name,int hp,int atk,int mAtk,int def,int spd,int mRes,int luck, int level, UnitClass c, Perk[]perks) {
		this.name  = name;
		this.hp    = hp;
		this.atk   = atk;
		this.mAtk  = mAtk;
		this.def   = def;
		this.spd   = spd;
		this.mRes  = mRes;
		this.luck  = luck;
		this.c	   = c;
		this.perks = perks;
		
	}
}
