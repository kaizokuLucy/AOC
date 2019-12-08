package day.d05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		File file = new File("2019/day/d05/input.txt");

		try {
			Scanner sc = new Scanner(file);
			String input = sc.nextLine();
			sc.close();

			List<Integer> instructions = Arrays.asList(input.split(",")).stream().map(Integer::parseInt)
					.collect(Collectors.toList());
			runProgram(instructions);

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	static void add(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index, 3);
		instructions.set(args.get(2), args.get(0) + args.get(1));
	}

	static void multiply(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index, 3);
		instructions.set(args.get(2), args.get(0) * args.get(1));
	}

	static void store(final List<Integer> instructions, int index, Scanner sc) {
		System.out.println("read: ");
		int input = Integer.parseInt(sc.nextLine());
		instructions.set(instructions.get(index + 1), input);
	}

	static void print(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index, 3);
		System.out.println(args.get(0));
	}

	static int jumpIfTrue(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index, 3);
		if (args.get(0) != 0) {
			return args.get(1);
		}
		return index + 3;
	}

	static int jumpIfFalse(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index, 3);
		if (args.get(0) == 0) {
			return args.get(1);
		}
		return index + 3;
	}

	static void lessThan(List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index, 3);
		if (args.get(0) < args.get(1)) {
			instructions.set(args.get(2), 1);
		} else {
			instructions.set(args.get(2), 0);
		}
	}

	static void equals(List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index, 3);
		if (args.get(0).equals(args.get(1))) {
			instructions.set(args.get(2), 1);
		} else {
			instructions.set(args.get(2), 0);
		}
	}
	
	static List<Integer> getArgs(List<Integer> instructions, int index, int num) {
		List<Integer> args = new ArrayList<>();
		for (int i = 1; i <= num; i++) {
			args.add(instructions.get(index + i));
		}
		int a = args.get(0);
		int b = args.get(1);
		args.set(0, (instructions.get(index) % 1000) / 100 == 0 ? instructions.get(a) : a);
		args.set(1, instructions.get(index) / 1000 == 0 ? instructions.get(b) : b);
		return args;
	}
	
	static void runProgram(List<Integer> instructions) {
		Scanner sc = new Scanner(System.in);
		for (int index = 0, step = 0; instructions.get(index) != 99; index += step) {
			step = 0;
			int ins = instructions.get(index);
			switch (ins % 10) {
			case 1:
				add(instructions, index);
				step = 4;
				break;
			case 2:
				multiply(instructions, index);
				step = 4;
				break;
			case 3:
				store(instructions, index, sc);
				step = 2;
				break;
			case 4:
				print(instructions, index);
				step = 2;
				break;
			case 5:
				index = jumpIfTrue(instructions, index);
				break;
			case 6:
				index = jumpIfFalse(instructions, index);
				break;
			case 7:
				lessThan(instructions, index);
				step = 4;
				break;
			case 8:
				equals(instructions, index);
				step = 4;
				break;
			}
		}
		sc.close();
	}
}