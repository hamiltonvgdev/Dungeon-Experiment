package enemies;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import map.Level;
import player.BasicPlayer;
import renders.AnimationSet;

public class QueenBee extends Entity
{
	ArrayList<ArrayList<Bee>> Swarms;
	BasicPlayer protag;
	long SwarmSpawn; 
	long SwarmSpawnCD;
	int SwarmSize;
	Random gen;
	
	public QueenBee(Level level, float x, float y, BasicPlayer protag)
	{
		super(level, x, y, -0.1F, 300);
		
		Swarms = new ArrayList<ArrayList<Bee>>();
		
		this.protag = protag;
		
		SwarmSpawn = System.currentTimeMillis();
		SwarmSpawnCD = 1000;
		SwarmSize = 10;
		BaseModel = new AnimationSet("res/Enemies/Queen Bee", 100);
		
		width = 100;
		height = 100;
		
		gen = new Random();
	}

	public void update()
	{
		super.update();
		
		follow(protag);
		
		if(System.currentTimeMillis() - SwarmSpawn >= SwarmSpawnCD)
		{
			ArrayList<Bee> Swarm = new ArrayList<Bee>();
			
			for(int i = 0; i < gen.nextInt(SwarmSize) + 1; i ++)
			{
				Bee bee = new Bee(level, x, y, Swarm, protag);
				Swarm.add(bee);
				level.addEntity(bee);
			}
			
			Swarms.add(Swarm);
			SwarmSpawn = System.currentTimeMillis();
		}
		
		for(int i = 0; i < Swarms.size(); i ++)
		{
			for(int j = 0; j < Swarms.get(i).size(); j ++)
			{
				Swarms.get(i).get(j).update();
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		super.render(gc, g);
		
		for(int i = 0; i < Swarms.size(); i ++)
		{
			for(int j = 0; j < Swarms.get(i).size(); j ++)
			{
				Swarms.get(i).get(j).render(gc, g);;
			}
		}
	}
}
