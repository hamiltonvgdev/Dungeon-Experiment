package particle;

import org.newdawn.slick.Graphics;

public class OrbitingParticle extends Particle
{
	float speed;
	float degree;
	public float radius;
	float centerX;
	float centerY;
	
	public OrbitingParticle(float x, float y, float radius, float degree, float speed) 
	{
		super(x + radius, y + radius);
		this.degree = degree;
		
		this.speed = speed;
		
		this.radius = radius;
		centerX = x;
		centerY = y;
	}
	
	public void update(int id)
	{
		super.update(id);
		
		float radians = (float) Math.toRadians(degree);
		
		this.x = (float) (radius * Math.cos(radians)) + centerX;
		this.y = (float) (radius * Math.sin(radians)) + centerY;
		
		degree += speed;
		
		if(degree >= 360)
		{
			degree = 0 + (degree % 360);
		}
	}
	
	public OrbitingParticle setSpeed(float speed)
	{
		this.speed = speed;
		return this;
	}
	
	public void setRadius(float radius)
	{
		this.radius = radius;
	}
	
	public void setOrigin(float x, float y)
	{
		centerX = x;
		centerY = y;
	}
	
	public float getRadius()
	{
		return radius;
	}
}
