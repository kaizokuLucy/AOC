package d01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 {
	public static void main(String[] args) {
		Path file = Path.of("D:\\Documents\\workspace\\gits\\AOC\\2022\\d01\\input.txt");
		try {
			String input = Files.readString(file);
			String[] caloriesPerElf = input.split("\\n\\n");
			int maxCaloriesPerElfSum = 0;
			for (String calories : caloriesPerElf) {
				int caloriesSum = Arrays.stream(
						calories.split("\n"))
						.map(Integer::parseInt)
						.mapToInt(Integer::intValue)
						.sum();

				maxCaloriesPerElfSum = Math.max(maxCaloriesPerElfSum, caloriesSum);
			}
			System.out.println(maxCaloriesPerElfSum);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
