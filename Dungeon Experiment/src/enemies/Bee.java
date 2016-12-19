package enemies;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import map.Level;
import player.BasicPlayer;
import renders.AnimationSet;

public class Bee extends Entity
{
	ArrayList<Bee> Swarm;
	BasicPlayer protag;
	float degree;
	
	public Bee(Level level, float x, float y, ArrayList<Bee> Swarm, BasicPlayer protag) 
	{
		super(level, x, y, 1, 50);
		
		this.Swarm = Swarm;
		
		this.protag = protag;
		
		BaseModel = new AnimationSet("res/Enemies/Bee", 100);
		
		width = 50;
		height = 50;
		
		degree = 0;
		
	}

	public void update()
	{
		if(health <= 0)
		{
			Swarm.remove(this);
		}
		super.update();
		if(distanceSense(200, protag))
		{
			if(distanceSense(50, protag))
			{
				speed = 2;
				wander();
			}else
			{
				speed = 1;
				follow(protag);
			}
		}else
		{
			speed = 1;
			for(int i = 0; i < Swarm.size(); i ++)
			{
				if(distanceSense(500, Swarm.get(i)) && !distanceSense(200, Swarm.get(i)))
				{
					follow(Swarm.get(i));
				}
				wander();
			}
		}
		
		BaseModel.resetAnimate();
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		super.render(gc, g);
	}
	
	public void swarm()
	{
		float radians = (float) Math.toRadians(degree);
		
		this.x = (float) (25 * Math.cos(radians)) + protag.getX();
		this.y = (float) (25 * Math.sin(radians)) + protag.getY();
		
		degree += speed * 10;
		
		if(degree >= 360)
		{
			degree = 0 + (degree % 360);
		}
	}
}
