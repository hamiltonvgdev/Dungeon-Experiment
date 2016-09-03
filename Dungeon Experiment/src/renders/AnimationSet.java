package renders;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Graphics;

public class AnimationSet 
{
	ArrayList<BasicImage> Animation;
	int frame;
	long delay;
	long lastTick;
	
	int counter;
	
	public AnimationSet(String ref, long delay)
	{
		File folder = new File(ref);
		File[] list = folder.listFiles();
		BasicImage sprite;
		
		for(File frame : list)
		{
			sprite = new BasicImage(frame.getPath());
			Animation.add(sprite);
		}
		
		this.delay = delay;
		lastTick = System.currentTimeMillis();
		
		frame = 0;
		counter = 0;
	}
	
	public void resetAnimate()
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			if(frame < Animation.size())
			{
				frame ++;
			}else
			{
				frame = 0;
			}
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void loopAnimate()
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			if(counter < Animation.size())
			{
				frame ++;
			}else if(counter < Animation.size()*2 - 1)
			{
				frame --;
			}else
			{
				frame = 0;
				counter = 0;
			}
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void randomAnimate()
	{
		Random gen = new Random();
		
		int index = gen.nextInt(Animation.size() - 1);
		
		frame = index;
	}
	
	
	
	public void render(float x, float y, float width, float height, float rot, Graphics g) 
	{   
		Animation.get(frame).render(x, y, width, height, rot, g);
	}
}
