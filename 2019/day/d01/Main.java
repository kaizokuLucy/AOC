package day.d01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		File file = new File("2019/day/d01/input.txt");
		
		IFuelCalculator fc = (int mass) -> {
			return mass/3 - 2;
		};
		System.out.println("part one: " + solver(file, fc));
		
		fc = (int mass) -> {
			int fuel = 0;
			while (mass/3 - 2 > 0) {
				mass = mass / 3 - 2;
				fuel += mass;
			}
			return fuel;
		};
		System.out.println("part two: " + solver(file, fc));

	}
	
	static int solver(File file, IFuelCalculator fc) {
		Scanner sc = null;
		int sum = 0;

		try {
			sc = new Scanner(file);
			while (sc.hasNextInt()) {
				sum += fc.calculateFuel(sc.nextInt());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

		return sum;
	}
}
