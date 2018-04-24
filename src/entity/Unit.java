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
	Perk[]perks;
	
	
	public Unit(String name,int hp,int atk,int mAtk,int def,int spd,int mRes,int luck,Perk[]perks) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.mAtk = mAtk;
		this.def = def;
		this.spd = spd;
		this.mRes = mRes;
		this.luck = luck;
		this.perks = perks;
	}
}
