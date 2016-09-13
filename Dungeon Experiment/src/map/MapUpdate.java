package map;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dungeon.Block;
import dungeon.SimpleRoom;
import main.Config;
import main.QM;
import main.Quad;

public class MapUpdate {

	public static SimpleRoom room = new SimpleRoom(new Block("res/textures/blocks/Stone.png"), new Block("res/textures/blocks/LightStone.png"));

	public static void update(GameContainer gc) throws SlickException {
		Input io = gc.getInput();

	/*	if (io.isMouseButtonDown(io.MOUSE_LEFT_BUTTON))
			LightMap.lights
					.add(new Light(Mouse.getX() / 8, (Config.HEIGHT - Mouse.getY()) / 8, 64, new Color(255, 225, 225)));

		LightMap.update(gc);*/
	}

	public static boolean doesCollide(double X, double Y) {
		for (int x = 0; x < 32; x++) {

			for (int y = 0; y < 20; y++) {

				if (room.getBlock(x, y).getCollides()) {
					Quad quad = new Quad(x * 32, y * 32, 32, 32);

					if (quad.checkPoint((float) X, (float) Y))
						return true;
				}
			}
		}
		return false;
	}
}
