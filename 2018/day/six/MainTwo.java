package day.six;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTwo {
	public static void main(String[] args) throws IOException {
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
		
		int[][] field = new int[maxY+1][maxX+1];
		for (int i = 0; i <= maxY; i++) {
			for(int j = 0; j <= maxX; j++) {
				field[i][j] = -1;
			}
		}
		int ManDist = 0;
		int sum = 0;
		for(int i = 0; i <= maxY; i++) {
			for(int j = 0; j <= maxX; j++) {
				for(int p = 0; p < list.size(); p++) {
					ManDist += Math.abs(j-listX.get(p)) + Math.abs(i-listY.get(p));
				}
				if(ManDist < 10000) {
					field[i][j] = ManDist;
					sum++;
				}
				ManDist = 0;
			}
		}
		System.out.println(sum);
	}
	
}
