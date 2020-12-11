package AOC20_d09;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Part1 {
	private static final int PREAMBLE_STEP = 25;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d09\\input.txt"))) {

			List<BigInteger> data = new ArrayList<>();
			while(sc.hasNext()) {
				data.add(new BigInteger(sc.nextLine()));
			}

			for (int i = PREAMBLE_STEP; i < data.size(); i++) {
				Set<BigInteger> preambles = new HashSet<>(data.subList(i - PREAMBLE_STEP, i));
				if (!canBeCalculated(data.get(i), preambles)) {
					System.out.println(data.get(i));
					break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static boolean canBeCalculated(BigInteger number, Set<BigInteger> preambles) {
		for (BigInteger p : preambles) {
			if (preambles.contains(number.subtract(p))) {
				return true;
			}
		}
		return false;
	}
}
