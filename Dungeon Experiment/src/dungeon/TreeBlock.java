package dungeon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import inanimate_objects.Tree;

public class TreeBlock extends Block
{
	Tree tree;

	public TreeBlock(String ref, Color id) 
	{
		super(ref, id);
		
		tree = new Tree(this);
	}
	
	public void update(float x, float y)
	{
		super.update(x, y);
	}
	
	public void render(float x, float y, float xa, float ya, float rot, Graphics g) throws SlickException
	{
		super.render(x, y, xa, ya, rot, g);
		
		tree.update( x * 32 - xa, y * 32 - ya);
		
		try {
			tree.render(g);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
