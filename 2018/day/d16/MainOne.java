package day.d16;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainOne {

	public static void main(String[] args) {
		// 547 ili 529 ili 549 too low
		File file = new File("src/day/d16/input.txt");
		String line = "";
		List<Register> before = new ArrayList<>();
		List<Integer> ins = new ArrayList<>();
		List<Register> after = new ArrayList<>();
		
		int count = 0;
		int countOpNum = 0;
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNext() && count <= 1) {
				line = sc.nextLine().trim();
				if(line.isEmpty()) {
					count++;
					continue;
				} 
				count = 0;
				line = line.replaceAll("[^0-9]+", " ");
				before = Arrays.stream(line.split(" ")).filter(p -> !p.isEmpty()).map(Integer::parseInt).map(Register::new).collect(Collectors.toList());
				
				line = sc.nextLine();
				ins = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

				line = sc.nextLine();
				line = line.replaceAll("[^0-9]+", " ");
				after = Arrays.stream(line.split(" ")).filter(p -> !p.isEmpty()).map(Integer::parseInt).map(Register::new).collect(Collectors.toList());

				if(findNumOfOp(before, ins, after) >= 3 ) {
					countOpNum++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Broj operacija: " + countOpNum);
	}

	private static int findNumOfOp(List<Register> original, List<Integer> ins, List<Register> after) {

		Methods m = new Methods();
		int afterValue = after.get(ins.get(3)).getValue();
		int count = 0;
		for(Method method : m.getClass().getDeclaredMethods()) {
			List<Register> before = original.stream().map(Register::new).collect(Collectors.toList());
			try {
				method.invoke(m, ins.get(0), before, ins.get(1), ins.get(2), ins.get(3));
				if(afterValue == before.get(ins.get(3)).getValue()) {
					count++;
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
