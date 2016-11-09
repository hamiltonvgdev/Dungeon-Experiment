package enemies;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import map.Level;
import particle.OrbitingParticle;
import player.BasicPlayer;
import renders.AnimationSet;

public class Fireling extends Entity
{
	ArrayList<OrbitingParticle> flames = new ArrayList<OrbitingParticle>();
	
	boolean pulsing;
	int pulseCounter;
	
	BasicPlayer protag;
	
	public Fireling(Level level, float x, float y, float speed, BasicPlayer protag)
	{
		super(level, x, y, speed, 100);
		
		for(int count = 0; count < 5; count ++)
		{
			flames.add(new OrbitingParticle(x, y, 128, 0 + count * 360 / 5, 0.1F));
		}
		
		for(OrbitingParticle flame : flames)
		{
			flame.setAnimation("res/Enemies/Fireling/Fireball", 100);
			flame = (OrbitingParticle) flame.setSize(32, 32);
		}
		
		setAnimation("res/Enemies/Fireling/Body", 100);
		
		width = 56 * 2;
		height = 57 * 2;
		
		pulsing = false;
		pulseCounter = 0;
		
		this.protag = protag;
	}

	public void update()
	{
		super.update();
		
		BaseModel.resetAnimate();
		
		for(OrbitingParticle flame : flames)
		{
			flame.setOrigin(x, y);
			flame.update(2);
		}
		
		wander();
		
		if(distanceSense(200, protag) || pulsing)
		{
			pulse();
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		super.render(gc, g);
		
		for(OrbitingParticle flame : flames)
		{
			flame.render(g);
		}
	}
	
	public void pulse()
	{
		pulsing = true;
		
		if(pulseCounter < 100 * 2)
		{
			if(pulseCounter < 100)
			{
				for(OrbitingParticle flame: flames)
				{
					flame.setRadius(flame.getRadius() + 1);
				}
			}else
			{
				for(OrbitingParticle flame: flames)
				{
					flame.setRadius(flame.getRadius() - 1);
				}
			}
			
			for(OrbitingParticle flame: flames)
			{
				flame.setSpeed(0.25F);
			}
			
			pulseCounter ++;
		}else
		{
			for(OrbitingParticle flame: flames)
			{
				flame.setSpeed(0.1F);
			}
			
			pulseCounter = 0;
			pulsing = false;
		}
	}
}
