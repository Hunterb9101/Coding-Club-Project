package windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.omg.Messaging.SyncScopeHelper;

import graphics.*;
import graphics.shapes.GraphicsGrid;
import main.Main;
import main.Registry;

public class InventoryWindow extends Window {
	public InventoryWindow(String name, boolean isStartup) {
		super(name, isStartup);
	}

	public static GraphicsGrid grid = new GraphicsGrid(120, 105, 5, 5, 90, GraphicsGrid.onClickBehavior.EQUIP);
	public static GraphicsGrid equipped = new GraphicsGrid(30, 145, 1, 6, 60, GraphicsGrid.onClickBehavior.UNEQUIP);

	@Override
	public BufferedImage draw(Component mainWindow) {
		Graphics g = mainWindow.getGraphics();
		equipped.drawObject(g);
		grid.drawObject(g);
		return null;
	}

	@Override
	public void mousePressed(MouseEvent evt) {
	}

	@Override
	public void keyPressed(KeyEvent evt) {
	}
}
