package AOC20_d12;

import AOC20_d11.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d12\\input.txt"))) {

			Point boaty = new Point(0, 0);
			Point waypoint = new Point(10, 1);			
			while(sc.hasNext()) {
				String line = sc.nextLine();
				char instruction = line.charAt(0);
				int value = Integer.parseInt(line.substring(1));

				switch (instruction) {
					case 'N', 'S', 'E', 'W' -> move(instruction, value, waypoint);
					case 'F' -> moveTo(value, boaty, waypoint);
					case 'L', 'R' -> rotate(instruction, value, boaty, waypoint);
				}
			}
			System.out.println(manhattan(boaty));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static int manhattan(Point boaty) {
		return Math.abs(boaty.getX()) + Math.abs(boaty.getY());
	}

	private static void rotate(char instruction, int value, Point boaty, Point waypoint) {
		int numOfRotations = value / 90;
		for (int i = 0; i < numOfRotations; i++) {
			int x = waypoint.getX() - boaty.getX();
			int y = waypoint.getY() - boaty.getY();

			waypoint.setX((instruction == 'L' ? -1 : 1) * y + boaty.getX());
			waypoint.setY((instruction == 'R' ? -1 : 1) * x + boaty.getY());
		}
	}

	private static void moveTo(int value, Point boaty, Point waypoint) {
		int xStep = value * (waypoint.getX() - boaty.getX());
		int yStep = value * (waypoint.getY() - boaty.getY());

		boaty.setX(boaty.getX() + xStep);
		boaty.setY(boaty.getY() + yStep);
		waypoint.setX(waypoint.getX() + xStep);
		waypoint.setY(waypoint.getY() + yStep);
	}


	private static void move(char instruction, int value, Point boaty) {
		switch (instruction) {
			case 'N' -> boaty.setY(boaty.getY() + value);
			case 'S' -> boaty.setY(boaty.getY() - value);
			case 'E' -> boaty.setX(boaty.getX() + value);
			case 'W' -> boaty.setX(boaty.getX() - value);
		}
	}
}
