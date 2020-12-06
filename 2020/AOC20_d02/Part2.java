package AOC20_d02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d02\\input.txt"))) {
			int count = 0;
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] data = line.split(" ");
				List<Integer> range = Arrays.asList(data[0].split("-")).stream().map(Integer::parseInt).collect(Collectors.toList());
				int start = range.get(0) - 1;
				int end = range.get(1) - 1;
				Character c = data[1].charAt(0);
				String password = data[2];
				if ((password.charAt(start) == c && password.charAt(end) != c) 
						|| (password.charAt(start) != c && password.charAt(end) == c)) {
					count++;
				}
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
