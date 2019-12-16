package day.d09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		File file = new File("2019/day/d09/input.txt");

		try {
			Scanner sc = new Scanner(file);
			String input = sc.nextLine();
			sc.close();

			List<Long> instructions = Arrays.asList(input.split(",")).stream().map(Long::parseLong)
					.collect(Collectors.toList());

			// add extra memory
			for (int i = 0; i < 1000; i++) {
				instructions.add(0l);
			}

			IntcodeComputer computer = new IntcodeComputer();
			computer.runProgram(instructions);

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}
}
