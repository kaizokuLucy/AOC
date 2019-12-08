package day.seven;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MainTwo {

	public static void main(String[] args) {
		File file = new File("src/day/seven/input.txt");
		Map<String, String> map = new TreeMap<>();
		String line;
		String[] pom;

		Map<String, Integer> timeMap = new TreeMap<>();
		for (char var = 'A'; var <= 'Z'; var++) {
			timeMap.put(Character.toString(var), var - 4);
		}
		
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext()) {
				line = sc.nextLine();
				line = line.replace("Step ", "").replace(" must be finished before step", "").replace(" can begin.",
						"");
				pom = line.split(" ");

				map.putIfAbsent(pom[1], "");
				map.put(pom[1], map.get(pom[1]) + pom[0]);
				map.putIfAbsent(pom[0], "");

			}
		} catch (FileNotFoundException e) {
		}

	
		int time = 1;
		Map<String, Integer> workers = new TreeMap<>();

		while (map.size() > 0) {
			
			Set<String> done = new TreeSet<>();
			for (Entry<String, Integer> w : workers.entrySet()) {
				if (w.getValue().equals(time)) {
					done.add(w.getKey());
					for (Entry<String, String> entry2 : map.entrySet()) {
						map.put(entry2.getKey(), entry2.getValue().replace(w.getKey(), ""));
					}
					map.remove(w.getKey());
				}
			}
			for (String d : done) {
				workers.remove(d);
			}
			for (Entry<String, String> e : map.entrySet()) {
				if (e.getValue().isEmpty() && workers.size() < 5 && !workers.containsKey(e.getKey())) {
					workers.put(e.getKey(), time + timeMap.get(e.getKey()));
				}
			}

			
			System.out.println("time: " + time);
			for (Entry<String, Integer> entry : workers.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
			}

			System.out.println("------------------");

			time++;

		}
		System.out.println(time - 2);
	}
}
