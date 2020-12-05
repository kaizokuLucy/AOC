package AOC20_d01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d01\\input.txt"))) {
			HashSet data = new HashSet<>();
			while(sc.hasNext()) {
				int i = Integer.parseInt(sc.nextLine());
				if (data.contains(2020 - i)) {
					System.out.println(i + " " + (2020 - i));
					System.out.println(i * (2020 - i));
					break;
				} 
				data.add(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
