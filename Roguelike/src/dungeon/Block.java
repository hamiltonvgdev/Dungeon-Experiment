package dungeon;

import org.newdawn.slick.Graphics;

import main.Initialize;

public class Block {

private int imageID;
private boolean collides;
public Block(int imageID){
	
this.imageID=imageID;

}
public void setCollides(boolean bool){
	collides=bool;
}
public Block setCollidesO(boolean bool){
	collides=bool;
	return this;
}
public boolean getCollides(){
	return collides;
}
public void update(int x,int y){
	
	
}
public void render(int x,int y,Graphics g){
	
	
	Initialize.imageID[imageID].draw(x*32,y*32,32,32);
	
	
}
public Block clone(){
	return new Block(imageID).setCollidesO(collides);
}
}
