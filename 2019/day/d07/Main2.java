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

			int[] comb = { 9, 8, 7, 6, 5 };
			List<int[]> combinations = new ArrayList<>();
			permute(comb, 0, combinations);

			List<Integer> amplifiedValues = new ArrayList<>();

			for (int i = 0; i < combinations.size(); i++) {
				Queue<Integer> data = new LinkedList<Integer>();
				data.add(9);
				data.add(0);
				List<Amplifier> ampList = new ArrayList<>();
				for (int j = 0; j < 5; j++) {
					// combinations.get(i)[j];
					Amplifier amp = new Amplifier(instructions);
					ampList.add(amp);
				}
				boolean first = true;
				int tmp = 0;
				while (ampList.get(4).getState() != State.STOPPED) {
					for (int j = 0; j < 5; j++) {
						ampList.get(j).runProgram(data);
						if (first && j != 4) {
							data.add(comb[j + 1]);
						}
						data.add(ampList.get(j).getResult());
						System.out.println(ampList.get(j).getInstructions());
					}
					first = false;
				}
				amplifiedValues.add(data.peek());
				break;
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
