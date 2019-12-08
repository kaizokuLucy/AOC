package day.one;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		File file = new File("src/day/one/input.txt");
		List<Integer> list = new ArrayList<>();

		Set<Integer> set = new HashSet<>();
		try (Scanner sc = new Scanner(file)){
			while(sc.hasNextInt()) {
				list.add(sc.nextInt());
			}
		} catch (FileNotFoundException e) {
		}
		
		int sum = 0;
		while(true) {
			for (Integer i : list) {
				sum += i;
				if(!set.add(sum)) {
					System.out.println(sum);
					System.exit(0);
				}
			}
		}
		
	}
}
