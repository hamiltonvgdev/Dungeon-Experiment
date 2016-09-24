package player;

import org.newdawn.slick.Input;

import map.MapRender;
import renders.AnimationSet;

public class BasicPlayer 
{
	float x;
	float y;
	float speed;
	Input input;
	AnimationSet idle;
	
	public BasicPlayer(float speed)
	{
		x = 300;
		y = 300;
		this.speed = speed;
		input = new Input(1);
	}
	
	public void update()
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
	
	public void render()
	{
		
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
