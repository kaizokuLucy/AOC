package day.nine;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MainTwo {
	public static void main(String[] args) {
		List<Integer> circle = new LinkedList<>();

		int numOfPlayers = 404;
		int maxMarble = 7185200;
		long[] score = new long[numOfPlayers];
		circle.add(0);
		circle.add(1);

		ListIterator<Integer> it = circle.listIterator();
		it.next();

		
		for (int i = 2, marble = 2; marble < maxMarble; i = (i + 1) % numOfPlayers, ++marble) {

			if (marble % 23 == 0) {
				for(int j = 0; j < 7; j++) {
					if(!it.hasPrevious()) {
						it = circle.listIterator(circle.size());
					}
					it.previous();
				}
				
				score[i] += it.next() + marble;
				it.previous();
				it.remove();				

			} else {				
				for(int j = 0; j < 2; j++) {				
					if(!it.hasNext()) {
						it = circle.listIterator();
					} 
					it.next();
				}
				it.add(marble);
				it.previous();
			}
		}
		
		long max = 0L;
		for (int p = 0; p < score.length; p++) {
			if (score[p] > max) {
				max = score[p];
			}
		}
		System.out.println("max:" + max);
	}
}
