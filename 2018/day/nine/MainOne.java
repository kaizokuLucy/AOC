package day.nine;

import java.util.ArrayList;
import java.util.List;

public class MainOne {
	public static void main(String[] args) {
		List<Integer> circle = new ArrayList<>();

		int marble = 1;
		int numOfPlayers = 404;
		int[] score = new int[numOfPlayers];
		circle.add(0);
		circle.add(1);
		int current = 1;
		int i = 2;
		loop: while (true) {
			for (; i < numOfPlayers; i++) {
				++marble;
				if (marble > 7185200)
					break loop;
				System.out.println(marble);
				if (marble % 23 == 0) {
					score[i] += marble;
					int index = ((circle.indexOf(current) - 7) + circle.size()) % circle.size();
					score[i] += circle.get(index);
					current = circle.get(index + 1);
					circle.remove(index);

				} else {
					circle.add((((circle.indexOf(current) + 1) % circle.size()) + 1), marble);
					current = marble;
				}
			}
			i = 0;
		}
		int max = 0;
		for (int p = 0; p < score.length; p++) {
			if (score[p] > max) {
				max = score[p];
			}
		}
		System.out.println("max:" + max);
	}
}
