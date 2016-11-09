package renders;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AfterImage 
{
	public boolean present;
	
	BasicImage[] sprite;
	float height;
	float width;
	float[][] values;// 0 is x, 1 is y, 2 is rotation
	float[][] qualities; // 0 is opacity, 1 is r, 2 is g, 3 is b
	
	long lastTick;
	long delay;
	
	public AfterImage(String ref, int num, long delay, float width, float height )
	{
		present = false;
		
		sprite = new BasicImage[num];
		values = new float[num][3];
		qualities = new float[num][4];
		this.width = width;
		this.height = height;
		
		for(int i = 0; i < num; i ++)
		{
			BasicImage Sprite = new BasicImage(ref);
			sprite[i] = Sprite;
			
			qualities[i][0] = 100 / 100F;
			qualities[i][1] = 250 / 255F;
			qualities[i][2] = 250 / 255F;
			qualities[i][3] = 255 / 255F;
		}
		
		this.delay = delay;
		
		lastTick = System.currentTimeMillis();
	}
	
	public void setFlip(boolean flip)
	{
		for(int i = 0; i < sprite.length; i ++)
		{
			sprite[i].setFlip(flip);;
		}
	}
	
	public void setAfterImage(boolean present)
	{
		this.present = present;
	}
	
	public void setDimensions(float width, float height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setIndividualOpacity(int index, float opacity)
	{
		qualities[index][0] = opacity;
	}
	
	public void toggleDecreasingOpacity()
	{
		for(int i = 0; i < sprite.length; i ++)
		{
			qualities[i][0] = (float) (1 * ((sprite.length - i  + 0.0) / (sprite.length + 0.0)));
		}
	}
	
	public void toggleIncreasingOpacity()
	{
		for(int i = 0; i < sprite.length; i ++)
		{
			qualities[i][0] = (float) (1 * ((i  + 0.0) / (sprite.length + 0.0)));
		}
	}
	
	public void colorAll(float r, float g, float b)
	{
		for(int i = 0; i < sprite.length; i ++)
		{
			qualities[i][1] = r;
			qualities[i][2] = g;
			qualities[i][3] = b;
		}
	}
	
	public void setIndividualColor(int index, float r, float g, float b)
	{
		qualities[index][1] = r;
		qualities[index][2] = g;
		qualities[index][3] = b;
	}
	
	public void shadeAll(float r, float g, float b)
	{
		for(int i = 0; i < sprite.length; i ++)
		{
			qualities[i][1] += r;
			qualities[i][2] += g;
			qualities[i][3] += b;
		}
	}
	
	public void update(float x, float y, float rot)
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{	
			
			for(int i =  sprite.length - 1; i >  0; i --)
			{
				values[i][0] = values[i - 1][0]; 
				values[i][1] = values[i - 1][1]; 
				values[i][2] = values[i - 1][2]; 
				
			}
			
			values[0][0] = x;
			values[0][1] = y; 
			values[0][2] = rot;
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void move(float xa, float ya)
	{
		for(int i = 0; i < sprite.length; i ++)
		{
			values[i][0] += xa;
			values[i][1] += ya;
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		for(int i = 0; i < sprite.length; i ++)
		{	
			Image image = sprite[i].getImage().copy();
		    image = sprite[i].getImage().getFlippedCopy(sprite[i].flip, false);
		    image.setFilter(2);
		    image.setRotation(values[i][2]);
		    image.draw(values[i][0] - width / 2.0F, values[i][1] - height / 2.0F, width, height, 
		    		new Color(qualities[i][1], qualities[i][2], qualities[i][3], qualities[i][0]));
		}
	}
}
