package entity;

import entity.items.Weapon;

public class UnitClass {
	//For setting up the unit classes 
	String name;
	int[]hp; //[hp+1,hp+2]
	int[]atk;
	int[]mAtk;
	int[]def;
	int[]spd;
	int[]mRes;
	int[]luck;
	int hitRate;
	Weapon weapon1;
	Weapon weapon2;
	Perk[]perkPath1;
	Perk[]perkPath2;
	
	
	public UnitClass(String name,int[]hp,int[]atk,int[]mAtk,int[]def,int[]spd,int[]mRes,int[]luck,int hitRate, Weapon weapon1,Weapon weapon2,Perk[]perkPath1,Perk[]perkPath2) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.mAtk = mAtk;
		this.def = def;
		this.spd = spd;
		this.mRes = mRes;
		this.luck = luck;
		this.hitRate = hitRate;
		this.weapon1 = weapon1;
		this.weapon2 = weapon2;
		this.perkPath1 = perkPath1;
		this.perkPath2 = perkPath2;
	}
}
