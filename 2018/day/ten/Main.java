package day.ten;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		File file = new File("src/day/ten/input.txt");
		String line;
		String[] pom;
		String[][] init = new String[50][50];
		for (int i = 0; i < init.length; i++) {
			for (int j = 0; j < init.length; j++) {
				init[i][j] = ".";
			}
		}

		List<String> stars = new ArrayList<>();
		List<String> stars2 = new ArrayList<>();

		int X, Y, x, y;
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				line = sc.nextLine();
				line = line.replace("position=<", "").replace(",", " ").replace("velocity=<", "").replaceAll(">", "").trim();
				pom = line.split("\\s+");
				X = Integer.parseInt(pom[0]) + 20;
				Y = Integer.parseInt(pom[1]) + 20;
				x = Integer.parseInt(pom[2]);
				y = Integer.parseInt(pom[3]);

				stars.add(X + ":" + Y + ":" + x + ":" + y);
				
				init[Y][X] = "#";
			}
		} catch (FileNotFoundException e) {
		}
		
		for(String s : stars) {
			System.out.println(s);
		}
		
		while (true) {
			for (int i = 0; i < init.length; i++) {
				for (int j = 0; j < init.length; j++) {
					System.out.print(init[i][j]);
				}
				System.out.println();
			}
			String[][] init2 = new String[50][50];
			for (int i = 0; i < init2.length; i++) {
				for (int j = 0; j < init2.length; j++) {
					init2[i][j] = ".";
				}
			}
			
			for(String s :stars) {
				String[] p = s.split(":");
				X = Integer.parseInt(p[0]);
				Y = Integer.parseInt(p[1]);
				x = Integer.parseInt(p[2]);
				y = Integer.parseInt(p[3]);

				init2[Y+y][X+x] = "#";
				stars2.add((X+x) + ":" + (Y+y) + ":" + x + ":" + y);
			}
			System.out.println(stars.size());
			stars.clear();
			stars.addAll(stars2);
			stars2.clear();
			Thread.sleep(3000);
			for (int i = 0; i < init.length; i++) {
				for (int j = 0; j < init.length; j++) {
					init[i][j] = init2[i][j];
				}
			}
		}
		
	}
}
