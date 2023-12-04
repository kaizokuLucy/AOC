package AOC20_d13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d13\\input.txt"))) {

			int timeStamp = Integer.parseInt(sc.nextLine());
			List<Integer> IDs = Arrays.stream(
					sc.nextLine()
							.replaceAll("x,?", "")
							.split(","))
					.map(Integer::parseInt)
					.collect(Collectors.toList());

			Map<Integer, Integer> timeTable = new HashMap<>();
			for (Integer id : IDs) {
				timeTable.put(id, id - timeStamp % id);
			}

			Integer min = Integer.MAX_VALUE;
			Integer result = 0;
			for (Map.Entry<Integer, Integer> tt : timeTable.entrySet()) {
				if (tt.getValue() < min) {
					min = tt.getValue();
					result = min * tt.getKey();
				}
			}

			System.out.println(result);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
