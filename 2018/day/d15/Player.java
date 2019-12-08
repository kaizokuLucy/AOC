package day.d15;

import java.util.ArrayList;
import java.util.List;

public class Player {

	boolean isAlive;
	int hp;
	int x;
	int y;
	String type;
	int damage;

	public Player(int x, int y, String type, boolean isALive) {
		this.hp = 200;
		this.x = x;
		this.y = y;
		this.type = type;
		this.isAlive = isALive;
		this.damage = 3;
	}
	
	public Player(Player p) {
		this.hp = p.hp;
		this.x = p.x;
		this.y = p.y;
		this.type = p.type;
		this.isAlive = p.isAlive;
		this.damage = p.damage;
	}

	@Override
	public String toString() {
		return "Player: " + type + " hp=" + hp + ", x=" + x + ", y=" + y + " isAlive= " + isAlive + " damage = " + damage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Player other = (Player) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public void movePlayer(List<String> combatMap) {
		List<Triplet> shortestPath = new ArrayList<>();
		for (Player p : MainOne.players) {
			if (this.equals(p)) {
				continue;
			}
			if (!this.type.equals(p.type) && p.isAlive) {
				try {
					shortestPath.add(MainOne.findDistance(this, p, combatMap));
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		if(shortestPath.isEmpty()) {
			return;
		}
		
		shortestPath.sort((Triplet t1, Triplet t2) -> t1.x - t2.x);
		shortestPath.sort((Triplet t1, Triplet t2) -> t1.y - t2.y);
		shortestPath.sort((Triplet t1, Triplet t2) -> t1.d - t2.d);
		this.x = shortestPath.get(0).x;
		this.y = shortestPath.get(0).y;		
	}

}
