package enemies;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.Quad;
import map.Level;
import player.BasicPlayer;
import renders.AnimationSet;

public abstract class Entity 
{
	float x;
	float y;
	float speed;
	boolean movable;
	
	Quad hitbox;
	int maxHealth;
	int health;
	Level level;
	
	float height;
	float width;
	float rot;
	AnimationSet BaseModel;
	
	int tickCount;
	long lastTick;
	
	Random gen = new Random();
	
	public Entity(Level level, float x, float y, float speed, int maxHealth)
	{
		this.x = x;
		this.y = y;	
		this.speed = speed;
		movable = true;
		
		hitbox = new Quad(x, y, width, height);
		this.maxHealth = maxHealth;
		health = maxHealth;
		this.level = level;
		
		tickCount = 0;
		lastTick = System.currentTimeMillis();
	}
	
	public void setAnimation(String ref, long delay)
	{
		BaseModel = new AnimationSet(ref, delay);
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public Quad getHitbox()
	{
		return hitbox;
	}
	
	public void damage(int damage)
	{
		health -= damage;
	}
	
	public void update()
	{
		tickCount ++;
		hitbox = new Quad(x, y, width, height);
		
		if(health <= 0)
		{
			die();
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		BaseModel.render(x, y, width, height, rot, g);
	}
	
	public void die()
	{
		for(int i = 0; i < level.getEntities().size(); i ++)
		{
			if(level.getEntities().get(i) == this)
			{
				level.getEntities().remove(i);
			}
		}
	}
	
	public boolean distanceSense(float distance, BasicPlayer protag)
	{
		double space = Math.sqrt(Math.pow(x - 
				protag.getX(), 2) + Math.pow(y - protag.getY(), 2));
		
		if(space <= distance)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public void follow(BasicPlayer protag)
	{
		float distX = protag.getX() - x;
		float distY = protag.getY() - y;
		
		if(distY != 0)
		{
			try
			{
				move(0, (distY) / (Math.abs(distY))); // 
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0,0);
		}
		
		if(distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) ,0); //
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0,0);
		}
	}
	
	public void move(float xa, float ya)
	{
		if(movable)
		{
			x += xa * speed;
			y += ya * speed;
		}
	}
	
	public void Move(float xa, float ya)
	{
		x += xa;
		y += ya;
	}
	
	public void follow(Entity patient)
	{	
		float distX = patient.getX() - x;
		float distY = patient.getY() - y;
		
		if(distY != 0)
		{
			try
			{
				move(0, (distY) / (Math.abs(distY))); // 
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0,0);
		}
		
		if(distX != 0)
		{
			try
			{
				move((distX) / (Math.abs(distX)) ,0); //
			}
			catch(Exception e)
			{
				move(0, 0);
			}
		}else
		{
			move(0,0);
		}
	}
	
	public boolean distanceSense(float distance, Entity patient)
	{
		double space = Math.sqrt(Math.pow(x - 
				patient.getX(), 2) + Math.pow(y - patient.getY(), 2));
		
		if(space <= distance)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public void wander()
	{
		int movement = gen.nextInt(8) + 1;
		
		switch(movement)
		{
			default:
			{
				move(0, 0);
				break;
				
			}
			
			case 1:
			{
				move(speed , 0);
				break;
			}
			
			case 2:
			{
				move(0 , speed);
				break;
			}
			
			case 3:
			{
				move(-speed , 0);
				break;
			}
			
			case 4:
			{
				move(0 , -speed);
				break;
			}
			
			case 5:
			{
				move(speed, speed);
				break;
			}
			
			case 6:
			{
				move(-speed , speed);
				break;
			}
			
			case 7:
			{
				move(-speed , -speed);
				break;
			}
			
			case 8:
			{
				move(speed , -speed);
				break;
			}
		}
	}
}
