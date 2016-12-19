package dungeon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import renders.BasicImage;

public class Block extends BasicTile{

	private boolean collides;

	public Block(String ref, Color id) {
		super(ref, id);
		
		x = 0;
		y = 0;
	}

	public void setCollides(boolean bool) {
		collides = bool;
	}

	public Block setCollidesO(boolean bool) {
		collides = bool;
		return this;
	}

	public boolean getCollides() {
		return collides;
	}

	public Block clone() {
		return new Block(ref, id).setCollidesO(collides);
	}
}
