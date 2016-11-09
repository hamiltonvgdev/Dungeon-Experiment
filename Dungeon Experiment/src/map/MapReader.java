package map;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dungeon.BasicTile;

public class MapReader 
{
	static Image tiles;
	static Image collide;
	static ArrayList<BasicTile> Tiles = new ArrayList<BasicTile>();
	public static float xT;
	public static float yT;
	public static float xC;
	public static float yC;
	
	public static ArrayList<Color> readMapImage(String ref)
	{
		ArrayList<Color> tileId = new ArrayList<Color>();
		
		try {
			tiles = new Image(ref);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int y = 0; y < tiles.getHeight(); y ++)
		{
			for(int x = 0; x < tiles.getWidth(); x ++)
			{
				tileId.add(tiles.getColor(x, y));
			}
		}
		
		xT = tiles.getWidth();
		yT = tiles.getHeight();
		
		return tileId;
	}
	
	public static ArrayList<Integer> readCollision(String ref)
	{
		ArrayList<Integer> collisionId = new ArrayList<Integer>();
		
		try {
			collide = new Image(ref);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int y = 0; y < collide.getHeight(); y ++)
		{
			for(int x = 0; x < collide.getWidth(); x ++)
			{
				collisionId.add(collide.getColor(x, y).getGreen());
			}
		}
		
		xC = collide.getWidth();
		yC = collide.getHeight();
		
		return collisionId;
	}
	
	public static void addTile(BasicTile derp)
	{
		Tiles.add(derp);
	}
	
	public static ArrayList<BasicTile> getTiles()
	{
		return Tiles;
	}
}
