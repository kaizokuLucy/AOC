package AOC20_d12;

import AOC20_d11.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d12\\input.txt"))) {

			Point boaty = new Point(0, 0);
			char direction = 'E';
			while(sc.hasNext()) {
				String line = sc.nextLine();
				char instruction = line.charAt(0);
				int value = Integer.parseInt(line.substring(1));

				switch (instruction) {
					case 'N', 'S', 'E', 'W' -> moveBoaty(instruction, value, boaty);
					case 'F' -> moveBoaty(direction, value, boaty);
					case 'L', 'R' -> direction = changeDirection(instruction, value, direction);
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

	private static char changeDirection(char instruction, int value, char direction) {
		List<Character> directionsLeft = Arrays.asList('N', 'W', 'S', 'E');
		int step = value / 90;
		int startIndex = directionsLeft.indexOf(direction);
		switch (instruction) {
			case 'L' -> {
				return directionsLeft.get((startIndex + step) % 4);
			}
			case 'R' -> {
				return directionsLeft.get((startIndex - step + 4) % 4);
			}
		}
		return ' ';
	}

	private static void moveBoaty(char instruction, int value, Point boaty) {
		switch (instruction) {
			case 'N' -> boaty.setY(boaty.getY() + value);
			case 'S' -> boaty.setY(boaty.getY() - value);
			case 'E' -> boaty.setX(boaty.getX() + value);
			case 'W' -> boaty.setX(boaty.getX() - value);
		}
	}
}
