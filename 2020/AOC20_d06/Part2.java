package AOC20_d06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
	public static void main(String[] args) {
		try {
			String path = "D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d06\\input.txt";
			List<String> passengers = new ArrayList<>(Files.readAllLines(Paths.get(path)));
			Map<Character, Integer> group = new HashMap<>();
			int groupSize = 0;
			int count = 0;
			for (String line : passengers) {
				if (line.isEmpty()) {
					final int size = groupSize;
					count += group.values().stream().filter(v -> v == size).count();
					group.clear();
					groupSize = 0;
					continue;
				}
				groupSize++;
				line.chars().forEach(c -> {
					group.merge((char) c, 1, Integer::sum);
				});
			}
			System.out.println(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
