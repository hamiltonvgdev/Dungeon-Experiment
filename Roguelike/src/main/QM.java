package main;

import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;



public class QM {
	public static Random r=new Random();

	
public static int r(double d,double e){
	return r.nextInt((int)e-(int)d)+(int)d;
}public static int r(double x2){
	return r.nextInt((int)x2);
}
public static double distance(double x,double y,double x2,double y2){

	double distance=Math.sqrt(((x-x2)*(x-x2)+(y-y2)*(y-y2)));
	return distance;
}
public static float GetAngleOfLineBetweenTwoPoints(float x1, float y1, float x2, float y2) {
    double xDiff = x1 - x2;
    double yDiff = y1 - y2;
    return (float)Math.toDegrees(Math.atan2(-yDiff, -xDiff));
  }
public static double cos(double num){
	return Math.cos(Math.toRadians(num));
}
public static double sin(double num){
	return Math.sin(Math.toRadians(num));
}
public static Color getMixedColor (Color color1,Color color2){
	
	
		        int red = color1.getRed();
		        int green = color1.getGreen();
		        int blue = color1.getBlue();
		       
		        red -= color2.getRed();
		        green -= color2.getGreen();
		        blue -= color2.getBlue();
		        
	return  new Color(Math.min(255, color1.getRed() - (red/2)), Math.min(255, color1.getGreen() - (green/2)), color1.getBlue() - (blue/2));

}

}
