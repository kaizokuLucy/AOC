package day.d14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main1Version2 {

	public static void main(String[] args) {

		File file = new File("2019/day/d14/input1.txt");

		try (Scanner sc = new Scanner(file)) {

			Map<String, Integer> outputChemicals = new HashMap<>();
			List<Recipe> recipes = new ArrayList<>();

			while (sc.hasNext()) {

				// split to get input and output
				// key = output, value = list of input elements
				String[] chemicals = sc.nextLine().split("=>");
				Chemical output = new Chemical(chemicals[1].trim().split(" "));

				recipes.add(new Recipe(Arrays.stream(chemicals[0].split(",")).map(String::trim)
						.map(c -> new Chemical(c.split(" "))).collect(Collectors.toList()), output));

			}
			for (Recipe r : recipes) {
				System.out.println(r.toString());
			}
			Set<String> filteredChemicals = new HashSet<>();
//			filter(chemicalReactions, outputChemicals, filteredChemicals);
//			System.out.println("rez: " + group(chemicalReactions, outputChemicals, filteredChemicals));
//
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static int group(Map<Chemical, List<Chemical>> chemicalReactions, Map<String, Integer> outputChemicals,
			Set<String> filteredChemicals) {

		Map<String, Integer> necessaryChemical = new HashMap<>();
//		Map<String, Integer> storage = new HashMap<>();

		Queue<Chemical> productionQueue = new LinkedList<Chemical>();
		productionQueue.add(new Chemical("FUEL", 1));

		while (!productionQueue.isEmpty()) {
			printNecessaryChemicals(necessaryChemical);

			Chemical currentlyPreparing = productionQueue.poll();
			System.out.println("Current: " + currentlyPreparing);

			for (Chemical chemical : chemicalReactions.get(currentlyPreparing)) {

//				if (storage.containsKey(chemical.getName())) {
//					if (storage.get(chemical.getName()) >= chemical.getAmount()) {
//						storage.put(chemical.getName(), storage.get(chemical.getName()) - chemical.getAmount());
//						continue;
//					}
//				}
//				
				System.out.println("chemical: " + chemical);

				String ingredient = chemical.getName();

				if (filteredChemicals.contains(ingredient)) {

					necessaryChemical.put(ingredient, necessaryChemical.getOrDefault(ingredient, 0)
							+ currentlyPreparing.getAmount() * chemical.getAmount());
				} else {
					productionQueue.add(chemical);
				}
			}
		}

		return calculateORE(necessaryChemical, chemicalReactions, outputChemicals);

	}

	private static void filter(Map<Chemical, List<Chemical>> chemicalReactions, Map<String, Integer> outputChemicals,
			Set<String> filteredChemicals) {

		Queue<Chemical> productionQueue = new LinkedList<Chemical>();
		productionQueue.add(new Chemical("FUEL", 1));

		while (!productionQueue.isEmpty()) {
			Chemical currentlyPreparing = productionQueue.poll();

			for (Chemical chemical : chemicalReactions.get(currentlyPreparing)) {
				if (chemical.getName().equals("ORE")) {
					filteredChemicals.add(currentlyPreparing.getName());
				} else {
					productionQueue.add(chemical);
				}
			}
		}
	}

	private static int calculateORE(Map<String, Integer> necessaryChemical,
			Map<Chemical, List<Chemical>> chemicalReactions, Map<String, Integer> outputChemicals) {
		System.out.println("***************");
		int oreNum = 0;
		for (Entry<String, Integer> ingredient : necessaryChemical.entrySet()) {
			System.out.println(ingredient);
//			System.out.println("brojnik: " + ingredient.getValue()); 
//			System.out.println("nazivnik: " + outputChemicals.get(ingredient.getKey()));
//			System.out.println("prva zagrada: " + Math.ceil(ingredient.getValue() / outputChemicals.get(ingredient.getKey())));
//			System.out.println("mnozitelj: "+ chemicalReactions.get(new Chemical(ingredient.getKey(), 0)).get(0).getAmount());
			double num = Math.ceil((double) ingredient.getValue() / outputChemicals.get(ingredient.getKey()))
					* chemicalReactions.get(new Chemical(ingredient.getKey(), 0)).get(0).getAmount();
			System.out.println("dodajem: " + num);
			oreNum += (int) num;

		}

		return oreNum;
	}

	private static void printStorage(Map<String, Integer> storage) {
		System.out.println("STORAGE:");
		for (Entry<String, Integer> e : storage.entrySet()) {
			System.out.println(e.getKey() + " -------- " + e.getValue());
		}
	}

	private static void printNecessaryChemicals(Map<String, Integer> necessaryChemical) {
		System.out.println("*************************************");
		System.out.println("Necessary Chemicals:");
		for (Entry<String, Integer> e : necessaryChemical.entrySet()) {
			System.out.println(e.getKey() + " -------- " + e.getValue());
		}
		System.out.println("*************************************");
	}
}
