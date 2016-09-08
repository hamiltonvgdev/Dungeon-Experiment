package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import map.MapRender;
import map.MapUpdate;

public class MainClass extends BasicGame 
{

	public MainClass(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Initialize.init();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{
		MapUpdate.update(gc);

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		// sets if the program should show FPS//
		gc.setShowFPS(Config.SHOW_FPS);

		// main render method//
		MapRender.render(gc, g);
		
	}

	public static void main(String[] args) {
		try {

			AppGameContainer appgc;

			appgc = new AppGameContainer(new MainClass(Config.NAME));

			appgc.setShowFPS(Config.SHOW_FPS);

			appgc.setClearEachFrame(false);

			System.out.println(Config.WIDTH + " " + Config.HEIGHT);
			
			appgc.setDisplayMode(Config.WIDTH, Config.HEIGHT, false);

			appgc.start();

		} catch (SlickException ex) {
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}