package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import entity.Perk;
import entity.Spell;

import entity.Unit;
import entity.UnitClass;
import windows.StartMenuWindow;
import windows.world.BattleWindow;
import windows.world.MapLoader;
import windows.world.Overlay;
import windows.world.World;
import windows.world.WorldEditor;

public class Registry {
	public static HashMap<String,Image> imgRes = new HashMap<String,Image>();
	
	public static HashMap<String,Image> tileRes = new HashMap<String,Image>();
	public static HashMap<String,Image> overlayRes = new HashMap<String,Image>();
	
	public static HashMap<String,UnitClass> presetClasses = new HashMap<String,UnitClass>();
	
	
	// These HashMaps exist so that the Registry can generate a save schema that isn't hard-coded //
	public static HashMap<String,Integer> saveTileKey = new HashMap<String,Integer>();
	public static HashMap<String,Integer> saveOverlayKey = new HashMap<String,Integer>();
	
	public static boolean resourceDebugOutput = false; // Suppresses/Enables "Loaded Resource" System.println() output
	static String imgLoadError = "Error loading image: ";
	
	
	public static void registerItems() {
		// Include Swords, Potions, and stuff here
	}
	

	
	public static void registerPresetClasses() {
		// Register PlayerClasses here
		
		
		//UnitClass Params: Name, HP, ATK, mATK, DEF, SPD, MRES, LUCK, LEVEL. Set level up chance
		registerPresetClass("Bleh", new UnitClass("Bleh", .5, .5, .5, .5, .5, .5, .5, .5));
	}
	
	private static void registerPresetClass(String key, UnitClass c) {
		
		presetClasses.put(key, c);
	}
	
	public static void registerWindows(){
		new WorldEditor("worldEditor", MapLoader.genMapKey);
		new StartMenuWindow("start");
		new BattleWindow("battle");
	}
	
	
	public static void registerOverlays(){
		registerOverlay("null", loadImage("world/null.png"));
		registerOverlay("tree",loadImage("world/foliage/tree.png"));
		registerOverlay("cactustop",loadImage("world/foliage/cactus_top.png"));
		registerOverlay("cactusbottom",loadImage("world/foliage/cactus_bottom.png"));
		
		registerOverlay("doortop",loadImage("world/village/door_top.png"));
		registerOverlay("doorbottom",loadImage("world/village/door_bottom.png"));
		registerOverlay("path",loadImage("world/village/path.png"));
	}
	
	public static void registerTileBases(){
		registerTile("grass", loadImage("world/bases/grass.png"));
		registerTile("dirt", loadImage("world/bases/dirt.png"));
		registerTile("beach", loadImage("world/bases/beach.png"));
		registerTile("beachocean", loadImage("world/bases/beach_ocean.png"));
		registerTile("desert", loadImage("world/bases/desert.png"));
		registerTile("desertrocks", loadImage("world/bases/desert_rocks.png"));
		registerTile("desertrocks2", loadImage("world/bases/desert_rocks_2.png"));
		registerTile("ocean", loadImage("world/bases/ocean.png"));
		registerTile("oceanwavy", loadImage("world/bases/ocean_wavy.png"));
		registerTile("oceanrocks", loadImage("world/bases/ocean_rocks.png"));
		registerTile("oceanrocks2", loadImage("world/bases/ocean_rocks_2.png"));
	}
	
	public static void registerImageResources(){
		imgRes.put("titleArt", loadImage("buttons/titleArt.png"));
		imgRes.put("creditButton", loadImage("buttons/Credits.png"));
		imgRes.put("exitButton", loadImage("buttons/Exit.png"));
		imgRes.put("loadGameButton", loadImage("buttons/LoadGame.png"));
		imgRes.put("newGameButton", loadImage("buttons/NewGame.png"));	
		
		imgRes.put("mainMap", loadImage("resMaps/MainMap.png"));
		imgRes.put("mainMapMonochromatic", loadImage("resMaps/MainMapMonochromatic.png"));
		
		imgRes.put("ShopMenu", loadImage("resMaps/RegionShop.png"));
		imgRes.put("FightLoopMenu", loadImage("resMaps/Adventure.png"));
		imgRes.put("InventoryMenu", loadImage("resMaps/Inventory.png"));
		imgRes.put("Flag", loadImage("resMaps/Flag.png"));//test image
		imgRes.put("Flag2", loadImage("resMaps/Flag2.png"));
		imgRes.put("Grid", loadImage("resMaps/Grid.png"));
		
		
		// Should not be placed here, but will be for now.
		imgRes.put("Character", loadImage("entity/PlayerALPHA.png"));
	}
	
	public static void registerTile(String key, Image value){
		tileRes.put(key, value);
		saveTileKey.put(key, saveTileKey.size());
	}
	
	public static void registerOverlay(String key, Image value){
		overlayRes.put(key, value);
		new Overlay(key);
		saveOverlayKey.put(key, saveOverlayKey.size());
	}
	
	
	public static Image loadImage(String path) {
		Image img = null;
		try {
			if(path.substring(path.indexOf(".")).equals(".gif")){
				if(resourceDebugOutput){
					System.out.println("Found animation: " + path);
				}
				img = new ImageIcon(path).getImage();
			}
			else{
				if(resourceDebugOutput){
					System.out.println("Loaded resource: " + path);
				}
				img = ImageIO.read(new File(path));
			}
		} catch (IOException e) {
			System.out.println(imgLoadError + path);
		} catch (NullPointerException e) {
			System.out.println(imgLoadError + path);
		}

		return img;
	}
	public void registerSpells() {
		Spell smashAttack = new Spell("Smash Attack", "earth",3, 1, 1);
		Spell rockWall = new Spell("Rock Wall", "earth", 2, 0, 2, 0, 100, 2, 1, "rock wall");
		Spell sink = new Spell("Sink", "earth", 5, 0, 3, "bury", 0, true, 2, 0);
		Spell sandstorm = new Spell("Sandstorm", "earth", 4, .5, 7, .5, 3, 5, 5);
		Spell landslide = new Spell("Landslide", "earth", 4, 1.5, 4, 0, 1, 1, 3);
		
		
		Spell bubble = new Spell("Bubble","water",5,.75,1);
		Spell steamBlast = new Spell("Steam Blast","water",3,1,1);
	}
	
}
