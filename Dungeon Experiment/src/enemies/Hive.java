package enemies;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import map.Level;
import player.BasicPlayer;
import renders.AnimationSet;

public class Hive extends Entity
{
	ArrayList<Bee> Swarm;
	int BeeLimit;
	BasicPlayer protag;
	
	public Hive(Level level, float x, float y, BasicPlayer protag)
	{
		super(level, x, y, 0, 200);
		
		this.protag = protag;
		
		Swarm = new ArrayList<Bee>();
		
		for(int i = 0; i < 5; i ++)
		{
			Bee bee = new Bee(level, x, y, Swarm, protag);
			Swarm.add(bee);
			level.addEntity(bee);
		}
		
		BaseModel = new AnimationSet("res/Enemies/Hive", 100);
		height = 100;
		width = 100;
		
		BaseModel.setAfterImage(10, 50);
		BaseModel.toggleAfterImage(true);
	}
	
	public void update()
	{
		if(health <= 0)
		{
			QueenBee queen = new QueenBee(level, x, y, protag);
			level.addEntity(queen);
		}
		super.update();
		
		if(Swarm.size() <= 0)
		{
			Swarm = new ArrayList<Bee>();
			for(int i = 0; i < 5; i ++)
			{
				Bee bee = new Bee(level, x, y, Swarm, protag);
				Swarm.add(bee);
				level.addEntity(bee);
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		super.render(gc, g);
	}
}
