package day.d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import day.d10.Point;

public class Main {

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	static Direction direction = Direction.UP;

	public static Map<Direction, Direction[]> directionMap;
	// can be replaces with initialization in main methon
	static {
		directionMap = new HashMap<>();

		directionMap.put(Direction.UP, new Direction[] { Direction.LEFT, Direction.RIGHT });
		directionMap.put(Direction.LEFT, new Direction[] { Direction.DOWN, Direction.UP });
		directionMap.put(Direction.DOWN, new Direction[] { Direction.RIGHT, Direction.LEFT });
		directionMap.put(Direction.RIGHT, new Direction[] { Direction.UP, Direction.DOWN });
	}

	public static void main(String[] args) {

		File file = new File("2019/day/d11/input.txt");

		try (Scanner sc = new Scanner(file); Scanner scInput = new Scanner(System.in)) {

			String input = sc.nextLine();

			List<Long> instructions = Arrays.asList(input.split(",")).stream().map(Long::parseLong)
					.collect(Collectors.toList());

			FakingIncode fkin = new FakingIncode(instructions, scInput);

			Map<Point, Integer> ship = new HashMap<>();
			// false = black, true = white
			Point current = new Point(0, 0);
			ship.put(current, 0);

			Queue<Integer> data = new LinkedList<Integer>();

			Set<Point> unique = new HashSet<>();

			// send first input into intcode (black color)
			data.add(1);
			while (!fkin.isStopped()) {
				// color output
				fkin.runProgram(data);
				int newColor = (int) fkin.getResult();
				if (newColor != ship.get(current)) {
					// memorize the point whose color has to change
					ship.put(current, newColor);
					unique.add(new Point(current.getX(), current.getY()));
				}

				// direction output
				fkin.runProgram(data);
				int nextMove = (int) fkin.getResult();
				int d = moveRobot(direction, nextMove, ship, current);
				data.add(d);
			}

			System.out.println("broj: " + unique.size());

			viewShipRegistration(ship);

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static void viewShipRegistration(Map<Point, Integer> ship) {

		int minX = ship.keySet().stream().mapToInt(Point::getX).min().getAsInt();
		int minY = ship.keySet().stream().mapToInt(Point::getY).min().getAsInt();
		int maxX = ship.keySet().stream().mapToInt(Point::getX).max().getAsInt();
		int maxY = ship.keySet().stream().mapToInt(Point::getY).max().getAsInt();

		for (int y = maxY; y >= minY; y--) {
			for (int x = minX; x <= maxX; x++) {
				System.out.print(ship.getOrDefault(new Point(x, y), 0) == 1 ? "#" : " ");
			}
			System.out.println();
		}

	}

	private static Integer moveRobot(Direction d, int nextMove, Map<Point, Integer> ship, Point current) {

		direction = directionMap.get(d)[nextMove];
		switch (direction) {
		case LEFT:
			current.setX(current.getX() - 1);
			break;
		case RIGHT:
			current.setX(current.getX() + 1);
			break;
		case DOWN:
			current.setY(current.getY() - 1);
			break;
		case UP:
			current.setY(current.getY() + 1);
			break;
		}

		// if key exists returns its value, else returns the value of the function
		return ship.computeIfAbsent(new Point(current.getX(), current.getY()), v -> 0);
	}
}
