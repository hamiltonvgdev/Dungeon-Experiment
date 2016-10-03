package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
//import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import enemies.Fireling;
import map.MapRender;
import map.MapUpdate;
import particle.OrbitingParticle;
import player.BasicPlayer;
import player.Ninja;

public class MainClass extends BasicGame 
{
	public map.Level level;
	Ninja derp;

	public MainClass(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		level = new map.Level();
		MapRender.init(level);
		
		derp = new Ninja(level, 2);
		
		Fireling Craig = new Fireling(level, 600, 300, 0.5F, derp);
		
		level.addEntity(Craig);
		
		Craig = new Fireling(level, 300, 600, 0.5F, derp);
		
		level.addEntity(Craig);
		
		Craig = new Fireling(level, 300, 900, 0.5F, derp);
		
		level.addEntity(Craig);
		
		Craig = new Fireling(level, 900, 300, 0.5F, derp);
		
		level.addEntity(Craig);
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException 
	{
		MapUpdate.update(gc);
		
		level.update();
		
		derp.update();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		// Draws black to "reset" background
		Rectangle background = new Rectangle(0, 0, Config.WIDTH, Config.HEIGHT);
		background.setBounds(0, 0, Config.WIDTH, Config.HEIGHT);
		g.setColor(Color.black);
		g.fill(background);
		g.draw(background);
		
		// sets if the program should show FPS//
		gc.setShowFPS(Config.SHOW_FPS);

		// main render method//
		MapRender.render(gc, g);
		
		level.render(gc, g);
		
		derp.render(g);
		
	}

	public static void main(String[] args) {
		try {

			AppGameContainer appgc;

			appgc = new AppGameContainer(new MainClass(Config.NAME));

			appgc.setShowFPS(Config.SHOW_FPS);

			appgc.setClearEachFrame(false);
			
			appgc.setDisplayMode(Config.WIDTH, Config.HEIGHT, false);
			
			appgc.setMinimumLogicUpdateInterval(1000 / Config.Ticks);

			appgc.start();

		} catch (SlickException ex) {
			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}