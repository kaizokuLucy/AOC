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

public class MainPart2 {

	static int SIZE = 50;

	static int blockNumber = 100;

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
			data.add(0);
			while (blockNumber != 0) {
				intcode.runProgram(data);
				x = (int) intcode.getResult();

				intcode.runProgram(data);
				y = (int) intcode.getResult();

				intcode.runProgram(data);
				type = (int) intcode.getResult();

				if (x == -1 && y == 0) {
					System.out.println("SCORE: " + type);
					drawGame(game);
					continue;
				}

				game[y][x] = Type.values()[type];

				if (type == 3) {
					paddle = new Tile(x, y, Type.PADDLE);
				}

				if (type == 4) {
					ball = new Tile(x, y, Type.BALL);
					if (paddle != null) {
						data.add(getNextMove(paddle, ball));
					}
					drawGame(game);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static int getNextMove(Tile paddle, Tile ball) {
		return ball.getX() < paddle.getX() ? -1 : 1;
	}

	private static void drawGame(Type[][] tiles) {
		blockNumber = 0;
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
					blockNumber += 1;
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
