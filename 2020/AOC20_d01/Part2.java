package AOC20_d01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Part2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("2020\\AOC20_d01\\input.txt"))) {
			Set<Integer> dataSet = new HashSet<>();
			while(sc.hasNext()) {
				dataSet.add(Integer.parseInt(sc.nextLine()));
			}
			
			List<Integer> data = List.copyOf(dataSet);
			loop:
			for (int i = 0; i < data.size(); i++) {
				for (int j = i; j < data.size(); j++) {
					for (int k = j; k < data.size(); k++) {
						int a = data.get(i);
						int b = data.get(j);
						int c = data.get(k);
						if (a + b + c == 2020) {
							System.out.format("%d %d %d\n", a, b, c);
							System.out.println(a * b * c);
							break loop;
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
