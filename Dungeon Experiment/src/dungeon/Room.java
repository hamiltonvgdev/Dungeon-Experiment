package dungeon;

public class Room {

	private Block[][] blocks = new Block[32][20];

	public Room(Block walls, Block floor) {

		walls.setCollides(true);
		floor.setCollides(false);

		for (int x = 0; x < 32; x++) {
			for (int y = 0; y < 20; y++) {
				blocks[x][y] = floor.clone();
			}
		}
		for (int x = 0; x < 32; x++)
			blocks[x][0] = walls.clone();

		for (int x = 0; x < 32; x++)
			blocks[x][19] = walls.clone();

		for (int y = 0; y < 20; y++)
			blocks[0][y] = walls.clone();

		for (int y = 0; y < 20; y++)
			blocks[31][y] = walls.clone();

	}

	public Block getBlock(int x, int y) {
		return blocks[x][y];
	}

}
