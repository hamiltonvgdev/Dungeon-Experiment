package projectiles;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enemies.Entity;
import map.Level;
import renders.BasicImage;

public class Kunai extends BasicProjectile
{
	Level level;
	ArrayList<Entity> hit;
	
	public Kunai(float x, float y, Level level) 
	{
		super(x, y, 11, 39);
		
		
		damage = 5;
		this.level = level;
		hit = new ArrayList<Entity>();
		
		speed = 3;
		Vx = ((Mouse.getX() - x) * speed);
		Vy = (((600 - Mouse.getY()) - y) * speed);
		
		if(Math.abs(Vx) > Math.abs(Vy))
		{
			try
			{
				Vy = (((600 - Mouse.getY()) - y) * speed) / Math.abs(Vx);
			}catch (Exception e)
			{
				Vy = 0;
			}
			
			try
			{
				Vx = ((Mouse.getX() - x) * speed) / Math.abs(((Mouse.getX() - x) * speed));
			}catch (Exception e)
			{
				Vx = 0;
			}
		}else if(Math.abs(Vx) < Math.abs(Vy))
		{
			try
			{
				Vx = ((Mouse.getX() - x) * speed) / Math.abs(Vy);
			}catch (Exception e)
			{
				Vy = 0;
			}
			
			try
			{
				Vy = (((600 - Mouse.getY()) - y) * speed) / Math.abs((((600 - Mouse.getY()) - y) * speed));
			}catch (Exception e)
			{
				Vy = 0;
			}
		}else
		{
			try
			{
				Vx = Vx / Math.abs(Vx);
			}catch (Exception e)
			{
				Vx = 0;
			}
			
			try
			{
				Vy = Vy / Math.abs(Vy);
			}catch (Exception e)
			{
				Vy = 0;
			}
		}
		
		rot = (float) Math.toDegrees(Math.atan(Vy / Vx));
		
		if(Vx > 0)
		{
			rot += 90;
		}else
		{
			rot -= 90;
		}
		
		sprite = new BasicImage("res/Player/Ninja/Kunai/Kunai.png");
		
		age = 100;
	}

	@Override
	public void update() 
	{
		super.update();
		
		x += Vx * speed;
		y += Vy * speed;
		
		for (Entity e : level.getEntities())
		{
			if(e.getHitbox().checkQuad(hitbox))
			{
				if(!checkHit(e))
				{
					e.damage(damage);
				}
			}
		}
		
		if(tickCount >= age)
		{
			end = true;
		}
		
		
	}

	@Override
	public void render(Graphics g) throws SlickException 
	{
		super.render(g);
	}
	
	public boolean checkHit(Entity E)
	{
		boolean hitted = false;
		
		for(Entity e: hit)
		{
			if(E == e)
			{
				hitted = true;
			}
		}
		
		if(!hitted)
		{
			hit.add(E);
		}
		
		return hitted;
	}

}
