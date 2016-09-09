package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MapRender {

	public static void render(GameContainer gc, Graphics g) throws SlickException {

		for (int x = 0; x < 32; x++) {

			for (int y = 0; y < 20; y++) {

				MapUpdate.room.getBlock(x, y).render((float)x,(float) y, 32.0F, 32.0F, 0.0F, g);

			}

		}

		// this is always last//
		//LightMap.render(gc, g);
	}
}
