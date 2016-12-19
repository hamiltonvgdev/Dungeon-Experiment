package inanimate_objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dungeon.Block;
import renders.AnimationSet;

public class Tree extends Thing
{

	public Tree(Block block) {
		super(block.x, block.y, 60, 119);
		
		id = "Tree";
		
		sprite = new AnimationSet("res/Inanimate Objects/Tree/", 100);
	}
	
	public void update(float x, float y)
	{
		super.update();
		
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) throws SlickException 
	{
		super.render(g);
	}

}
