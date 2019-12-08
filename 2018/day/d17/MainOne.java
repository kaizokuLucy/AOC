package day.d17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MainOne {

	public static void main(String[] args) {
		File file = new File("src/day/d17/inputPr.txt");

		List<String> yList = new ArrayList<>();
		Map<Integer, List<String>> input = new HashMap<>();
		
		
		Set<Tuple> points = new LinkedHashSet<>();

		try (Scanner sc = new Scanner(file)) {
			String line = "";
			String var[] = new String[2];
			int pom[];
			while (sc.hasNext()) {
				line = sc.nextLine().trim();
				
				
				if (line.startsWith("x")) {
					// remove everything that is not a number, comma or full stop
					line = line.replaceAll("[^0-9,.]", "");
					var = line.split(",");				
					pom = Arrays.asList(var[1].split("\\..")).stream().mapToInt(Integer::parseInt).toArray();
					for (int i = pom[0]; i <= pom[1]; i++) {
						points.add(new Tuple(Integer.parseInt(var[0]), i));
					}					
				} else if (line.startsWith("y")) {
					// remove everything that is not a number, comma or full stop
					line = line.replaceAll("[^0-9,.]", "");
					var = line.split(",");				
					pom = Arrays.asList(var[1].split("\\..")).stream().mapToInt(Integer::parseInt).toArray();
					for (int i = pom[0]; i <= pom[1]; i++) {
						points.add(new Tuple(i, Integer.parseInt(var[0])));
					}	
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (Tuple s : points) {
			System.out.println(s.toString());
		}
		
		int maxX = points.stream().max(Comparator.comparingInt(Tuple::getX)).get().getX();
		int maxY = points.stream().max(Comparator.comparingInt(Tuple::getY)).get().getY();
		
		System.out.println("maxX: " + maxX + ", maxY: " + maxY);
		
		String field[][] = new String[maxY+1][maxX+1];
		Arrays.stream(field).forEach(e -> Arrays.fill(e, "."));

		field[0][500] = "+";
		
		for (Tuple t : points) {
			field[t.getY()][t.getX()] = "#";
		}
		
		for (int i = 0; i <= maxY; i++) {
			for (int j = 0; j <= maxX; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}

		
		/*	
		int maxY = Collections.max(input.values().stream().flatMap(x -> x.stream()).map(s -> s.split("\\..")[1]).map(Integer::parseInt).collect(Collectors.toList()));
		
		System.out.println(maxY);
		int maxX = Collections.max(input.keySet().stream().collect(Collectors.toList()));
		System.out.println(maxX);
		

		int minY = Collections.min(input.values().stream().flatMap(x -> x.stream()).map(s -> s.split("\\..")[0]).map(Integer::parseInt).collect(Collectors.toList()));
		
		System.out.println(minY);
		int minX = Collections.min(yList.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList()));
		System.out.println(minX);
		*/
	}
}
