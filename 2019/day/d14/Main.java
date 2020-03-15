package day.d14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	private static Map<String, Integer> storage = new HashMap<String, Integer>();
	private static Integer numOfORE = 0;
	private static Map<String, Integer> outputChemicals = new HashMap<>();

	public static void main(String[] args) {
		File file = new File("2019/day/d14/input.txt");

		try (Scanner sc = new Scanner(file)) {

			Map<Chemical, List<Chemical>> chemicalReactions = new HashMap<Chemical, List<Chemical>>();
			while (sc.hasNext()) {

				// split to get input and output
				// key = output, value = list of input elements
				String[] chemicals = sc.nextLine().split("=>");
				Chemical output = new Chemical(chemicals[1].trim().split(" "));

				chemicalReactions.put(output, Arrays.stream(chemicals[0].split(",")).map(String::trim)
						.map(c -> new Chemical(c.split(" "))).collect(Collectors.toList()));
				outputChemicals.put(output.getName(), output.getAmount());
			}

			System.out.println(outputChemicals);
			System.out.println("povratna info: " + calculateORE(chemicalReactions, new Chemical("FUEL", 1)));
			System.out.println("ISPIS " + numOfORE);

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static int calculateORE(Map<Chemical, List<Chemical>> chemicalReactions, Chemical chemical) {

		printStorage();
		int sum = 0;

		String chemicalName = chemical.getName();

		// the needed amount of chemical
		int neededAmount = chemical.getAmount();
		System.out.println("Looking for " + chemicalName + "(" + neededAmount + ")");
		System.out.println("needed chemicals: " + chemicalReactions.get(chemical));

		if (storage.containsKey(chemicalName)) {
			int stored = storage.get(chemicalName);
			if (stored >= neededAmount) {
				storage.put(chemicalName, stored - neededAmount);
				return 0;
			} 
//				else {
//				neededAmount -= stored;
//			}
//			storage.put(chemicalName, 0);
		}

		for (Chemical c : chemicalReactions.get(chemical)) {
			if (c.getName().equals("ORE")) {
				int gottenAmount = outputChemicals.get(chemicalName);
				System.out.println("ORE: " + numOfORE);
				System.out.println("gotten: " + gottenAmount);
				System.out.println("needed: " + neededAmount);

				if (gottenAmount >= neededAmount) {
					numOfORE += c.getAmount();
					int amount = gottenAmount - neededAmount;
					storage.put(chemicalName, storage.getOrDefault(chemicalName, 0) + amount);
				}

				return c.getAmount();
			} else {
				int neededChemicalAmount = c.getAmount();
				int chemicalAmountFromRecipe = outputChemicals.get(c.getName());
				Chemical newChemical = new Chemical(c.getName(), chemicalAmountFromRecipe);
				int k = (int) Math.ceil((float) neededChemicalAmount / chemicalAmountFromRecipe);

				System.out.println("Iterations " + k + " for " + newChemical);
				if (k * chemicalAmountFromRecipe > neededChemicalAmount) {
					System.out.println(chemicalAmountFromRecipe + ", " + neededChemicalAmount);
					System.out.println("stavljam u storage ");
					storage.put(c.getName(),
							storage.getOrDefault(c.getName(), 0) + k * chemicalAmountFromRecipe - neededChemicalAmount);
				}
				for (int i = 0; i < k; i++) {
					sum += calculateORE(chemicalReactions, newChemical);
				}
			}
		}

		return sum;
	}

	private static void printStorage() {
		System.out.println("STORAGE: ");
		for (Entry<String, Integer> e : storage.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
		System.out.println("-------------------------------------------------------");
	}
}
