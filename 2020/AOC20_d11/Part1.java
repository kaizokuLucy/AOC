package AOC20_d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {
	private static int WIDTH;
	private static int HEIGHT;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d11\\input.txt"))) {

			List<String> grid = new ArrayList<>();
			while(sc.hasNext()) {
				grid.add(sc.nextLine());
			}

			WIDTH = grid.get(0).length();
			HEIGHT = grid.size();

			boolean changeOccured;
			do {
				changeOccured = false;
				List<String> gridNew = grid.stream().map(String::new).collect(Collectors.toList());
				for (int row = 0; row < HEIGHT; row++) {
					for (int col = 0; col < WIDTH; col++) {
						changeOccured |= changePositionValue(row, col, grid, gridNew);
					}
				}
				grid = gridNew.stream().map(String::new).collect(Collectors.toList());
			} while (changeOccured);

			System.out.println(grid.stream().map(s -> s.chars().filter(c -> c == '#').count()).reduce(0L, Long::sum));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static boolean changePositionValue(int row, int col, List<String> grid, List<String> gridNew) {
		String rowData = gridNew.get(row);
		char currentElement = grid.get(row).charAt(col);

		if (currentElement == 'L' && areAllAdjacentEmpty(row, col, grid)) {
			gridNew.set(row, setAt(rowData, col, '#'));
			return true;
		} else if (currentElement == '#' && areOccupied(row, col, grid)) {
			gridNew.set(row, setAt(rowData, col, 'L'));
			return true;
		}
		return false;
	}

	private static String setAt(String rowData, int col, char c) {
		return rowData.substring(0, col) + c + rowData.substring(col + 1);
	}

	private static boolean areOccupied(int row, int col, List<String> grid) {
		List<Point> coordinates = getAdjacentCoordinates(row, col);
		return coordinates.stream().filter(p -> grid.get(p.getY()).charAt(p.getX()) == '#').count() >= 4;
	}

	private static boolean areAllAdjacentEmpty(int row, int col, List<String> grid) {
		List<Point> coordinates = getAdjacentCoordinates(row, col);
		return coordinates.stream().allMatch(p -> grid.get(p.getY()).charAt(p.getX()) == 'L'
				|| grid.get(p.getY()).charAt(p.getX()) == '.');
	}

	private static List<Point> getAdjacentCoordinates(int Y, int X) {
		List<Point> coordinates = new ArrayList<>();
		coordinates.add(new Point(X - 1, Y));
		coordinates.add(new Point(X + 1, Y));
		coordinates.add(new Point(X, Y - 1));
		coordinates.add(new Point(X, Y + 1));
		coordinates.add(new Point(X - 1, Y - 1));
		coordinates.add(new Point(X - 1, Y + 1));
		coordinates.add(new Point(X + 1, Y + 1));
		coordinates.add(new Point(X + 1, Y - 1));

		return coordinates.stream().filter(
				p -> p.getX() >= 0 && p.getY() >= 0
						&& p.getX() < WIDTH && p.getY() < HEIGHT).collect(Collectors.toList());
	}
}
