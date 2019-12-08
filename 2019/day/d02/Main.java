package day.d02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		final int expectedOutput = 19690720;
		File file = new File("2019/day/d02/input.txt");

		try {
			Scanner sc = new Scanner(file);
			String input = sc.nextLine();
			sc.close();

			List<Integer> instructions = Arrays.asList(input.split(",")).stream().map(Integer::parseInt)
					.collect(Collectors.toList());
			System.out.println("First part: " + runProgram(12, 2, instructions));

			loop: for (int noun = 0; noun < 99; noun++) {
				for (int verb = 0; verb < 99; verb++) {
					if (runProgram(noun, verb, instructions) == expectedOutput) {
						System.out.println("Second part: " + (100 * noun + verb));
						break loop;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	static int runProgram(int noun, int verb, final List<Integer> original) {

		List<Integer> instructions = new ArrayList<>(original);

		instructions.set(1, noun);
		instructions.set(2, verb);

		for (int index = 0; instructions.get(index) != 99; index += 4) {
			int dst = instructions.get(index + 3);
			int a = instructions.get(instructions.get(index + 1));
			int b = instructions.get(instructions.get(index + 2));
			if (instructions.get(index) == 1) {
				instructions.set(dst, a + b);
			} else {
				instructions.set(dst, a * b);
			}
		}
		return instructions.get(0);
	}
}
