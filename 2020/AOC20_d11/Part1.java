package AOC20_d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d10\\input.txt"))) {

			Set<Integer> sortedJolts = new TreeSet<>();
			while(sc.hasNext()) {
				sortedJolts.add(Integer.parseInt(sc.nextLine()));
			}
			sortedJolts.add(0);
			sortedJolts.add(Collections.max(sortedJolts) + 3);

			int oneJoltDiff = 0;
			int threeJoltDiff = 0;
			List<Integer> jolts = new ArrayList<>(sortedJolts);
			for (int i = 0; i < jolts.size() - 1; i++) {
				int diff = jolts.get(i + 1) - jolts.get(i);
				switch (diff) {
					case 1 -> oneJoltDiff++;
					case 3 -> threeJoltDiff++;
				}
			}
			System.out.println(oneJoltDiff * threeJoltDiff);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
