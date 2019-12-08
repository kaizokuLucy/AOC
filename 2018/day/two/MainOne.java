package day.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainOne {

	static int two = 0;
	static int three = 0;
	
	public static void main(String[] args) {
		File file = new File("src/day/two/input.txt");
		List<String> list = new ArrayList<>();
		
		try (Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				list.add(sc.next());
			}
		} catch (FileNotFoundException e) {
		}
		
		Map<Character, Integer> mapa = new HashMap<>();
		
		for (String s : list) {
			char[] pom = s.toCharArray();
			for (char c : pom) {
				if(mapa.containsKey(c)) {
					mapa.put(c, mapa.get(c)+1);
				} else {
					mapa.put(c, 1);
				}
			}
			
			if(mapa.containsValue(2)) {
				two++;
			}
			if(mapa.containsValue(3)) {
				three++;
			}
			
			mapa.clear();
		}
		
		System.out.println("two: " + two + ", three: " + three);
		System.out.println("two*three: " + two*three);
	}
}
