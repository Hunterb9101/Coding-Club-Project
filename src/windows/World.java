package windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import main.Utils;

public class World extends Window{

	public World(String name) {
		super(name,false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedImage draw(Component mainWindow) {
		// Needed Statements in any draw()
		BufferedImage render = Utils.toBufferedImage(mainWindow.createImage(800,800)); // 200x200 is the window size
		Graphics g = render.getGraphics();
		
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				g.setColor(new Color(20*x,20*y,0));
				g.fillRect(x*20+40,y*20+40,(int)(20),(int)(20));
			}
		}
		
		return render;
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
