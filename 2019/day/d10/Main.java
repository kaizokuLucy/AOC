package day.d10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		
		File file = new File("2019/day/d10/input.txt");

		try (Scanner sc = new Scanner(file)) {

			List<Point> asteroidMap = new ArrayList<>();
			int y = 0;
			while (sc.hasNext()) {
				String line = sc.nextLine();
				for (int x = 0; x < line.length(); x++) {
					if (line.charAt(x) == '#') {
						Point p = new Point(x, y);
						asteroidMap.add(p);
					}
				}
				y++;
			}

			Map<Integer, Point> visibleAstNum = new HashMap<>();
			for (Point p : asteroidMap) {
				visibleAstNum.put(getNeighNum(p, asteroidMap), p);
			}

			int k = visibleAstNum.keySet().stream().max(Integer::compareTo).get();
			System.out.println(k);

			// PART TWO
			Point tower = visibleAstNum.get(k);
			Map<Double, List<Point>> allNeighbours = new TreeMap<>();
			getAnglesForAsteroids(tower, allNeighbours, asteroidMap);
			
			int cnt = 0;
			Point finalPoint = null;
			for (Entry<Double, List<Point>> entry : allNeighbours.entrySet()) {
				if (cnt == 200) {
					break;
				}
				finalPoint = entry.getValue().get(0);
				entry.getValue().remove(0);
				cnt++;
			}

			System.out.println(finalPoint.getX() * 100 + finalPoint.getY());
			
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static void getAnglesForAsteroids(Point tower, Map<Double, List<Point>> allNeighbours,
			List<Point> asteroidMap) {
		for (Point p : asteroidMap) {
			if (!tower.equals(p)) {
				allNeighbours.computeIfAbsent(wrapAngle((calculateAngle(p, tower) - Math.PI / 2)),
						k -> new ArrayList<>()).add(p);
			}
		}
	}

	public static double calculateAngle(Point p1, Point p2) {
		int yDiff = p2.getY() - p1.getY();
		int xDiff = p2.getX() - p1.getX();
		return Math.atan2(yDiff, xDiff);
	}

	private static double wrapAngle(double angle) {
		while (angle < 0) {
			angle += 2 * Math.PI;
		}
		return angle;
	}

	private static int getNeighNum(Point tower, List<Point> asteroidMap) {
		Set<Double> angleSet = new HashSet<>();

		for (Point p : asteroidMap) {
			if (!tower.equals(p)) {
				angleSet.add(calculateAngle(p, tower));
			}
		}
		return angleSet.size();
	}
}
