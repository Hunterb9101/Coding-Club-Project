package main;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

// MapLoader handles all saving/loading of maps //
public class MapLoader {
	public static HashMap<String,Tile> tileLoadKey = new HashMap<String,Tile>();	// Contains the Key for the Map
	public static HashMap<String,Overlay> overlayLoadKey = new HashMap<String,Overlay>(); // Contains the Key for the Overlays
	public static String loadedMap = null;
	public static Random rand = new Random();
	
	public static void save(String mapName){
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
		
		for(int i = 0; i<Tile.allTiles.size(); i++){ // Store Base Tile Images
			saveGame += String.valueOf(Registry.saveTileKey.get(Tile.allTiles.get(i).baseImage));
			if(i != Tile.allTiles.size() - 1){
				saveGame += ",";
			}
		}
		
		saveGame += "//";
		
		for(int i = 0; i<Tile.allTiles.size(); i++){ // Store Overlay
			System.out.println(Tile.allTiles.get(i).coords[0] + ", " + Tile.allTiles.get(i).coords[1]);
			System.out.println(Tile.allTiles.get(i).overlay);
			System.out.println(Tile.allTiles.get(i).overlay.image);
			saveGame += String.valueOf(Registry.saveOverlayKey.get(Tile.allTiles.get(i).overlay.image));
			if(i != Tile.allTiles.size() - 1){
				saveGame += ",";
			}
		}
		FileOps.writeFile(Utils.getWorkspaceDirectory() + "res\\maps\\" + mapName + ".txt", new String[]{"",saveGame});
	}
	
	public static void load(String path){
		loadedMap = path;
		String[] map = FileOps.readFile(new File(path)).get(0).split("//");	
		Tile.allTiles.clear();
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
		
		for(int i = 0; i<Tile.maxRow; i++){
			for(int j = 0; j<Tile.maxCol; j++){
				new Tile(new int[]{j,i},loadTileKey.get(Integer.valueOf(tile[i+j*Tile.maxRow])),Overlay.allOverlays.get(loadOverlayKey.get(Integer.valueOf(overlay[i+j*Tile.maxRow]))));
			}
		}
	}
	
	public static void generateMap(){
		Tile.allTiles.clear();
		loadedMap = null;
		
		for(int x = 0; x < 40; x++){
			for(int y = 0; y < 40; y++){
				if(rand.nextDouble()< .05){
					new Tile(new int[]{x,y},"grass",Overlay.allOverlays.get("tree"));
				}
				else{
					new Tile(new int[]{x,y},"grass");
				}	
			}
		}
	}
	
}
