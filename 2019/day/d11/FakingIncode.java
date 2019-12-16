package day.d11;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class FakingIncode {

	public enum State {
		RUNNING, PAUSED, STOPPED;
	}

	private int relativeBase = 0;

	private State state = State.RUNNING;
	private List<Long> instructions;
	private long result;
	private int index = 0;

	Scanner sc;

	public FakingIncode(List<Long> instructions, Scanner sc) {
		this.sc = sc;
		this.instructions = instructions;
		for (int i = 0; i < 1000; i++) {
			instructions.add(0l);
		}
	}

	private void store(final List<Long> instructions, int index, Queue<Integer> data) {
		long userValue;
		if (data == null || data.isEmpty()) {
			System.out.println("read: ");
			userValue = sc.nextLong();
		} else {
			userValue = data.poll();
		}

		int mode = getHundreds(instructions.get(index).intValue());
		long arg1 = instructions.get(index + 1);

		int destination = (int) (mode == 2 ? relativeBase + arg1 : arg1);
		instructions.set(destination, userValue);
	}

	private void print(final List<Long> instructions, int index) {
		state = State.PAUSED;
		int mode = getHundreds(instructions.get(index).intValue());
		long arg = getArgument(instructions, mode, instructions.get(index + 1));
		System.out.println(arg);
		result = arg;
	}

	private void add(final List<Long> instructions, int index) {
		List<Long> args = getArgs(instructions, index);
		instructions.set(args.get(2).intValue(), args.get(0) + args.get(1));
	}

	private void multiply(final List<Long> instructions, int index) {
		List<Long> args = getArgs(instructions, index);
		instructions.set(args.get(2).intValue(), args.get(0) * args.get(1));
	}

	private int jumpIfTrue(final List<Long> instructions, int index) {
		List<Long> args = getArgs(instructions, index);
		if (args.get(0) != 0) {
			return args.get(1).intValue();
		}
		return index + 3;
	}

	private int jumpIfFalse(final List<Long> instructions, int index) {
		List<Long> args = getArgs(instructions, index);
		if (args.get(0) == 0) {
			return args.get(1).intValue();
		}
		return index + 3;
	}

	private void lessThan(List<Long> instructions, int index) {
		List<Long> args = getArgs(instructions, index);
		if (args.get(0) < args.get(1)) {
			instructions.set(args.get(2).intValue(), 1l);
		} else {
			instructions.set(args.get(2).intValue(), 0l);
		}
	}

	private void equals(List<Long> instructions, int index) {
		List<Long> args = getArgs(instructions, index);
		if (args.get(0).equals(args.get(1))) {
			instructions.set(args.get(2).intValue(), 1l);
		} else {
			instructions.set(args.get(2).intValue(), 0l);
		}
	}

	// TODO add num of args and change getHunded....as generic
	private List<Long> getArgs(List<Long> instructions, int opIndex) {
		List<Long> args = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			args.add(instructions.get(opIndex + i));
		}

		int arg1Mode = getHundreds(instructions.get(opIndex).intValue());
		int arg2Mode = getThousands(instructions.get(opIndex).intValue());
		int arg3Mode = get10ks(instructions.get(opIndex).intValue());

		args.set(0, getArgument(instructions, arg1Mode, args.get(0)));
		args.set(1, getArgument(instructions, arg2Mode, args.get(1)));
		args.set(2, arg3Mode == 2 ? relativeBase + args.get(2) : args.get(2));

		return args;
	}

	private long getArgument(List<Long> instructions, int mode, long arg) {
		switch (mode) {
		case 0:
			return instructions.get((int) arg);
		case 1:
			return arg;
		default:
			return instructions.get((int) arg + relativeBase);
		}
	}

	private int getHundreds(int value) {
		return (value % 1000) / 100;
	}

	private int getThousands(int value) {
		return (value % 10000) / 1000;
	}

	private int get10ks(int value) {
		return (value % 100000) / 10000;
	}

	private void adjustRelativeBase(List<Long> instructions, int index) {
		long arg = instructions.get(index + 1);
		int mode = getHundreds(instructions.get(index).intValue());

		relativeBase += getArgument(instructions, mode, arg);
	}

	public void runProgram(Queue<Integer> data) {

		if (state == State.PAUSED) {
			state = State.RUNNING;
		}

		for (int step = 0; state == State.RUNNING; index += step) {
			step = 0;
			int ins = instructions.get(index).intValue();
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
				store(instructions, index, data);
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
			case 9:
				adjustRelativeBase(instructions, index);
				step = 2;
				break;
			case 99:
				state = State.STOPPED;
				break;
			}
		}
	}

	public long getResult() {
		return result;
	}

	public boolean isStopped() {
		return state.equals(State.STOPPED);
	}
}