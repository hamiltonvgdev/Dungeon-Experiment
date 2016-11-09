package inanimate_objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import renders.AnimationSet;

public class Thing 
{
	String id;
	
	float x;
	float y;
	
	int tickCount;
	
	AnimationSet sprite;
	float width;
	float height;
	float rot;
	
	public Thing(float x, float y, float width, float height)
	{
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		rot = 0;
		
		tickCount = 0;
		
		id = " ";
	}
	
	public void update()
	{
		tickCount++;
	}
	
	public void render(Graphics g) throws SlickException
	{
		sprite.render(x, y - height / 2, width, height, rot, g);
	}
}
