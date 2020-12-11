package AOC20_d09;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Part2 {
	private static final BigInteger SEARCH_NUMBER = new BigInteger("10884537");

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d09\\input.txt"))) {

			List<BigInteger> data = new ArrayList<>();
			while(sc.hasNext()) {
				data.add(new BigInteger(sc.nextLine()));
			}

			loop:
			for (int i = 0; i < data.size(); i++) {
				for (int j = i + 1; j < data.size(); j++) {
					BigInteger sum = sum(i, j, data);
					if (sum.compareTo(SEARCH_NUMBER) > 0) {
						continue loop;
					} else if (sum.compareTo(SEARCH_NUMBER) == 0) {
						System.out.println(calculateResult(i, j, data));
						break loop;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static BigInteger calculateResult(int start, int end, List<BigInteger> data) {
		BigInteger max = new BigInteger("0");
		for (int i = start; i < end; i++) {
			max = data.get(i).max(max);
		}

		BigInteger min = new BigInteger("" + data.get(start));
		for (int i = start; i < end; i++) {
			min = data.get(i).min(min);
		}
		return max.add(min);
	}

	private static BigInteger sum(int start, int end, List<BigInteger> data) {
		BigInteger sum = new BigInteger("0");
		for (int i = start; i < end; i++) {
			sum = sum.add(data.get(i));
		}
		return sum;
	}
}
