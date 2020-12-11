package AOC20_d10;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Part2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d10\\input.txt"))) {

			Set<Integer> sortedJolts = new TreeSet<>();
			while(sc.hasNext()) {
				sortedJolts.add(Integer.parseInt(sc.nextLine()));
			}
			sortedJolts.add(0);
			int device = Collections.max(sortedJolts) + 3;
			sortedJolts.add(device);

			Map<Integer, BigInteger> memoization = new HashMap<>();
			BigInteger count = count(sortedJolts, device, memoization);
			System.out.println(count);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static BigInteger count(Set<Integer> jolts, int adapter, Map<Integer, BigInteger> memoization) {
		if (adapter == 0) {
			return new BigInteger("1");
		}

		BigInteger sum = new BigInteger("0");
		for (int i = 1; i <= 3; i++) {
			int nextAdapter = adapter - i;
			if (jolts.contains((nextAdapter))) {
				if (memoization.containsKey(nextAdapter)) {
					sum = sum.add(memoization.get(nextAdapter));
				} else {
					BigInteger value = count(jolts, nextAdapter, memoization);
					memoization.put(nextAdapter, value);
					sum = sum.add(value);
				}
			}
		}

		return sum;
	}
}
