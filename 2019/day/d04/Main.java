package day.d04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		int from = 172851;
		int to = 675869;

		int cnt1 = 0;
		int cnt2 = 0;
		for (int i = from; i <= to; i++) {
			if (checkPasswordPartOne(Integer.toString(i))) {
				cnt1++;
			}
			if (checkPasswordParTwo(Integer.toString(i))) {
				cnt2++;
			}
		}
		System.out.println(cnt1);
		System.out.println(cnt2);
	}

	static boolean checkPasswordPartOne(String s) {
		char[] cList = s.toCharArray();
		char[] sortedList = Arrays.copyOf(cList, 6);

		Arrays.sort(sortedList);
		if (!Arrays.equals(cList, sortedList)) {
			return false;
		}

		for (int i = 0; i < cList.length - 1; i++) {
			if (cList[i] == cList[i + 1]) {
				return true;
			}
		}
		return false;
	}

	private static boolean checkPasswordParTwo(String s) {
		String[] cList = s.split("");
		String[] sortedList = Arrays.copyOf(cList, 6);

		Arrays.sort(sortedList);
		if (!Arrays.equals(cList, sortedList)) {
			return false;
		}

		Map<String, Integer> adjacentCnt = new HashMap<>();
		for (int i = 0; i < cList.length - 1; i++) {
			if (cList[i].equals(cList[i + 1])) {
				adjacentCnt.compute(cList[i], (k, v) -> v == null ? 1 : ++v);
			}
		}
		return adjacentCnt.values().contains(1);
	}
}
