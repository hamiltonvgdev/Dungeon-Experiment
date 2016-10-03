package projectiles;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import enemies.Entity;
import main.Quad;
import map.Level;
import player.BasicPlayer;
import renders.BasicImage;

public class Shuriken extends BasicProjectile 
{
	BasicPlayer ninja;
	
	Input input;
	int abilityKey;
	boolean reversed;
	boolean returned;
	
	Level level;
	ArrayList<Entity> hit;
	
	
	public Shuriken(BasicPlayer ninja, Level level, Input input, int abilityKey)
	{
		super(0,0, 32, 32);
		
		this.ninja = ninja;
		
		this.input = input;
		this.abilityKey = abilityKey;
		reversed = false;
		
		rot = 0;
		rotSpeed = 5;
		
		damage = 15;
		this.level = level;
		hit = new ArrayList<Entity>();
		
		x = ninja.getX();
		y = ninja.getY();
		speed = 3;
		Vx = ((Mouse.getX() - ninja.getX()) * speed);
		Vy = (((600 - Mouse.getY()) - ninja.getY()) * speed);
		
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
		
		sprite = new BasicImage("res/Player/Ninja/Shuriken/Shuriken.png");
		
		returned = false;
		
		age = 1000;
	}
	
	public Shuriken setImage(String ref)
	{
		sprite = new BasicImage(ref);
		return this;
	}
	
	public synchronized void update()
	{
		super.update();
		
		hitbox = new Quad(x, y, width, height);
		
		if(input.isKeyDown(Input.KEY_LSHIFT) && input.isKeyDown(abilityKey) && !reversed)
		{
			reversed = true;
			hit.clear();
			tickCount = 0;
		}
		
		if(reversed)
		{
			Vx = ((ninja.getX() - x) * speed);
			Vy = ((ninja.getY() - y) * speed);
			
			if(Math.abs(Vx) > Math.abs(Vy))
			{
				try
				{
					Vy =- ((ninja.getY() - y) * speed) / Math.abs(Vx);
				}catch (Exception e)
				{
					Vy = 0;
				}
				
				try
				{
					Vx = ((ninja.getX() - x) * speed) / Math.abs(((ninja.getX() - x) * speed));
				}catch (Exception e)
				{
					Vx = 0;
				}
			}else if(Math.abs(Vx) < Math.abs(Vy))
			{
				try
				{
					Vx = ((ninja.getX() - x) * speed) / Math.abs(Vy);
				}catch (Exception e)
				{
					Vy = 0;
				}
				
				try
				{
					Vy = -((ninja.getY() - y) * speed) / Math.abs(((ninja.getY() - y) * speed));
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
					Vy = -Vy / Math.abs(Vy);
				}catch (Exception e)
				{
					Vy = 0;
				}
			}
		}
		
		rot += rotSpeed;
		
		x += Vx * speed;
		if(reversed)
		{
			y += -Vy * speed;
		}else
		{
			y += Vy * speed;
		}
		
		if(Math.abs(x -ninja.getX()) <= 1 && Math.abs(y - ninja.getY()) <= 1)
		{
			returned = true;
		}else
		{
			returned = false;
		}
		
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
		
		if((returned && reversed) || tickCount >= age)
		{
			end = true;
		}
	}
	
	public synchronized void render(Graphics g) throws SlickException
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
	
	public float getY()
	{
		return y;
	}
	
	public float getX()
	{
		return x;
	}
}
