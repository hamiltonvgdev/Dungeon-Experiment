package map;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dungeon.BasicTile;
import dungeon.Block;
import dungeon.SimpleRoom;
import main.Config;
import main.QM;
import main.Quad;

public class MapUpdate {
	public static void init()
	{
		
	}

	public static void update(GameContainer gc) throws SlickException {
		Input io = gc.getInput();
		
		for(int y = 0; y < MapReader.yT; y ++)
		{
			for(int x = 0; x < MapReader.xT; x ++)
			{
				MapRender.tiles.get((int) (x + y * MapReader.yT)).update( x * 32 - MapRender.xD, y * 32 - MapRender.yD);
			}
		}
		
	}

	public static boolean doesCollide(double X, double Y) {
		for (int x = 0; x < 32; x++) {

		}
		return false;
	}
}
