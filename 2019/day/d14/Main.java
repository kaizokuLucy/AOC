package day.d14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	// class whose solution in calculated with a recursion 
	
	private static Map<String, Integer> storage = new HashMap<String, Integer>();
	private static Map<String, Integer> outputChemicals = new HashMap<>();

	public static void main(String[] args) {
		File file = new File("2019/day/d14/input2.txt");

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
			
			System.out.println(calculateORE(chemicalReactions, new Chemical("FUEL", 1)));
			
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static int calculateORE(Map<Chemical, List<Chemical>> chemicalReactions, Chemical chemical) {
		int sum = 0;
		String chemicalName = chemical.getName();
		int neededAmount = chemical.getAmount();

		if (storage.containsKey(chemicalName)) {
			int stored = storage.get(chemicalName);
			if (stored >= neededAmount) {
				storage.put(chemicalName, stored - neededAmount);
				return 0;
			} 
		}

		for (Chemical c : chemicalReactions.get(chemical)) {
			if (c.getName().equals("ORE")) {
				int gottenAmount = outputChemicals.get(chemicalName);
				if (gottenAmount >= neededAmount) {
					int amount = gottenAmount - neededAmount;
					storage.put(chemicalName, storage.getOrDefault(chemicalName, 0) + amount);
				}
				return c.getAmount();
			} else {
				int neededChemicalAmount = c.getAmount();
				int chemicalAmountFromRecipe = outputChemicals.get(c.getName());
				Chemical newChemical = new Chemical(c.getName(), chemicalAmountFromRecipe);
				int k = (int) Math.ceil((float) neededChemicalAmount / chemicalAmountFromRecipe);

				if (k * chemicalAmountFromRecipe > neededChemicalAmount) {
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

	/*private static void printStorage() {
		System.out.println("STORAGE: ");
		for (Entry<String, Integer> e : storage.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
		System.out.println("-------------------------------------------------------");
	}*/
}
