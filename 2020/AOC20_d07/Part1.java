package AOC20_d07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
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
					graph.computeIfAbsent(child[1], k -> new HashSet<>()).add(new Node(parent, Integer.parseInt(child[0])));
				}
			}
			printGraph(graph);
			Set<Node> parents = new HashSet<>();
			findNumber(SHINY_GOLD, graph, parents);
			System.out.println(parents.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void findNumber(String node, Map<String, Set<Node>> graph, Set<Node> parents) {
		if (!graph.containsKey(node)) {
			return;
		}
		parents.addAll(graph.get(node));
		for (Node n : graph.get(node)) {
			findNumber(n.name, graph, parents);
		}
	}

	private static void printGraph(Map<String, Set<Node>> graph) {
		System.out.println("------------------------------------");
		for (Map.Entry<String, Set<Node>> e : graph.entrySet()) {
			System.out.println(e.getKey());
			System.out.println("\t" + e.getValue());
		}
	}
}
