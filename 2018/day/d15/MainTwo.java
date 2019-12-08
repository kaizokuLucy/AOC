package day.d15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainTwo {

	static List<Player> players = new ArrayList<>();

	public static void main(String[] args) {

		List<String> combatMap = new ArrayList<>();
		List<Goblin> goblins = new ArrayList<>();
		List<Elf> elves = new ArrayList<>();
		List<Player> initialPlayers = new ArrayList<>();

		File file = new File("src/day/d15/input.txt");
		String line = "";
		try (Scanner sc = new Scanner(file)) {
			Pattern pattern = Pattern.compile("([GE])");
			Matcher matcher;
			int y = 0;
			while (sc.hasNext()) {
				line = sc.nextLine();
				matcher = pattern.matcher(line);
				while (matcher.find()) {
					String s = matcher.group();
					if (s.equals("G")) {
						Goblin g = new Goblin(matcher.start(), y, "G", true);
						goblins.add(g);
						players.add(g);
						initialPlayers.add(g);
					} else {
						Elf e = new Elf(matcher.start(), y, "E", true);
						elves.add(e);
						players.add(e);
						initialPlayers.add(e);
					}
				}
				y++;
				line = line.replaceAll("[GE]", ".");
				combatMap.add(line);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int startNumOfElves = elves.size();
		while (true) {
			players.clear();
			players = initialPlayers.stream().map(Player::new).collect(Collectors.toList());
			int goblinNum = goblins.size();
			int elfNum = elves.size();
			int round = 0;
			loop: 
			while (true) {
				players.removeIf(p -> !p.isAlive);
				players.sort((Player p1, Player p2) -> p1.x - p2.x);
				players.sort((Player p1, Player p2) -> p1.y - p2.y);

				Iterator<Player> it = players.iterator();
				while (it.hasNext()) {
					Player p = it.next();
					if (p.isAlive == false) {
						continue;
					}
					List<Player> neiEnemy = new ArrayList<>();
					neiEnemy = getEnemyNei(p);
					if (neiEnemy.isEmpty()) {
						p.movePlayer(combatMap);
					}
					List<Player> enemyList = getEnemyNei(p);
					if (enemyList.isEmpty()) {
						continue;
					}
					Player attacked = attack(enemyList, p.damage);
					if (attacked != null) {
						if (attacked.type.equals("G")) {
							goblinNum--;
						} else {
							elfNum--;
						}
					}
					if (goblinNum == 0 || elfNum == 0) {
						if (p.equals(players.get(players.size() - 1))) {
							round++;
						}
						System.out.println("GOTOVO ZA RUNDU: " + round);
						int sum = players.stream().filter(pr -> pr.isAlive).mapToInt(pr -> pr.hp).sum();
						System.out.println(sum + " " + round);
						System.out.println(sum * round);

						break loop;
					}
				}
				round++;
			}
//			for(Player p : initialPlayers) {
//				System.out.println(p.toString());
//			}
			System.out.println(goblinNum + " " + elfNum);
			if(elfNum == startNumOfElves) {
				break;
			}
			for(Player p : initialPlayers) {
				if(p.type.equals("E")) {
					p.damage += 1;
				}
			}
		}
	}

	private static Player attack(List<Player> enemyList, int damage) {

		enemyList.sort((Player p1, Player p2) -> p1.x - p2.x);
		enemyList.sort((Player p1, Player p2) -> p1.y - p2.y);
		enemyList.sort((Player p1, Player p2) -> p1.hp - p2.hp);

		Player toAttack = enemyList.get(0);
		toAttack.hp -= damage;
		if (toAttack.hp <= 0) {
			toAttack.isAlive = false;
			return toAttack;
		}
		return null;

	}

	private static List<Player> getEnemyNei(Player player) {
		List<Player> nei = new ArrayList<>();

		int startX = player.x;
		int startY = player.y;
		for (Player p : players) {
			if (((p.x == startX && p.y == startY - 1) || (p.x == startX && p.y == startY + 1)
					|| (p.x == startX - 1 && p.y == startY) || (p.x == startX + 1 && p.y == startY))
					&& player.type != p.type && p.isAlive == true) {
				nei.add(p);
			}
		}
		return nei;
	}

	public static Triplet findDistance(Player startPlayer, Player endPlayer, List<String> combatMap) {
		List<Triplet> queue = new ArrayList<>();

		queue.add(new Triplet(endPlayer.x, endPlayer.y, 0));
		int count = 1;
		int i = 0;
		while (true) {
			// System.out.println(queue.size() + " " + i);
			if (i == queue.size())
				break;

			List<Triplet> adjacent = new ArrayList<>();
			Triplet t = queue.get(i);
			count = t.d + 1;
			adjacent.add(new Triplet(t.x, t.y + 1, count));
			adjacent.add(new Triplet(t.x, t.y - 1, count));
			adjacent.add(new Triplet(t.x + 1, t.y, count));
			adjacent.add(new Triplet(t.x - 1, t.y, count));

			next: 
			for (Triplet a : adjacent) {
				if (combatMap.get(a.y).charAt(a.x) != '#') {
					for (Player p : players) {
						if (p.x == a.x && p.y == a.y && p.isAlive) {
							continue next;
						}
					}
					for (Triplet q : queue) {
						if (q.x == a.x && q.y == a.y && a.d >= q.d) {
							continue next;
						}
					}
					queue.add(a);
				}
			}
			i++;
		}
		
		// nei polje susjeda od starta
		List<Triplet> nei = new ArrayList<>();
		int startX = startPlayer.x;
		int startY = startPlayer.y;
		for (Triplet q : queue) {
			if ((q.x == startX && q.y == startY - 1) || (q.x == startX && q.y == startY + 1)
					|| (q.x == startX - 1 && q.y == startY) || (q.x == startX + 1 && q.y == startY)) {
				nei.add(q);
			}
		}
		if (!nei.isEmpty()) {
			nei.sort((Triplet t1, Triplet t2) -> t1.x - t2.x);
			nei.sort((Triplet t1, Triplet t2) -> t1.y - t2.y);
			nei.sort((Triplet t1, Triplet t2) -> t1.d - t2.d);
			return nei.get(0);
		}
		return new Triplet(startPlayer.x, startPlayer.y, 999);
	}

//	private static void currentMap(List<String> combatMap) {
//
//		for (int i = 0; i < combatMap.size(); i++) {
//			String l = combatMap.get(i);
//			for (Player p : players) {
//				if (p.y == i && p.isAlive) {
//					l = l.substring(0, p.x) + p.type + l.substring(p.x + 1);
//				}
//			}
//			System.out.println(l);
//		}
//	}
}
