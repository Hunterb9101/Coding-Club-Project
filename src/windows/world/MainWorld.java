package windows.world;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JOptionPane;

import main.Main;
import main.Registry;
import main.Utils;
import windows.Window;

public class MainWorld extends World{
	public int scrollSpeed = 80;
	public static boolean isOverlay = false;
	public static int item = 0;
	
	public MainWorld(String name, String mapPath) {
		super(name,mapPath);
		setMap(mapPath);
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int[] coords = Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset,this);		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyChar() == 'd'){
			xOffset -= scrollSpeed;
		}
		else if(evt.getKeyChar() == 'a'){
			xOffset += scrollSpeed;
			
		}
		else if(evt.getKeyChar() == 's'){
			yOffset -= scrollSpeed;
		}
		else if(evt.getKeyChar() == 'w'){
			yOffset += scrollSpeed;
		}
	}

	@Override
	public void drawWorld(Graphics g, int width, int height) {
	}
}
