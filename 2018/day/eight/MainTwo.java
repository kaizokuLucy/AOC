package day.eight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainTwo {

	static Scanner sc;

	public static void main(String[] args) {
		File file = new File("src/day/eight/input.txt");
		try {
			sc = new Scanner(file);
			System.out.println(find());

		} catch (FileNotFoundException e) {
		}
		sc.close();
	}

	private static int find() {
		int sum = 0;
		int brDjece = sc.nextInt();
		int meta = sc.nextInt();
		int[] pom = new int[brDjece];

		for (int br = 0; br < brDjece; br++) {
			pom[br] = find();
		}

		int l = 0;
		for (int m = 0; m < meta; m++) {
			l = sc.nextInt();
			if(brDjece == 0) {
				sum += l;
			}
			else {
				if(l-1 < brDjece) {
					sum += pom[l-1];
				}
			}
		}
		
		return sum;
	}
}
