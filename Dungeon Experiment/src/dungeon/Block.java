package dungeon;

import org.newdawn.slick.Graphics;

import renders.BasicImage;

public class Block {

	private String ref;
	private boolean collides;
	private BasicImage sprite;

	public Block(String ref) {

		this.ref = ref;
		sprite = new BasicImage(ref);
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

	public void update(int x, int y) {

	}

	public void render(float x, float y, float width, float height, float rot, Graphics g) {

		sprite.render( x * 32, y * 32, width, height, rot, g);

	}

	public Block clone() {
		return new Block(ref).setCollidesO(collides);
	}
}
