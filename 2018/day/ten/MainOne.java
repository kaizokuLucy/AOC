package day.ten;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainOne {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		File file = new File("src/day/ten/input2.txt");
		String line;
		String[] pom;
		

		List<String> stars = new ArrayList<>();
		List<String> stars2 = new ArrayList<>();
		List<String> shine = new ArrayList<>();
		int X, Y, x, y;
		
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				line = sc.nextLine();
				line = line.replace("position=<", "").replace(",", " ").replace("velocity=<", "").replaceAll(">", "").trim();
				pom = line.split("\\s+");
//				X = Integer.parseInt(pom[0])/1000 + Integer.parseInt(pom[0])%100 + 50;
//				Y = Integer.parseInt(pom[1])/1000 + Integer.parseInt(pom[1])%100 + 50;
				X = Integer.parseInt(pom[0]);
				Y = Integer.parseInt(pom[1]);
				x = Integer.parseInt(pom[2]);
				y = Integer.parseInt(pom[3]);

				stars.add(X + ":" + Y + ":" + x + ":" + y);
				
				shine.add(Y+"#"+X);
				
			}
		} catch (FileNotFoundException e) {
		}
		
		
		PrintWriter writer = new PrintWriter("src/day/ten/output.txt", "UTF-8");

		int count = 0;
		while(count < 10000) {
			System.out.println(count);
			shine.clear();
			for(String s :stars) {
				String[] p = s.split(":");
				X = Integer.parseInt(p[0]);
				Y = Integer.parseInt(p[1]);
				x = Integer.parseInt(p[2]);
				y = Integer.parseInt(p[3]);

				shine.add((Y+y)+"#"+(X+x));
				stars2.add((X+x) + ":" + (Y+y) + ":" + x + ":" + y);
			}
			stars.clear();
			stars.addAll(stars2);
			stars2.clear();
			count++;
		}
		
		while (count < 12000) {
			int hash = 0;
			for (int i = -100; i < 400; i++) {
				for (int j = -100; j < 400; j++) {
					if(shine.contains(i+"#"+j)) {
						hash++;
					}
				}
				if(hash >= 10) break;
			}
			
			if(hash >= 10) {
				for (int i = -100; i < 400; i++) {
					for (int j = -100; j < 400; j++) {
						if(shine.contains(i+"#"+j)) {
							writer.print("#");
						} else {
							writer.print(".");
						}
					}
					writer.println();
				}
				writer.println(count);
				
			}
			shine.clear();
			
			for(String s :stars) {
				String[] p = s.split(":");
				X = Integer.parseInt(p[0]);
				Y = Integer.parseInt(p[1]);
				x = Integer.parseInt(p[2]);
				y = Integer.parseInt(p[3]);

				shine.add((Y+y)+"#"+(X+x));
				stars2.add((X+x) + ":" + (Y+y) + ":" + x + ":" + y);
			}
			System.out.println(count + ": " + hash);
			stars.clear();
			stars.addAll(stars2);
			stars2.clear();
			count++;
		}
		writer.close();
	}
}
