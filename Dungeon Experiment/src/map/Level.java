package map;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enemies.Entity;
import projectiles.BasicProjectile;

public class Level 
{
	ArrayList<Entity> entities;
	ArrayList<BasicProjectile> projectiles;
	
	public Level()
	{
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<BasicProjectile>();
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
	
	public ArrayList<BasicProjectile> getProjectiles()
	{
		return projectiles;
	}
	
	public void addProjectile(BasicProjectile p)
	{
		projectiles.add(p);
	}
	
	public void removeProjectile(BasicProjectile p)
	{
		projectiles.remove(p);
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
		for(int i = 0; i < entities.size(); i ++)
		{
			entities.get(i).update();
		}
	}
}
