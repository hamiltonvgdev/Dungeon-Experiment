package dungeon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import renders.BasicImage;

public class BasicTile 
{
	Color id;
	protected BasicImage sprite;
	protected String ref;
	
	public BasicTile(String ref, Color id)
	{
		this.id = id;
		this.ref = ref;
		sprite = new BasicImage(this.ref);
	}
	
	public Color getID()
	{
		return id;
	}
	
	public BasicImage getSprite()
	{
		return sprite;
	}
	
	public void render(float x, float y, float xa, float ya, float rot, Graphics g) {

		sprite.render( x * 32 - xa, y * 32 - ya, 32, 32, rot, g);

	}
}
