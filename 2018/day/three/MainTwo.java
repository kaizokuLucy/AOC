package day.three;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTwo {

	public static void main(String[] args) {
		File file = new File("src/day/three/input.txt");
		List<String> list = new ArrayList<>();
		
		try (Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				list.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
		}
		
		int[][] fabric = new int[2000][2000];
		String[] pom;
		int x, y, w, h;
		int sum = 0;
		for(String s : list) {
			s = s.replaceAll("[x,:]", " ");
			pom = s.split("\\s+");
			x = Integer.parseInt(pom[2]); y = Integer.parseInt(pom[3]); w = Integer.parseInt(pom[4]); h = Integer.parseInt(pom[5]);
			for(int i = y; i < y+h; i++) {
				for(int j = x; j < x+w; j++) {
					if(fabric[i][j] == 1) sum++;
					fabric[i][j] += 1;
				}
			}
		}
		
		System.out.println(sum);
		
		int count = 0;
		for(String s : list) {
			s = s.replaceAll("[x,:]", " ");
			pom = s.split("\\s+");
			x = Integer.parseInt(pom[2]); y = Integer.parseInt(pom[3]); w = Integer.parseInt(pom[4]); h = Integer.parseInt(pom[5]);
			outerloop:
			for(int i = y; i < y+h; i++) {
				for(int j = x; j < x+w; j++) {
					if(fabric[i][j] != 1) break outerloop;
					else count++;
				}
			}	
			if (count == (w*h)) {
				System.out.println(pom[0]);
				break;
			}
			count = 0;
		}
	}
}

