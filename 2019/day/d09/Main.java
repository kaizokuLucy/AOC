package day.d09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	static int relativeBase = 0;
	public static void main(String[] args) {

		File file = new File("2019/day/d09/input.txt");
		
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
	
	static void store(final List<Integer> instructions, int index, Scanner sc) {
		System.out.println("read: ");
		int input = Integer.parseInt(sc.nextLine());
		instructions.set(instructions.get(index + 1), input);
	}

	static void print(final List<Integer> instructions, int index) {
		int a = instructions.get(index + 1);
		a = (instructions.get(index) % 1000) / 100 == 0 ? instructions.get(a) : a;
		System.out.println(a);
	}

	static void add(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		instructions.set(args.get(2), args.get(0) + args.get(1));
	}

	static void multiply(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		instructions.set(args.get(2), args.get(0) * args.get(1));
	}

	static int jumpIfTrue(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0) != 0) {
			return args.get(1);
		}
		return index + 3;
	}

	static int jumpIfFalse(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0) == 0) {
			return args.get(1);
		}
		return index + 3;
	}

	static void lessThan(List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0) < args.get(1)) {
			instructions.set(args.get(2), 1);
		} else {
			instructions.set(args.get(2), 0);
		}
	}

	static void equals(List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0).equals(args.get(1))) {
			instructions.set(args.get(2), 1);
		} else {
			instructions.set(args.get(2), 0);
		}
	}

	static List<Integer> getArgs(List<Integer> instructions, int index) {
		List<Integer> args = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			args.add(instructions.get(index + i));
		}
		int a = args.get(0);
		int b = args.get(1);
		
		int aMode = getHundreds(instructions, index);
		int bMode = getThousands(instructions, index);
		
		if (getHundreds(instructions, index) == 0) {
			args.set(0, instructions.get(a));
		} else if (getHundreds(instructions, index) == 1) {
			args.set(0, a);
		} else {
			
		}
		args.set(0,  setter(instructions, index, aMode, a));
		args.set(1,  setter(instructions, index, bMode, b));
		return args;
	}
	
	static int setter(List<Integer> instructions, int index, int mode, int num) {
		if (mode == 0) {
			return instructions.get(num);
		} else if (mode == 1) {
			return num;
		} 
		return instructions.get(relativeBase);
	}
	
	static int getHundreds(List<Integer> instructions, int index) {
		return (instructions.get(index) % 1000) / 100;
	}
	
	static int getThousands(List<Integer> instructions, int index) {
		return instructions.get(index) / 1000;
	}
	

	static void adjustRelativeBase(List<Integer> instructions, int index) {
		int a = instructions.get(index + 1);
		relativeBase += (instructions.get(index) % 1000) / 100 == 0 ? instructions.get(a) : a;
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
			case 9:
				adjustRelativeBase(instructions, index);
				break;
			}
		}
		sc.close();
	}	
}