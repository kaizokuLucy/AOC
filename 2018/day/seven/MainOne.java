package day.seven;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class MainOne {

	

public static void main(String[] args) {
		
		File file = new File("src/day/seven/input.txt");
		Map<String, String> map = new TreeMap<>();
		String line;
		String[] pom;
		String res = "";

		try (Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				line = sc.nextLine();
				line = line.replace("Step ", "").replace(" must be finished before step", "").replace(" can begin.", "");
				pom = line.split(" ");
				
				map.putIfAbsent(pom[1], "");
				map.put(pom[1], map.get(pom[1])+pom[0]);
				map.putIfAbsent(pom[0], "");

				
			}
		} catch (FileNotFoundException e) {
		}
		
		String k = "";
		while(map.size() > 0) {
			for(Entry<String, String> entry : map.entrySet()) {
				if(entry.getValue().isEmpty()) {
					res += entry.getKey();
					k = entry.getKey();
					break;
				}
			}
			for(Entry<String, String> entry2 : map.entrySet()) {
				map.put(entry2.getKey(), entry2.getValue().replace(k, ""));
			}
			map.remove(k);			
		}
		System.out.println(res);
	}
}
