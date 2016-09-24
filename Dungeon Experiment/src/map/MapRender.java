package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import enemies.Entity;
import main.MainClass;

public class MapRender {
	private static float xD = 0;
	private static float yD = 0;
	private static Level level;
	
	public static void init(Level map)
	{
		level = map;
	}

	public static void render(GameContainer gc, Graphics g) throws SlickException {

		for (int x = 0; x < 32; x++) {

			for (int y = 0; y < 20; y++) {

				MapUpdate.room.getBlock(x, y).render((float)x,(float) y, xD, yD, 32.0F, 32.0F, 0.0F, g);

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
	}
}
