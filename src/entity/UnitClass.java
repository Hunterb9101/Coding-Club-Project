package entity;

import entity.items.Weapon;

public class UnitClass {
	//For setting up the unit classes 
	String name;
	double hp; //[hp+1,hp+2]
	double atk;
	double mAtk;
	double def;
	double spd;
	double mRes;
	double luck;
	double hitRate;
	
	
	public UnitClass(String name,double hp,double atk,double mAtk,double def,double spd,double mRes,double luck,double hitRate) {
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.mAtk = mAtk;
		this.def = def;
		this.spd = spd;
		this.mRes = mRes;
		this.luck = luck;
		this.hitRate = hitRate;
	}
}
