package player;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import main.Config;
import map.Level;
import map.MapRender;
import projectiles.Kunai;
import projectiles.Shuriken;
import renders.AnimationSet;

public class Ninja extends BasicPlayer
{
	AnimationSet back;
	
	Level level;
	
	int shurikenCharges;
	ArrayList<Shuriken> Shurikens; 
	long shurikenLastTick;
	boolean shurikenReturn;
	boolean shurikening;
	
	int kunaiCharges;
	long reloadTime;
	long reloadTick;
	long kunaiLastTick;
	boolean throwing;
	ArrayList<Kunai> Kunais;
	
	boolean shadow;
	float shadowX;
	float shadowY;
	float shadowSpeed;
	long shadowLastTick;
	AnimationSet Shadow;

	public Ninja(Level level, float speed) 
	{
		super(speed, 150);
		
		height = (float) (64 * 1.5);
		width = (float) (64 * 1.5);
		
		this.level = level;
		
		shurikenCharges = 3;
		Shurikens = new ArrayList<Shuriken>();
		shurikenLastTick = System.currentTimeMillis();
		shurikenReturn = false;
		
		kunaiCharges = 4;
		Kunais = new ArrayList<Kunai>();
		reloadTime = 1000;
		reloadTick = System.currentTimeMillis();
		kunaiLastTick = System.currentTimeMillis();
		
		shadow = false;
		shadowX = x;
		shadowY = y;
		shadowSpeed = 5;
		shadowLastTick = System.currentTimeMillis();
		
		side = new AnimationSet("res/Player/Ninja/Body/Front", 60);
		back = new AnimationSet("res/Player/Ninja/Body/Back", 300);
		Shadow = new AnimationSet("res/Player/Ninja/Shadow", 100);
		
		side.setAfterImage(10, 50);
		side.toggleAfterImage(true);
	}

	public void update()
	{
		super.update();
		
		for(Shuriken shuriken : Shurikens)
		{
			if(!shuriken.end)
			{
				shuriken.update();
			}
		}
		
		for(Kunai kunai : Kunais)
		{
			kunai.update();
		}
		
		if(input.isKeyDown(input.KEY_SPACE) && input.isKeyDown(input.KEY_LSHIFT))
		{
			shurikenReturn = true;
		}else if(input.isKeyDown(input.KEY_SPACE) && !shurikenReturn)
		{
			if(System.currentTimeMillis() - shurikenLastTick >= 300 
					&& Shurikens.size() < shurikenCharges)
			{
				Shuriken derp = new Shuriken(this, level, input, input.KEY_SPACE);
				Shurikens.add(derp);
				level.getProjectiles().add(derp);
				shurikenLastTick = System.currentTimeMillis();
			}
		}
		
		for(int i = 0; i < Shurikens.size() ; i ++)
		{
			if(Shurikens.get(i).end)
			{
				level.getProjectiles().remove(Shurikens.get(i));
				Shurikens.remove(i);
			}
		}
		
		if(Shurikens.size() == 0)
		{
			shurikenReturn = false;
		}
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) 
				&& System.currentTimeMillis() - reloadTick > reloadTime)
		{
			throwing = true;
		}
		
		if(throwing)
		{
			if(Kunais.size() < kunaiCharges)
			{
				if(System.currentTimeMillis() - kunaiLastTick > 60)
				{
					Kunai derp = new Kunai(x, y, level);
					Kunais.add(derp);
					level.addProjectile(derp);
					kunaiLastTick = System.currentTimeMillis();
				}
			}else
			{
				reloadTick = System.currentTimeMillis();
				throwing = false;
			}
			
			side.endAnimate();
		}
		
		if(!throwing && System.currentTimeMillis() - reloadTick > reloadTime - 10)
		{
			side.reset();
		}
		
		for(int i = 0; i < Kunais.size() ; i ++)
		{
			if(Kunais.get(i).end)
			{
				level.getProjectiles().remove(Kunais.get(i));
				Kunais.remove(i);
			}
		}
		
		if(input.isMouseButtonDown(input.MOUSE_RIGHT_BUTTON))
		{
			projectShadow();
			movable = false;
		}else if(input.isKeyDown(input.KEY_Z))
		{
			shadowX = 300;
			shadowY = 300;
			shadow = false;
		}else if(input.isMouseButtonDown(input.MOUSE_MIDDLE_BUTTON))
		{
			if(shadow)
			{
				if(System.currentTimeMillis() - shadowLastTick > 500)
				{
					float xa = x;
					float ya = y;
					
					MapRender.move(shadowX - x, shadowY - y);
					
					shadowX = xa;
					shadowY = ya;
					
					shadowLastTick = System.currentTimeMillis();
				}
				shadow = false;
			}
		}
		
		if(input.isKeyDown(input.KEY_W))
		{
			shadowY += speed;
		}
		if(input.isKeyDown(input.KEY_S))
		{
			shadowY += -speed;
		}
		if(input.isKeyDown(input.KEY_A))
		{
			shadowX += speed;
		}
		if(input.isKeyDown(input.KEY_D))
		{
			shadowX += -speed;
		}
		
		if(!shadow)
		{
			shadowX = x;
			shadowY = y;
		}
		
		if(!input.isMouseButtonDown(input.MOUSE_RIGHT_BUTTON))
		{
			movable = true;
		}
	}
	
	public synchronized void render(Graphics g) throws SlickException
	{	
		
		Shadow.render(shadowX, shadowY, width, height, 0, g);
		
		if(super.back)
		{
			back.render(x, y, width, height, 0, g);
		}else
		{
			super.render(g);
		}
		
		for(Shuriken shuriken : Shurikens)
		{
			if(!shuriken.end)
			{
				shuriken.render(g);
			}
		}
		
		for(Kunai kunai : Kunais)
		{
			kunai.render(g);
		}
		
	}
	
	public void projectShadow()
	{
		shadow = true;
		if(input.isKeyDown(input.KEY_W))
		{
			if(shadowY - height > 0)
			{
				shadowY -= shadowSpeed;
			}
		}
		
		if(input.isKeyDown(input.KEY_S))
		{
			if(shadowY + height < Config.HEIGHT)
			{
				shadowY += shadowSpeed;
			}
		}
		
		if(input.isKeyDown(input.KEY_A))
		{
			if(shadowX - width > 0)
			{
				shadowX -= shadowSpeed;
			}
		}
		
		if(input.isKeyDown(input.KEY_D))
		{
			if(shadowX + width < Config.WIDTH)
			{
				shadowX += shadowSpeed;
			}
		}
	}
}
