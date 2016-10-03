package projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Quad;
import renders.BasicImage;

public abstract class BasicProjectile 
{
	int tickCount;
	
	float x;
	float y;
	
	float Vx;
	float Vy;
	float speed;
	
	float width;
	float height;
	float rot;
	float rotSpeed;
	
	int damage;
	Quad hitbox;

	BasicImage sprite;
	
	int age;
	public boolean end;
	
	public BasicProjectile(float x, float y, float width, float height)
	{
		tickCount = 0;
		
		this.x = x;
		this.y = y;
		Vx = 0;
		Vy = 0;
		
		this.height = height;
		this.width = width;
		rot = 0;
		rotSpeed = 1;
		
		hitbox = new Quad(x, y, width, height);
		
		end = false;
	}
	
	public void move(float xa, float ya)
	{
		x += xa;
		y += ya;
	}
	
	public void update()
	{
		tickCount ++;
		
		hitbox = new Quad(x, y, width, height);
	}
	public void render(Graphics g) throws SlickException
	{
		sprite.render(x, y, width, height, rot, g);
	}
}
