package day.d13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

import day.d11.Intcode;

public class Play {

	static int SIZE = 50;

	public static void main(String[] args) {
		File file = new File("2019/day/d13/inputPart2.txt");

		try (Scanner sc = new Scanner(file); Scanner scInput = new Scanner(System.in)) {

			String input = sc.nextLine();

			List<Long> instructions = Arrays.asList(input.split(",")).stream().map(Long::parseLong)
					.collect(Collectors.toList());

			Intcode intcode = new Intcode(instructions, scInput, false);

			Queue<Integer> data = new LinkedList<Integer>();

			int x = 0;
			int y = 0;
			int type;
			Type game[][] = new Type[SIZE][SIZE];

			for (Type[] row : game) {
				Arrays.fill(row, Type.EMPTY);
			}

			Tile paddle = null;
			Tile ball = null;
			while (!intcode.isStopped()) {
				intcode.runProgram(data);
				x = (int) intcode.getResult();

				intcode.runProgram(data);
				y = (int) intcode.getResult();

				intcode.runProgram(data);
				type = (int) intcode.getResult();

				if (x == -1 && y == 0) {
					System.out.println("SCORE: " + type);
					continue;
				}

				if (type == 3) {
					paddle = new Tile(x, y, Type.PADDLE);
					System.out.println("paddle je " + paddle);
				} else if (type == 4) {
					ball = new Tile(x, y, Type.BALL);
					System.out.println("LOPTAAAAAA");
				}

				game[y][x] = Type.values()[type];
				drawGame(game);
				getNextMove(paddle, ball);
			}
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static void getNextMove(Tile paddle, Tile ball) {
		// TODO Auto-generated method stub
	}

	private static void drawGame(Type[][] tiles) {
		for (int i = SIZE - 20; i >= 0; i--) {
			for (int j = 0; j < SIZE; j++) {
				switch (tiles[i][j]) {
				case EMPTY:
					System.out.print(" ");
					break;
				case WALL:
					System.out.print("|");
					break;
				case BLOCK:
					System.out.print("#");
					break;
				case PADDLE:
					System.out.print("_");
					break;
				case BALL:
					System.out.print("O");
					break;
				}
			}
			System.out.println();
		}

	}

}
