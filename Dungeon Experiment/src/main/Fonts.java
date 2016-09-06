package main;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Fonts {

	public static Font SmallArial;

	public static Font Arial;

	public static Font HugeArial;

	public static UnicodeFont getFont(String font, int size) {

		// the returned font

		UnicodeFont returned = null;

		// load font
		try {

			Font awtFont = new Font(font, Font.BOLD, size);
			returned = new UnicodeFont(awtFont, size, false, false);
			returned.addAsciiGlyphs();
			returned.loadGlyphs();

		} catch (SlickException e) {
			e.printStackTrace();
		}

		return returned;

	}
}
