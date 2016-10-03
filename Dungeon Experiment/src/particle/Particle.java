package particle;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import renders.AnimationSet;

public class Particle 
{
	protected float x;
	protected float y;
	private float vx;
	private float vy;
	private int age, width, height;
	private boolean isDone;
	private AnimationSet animation;
	private Color color;
	private int tickCount;
	
	public Particle(float x,float y)
	{
		this.x=x;
		this.y=y;
		width = 32;
		height = 32;
		tickCount = 0;
	}	
	
	public Particle setColor(Color color)
	{
		this.color=color;
		return this;
	}
	
	public Particle setAnimation(String ref, long delay)
	{
		this.animation= new AnimationSet(ref, delay);
		return this;
	}
	
	public Particle setVelocity(float vx,float vy)
	{
		this.vx=vx;
		this.vy=vy;
		return this;
	}
	
	public Particle setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		return this;
	}
	
	public Particle changeVelocity(float vx,float vy)
	{	
		this.vx+=vx;
		this.vy+=vy;
		return this;		
	}

	public Particle setAge(int age)
	{
		this.age=age;
		return this;
	}

	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public AnimationSet getAnimation()
	{
		return animation;
	}
	
	public void update(int id)
	{
		if(!isDone)
		{
			tickCount ++;
			
			
			if(id == 0)
			{
				animation.resetAnimate();
			}else if(id == 1)
			{
				animation.loopAnimate();
			}else if(id == 2)
			{
				animation.randomAnimate();
			}
			
			if(tickCount == age)
			{
				isDone = true;
			}
			
		}
	}
	public void render(Graphics g)
	{
		if(!isDone)
		{
			animation.render(x, y, width, height, 0, g);
		}
	}
}
