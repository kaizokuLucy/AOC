package day.d13;

public class Cart {
	char direction;
	int crossDir;
	int x;
	int y;
	
	public Cart(char direction, int crossDir, int x, int y) {
		this.direction = direction;
		this.crossDir = crossDir;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "direction=" + direction + ", crossDir=" + crossDir + ", x=" + x + ", y=" + y;
	}

	public void nextMove(char track) {
		if (track == '-') {
			x += (direction == '<') ? -1 : 1;
		} else if (track == '|') {
			y += (direction == 'v') ? 1 : -1;
		} else if (track == '+') {
			if (crossDir == 0) {
				if (direction == '<') {
					direction = 'v';
					y++;
				} else if (direction == '>') {
					direction = '^';
					y--;
				} else if (direction == '^') {
					direction = '<';
					x--;
				} else {
					direction = '>';
					x++;
				}
			} else if (crossDir == 1) {
				x += (direction == '<') ? -1 : 0;
				x += (direction == '>') ? 1 : 0;
				y += (direction == '^') ? -1 : 0;
				y += (direction == 'v') ? 1 : 0;
			} else {
				if (direction == '<') {
					direction = '^';
					y--;
				} else if (direction == '>') {
					direction = 'v';
					y++;
				} else if (direction == '^') {
					direction = '>';
					x++;
				} else {
					direction = '<';
					x--;
				}
			}
			crossDir = (crossDir + 1) % 3;
		} else if (track == '/') {
			if (direction == '<') {
				direction = 'v';
				y++;
			} else if (direction == '>') {
				direction = '^';
				y--;
			} else if (direction == '^') {
				direction = '>';
				x++;
			} else {
				direction = '<';
				x--;
			}
		} else if (track == '\\') {
			if (direction == '<') {
				direction = '^';
				y--;
			} else if (direction == '>') {
				direction = 'v';
				y++;
			} else if (direction == '^') {
				direction = '<';
				x--;
			} else {
				direction = '>';
				x++;
			}
		}
				
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + crossDir;
		result = prime * result + direction;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (crossDir != other.crossDir)
			return false;
		if (direction != other.direction)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
