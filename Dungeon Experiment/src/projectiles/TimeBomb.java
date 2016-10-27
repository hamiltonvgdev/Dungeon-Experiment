package projectiles;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enemies.Entity;
import main.Quad;
import map.Level;
import player.BasicPlayer;
import renders.AnimationSet;
import renders.BasicImage;

public class TimeBomb extends BasicProjectile
{
	float xO;
	float yO;
	float range;
	float radius;
	
	boolean exploding;
	boolean Ticking;
	int ticks;
	AnimationSet ticking;
	AnimationSet explosion;
	
	boolean exploded;
	
	Level level;
	
	public TimeBomb(float x, float y, float radius, float range, BasicPlayer thrower,Level level) 
	{
		super(x, y, 48, 48);
		
		level.addProjectile(this);
		xO = thrower.getX();
		yO = thrower.getY();
		this.range = range;
		this.radius = radius;
		sprite = new BasicImage("res/Player/CyberMage/Time Bomb/Time Bomb.png");
		
		Ticking = false;
		ticking = new AnimationSet("res/Player/CyberMage/Time Bomb/Ticking", 100);
		ticks = 0;
		
		exploding = false;
		explosion = new AnimationSet("res/Player/CyberMage/Time Bomb/Exploding", 50);
		exploded = false;
		
		this.level = level;
		
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
	}

	public void update()
	{
		super.update();
		
		if(Math.abs(x - xO) >= range || Math.abs(y - yO ) >= range)
		{
			Ticking = true;
		}
		
		if(Ticking)
		{
			ticking.resetAnimate();
			if(ticking.getCurrentFrame() >= ticking.getSet().size() - 1)
			{
				if(ticking.lastTick == System.currentTimeMillis())
				{
					ticks ++;
				}
			}
		}
		
		if(ticks >= 3)
		{
			ticking.setDelay(50);
		}
		
		if(ticks >= 6)
		{
			ticking.setDelay(25);
		}
		
		if(ticks >= 9)
		{
			Ticking = false;
			exploding = true;
		}
		
		if(exploding)
		{
			explosion.endAnimate();
		}
		
		if(explosion.ended())
		{
			exploded = true;
			exploding = false;
		}
		
		if(exploded)
		{
			for(Entity e : level.getEntities())
			{
				if(e.getHitbox().checkQuad(new Quad(x, y, radius, radius)))
				{
					e.setMovable(false);
				}
			}
			
			end = true; 
		}
		
		if(!exploded && !exploding && !Ticking)
		{
			x += Vx * speed;
			y += Vy * speed;
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(!exploding)
		{
			super.render(g);
		}
		
		if(Ticking)
		{
			ticking.render(x, y, width, height, rot, g);
		}
		
		if(exploding)
		{
			explosion.render(x, y, width + radius, height + radius, rot, g);
		}
	}
	
}
