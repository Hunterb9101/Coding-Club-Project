package entity;

import entity.items.Weapon;

public class Unit {
	String name;
	int[]hp; //[hp,hp+1,hp+2]
	int[]atk;
	int[]mAtk;
	int[]def;
	int[]spd;
	int[]mRes;
	int[]luck;
	int hitRate;
	Weapon weapon1;
	Weapon weapon2;
	Perk[]perks;
	
	
	public Unit(int[]hp,int[]atk,int[]mAtk,int[]def,int[]spd,int[]mRes,int[]luck,int hitRate, Weapon weapon1,Weapon weapon2,Perk[]perks) {
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
		this.perks = perks;
	}
}
