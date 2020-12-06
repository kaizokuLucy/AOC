package AOC20_d05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Part2 {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d05\\input.txt"))) {
			Set<Integer> IDs = new TreeSet<>();
			while(sc.hasNext()) {
				String seat = sc.nextLine();
				Integer row = Integer.parseInt(
						seat.substring(0, 7).replace('F', '0').replace('B', '1'), 2);
				Integer column = Integer.parseInt(
						seat.substring(7, 10).replace('L', '0').replace('R', '1'), 2);
				IDs.add(row * 8 + column);
			}
			int previous = IDs.iterator().next();
			for (Integer id : IDs) {
				if (previous == id) continue;
				if (previous + 1 != id) {
					System.out.println(id - 1);
					break;
				}
				previous = id;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
