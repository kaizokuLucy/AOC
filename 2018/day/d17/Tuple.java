package day.d17;

public class Tuple {

	private int x;
	private int y;
	
	public Tuple(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tuple && ((Tuple) obj).getX() == x && ((Tuple) obj).getY() == y) 
			return true;
		return false;
		/*if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (x != other.getX() || y != other.getY())
			return false;
		return true;
		*/
	}


	@Override
	public String toString() {
		return "x=" + x + ", y=" + y;
	}
	
	
}
