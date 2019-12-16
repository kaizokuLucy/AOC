package day.d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

import day.d10.Point;

public class Main {

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	public static void main(String[] args) {

		File file = new File("2019/day/d11/input.txt");

		try (Scanner sc = new Scanner(file); Scanner scInput = new Scanner(System.in)) {

			String input = sc.nextLine();

			List<Long> instructions = Arrays.asList(input.split(",")).stream().map(Long::parseLong)
					.collect(Collectors.toList());

			Direction direction = Direction.UP;
			FakingIncode fkin = new FakingIncode(instructions, scInput);

			Map<Point, Integer> ship = new HashMap<>();
			// false = black, true = white
			Point current = new Point(0, 0);
			ship.put(current, 0);

			Queue<Integer> data = new LinkedList<Integer>();
			data.add(0);
			while (!fkin.isStopped()) {
				fkin.runProgram(data);
				int color = (int) fkin.getResult();
				fkin.runProgram(data);
				int nextMove = (int) fkin.getResult();

				ship.put(current, color);
				data.add(moveRobot(direction, nextMove, ship, current));
			}
			
			System.out.println(ship.values().stream().filter(v -> v == 1).count());
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static Integer moveRobot(Direction direction, int nextMove, Map<Point, Integer> ship, Point current) {
		
		Direction newDirection;
		
		// TODO check one line initialization and directionMap global variable
		Map<Direction, Direction[]> directionMap = new HashMap<>();

		directionMap.put(Direction.UP, new Direction[] { Direction.LEFT, Direction.RIGHT });
		directionMap.put(Direction.LEFT, new Direction[] { Direction.DOWN, Direction.UP });
		directionMap.put(Direction.DOWN, new Direction[] { Direction.RIGHT, Direction.LEFT });
		directionMap.put(Direction.RIGHT, new Direction[] { Direction.UP, Direction.DOWN });

		newDirection = directionMap.get(direction)[nextMove];

		switch (newDirection) {
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
		return ship.get(current);
	}
}
