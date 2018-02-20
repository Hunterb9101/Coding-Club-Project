package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import windows.StartMenuWindow;
import windows.World;

public class Registry {
	public static HashMap<String,Image> imgRes = new HashMap<String,Image>();
	static String imgLoadError = "Error loading image: ";
	
	public static void registerWindows(){
		new World("world");
		new StartMenuWindow("start");
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
