package windows;
 
 import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import graphics.shapes.GraphicsImage;
import graphics.shapes.GraphicsObject;
import graphics.shapes.GraphicsPrimitives;
import main.Main;
import main.Registry;
import main.Utils;
 
 public class StartMenuWindow extends Window {
	GraphicsImage title = new GraphicsImage(Registry.imgRes.get("titleArt"),0,0,800,200);
 	GraphicsPrimitives titleLineTop    = new GraphicsPrimitives(new Color(234,208,0),0,198,800,2);
 	GraphicsPrimitives titleLineBottom = new GraphicsPrimitives(new Color(234,208,0),0,0,800, 2);
 	GraphicsPrimitives titleLineLeft   = new GraphicsPrimitives(new Color(234,208,0),0,0,2,200);
 	GraphicsPrimitives titleLineRight  = new GraphicsPrimitives(new Color(234,208,0),798,0,2,200);
 	// GraphicsImage(Image source, Int X, Int Y, Int Width, Int Height);
 
 	GraphicsImage loadGame = new GraphicsImage(Registry.imgRes.get("loadGameButton"),300,225,200,50){
 			@Override
 			public void onClick(){ //What happens when this image is clicked?
 				Window.setWindow("worldEditor"); // Sets the window to the "world" window (as defined in the registry
 			}
 	};
 
 	GraphicsImage newGame = new GraphicsImage(Registry.imgRes.get("newGameButton"),300,300,200,50){
 			@Override
 			public void onClick(){
 				Window.setWindow("world");
 			}
 	};
 
 	GraphicsImage credits = new GraphicsImage(Registry.imgRes.get("creditButton"),300,375,200,50){ 
 		@Override
 		public void onClick(){
 			Window.setWindow("battle");
 		}
 	};
 	
 	GraphicsImage exit = new GraphicsImage(Registry.imgRes.get("exitButton"),300,450,200,50){
 	};
 	
 	GraphicsImage mapBg = new GraphicsImage(Registry.imgRes.get("mainMapMonochromatic"),0,0,800,800);
 	
 	public StartMenuWindow(String name) {
		super(name, true);
	}
 	
 	@Override
 	public BufferedImage draw(Component mainWindow) {
 		BufferedImage render = Utils.toBufferedImage(mainWindow.createImage(GraphicsObject.currWidth,GraphicsObject.currHeight)); // Windows are created as bufferedImages so that they can be transformed later
		Graphics g = render.getGraphics();
		
		mapBg.drawObject(g);
		g.setColor(new Color(234,208,0,192));
		g.fillRect(0, 0, GraphicsObject.currWidth, GraphicsObject.currHeight);
		
 		title.drawObject(g); // Used to draw any of the GraphicsObjects
 		titleLineTop.drawObject(g);
 		titleLineBottom.drawObject(g);
 		titleLineLeft.drawObject(g);
 		titleLineRight.drawObject(g);
 		
 		loadGame.drawObject(g);
 		newGame.drawObject(g);
 		credits.drawObject(g);
 		exit.drawObject(g);
 		// TODO Auto-generated method stub
		return render;
 		
 	}

	@Override
	public void mousePressed(MouseEvent evt) {
		GraphicsObject.checkOnClick(evt.getX(), evt.getY());
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		
	}
 }