package main;

import entity.Spell;

public class Spells {
	Spell smashAttack = new Spell("Smash Attack", "earth",3, 1, 1);
	Spell rockWall = new Spell("Rock Wall", "earth", 2, 0, 2, 0, 100, 2, 1, "rock wall");
	Spell sink = new Spell("Sink", "earth", 5, 0, 3, "bury", 0, true, 2, 0);
	Spell sandstorm = new Spell("Sandstorm", "earth", 4, .5, 7, .5, 3, 5, 5);
	Spell landslide = new Spell("Landslide", "earth", 4, 1.5, 4, 0, 1, 1, 3);
	
	



}
