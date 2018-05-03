package windows.world;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import main.Registry;
import main.Utils;

// MapLoader handles all saving/loading of maps //
public class MapLoader {
	public static HashMap<String,Tile> tileLoadKey = new HashMap<String,Tile>();	// Contains the Key for the Map
	public static HashMap<String,Overlay> overlayLoadKey = new HashMap<String,Overlay>(); // Contains the Key for the Overlays
	
	//Put this in currMapPath in World or WorldEditor to create a map
	public static final String genMapKey = "%ToGenerate%"; // DO NOT MAKE A FILE WITH THIS NAME! 
		
	public static String loadedMap = genMapKey; // Currently Loaded Map
	
	public static Random rand = new Random();
	
	public static void save(String mapName, World w){
		String saveGame = "";
		saveGame += Tile.maxRow + "," + Tile.maxCol + "//"; // Stores Map Dimensions
		
		for(int i = 0; i<Registry.saveTileKey.size(); i++){ // Store Tile Key
			saveGame += String.valueOf(i) + "." + Utils.getKeyByValue(Registry.saveTileKey, i);
			if(i != Registry.saveTileKey.size() - 1){
				saveGame += ",";
			}
		}
		
		saveGame += "//";
		
		for(int i = 0; i<Registry.saveOverlayKey.size(); i++){ // Store Overlay Key
			saveGame += String.valueOf(i) + "." + Utils.getKeyByValue(Registry.saveOverlayKey, i);
			if(i != Registry.saveTileKey.size() - 1){
				saveGame += ",";
			}
		}
		
		saveGame += "//";
		
		for(int i = 0; i<w.worldTiles.size(); i++){ // Store Base Tile Images
			saveGame += String.valueOf(Registry.saveTileKey.get(w.worldTiles.get(i).baseImage));
			if(i != w.worldTiles.size() - 1){
				saveGame += ",";
			}
		}
		
		saveGame += "//";
		
		for(int i = 0; i<w.worldTiles.size(); i++){ // Store Overlay
			saveGame += String.valueOf(Registry.saveOverlayKey.get(w.worldTiles.get(i).overlay.image));
			if(i != w.worldTiles.size() - 1){
				saveGame += ",";
			}
		}
		Utils.writeFile(Utils.getWorkspaceDirectory() + "res\\maps\\" + mapName + ".txt", new String[]{"",saveGame});
	}
	
	public static void load(String path, World w){
		System.out.println("Loading Map: " + path);
		loadedMap = path;
		String[] map = Utils.readFile(new File(path)).get(0).split("//");	
		w.worldTiles.clear();
		// Set Dimensions //
		Tile.maxRow = Integer.valueOf(map[0].split(",")[0]);
		Tile.maxCol = Integer.valueOf(map[0].split(",")[1]);
		
		// Create Key for Tiles from Schema //
		HashMap<Integer,String> loadTileKey = new HashMap<Integer,String>();
		String[] tileKeyEntries = map[1].split(",");
		
		for(int i = 0; i<tileKeyEntries.length; i++){
			loadTileKey.put(Integer.valueOf(tileKeyEntries[i].split("\\.")[0]), tileKeyEntries[i].split("\\.")[1]);
		}
		
		// Create Key for Overlay from Schema //
		HashMap<Integer,String> loadOverlayKey = new HashMap<Integer,String>();
		String[] overlayKeyEntries = map[2].split(",");
		for(int i = 0; i<overlayKeyEntries.length; i++){
			loadOverlayKey.put(Integer.valueOf(overlayKeyEntries[i].split("\\.")[0]), overlayKeyEntries[i].split("\\.")[1]);
		}
		
		String[] tile = map[3].split(",");
		String[] overlay = map[4].split(",");
		
		for(int j = 0; j<Tile.maxCol; j++){
			for(int i = 0; i<Tile.maxRow; i++){
				new Tile(new int[]{i,j},loadTileKey.get(Integer.valueOf(tile[i+j*Tile.maxRow])),Overlay.allOverlays.get(loadOverlayKey.get(Integer.valueOf(overlay[i+j*Tile.maxRow]))),w);
			}
		}
	}
	
	public static void generateMap(World w){
		System.out.println("Generating Map");
		w.worldTiles.clear();
		loadedMap = genMapKey;
		
		for(int x = 0; x < 40; x++){
			for(int y = 0; y < 40; y++){
				if(rand.nextDouble()< .05){
					new Tile(new int[]{x,y},"grass",Overlay.allOverlays.get("tree"),w);
				}
				else{
					new Tile(new int[]{x,y},"grass",w);
				}	
			}
		}
	}
	
}
