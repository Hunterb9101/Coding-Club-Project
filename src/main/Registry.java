package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import windows.StartMenuWindow;
import windows.World;
import windows.WorldEditor;

public class Registry {
	public static HashMap<String,Image> imgRes = new HashMap<String,Image>();
	
	public static HashMap<String,Image> tileRes = new HashMap<String,Image>();
	public static HashMap<String,Image> overlayRes = new HashMap<String,Image>();
	
	
	// These HashMaps exist so that the Registry can generate a save schema that isn't hard-coded //
	public static HashMap<String,Integer> saveTileKey = new HashMap<String,Integer>();
	public static HashMap<String,Integer> saveOverlayKey = new HashMap<String,Integer>();
	
	
	static String imgLoadError = "Error loading image: ";
	
	public static void registerWindows(){
		new World("world");
		new WorldEditor("worldEditor");
		new StartMenuWindow("start");
	}
	
	
	public static void registerOverlays(){
		registerOverlay("null", loadImage("world/null.png"));
		registerOverlay("tree1",loadImage("world/foliage/tree.png"));
	}
	
	public static void registerTileBases(){
		registerTile("grass", loadImage("world/bases/grass.png"));
		registerTile("dirt", loadImage("world/bases/dirt.png"));
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
				System.out.println("Found animation: " + path);
				img = new ImageIcon(path).getImage();
			}
			else{
				System.out.println("Loaded resource: " + path);
				img = ImageIO.read(new File(path));
			}
		} catch (IOException e) {
			System.out.println(imgLoadError + path);
		} catch (NullPointerException e) {
			System.out.println(imgLoadError + path);
		}

		return img;
	}
}
