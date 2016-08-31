package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Initialize {
	
public static Image[] imageID=new Image[1000];	
	
public static void init() throws SlickException{
	
imageID[0]=new Image("res/textures/blocks/Stone.png");
imageID[1]=new Image("res/textures/blocks/LightStone.png");

for (int i=0;i<imageID.length;i++){
if (imageID[i]!=null)
imageID[i].setFilter(Image.FILTER_NEAREST);	
	
}
	
	
	
}

}
