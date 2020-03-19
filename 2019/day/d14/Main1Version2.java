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

		File file = new File("2019/day/d14/input.txt");

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

			System.out.println(getNumfORE(recipes));
//
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static int getNumfORE(List<Recipe> recipes) {
		int numOfORE = 0;
		for (Entry<String, Integer> e : filter(recipes).entrySet()) {
			Recipe r = findRecipe(recipes, e.getKey());
			numOfORE += Math.ceil((double) e.getValue()/r.result.getAmount()) * r.ingredients.get(0).getAmount();
		}
		return numOfORE;
	}

	private static Map<String, Integer> filter(List<Recipe> recipes) {

		Map<String, Integer> necessaryIngredients = new HashMap<>();
		Map<String, Integer> storage = new HashMap<>();
		Queue<Recipe> productionQueue = new LinkedList<>();

		productionQueue.add(findRecipe(recipes, "FUEL"));

		while (!productionQueue.isEmpty()) {

			Recipe currentRecipe = productionQueue.poll();
			String currentResultName = currentRecipe.result.getName();
			Integer currentResultAmount = currentRecipe.result.getAmount();

			if (currentRecipe.ingredients.get(0).getName().equals("ORE")) {
				necessaryIngredients.put(currentResultName,
						necessaryIngredients.getOrDefault(currentResultName, 0) + currentResultAmount);
			} else {

				for (Chemical chemical : currentRecipe.ingredients) {
					int needed = chemical.getAmount();
					String chemicalName = chemical.getName();
					if (storage.containsKey(chemicalName)) {
						int storedValue = storage.get(chemicalName);
						if (storedValue >= needed) {
							storage.put(chemicalName, storedValue - needed);
							continue;
						} else {
							needed -= storedValue;
							storage.put(chemicalName, 0);
						}
					}

					Recipe recipe = findRecipe(recipes, chemicalName);
					while (needed > 0) {
						if (needed < recipe.result.getAmount()) {
							storage.put(chemicalName,
									storage.getOrDefault(chemicalName, 0) + recipe.result.getAmount() - needed);
						}

						productionQueue.add(recipe);
						needed -= recipe.result.getAmount();
					}

				}
			}
		}
		return necessaryIngredients;
	}

	private static Recipe findRecipe(List<Recipe> recipes, String name) {
		return recipes.stream().filter(r -> r.result.getName().equals(name)).collect(Collectors.toList()).get(0);
	}
}
