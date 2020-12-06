package AOC20_d05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d05\\input.txt"))) {
			int max = 0;
			while(sc.hasNext()) {
				String seat = sc.nextLine();
				Integer row = Integer.parseInt(
						seat.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
				Integer column = Integer.parseInt(
						seat.substring(7, 10).replace('L', '0').replace('R', '1'), 2);
				max = Math.max(max, row * 8 + column);
			}
			System.out.println(max);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
