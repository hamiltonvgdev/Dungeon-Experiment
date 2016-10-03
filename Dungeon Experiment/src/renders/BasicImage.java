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
		
		if(ref.equals("") || ref.equals(null))
		{
			setError();
		}else
		{
			try {
				sprite = new Image(ref);
			} catch (SlickException e) {
				setError();
			}
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
	
	public Image getImage()
	{
		return sprite;
	}
	
	public void render(float x, float y, float width, float height, float rot, Graphics g) 
	{
		Image image = sprite.copy();
	    image = sprite.getFlippedCopy(this.flip, false);
	    image.setFilter(2);
	    image.setRotation(rot);
	    image.draw(x - width / 2.0F, y - height / 2.0F, width, height);
	}
}
