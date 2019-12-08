package day.d12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MainOne {

	public static void main(String[] args) {
		File file = new File("src/day/d12/input.txt");
		Map<String, String> rules = new LinkedHashMap<>();
		String init = "";

		try (Scanner sc = new Scanner(file)) {
			init = sc.nextLine().replaceAll("initial state: ", "");
			sc.nextLine();
			while (sc.hasNext()) {
				String[] pom = sc.nextLine().split(" => ");
				rules.put(pom[0], pom[1]);
			}
		} catch (FileNotFoundException e) {
		}
		String add = "";
		for (int s = 0; s < 20; s++) {
			add += ".................................................................................";
		}
		init = add + init + add;

		Map<Integer, String> indx = new LinkedHashMap<>();
		for (int g = 0; g < 20; g++) {
			indx.clear();
			for (int i = 0; i < init.length() - 4; i++) {
				String sub = init.substring(i, i + 5);
				indx.put(i, rules.getOrDefault(sub, "."));
			}
			for (Entry<Integer, String> ent : indx.entrySet()) {
				init = init.substring(0, ent.getKey() + 2) + ent.getValue() + init.substring(ent.getKey() + 3);
			}
		}
		System.out.println(init);
		int sum = 0;
		int size = -add.length();
		System.out.println(size);
		System.out.println(add.length());
		for (int j = 0; j < init.length(); j++, size++) {
			if (init.charAt(j) == '#') {
				System.out.println("pronasao # na: " + j + " " + size);
				sum += size;
			}
		}
		System.out.println(sum);
	}
}
