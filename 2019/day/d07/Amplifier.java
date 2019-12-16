package day.d07;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Amplifier {

	public enum State {
		RUNNING, PAUSED, STOPPED;
	}

	private State state = State.RUNNING;
	private List<Integer> instructions;
	private int result;
	private int index = 0;

	public Amplifier(List<Integer> instructions) {
		this.instructions = instructions;
	}

	void store(final List<Integer> instructions, int index, int input) {
		instructions.set(instructions.get(index + 1), input);
	}

	private void print(final List<Integer> instructions, int index) {
		state = State.PAUSED;
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

	void runProgram(Queue<Integer> data) {
		if(state == State.PAUSED) {
			state = State.RUNNING;
		}
		for (int step = 0; state == State.RUNNING; index += step) {
			step = 0;
			int ins = instructions.get(index);
			switch (ins % 100) {
			case 1:
				add(instructions, index);
				step = 4;
				break;
			case 2:
				multiply(instructions, index);
				step = 4;
				break;
			case 3:
				store(instructions, index, data.poll());
				step = 2;
				break;
			case 4:
				print(instructions, index);
				step = 2;
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
			case 99:
				state = State.STOPPED;
				break;
			}
		}
	}

	public int getResult() {
		return result;
	}

	public State getState() {
		return state;
	}
	
	public List<Integer> getInstructions() {
		return instructions;
	}
}
