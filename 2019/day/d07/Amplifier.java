package day.d07;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

public class Amplifier {

	private List<Integer> instructions;
	private int result;

	public Amplifier(List<Integer> instructions) {
		this.instructions = instructions;
	}

	void store(final List<Integer> instructions, int index, int input) {
		instructions.set(instructions.get(index + 1), input);
	}

	void print(final List<Integer> instructions, int index) {
		int a = instructions.get(index + 1);
		a = (instructions.get(index) % 1000) / 100 == 0 ? instructions.get(a) : a;
		result = a;
	}

	void add(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		instructions.set(args.get(2), args.get(0) + args.get(1));
	}

	void multiply(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		instructions.set(args.get(2), args.get(0) * args.get(1));
	}

	int jumpIfTrue(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0) != 0) {
			return args.get(1);
		}
		return index + 3;
	}

	int jumpIfFalse(final List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0) == 0) {
			return args.get(1);
		}
		return index + 3;
	}

	void lessThan(List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0) < args.get(1)) {
			instructions.set(args.get(2), 1);
		} else {
			instructions.set(args.get(2), 0);
		}
	}

	void equals(List<Integer> instructions, int index) {
		List<Integer> args = getArgs(instructions, index);
		if (args.get(0).equals(args.get(1))) {
			instructions.set(args.get(2), 1);
		} else {
			instructions.set(args.get(2), 0);
		}
	}

	List<Integer> getArgs(List<Integer> instructions, int index) {
		List<Integer> args = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			args.add(instructions.get(index + i));
		}
		int a = args.get(0);
		int b = args.get(1);
		args.set(0, (instructions.get(index) % 1000) / 100 == 0 ? instructions.get(a) : a);
		args.set(1, instructions.get(index) / 1000 == 0 ? instructions.get(b) : b);
		return args;
	}

	void runProgram(int[] data) {
		for (int index = 0, i = 0, step = 0; instructions.get(index) != 99; index += step) {
			step = 0;
			int ins = instructions.get(index);
			System.out.println(ins);

			switch (ins % 10) {
			case 1:
				add(instructions, index);
				step = 4;
				break;
			case 2:
				multiply(instructions, index);
				step = 4;
				break;
			case 3:
				store(instructions, index, data[i++]);
				step = 2;
				break;
			case 4:
				print(instructions, index);
				step = 2;
				System.out.println("res: " + result);
				break;
			case 5:
				index = jumpIfTrue(instructions, index);
				break;
			case 6:
				index = jumpIfFalse(instructions, index);
				break;
			case 7:
				lessThan(instructions, index);
				step = 4;
				break;
			case 8:
				equals(instructions, index);
				step = 4;
				break;
			}
			System.out.println(instructions);
		}
	}

	public int getResult() {
		return result;
	}
}
