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
	public long lastTick;
	boolean ended;
	
	int counter;
	
	boolean flip;
	
	public AnimationSet(String ref, long delay)
	{
		File folder = new File(ref);
		File[] list = folder.listFiles();
		
		Animation = new ArrayList<BasicImage>();
		
		for(File frame : list)
		{
			BasicImage sprite = new BasicImage(frame.getPath());
			Animation.add(sprite);
		}
		
		this.delay = delay;
		lastTick = System.currentTimeMillis();
		
		frame = 0;
		counter = 0;
		
		ended = false;
	}
	
	public void resetAnimate()
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			if(frame < Animation.size() - 1)
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
			if(counter < Animation.size() - 1)
			{
				frame ++;
				counter ++;
			}else if(counter < Animation.size()*2 - 2)
			{
				frame --;
				counter ++;
			}else
			{
				counter = 0;
			}
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void randomAnimate()
	{
		Random gen = new Random();
		
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			int index = gen.nextInt(Animation.size() - 1) + 1;
			
			frame = index;
			
			lastTick = System.currentTimeMillis();
		}
		
	}
	
	public void endAnimate()
	{
		if(System.currentTimeMillis() - lastTick >= delay)
		{
			if(frame < Animation.size() - 1)
			{
				frame ++;
			}else
			{
				ended = true;
			}
			
			lastTick = System.currentTimeMillis();
		}
	}
	
	public void render(float x, float y, float width, float height, float rot, Graphics g) 
	{
		Animation.get(frame).render(x, y, width, height, rot, g);
	}
	
	public void setFlip(boolean Flip)
	{
		for(BasicImage frame : Animation)
		{
			frame.setFlip(Flip);
		}
	}
	
	public void setDelay(long delay)
	{
		this.delay = delay;
	}
	
	public void reset()
	{
		frame = 0;
	}
	
	public String getRef(int i)
	{
		return Animation.get(i).getPath();
	}
	
	public ArrayList getSet()
	{
		return Animation;
	}
	
	public int getCurrentFrame()
	{
		return frame;
	}
	
	public boolean ended()
	{
		return ended;
	}
}
