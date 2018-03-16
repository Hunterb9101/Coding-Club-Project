package main;

import java.awt.Image;
import java.util.HashMap;

public class Overlay {
	public static HashMap<String,Overlay> allOverlays = new HashMap<String,Overlay>();
	public String image;
	boolean isPassable = true;
	
	public Overlay(String image){
		this.image = image;
		allOverlays.put(image, this);
	}
	
	public void onEnter(){ // Eventually for when people enter certain areas.
		
	}
}
