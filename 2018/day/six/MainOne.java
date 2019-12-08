package day.six;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MainOne {
	public static void main(String[] args) {
		File file = new File("src/day/six/input.txt");
		List<String> list = new ArrayList<>();
		List<Integer> listX = new ArrayList<>();
		List<Integer> listY = new ArrayList<>();

		String line;
		String[] pom;
		int maxX = 0, maxY = 0;
		try (Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				line = sc.nextLine();
				list.add(line);
				line = line.replaceAll(",", "");
				pom = line.split(" ");
				listX.add(Integer.parseInt(pom[0]));
				listY.add(Integer.parseInt(pom[1]));
				maxX = Math.max(maxX, Integer.parseInt(pom[0]));
				maxY = Math.max(maxY, Integer.parseInt(pom[1]));
			}
		} catch (FileNotFoundException e) {
		}
		
		System.out.println(maxX + " " + maxY);
		
		int[][] field = new int[maxY+1][maxX+1];
		
		for (int i = 0; i <= maxY; i++) {
			for(int j = 0; j <= maxX; j++) {
				field[i][j] = -1;
			}
		}
		boolean flag = false;
		for(int i = 0; i <= maxY; i++) {
			for(int j = 0; j <= maxX; j++) {
				int min = Math.max(maxX, maxY);
				int minIndex = Math.max(maxX, maxY);
				for(int p = 0; p < list.size(); p++) {
					int ManDist = Math.abs(j-listX.get(p)) + Math.abs(i-listY.get(p));
					if (ManDist < min) {
						min = ManDist;
						minIndex = p;
						flag = false;
					} else if (ManDist == min) {
						flag = true;
						field[i][j] = -2;
					}
				}
				if(!flag) {
					field[i][j] = minIndex;
				}
			}
		}
//		for (int i = 0; i <= maxY; i++) {
//			for(int j = 0; j <= maxX; j++) {
//				System.out.printf("%3d", field[i][j]);
//			}
//			System.out.println();
//		}
		Set<Integer> setEliminated = new HashSet<>();
		for(int j = 0; j <= maxX; j++) {
			setEliminated.add(field[0][j]);
		}
		for(int j = 0; j <= maxX; j++) {
			setEliminated.add(field[maxY][j]);
		}
		for(int i = 0; i <= maxY; i++) {
			setEliminated.add(field[i][0]);
		}
		for(int i = 0; i <= maxY; i++) {
			setEliminated.add(field[i][maxX]);
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i <= maxY; i++) {
			for(int j = 0; j <= maxX; j++) {
				if(field[i][j] == -2 || setEliminated.contains(field[i][j])) continue;
				else {
					if(map.containsKey(field[i][j])) {
						map.put(field[i][j], map.get(field[i][j]) + 1);
					} else {
						map.put(field[i][j], 1);
					}
				}
			}
		}
		int maxK = map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		System.out.println(maxK);
		System.out.println(map.get(maxK));

	}
}
