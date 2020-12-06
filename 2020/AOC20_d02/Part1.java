package AOC20_d02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d02\\input.txt"))) {
			int count = 0;
			while(sc.hasNext()) {
				final String line = sc.nextLine();
				Pattern pattern = Pattern.compile("(\\d+)-(\\d+) (.): (\\w+)");
				Matcher matcher = pattern.matcher(line);
				matcher.find();
				int lowerBound = Integer.parseInt(matcher.group(1));
				int upperBound = Integer.parseInt(matcher.group(2));
				char c = matcher.group(3).charAt(0);
				String password = matcher.group(4);
				var sum = password.chars().filter(pc -> pc == c).count();
				if (lowerBound <= sum && sum <= upperBound) {
					count++;
				}
			}
			System.out.println(count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
