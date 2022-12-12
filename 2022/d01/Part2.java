package d01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {
	public static void main(String[] args) {
		Path file = Path.of("D:\\Documents\\workspace\\gits\\AOC\\2022\\d01\\input.txt");
		try {
			String input = Files.readString(file);
			String[] caloriesPerElf = input.split("\\n\\n");
			List<Integer> caloriesPerElfSum = new ArrayList<>();
			for (String calories : caloriesPerElf) {
				int caloriesSum = Arrays.stream(
						calories.split("\n"))
						.map(Integer::parseInt)
						.mapToInt(Integer::intValue)
						.sum();

				caloriesPerElfSum.add(caloriesSum);
			}
			caloriesPerElfSum.sort(Collections.reverseOrder());
			System.out.println(caloriesPerElfSum.get(0) + caloriesPerElfSum.get(1) + caloriesPerElfSum.get(2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
