package day.d13;

public class Tile {

	private int x;
	private int y;
	private Type type;

	public Tile(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return x + ", " + y + " " + type;
	}
}
