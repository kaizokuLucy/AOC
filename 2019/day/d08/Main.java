package day.d08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		File file = new File("2019/day/d08/input.txt");

		final int width = 25;
		final int height = 6;
		final int layerSize = width * height;

		try {
			Scanner sc = new Scanner(file);
			List<String> input = Arrays.asList(sc.nextLine().split(""));
			sc.close();

			Map<Integer, List<String>> layers = new LinkedHashMap<>();
			for (int i = 0; i < input.size(); i += layerSize) {
				layers.computeIfAbsent(i / layerSize, ArrayList::new).addAll(input.subList(i, i + layerSize));
			}

			Entry<Integer, List<String>> minLayer = Collections.min(layers.entrySet(), 
					(e1,e2) -> (int) e1.getValue().stream().filter(v -> v.equals("0")).count() - (int) e2.getValue().stream().filter(v -> v.equals("0")).count());
			
			long oneNum = minLayer.getValue().stream().filter(v -> v.equals("1")).count();
			long twoNum = minLayer.getValue().stream().filter(v -> v.equals("2")).count();
			System.out.println(oneNum * twoNum);
			

			// PART TWO
			List<String> firstLayer = layers.get(0);
			List<String> res = new ArrayList<>();
			/*for (List<String> layer : layers.values()) {
				for (int i = 0; i < firstLayer.size(); i++) {
					firstLayer.set(i, firstLayer.get(i).equals("2") ? layer.get(i) : firstLayer.get(i));
				}
			}
			*/
			
			for (int i = 0; i < firstLayer.size(); i++) {
				if (firstLayer.get(i).equals("2")) {
					for (int l = 1; l < layers.size(); l++) {
						if (!layers.get(l).get(i).equals("2")) {
							res.add(layers.get(l).get(i));
							break;
						}
					}
				} else {
					res.add(firstLayer.get(i));
				}
			}

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					System.out.print((res.get(i * width + j).equals("1")) ? "*" : " ");
				}
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}
}
