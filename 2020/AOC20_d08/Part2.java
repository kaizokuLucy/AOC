package AOC20_d08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
	private static int accumulator = 0;
	private static int PC = 0;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d08\\input.txt"))) {

			List<Instruction> instructions = new ArrayList<>();
			while(sc.hasNext()) {
				String[] inst = sc.nextLine().split(" ");
				instructions.add(new Instruction(inst[0], Integer.parseInt(inst[1])));
			}

			Set<Integer> indexes = new HashSet<>();
			int size = instructions.size();
			int i = 0;
			loop:
			while (true) {
				accumulator = 0;
				PC = 0;
				List<Instruction> instructionsCopy = new ArrayList<>();
				for (Instruction inst : instructions) {
					instructionsCopy.add(new Instruction(inst.getOperation(), inst.getArgument()));
				}
				if (instructions.get(i).getOperation().equals("nop")) {
					instructionsCopy.get(i).setOperation("jmp");
				} else if (instructions.get(i).getOperation().equals("jmp")) {
					instructionsCopy.get(i).setOperation("nop");
				} else {
					i++;
					continue;
				}
				while(true) {
					if (PC == size) {
						System.out.println(accumulator);
						break loop;
					}
					if (!indexes.add(PC)) {
						indexes.clear();
						i++;
						continue loop;
					}
					calculate(instructionsCopy);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void calculate(List<Instruction> instructions) {
		Instruction inst = instructions.get(PC);
		switch (inst.getOperation()) {
			case "nop":
				PC += 1;
				break;
			case "acc":
				accumulator += inst.getArgument();
				PC += 1;
				break;
			case "jmp":
				PC += inst.getArgument();
				break;
		}
	}
}
