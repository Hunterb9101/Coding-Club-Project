package windows;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Window {
	public static HashMap<String,Window> allWindows = new HashMap<String,Window>();
	private static Window currentWindow = null; // This is private so that nothing can just randomly change it except the subclasses. 
	public String name;
	
	public Window(String name, boolean isStartup){
		this.name = name;
		
		if(isStartup){
			currentWindow = this;
		}
		allWindows.put(name, this);
	}
	
	public static Window getWindow(){
		return currentWindow;
	}
	
	public static void setWindow(String key){
		if(allWindows.get(key) != null){
			currentWindow = allWindows.get(key);
		}
		else{
			System.err.println("Window " + key + " not found.");;
		}
	}
	public abstract BufferedImage draw(Component mainWindow);
	
	public abstract void mousePressed(MouseEvent evt);
	public abstract void keyPressed(KeyEvent evt);
}
