package graphics.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import entity.items.InventoryItem;
import main.Main;
import main.Registry;
import main.Utils;
import windows.InventoryWindow;

public class GraphicsGridEntry extends GraphicsObject {
	public Image src;
	public String text;
	
	public InventoryItem i;
	
	private HoverBox descrip;
	private GraphicsGrid parent;

	protected boolean isHidden = false;
	
	public GraphicsGridEntry(Image iSrc, int iX, int iY, String iText, GraphicsGrid parent) {
		// SHOULD NEVER BE CALLED EXCEPT BY GRAPHICS GRID
		super(iX, iY, parent.itemWidth, parent.itemHeight);
		this.parent = parent;
		src = iSrc;
		text = iText;
		descrip = new HoverBox(iX, iY, text);
	}
	
	public GraphicsGridEntry(int iX, int iY, InventoryItem i, GraphicsGrid parent) {
		// SHOULD NEVER BE CALLED EXCEPT BY GRAPHICS GRID
		super(iX, iY, parent.itemWidth, parent.itemHeight);
		this.parent = parent;
		this.i = i;
		src = i.src;
		text = i.parseText();
		descrip = new HoverBox(iX, iY, text);
	}

	public void drawObject(Graphics g) {
		// SHOULD NEVER BE CALLED EXCEPT BY GRAPHICS GRID
		g.drawImage(src, (int) (x * xScalar), (int) (y * yScalar), (int) (width * xScalar),
				(int) (height * yScalar), null);
		g.setColor(Color.black);
		g.drawRect((int) (x * xScalar), (int) (y * yScalar), (int) (width * xScalar),
				(int) (height * yScalar));// adds outline to the image
	}

	public void onClick() {
		switch(parent.behavior){
		case BUY:
			if(Main.me.gold >= i.cost){
				Main.me.gold -= i.cost;
				InventoryWindow.grid.addEntry(i);
			}else{
				Utils.printBox("Not enough gold");
			}
			
			break;
		case EQUIP:
			int index = 5;
				if(i.isWeapon()){
					Main.me.equippedWeapon = i.getWeapon();
					InventoryWindow.equipped.addEntry(i, index);
				}
				else if(i.isArmor()){
					switch(i.getArmor().armorLocation){
					case BOOTS:
						Main.me.equippedBoots = i.getArmor();
						index = 4;
						break;
					case CHEST:
						Main.me.equippedChestplate = i.getArmor();
						index = 1;
						break;
					case GLOVES:
						Main.me.equippedGloves = i.getArmor();
						index = 2;
						break;
					case HEAD:
						Main.me.equippedHelmet = i.getArmor();
						index = 0;
						break;
					case PANTS:
						Main.me.equippedLeggings = i.getArmor();
						index = 3;
						break;
					default:
						break;					
					}
					InventoryWindow.equipped.addEntry(i.getArmor(), index);
			}
			break;
		case NONE:
			break;
		case SELL:
			break;
		case UNEQUIP:
			parent.removeEntry(i);
			break;
		default:
			break;
		
		}
		// equip
		
	}

	public void onHover(Graphics g) {
		descrip.drawObject(g); // Show Hover Box
	}

}