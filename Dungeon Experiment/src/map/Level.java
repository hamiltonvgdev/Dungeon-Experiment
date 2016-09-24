package map;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enemies.Entity;

public class Level 
{
	ArrayList<Entity> entities;
	
	public Level()
	{
		entities = new ArrayList<Entity>();
	}
	
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	public void removeEntity(Entity e)
	{
		entities.remove(e);
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		for(Entity e : entities)
		{
			e.render(gc, g);
		}
	}
	
	public void update()
	{
		for(Entity e : entities)
		{
			e.update();
		}
	}
}
