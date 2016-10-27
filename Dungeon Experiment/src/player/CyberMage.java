package player;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enemies.Entity;
import map.Level;
import projectiles.TimeBomb;
import renders.AnimationSet;

public class CyberMage extends BasicPlayer
{
	Level level;
	
	long attackLastTick;
	long attackRecoil;
	float attackRange;
	float attackMove;
	float attackSpeed;
	ArrayList<Entity> Hit;
	boolean attacking;
	
	long freezeTick;
	float freezeRadius;
	float freezeRange;
	long freezeCD;
	TimeBomb FreezeBomb;
	boolean throwing;
	
	
	public CyberMage(Level level, float speed) {
		super(speed, 200);
		
		side = new AnimationSet("res/Player/CyberMage/Body", 100);
		width = 65 * 2;
		height = 65 * 2;
		
		freezeRadius = 65;
		freezeRange = 250;
		throwing= false;
		
		attackLastTick = System.currentTimeMillis();
		attackRecoil = 100;
		attacking = false;
		attackRange = 300;
		attackMove = 25;
		attackSpeed = 3;
		
		this.level = level;
	}
	
	public void update()
	{
		super.update();
		
		if(input.isKeyDown(input.KEY_SPACE))
		{
			if(FreezeBomb == null || FreezeBomb.end)
			{
				throwing = true;
				FreezeBomb = new TimeBomb(x, y, freezeRadius, 200, this, level);			
			}
		}
		
		if(FreezeBomb != null)
		{
			if(FreezeBomb.end)
			{
				throwing = false;
			}
		}
		
		if(throwing)
		{
			FreezeBomb.update();
		}
		
		if(input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON))
		{
			atkPush();
		}else if(input.isMouseButtonDown(input.MOUSE_RIGHT_BUTTON))
		{
			atkPull();
		}
	}

	public void render(Graphics g) throws SlickException
	{
		super.render(g);
		
		if(throwing)
		{
			FreezeBomb.render(g);
		}
	}
	
	public void atkPush()
	{
		
	}
	
	public void atkPull()
	{
		
	}
	
	public boolean checkHit(Entity E)
	{
		boolean hitted = false;
		
		for(Entity e: Hit)
		{
			if(E == e)
			{
				hitted = true;
			}
		}
		
		if(!hitted)
		{
			Hit.add(E);
		}
		
		return hitted;
	}
}
