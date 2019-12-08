package day.d14;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainTwo {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		List<Integer> recipes = new ArrayList<>();

		recipes.add(3);
		recipes.add(7);
		int first = 3;
		int second = 7;
		int firstIndex = 0;
		int secondIndex = 1;

		String end = "165061";

		String endGame = "37";
		
//		int index = 1;
		
		// 59926
		while (true) {
			if(endGame.contains(end)) break;
			if(endGame.length() >= 10) {
				endGame = endGame.substring(endGame.length() - 10);
			}
			//System.out.println(index);
			first = recipes.get(firstIndex);
			second = recipes.get(secondIndex);

			int number = first + second;

			if (number < 10) {
				recipes.add(number);
				endGame += Integer.toString(number);
			} else {
				recipes.add(number / 10);
				endGame += Integer.toString(number/10);
				recipes.add(number % 10);
				endGame += Integer.toString(number%10);

			}
			firstIndex = (firstIndex + first + 1) % recipes.size();
			secondIndex = (secondIndex + second + 1) % recipes.size();
		//	index++;
		}

		System.out.println(endGame);
		System.out.println("velicina: " + (recipes.size()-end.length()));
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
