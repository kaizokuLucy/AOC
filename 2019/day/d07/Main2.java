package day.d07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

import day.d07.Amplifier.State;

public class Main2 {
	public static void main(String[] args) {
		File file = new File("2019/day/d07/input.txt");

		try {
			Scanner sc = new Scanner(file);
			String input = sc.nextLine();
			sc.close();

			List<Integer> instructions = Arrays.asList(input.split(",")).stream().map(Integer::parseInt)
					.collect(Collectors.toList());

			// PART ONE phases
			// int[] comb = { 9, 8, 7, 6, 5 };

			// PART TWO phases
			int[] comb = { 9, 8, 7, 6, 5 };
			List<int[]> combinations = new ArrayList<>();
			permute(comb, 0, combinations);

			List<Integer> amplifiedValues = new ArrayList<>();

			for (int[] combination : combinations) {

				List<Amplifier> ampList = new ArrayList<>();
				for (int j = 0; j < 5; j++) {
					// create copyList so every amplifier has its own instruction list
					// and not a reference to just one list
					List<Integer> copyInstructionList = new ArrayList<>(instructions);
					Amplifier amp = new Amplifier(copyInstructionList);
					ampList.add(amp);
				}

				int inputValue = 0;
				Queue<Integer> data = new LinkedList<Integer>();

				boolean firstIteration = true;
				while (ampList.get(4).getState() != State.STOPPED) {
					for (int j = 0; j < 5; j++) {

						if (firstIteration) {
							// phase
							data.add(combination[j]);
						}
						data.add(inputValue);
						ampList.get(j).runProgram(data);
						inputValue = ampList.get(j).getResult();
					}
					firstIteration = false;
				}
				amplifiedValues.add(inputValue);
			}

			System.out.println(amplifiedValues.stream().reduce(Integer::max).get());

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static void permute(int[] comb, int index, List<int[]> combinations) {
		if (index >= comb.length - 1) {
			int[] a = new int[5];
			System.arraycopy(comb, 0, a, 0, 5);
			combinations.add(a);
			return;
		}

		for (int i = index; i < comb.length; i++) {

			int t = comb[index];
			comb[index] = comb[i];
			comb[i] = t;

			permute(comb, index + 1, combinations);

			t = comb[index];
			comb[index] = comb[i];
			comb[i] = t;
		}
	}
}
