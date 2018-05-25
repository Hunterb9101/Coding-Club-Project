package windows.world;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import entity.Perk;
import entity.Unit;
import entity.UnitClass;
import graphics.shapes.GraphicsImage;
import graphics.shapes.GraphicsObject;
import graphics.shapes.GraphicsPrimitives;
import main.Main;
import main.Registry;
import main.Utils;
import windows.Window;

public class BattleWindow extends World{
	public static Random rand = new Random();
	public static boolean isPlayerTurn = true;
	
	GraphicsPrimitives statsBox  = new GraphicsPrimitives(new Color(234,208,0),0,0,240,Main.height);
	GraphicsImage attackLeft = new GraphicsImage(Registry.imgRes.get("Flag2"),30,75,80,80);
	GraphicsImage attackRight = new GraphicsImage(Registry.imgRes.get("Flag2"),130,75,80,80);
	
	public static Unit selectedUnit;
	
	public BattleWindow(String name,String currMapPath) {
		super(name,currMapPath);
		UnitClass uClass = new UnitClass("NullUClass",0.0,0.0,0.0,0.0,2.0,0.0,0.0,0.0);
		Unit u = new Unit(":-)","Character",new int[]{5,4},uClass, 1,new Perk[]{},this, false);
		u.setTarget(new int[] {20,20});
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int x = Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset,this)[0];
		int y = Tile.selectTile(evt.getX() - xOffset, evt.getY() - yOffset,this)[1];
		System.out.println(x + ", " + y);
		int[][] bounds = Tile.getDrawBounds(this);
		//Unit.moveAllUnits(this);
		
		selectedUnit = Unit.selectUnit(this, new int[] {x,y});
		
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
		statsBox.drawObject(g);
		g.setColor(Color.black);
		g.setFont(new Font("Helvetica", Font.PLAIN, 36));
		g.drawString("Attack", 10, 30);
		attackLeft.drawObject(g);
		attackRight.drawObject(g);
		g.drawString("Abilities", 10, 210);
	}
}
