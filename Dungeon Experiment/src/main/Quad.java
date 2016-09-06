package main;

public class Quad {
	public int width, height, x, y;

	public Quad(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean checkPoint(float x, float y) {
		if (x >= this.x)
			if (y >= this.y)
				if (x <= this.x + this.width)
					if (y <= this.y + this.height)
						return true;
		return false;
	}
}
