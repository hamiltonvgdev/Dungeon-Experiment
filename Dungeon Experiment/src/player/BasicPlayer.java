package player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import map.MapRender;
import renders.AnimationSet;

public class BasicPlayer 
{
	float x;
	float y;
	float speed;
	boolean movable;
	
	int maxHealth;
	int health;
	boolean damaged;
	boolean poisoned;
	boolean slowed;
	
	Input input;
	
	AnimationSet idle;
	float height;
	float width;
	
	public BasicPlayer(float speed, int health)
	{
		x = 300;
		y = 300;
		this.speed = speed;
		movable = true;
		
		maxHealth = health;
		this.health = maxHealth;
		damaged = false;
		poisoned = false;
		slowed = false;
		
		input = new Input(1);
	}
	
	public void update()
	{
		if(movable)
		{
			if(input.isKeyDown(input.KEY_W))
			{
				MapRender.move(0, -speed);
			}
			if(input.isKeyDown(input.KEY_S))
			{
				MapRender.move(0, speed);
			}
			if(input.isKeyDown(input.KEY_A))
			{
				MapRender.move(-speed, 0);
			}
			if(input.isKeyDown(input.KEY_D))
			{
				MapRender.move(speed, 0);
			}
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		idle.render(x, y, width, height, 0, g);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
