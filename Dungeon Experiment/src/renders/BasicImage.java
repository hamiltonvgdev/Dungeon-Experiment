package renders;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BasicImage 
{
	Image sprite;
	boolean flip;
	
	public BasicImage(String ref)
	{
		flip = false;
		
		try {
			sprite = new Image(ref);
		} catch (SlickException e) {
			e.printStackTrace();
			setError();
		}
	}

	public String getPath()
	{
		return sprite.getResourceReference();
	}
	
	private void setError()
	{
		try {
			sprite = new Image("res/The Error Picture.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void setFlip(boolean flip)
	{
		this.flip = flip;
	}
	
	public void render(float x, float y, float width, float height, float rot, Graphics g) 
	{
	    sprite = sprite.getFlippedCopy(this.flip, false);
	    sprite.setFilter(2);
	    sprite.setRotation(rot);
	    sprite.draw(x - width / 2.0F, y - height / 2.0F, width, height);
	}
}
