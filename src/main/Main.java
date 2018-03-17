package main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.GraphicsUtils;
import graphics.shapes.GraphicsObject;
import main.Player.traits;
import windows.Window;
import windows.world.World;

//////////////////////////////////
//				MAIN			//
//		Handles all Windows		//
//		Should not be edited	//
//		for addition of windows //
//		Press Compile Here		//
//////////////////////////////////

public class Main extends graphics.ConstructorClass{
	public static Player me = new Player(traits.NONE);
	public static int width = 800;
	public static int height = 800;
	
	public void init(int width, int height){
		this.setSize(800,800);
		
		
		Registry.registerOverlays();
		Registry.registerTileBases();
		
		Registry.registerImageResources();
		Registry.registerWindows();
		//MapLoader.save("map.txt");

	}
	
	public void draw(Graphics g, int width, int height){
		width = getSize().width;
		height = getSize().height;
		
		GraphicsObject.setDimens(width,height);
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.drawImage(Window.getWindow().draw(this),0,0,this);
	}
	
	public void mousePressed(MouseEvent evt) {
		super.mousePressed(evt);
		Window.getWindow().mousePressed(evt);
	}
	
	public void keyPressed(KeyEvent evt){
		super.keyPressed(evt);
		Window.getWindow().keyPressed(evt);
	}
}
