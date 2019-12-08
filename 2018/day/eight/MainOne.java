package day.eight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainOne {
	
	static int res = 0;
	static int indx = 0;
	static Scanner sc;
	
	public static void main(String[] args) {
		File file = new File("src/day/eight/input.txt");
		try {
			sc = new Scanner(file);
			find();			
			
		} catch (FileNotFoundException e) {
		}
		
		sc.close();
		System.out.println("res: " + res);
	}
	
	private static void find() {
	int brDjece = sc.nextInt();
	int meta = sc.nextInt();
	
	for(int br = 0; br < brDjece; br++) {
		find();
	}
	for(int m = 0; m < meta; m++) {
		res += sc.nextInt();
	}		
}

//	private static void find(String[] line) {
//		System.out.println("index " + indx);
//		int brDjece = Integer.parseInt(line[indx++]);
//
//		int meta = Integer.parseInt(line[indx++]);
//		System.out.println(brDjece + " " + meta);		
//		
//		for(int br = 0; br < brDjece; br++) {
//			find(line);
//		}
//		for(int m = 0; m < meta; m++) {
//			res += Integer.parseInt(line[indx++]);
//			System.out.println(res);
//		}		
//	}
}
