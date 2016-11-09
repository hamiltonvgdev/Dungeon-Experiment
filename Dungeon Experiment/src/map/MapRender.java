package map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dungeon.BasicTile;
import dungeon.Block;
import dungeon.Lava;
import dungeon.TreeBlock;
import enemies.Entity;
import projectiles.BasicProjectile;

public class MapRender {
	static float xD = 0;
	static float yD = 0;
	private static Level level;
	static ArrayList<BasicTile> tiles;
	
	public static void init(Level map)
	{
		level = map;
		
		MapReader.addTile(new Block("res/Textures/blocks/Stone/Stone.png", 
				new Color((float) (64.0/255), (float) (64.0/255), (float) (64.0/255))));
		MapReader.addTile(new Block("res/Textures/blocks/Stone/LightStone.png", 
				new Color((float) (128.0/255), (float) (128.0/255), (float) (128.0/255))));
		
		MapReader.addTile(new Lava("res/Textures/blocks/Lava/lava 1.png", 
				new Color((float) (255.0/255), (float) (97.0/255), (float) (0.0/255))));
		MapReader.addTile(new Lava("res/Textures/blocks/Lava/lava 2.png", 
				new Color((float) (255.0/255), (float) (98.0/255), (float) (0.0/255))));
		MapReader.addTile(new Lava("res/Textures/blocks/Lava/lava 3.png", 
				new Color((float) (255.0/255), (float) (99.0/255), (float) (0.0/255))));
		MapReader.addTile(new Lava("res/Textures/blocks/Lava/lava 4.png", 
				new Color((float) (255.0/255), (float) (100.0/255), (float) (0.0/255))));
		
		MapReader.addTile(new TreeBlock("res/Textures/blocks/Tree Block/Tree Block.png", 
				new Color((float) (0.0/255), (float) (255.0/255), (float) (0.0/255))));
		
		MapReader.readMapImage("res/Level/Tiles/Levelish.png");
		tiles = new ArrayList<BasicTile>();
		for(int y = 0; y < MapReader.yT; y ++)
		{
			for(int x = 0; x < MapReader.xT; x ++)
			{
				Color ID = MapReader.readMapImage("res/Level/Tiles/Levelish.png").get((int) (x + y * MapReader.yT));
				
				BasicTile tile = new BasicTile("", ID);
				
				for(int i = 0; i < MapReader.getTiles().size(); i ++)
				{
					if(Math.abs(MapReader.getTiles().get(i).getID().getRed() - ID.getRed()) < 0.004
							&& Math.abs(MapReader.getTiles().get(i).getID().getBlue() - ID.getBlue()) < 0.004
							&& Math.abs(MapReader.getTiles().get(i).getID().getGreen() - ID.getGreen()) < 0.004)
					{
						tile = MapReader.getTiles().get(i);
						break;
					}
				}
				
				tiles.add(tile);
			}
		}

	}

	public static void render(GameContainer gc, Graphics g) throws SlickException {
		
		for(int y = 0; y < MapReader.yT; y ++)
		{
			for(int x = 0; x < MapReader.xT; x ++)
			{
				tiles.get((int) (x + y * MapReader.yT)).render(x, y, xD, yD, 0, g);
			}
		}
		
		// this is always last//
		//LightMap.render(gc, g);
	}
	
	public static void move(float xa, float ya)
	{
		xD += xa;
		yD += ya;
		
		for (Entity e : level.getEntities())
		{
			e.Move(-xa, -ya);
		}
		
		for (BasicProjectile p : level.getProjectiles())
		{
			p.move(-xa, -ya);
			
		}
	}
}
