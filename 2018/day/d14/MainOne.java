package day.d14;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class MainOne {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		List<Integer> recipes = new LinkedList<>();
		
		PrintWriter writer = new PrintWriter("src/day/d14/output.txt", "UTF-8");

		recipes.add(3);
		recipes.add(7);
		int first = 3;
		int second = 7;
		int firstIndex = 0;
		int secondIndex = 1;
		
		int end = 165061;
		
		while (recipes.size() <= (end + 10)) {
			
			
			first = recipes.get(firstIndex);
			second = recipes.get(secondIndex);
				
			int number = first + second;
			if (number < 10) {
				recipes.add(number);
			} else {
		        recipes.add(number / 10);
		        recipes.add(number % 10);
		    }
			firstIndex = (firstIndex + first + 1) % recipes.size();
			secondIndex = (secondIndex + second + 1) % recipes.size();
			
		}
		
		writer.write(recipes.toString() + "\n");
		
		for(int i = end; i < (end + 10); i++) {
			System.out.print(recipes.get(i));
		}
		
		writer.close();
	}

	static List<Integer> getDigits(int number) {
		List<Integer> num = new LinkedList<>();
		while (number > 0) {
			num.add(0, number % 10);
			number = number / 10;
		}
		return num;
	}
}
