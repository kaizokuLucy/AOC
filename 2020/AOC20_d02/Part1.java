package AOC20_d02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d02\\input.txt"))) {
			int count = 0;
			// 8-9 n: nnnnnnnnn
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] data = line.split(" ");
				List<Integer> range = Arrays.asList(data[0].split("-")).stream().map(Integer::parseInt).collect(Collectors.toList());
				Character c = data[1].charAt(0);
				int sum = 0;
				for (int i = 0; i < data[2].length(); i++) {
					if (data[2].charAt(i) == c) {
						sum++;
					}
				}
				if (range.get(0) <= sum && sum <= range.get(1)) {
					count++;
				}
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
