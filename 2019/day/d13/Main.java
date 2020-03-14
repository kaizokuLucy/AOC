package day.d13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

import day.d11.Intcode;

public class Main {

	public static void main(String[] args) {
		File file = new File("2019/day/d13/input.txt");

		try (Scanner sc = new Scanner(file); Scanner scInput = new Scanner(System.in)) {

			String input = sc.nextLine();

			List<Long> instructions = Arrays.asList(input.split(",")).stream().map(Long::parseLong)
					.collect(Collectors.toList());

			Intcode intcode = new Intcode(instructions, scInput);
			
			Queue<Integer> data = new LinkedList<Integer>();
			
			List<Integer> types = new ArrayList<>();
			while (!intcode.isStopped()) {
				intcode.runProgram(data);
				intcode.runProgram(data);
				intcode.runProgram(data);
				types.add((int) intcode.getResult());
			}
			
			System.out.println("Number of blocks: " + types.stream().filter(t -> t == 2).count());

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}
}
