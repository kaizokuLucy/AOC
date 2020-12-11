package AOC20_d07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
	private static final String SHINY_GOLD = "shiny gold";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d07\\input.txt"))) {

			Map<String, Set<Node>> graph = new HashMap<>();
			while(sc.hasNext()) {
				String line = sc.nextLine();
				if (line.contains("no other")) {
					continue;
				}
				line = line.replaceAll("contain|,|\\.", "").replaceAll("\\s+", " ");
				line = line.replaceAll("\\s*bags?\\s*", "#");
				String[] data = line.split("#");
				String parent = data[0];
				for (int i = 1; i < data.length; i++) {
					String[] child = data[i].split(" ", 2);
					graph.computeIfAbsent(parent, k -> new HashSet<>()).add(new Node(child[1], Integer.parseInt(child[0])));
				}
			}
			printGraph(graph);
			int count = findNumber(SHINY_GOLD, graph);
			System.out.println(count);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static int findNumber(String node, Map<String, Set<Node>> graph) {
		if (!graph.containsKey(node)) {
			return 0;
		}
		int count = 0;
		for (Node n : graph.get(node)) {
			int value = n.value;
			count += value + value * findNumber(n.name, graph);
		}
		return count;
	}

	private static void printGraph(Map<String, Set<Node>> graph) {
		System.out.println("------------------------------------");
		for (Map.Entry<String, Set<Node>> e : graph.entrySet()) {
			System.out.println(e.getKey());
			System.out.println("\t" + e.getValue());
		}
	}
}
