package AOC20_d06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
	public static void main(String[] args) {
		try {
			String path = "D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d06\\input.txt";
			List<String> passengers = new ArrayList<>(Files.readAllLines(Paths.get(path)));

			StringBuilder group = new StringBuilder();
			int count = 0;
			for (String line : passengers) {
				if (line.isEmpty()) {
					count += group.chars().distinct().count();
					group = new StringBuilder();
				}
				group.append(line);
			}
			System.out.println(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
