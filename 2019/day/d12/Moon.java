package day.d12;

public class Moon {
	
	private int positionX;
	private int positionY;
	private int positionZ;
	
	private int velocityX;
	private int velocityY;
	private int velocityZ;
	
	public Moon(int positionX, int positionY, int positionZ) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.positionZ = positionZ;
		
		this.velocityX = 0;
		this.velocityY = 0;
		this.velocityZ = 0;
	}

	public Moon(int[] varList) {
		this.positionX = varList[0];
		this.positionY = varList[1];
		this.positionZ = varList[2];
		
		this.velocityX = 0;
		this.velocityY = 0;
		this.velocityZ = 0;
	}

	public Moon(Moon moon) {
		this.positionX = moon.getPositionX();
		this.positionY = moon.getPositionY();
		this.positionZ = moon.getPositionZ();
		this.velocityX = moon.getVelocityX();
		this.velocityY = moon.getVelocityY();
		this.velocityZ = moon.getVelocityZ();
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPositionZ() {
		return positionZ;
	}

	public void setPositionZ(int positionZ) {
		this.positionZ = positionZ;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public int getVelocityZ() {
		return velocityZ;
	}

	public void setVelocityZ(int velocityZ) {
		this.velocityZ = velocityZ;
	}

	@Override
	public String toString() {
		return String.format("%4d %4d %4d %4d %4d %4d", positionX, positionY, positionZ, velocityX, velocityY, velocityZ);
	}
	
	
}
