package day.d16;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MainTwo {

	static Map<Integer, String> opMap = new TreeMap<>();

	public static void main(String[] args) {

		File file = new File("src/day/d16/input.txt");
		String line = "";
		List<Register> before = new ArrayList<>();
		List<Integer> ins = new ArrayList<>();
		List<Register> after = new ArrayList<>();
		List<String> input = new ArrayList<>();
		List<String> operations = new ArrayList<>();

		try (Scanner sc = new Scanner(file)) {
			int count = 0;
			while (sc.hasNext()) {
				if (count != 3) {
					line = sc.nextLine().trim();
					if (line.isEmpty()) {
						count++;
						continue;
					}
					count = 0;
					input.add(line);
				} else {
					line = sc.nextLine();
					operations.add(line);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (opMap.size() < 16) {
			Iterator<String> it = input.iterator();
			while (it.hasNext()) {
				line = it.next().trim();
				if (line.isEmpty()) {
					continue;
				}
				line = line.replaceAll("[^0-9]+", " ");
				before = Arrays.stream(line.split(" ")).filter(p -> !p.isEmpty()).map(Integer::parseInt)
						.map(Register::new).collect(Collectors.toList());

				line = it.next();
				ins = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

				line = it.next();
				line = line.replaceAll("[^0-9]+", " ");
				after = Arrays.stream(line.split(" ")).filter(p -> !p.isEmpty()).map(Integer::parseInt)
						.map(Register::new).collect(Collectors.toList());

				SimpleEntry<Integer, String> res = (SimpleEntry<Integer, String>) findNumOfOp(before, ins, after);
				if (res != null) {
					System.out.println(res.getKey() + " " + res.getValue());
					opMap.put(res.getKey(), res.getValue());
				} else {
					continue;
				}
			}
		}
		int res = calculate(operations);
		System.out.println("rezultat je: " + res);
	}

	private static int calculate(List<String> operations) {
		Register A = new Register(0);
		Register B = new Register(0);
		Register C = new Register(0);
		Register D = new Register(0);

		List<Integer> ins = new ArrayList<>();

		Methods m = new Methods();

		List<Register> registers = new ArrayList<>();
		registers.add(A);
		registers.add(B);
		registers.add(C);
		registers.add(D);

		Iterator<String> it = operations.iterator();
		while (it.hasNext()) {
			String line = it.next();
			ins = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
			try {
				System.out.println(opMap.get(ins.get(0)));
		        Class<?>[] paramTypes = {int.class, List.class, int.class, int.class, int.class};
				Method method = m.getClass().getMethod(opMap.get(ins.get(0)), paramTypes);
				try {
					method.invoke(m, ins.get(0), registers, ins.get(1), ins.get(2), ins.get(3));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}

			} catch (SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		return registers.get(0).getValue();
	}

	private static Entry<Integer, String> findNumOfOp(List<Register> original, List<Integer> ins,
			List<Register> after) {

		Methods m = new Methods();
		int afterValue = after.get(ins.get(3)).getValue();
		int count = 0;
		String name = "";
		for (Method method : m.getClass().getDeclaredMethods()) {
			List<Register> before = original.stream().map(Register::new).collect(Collectors.toList());
			try {
				method.invoke(m, ins.get(0), before, ins.get(1), ins.get(2), ins.get(3));
				if (afterValue == before.get(ins.get(3)).getValue()) {
					if (!opMap.containsValue(method.getName())) {
						name = method.getName();
						count++;
					}
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		if (count == 1) {
			return new AbstractMap.SimpleEntry<Integer, String>(ins.get(0), name);
		}
		return null;
	}
}
