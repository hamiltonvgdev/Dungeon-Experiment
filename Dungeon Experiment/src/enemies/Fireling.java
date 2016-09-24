package enemies;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import particle.OrbitingParticle;
import player.BasicPlayer;
import renders.AnimationSet;

public class Fireling extends Entity
{
	ArrayList<OrbitingParticle> flames = new ArrayList<OrbitingParticle>();
	AnimationSet eye = new AnimationSet("res/Enemies/Fireling/Eyes", 100);
	
	boolean pulsing;
	int pulseCounter;
	
	BasicPlayer protag;
	
	public Fireling(float x, float y, float speed, BasicPlayer protag)
	{
		super(x, y, speed);
		
		for(int count = 0; count < 4; count ++)
		{
			flames.add(new OrbitingParticle(x, y, 128, 0 + count * 360 / 4, 0.1F));
		}
		
		for(OrbitingParticle flame : flames)
		{
			flame.setAnimation("res/Enemies/Fireling/Eyes", 100);
			flame = (OrbitingParticle) flame.setSize(64, 64);
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
		
		eye.loopAnimate();
		
		BaseModel.resetAnimate();
		
		for(OrbitingParticle flame : flames)
		{
			flame.setOrigin(x, y);
			flame.update(1);
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
		
		eye.render(x, y, width, height, rot, g);
		
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
