package main;

import java.util.ArrayList;

import entity.items.Armor;
import entity.items.InventoryItem;
import entity.items.Weapon;

public class Player {
	public ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
	
	public int zoneLvl = 1;
	public int lvl = 1; // Player's Current Level
	public int totalXp = 0; // The stuff that makes up the level, certain amount
							// of
	// XP increases level
	public int gold = 0;

	public int hp = 10;
	public int maxHp = 0; // !SHOULD NOT BE EDITED! //
	public int attack = 0;

	public Armor equippedHelmet;
	public Armor equippedChestplate;
	public Armor equippedGloves;
	public Armor equippedLeggings;
	public Armor equippedBoots;
	public Weapon equippedWeapon;
	/* Skills */
	public int skillAttk = 0; // Melee Weapons Attack Bonus
	public int skillAccuracy = 0; // Ranged Weapons Attack Bonus
	public int skillHitPoints = 0; // Adds Hitpoints to User
	public double skillEvasionChance = 0; // Chance to Evade Enemy
	public int skillGoldHoarder = 0; // Gold gained bonus
	public int skillSpeed = 1;

	public enum traits {
		NONE, THIEF, WARRIOR, ARCHER
	};

	public traits job;
	public int critic = 1;
	private Timer playerTimer;

	public Player(traits job) {
		playerTimer = new Timer();
		switch (job) {
		case NONE:
			break;
		case THIEF:
			// Set weaknesses and strengths
			break;
		case WARRIOR:
			// Set weaknesses and strengths
			break;
		case ARCHER:
			// Set weaknesses and strengths
			break;
		}
	}

	public boolean canAttack() {
		if (playerTimer.getElapsedTime() > 10 / skillSpeed) {
			return true;
		}
		return false;
	}

	public void attacked() {
		playerTimer.start();
	}

	public int calculateMaxHP() {
		maxHp = (hp * lvl);
		//checks that some armor is equipped
		//then adds it to the max HP
		if (equippedHelmet != null) {
			maxHp += equippedHelmet.defense;
		}
		if (equippedChestplate != null) {
			maxHp += equippedChestplate.defense;
		}
		if (equippedGloves != null) {
			maxHp += equippedGloves.defense;
		}
		if (equippedLeggings != null) {
			maxHp += equippedLeggings.defense;
		}
		if (equippedBoots != null) {
			maxHp += equippedBoots.defense;
		}
		// Calculate the max hitpoints based off of armor, skills, etc. //
		return maxHp;
	}

	public void giveGold(int GoldGiven) {
		// Will be used to give gold to the player (Will take into mind the
		// skill sets //
		gold += GoldGiven;
	}

	public int getAttackDamage() {
		attack = lvl * 5;
		if (equippedWeapon == null) {

		} else {
			attack += equippedWeapon.damage;
		}
		// This formula will be used to calculate the amount of damage will be
		// done to a target //
		return attack;
	}
}
