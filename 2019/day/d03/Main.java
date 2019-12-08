package day.d03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		File file = new File("2019/day/d03/input.txt");

		try {
			Scanner sc = new Scanner(file);

			List<String> wire1 = Arrays.asList(sc.nextLine().split(","));
			List<String> wire2 = Arrays.asList(sc.nextLine().split(","));

			Set<Tuple> wire1Installations = installWire(wire1);
			Set<Tuple> wire2Installations = installWire(wire2);

			Set<Tuple> intersection = new HashSet<>(wire1Installations); // use the copy constructor
			intersection.retainAll(wire2Installations);

			intersection.remove(new Tuple(0, 0));
			System.out
					.println(intersection.stream().map(t -> Math.abs(t.x) + Math.abs(t.y)).min(Integer::compare).get());

			List<Tuple> intersectionList = new ArrayList<>(intersection);
			List<Integer> dList = new ArrayList<>();

			List<Tuple> wire1List = new ArrayList<>(wire1Installations);
			List<Tuple> wire2List = new ArrayList<>(wire2Installations);

			for (Tuple t : intersectionList) {
				dList.add(wire1List.get(wire1List.indexOf(t)).d + wire2List.get(wire2List.indexOf(t)).d);
			}

			System.out.println("distance: " + dList.stream().min(Integer::compare).get());
			
			sc.close();

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static Set<Tuple> installWire(List<String> wire) {

		Tuple current = new Tuple(0, 0);
		Set<Tuple> coordinates = new HashSet<>();

		int dist = 0;

		for (String s : wire) {
			int dst = Integer.parseInt(s.substring(1));
			char direction = s.charAt(0);

			for (int i = 0; i < dst; i++) {
				switch (direction) {
				case 'R':
					current.x += 1;
					break;
				case 'L':
					current.x -= 1;
					break;
				case 'U':
					current.y += 1;
					break;
				case 'D':
					current.y -= 1;
					break;
				}
				coordinates.add(new Tuple(current.x, current.y, ++dist));
			}
		}
		return coordinates;
	}

	public static class Tuple {
		private int x;
		private int y;
		private int d;

		public Tuple(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Tuple(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tuple other = (Tuple) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "x: " + x + ", y: " + y + ", d: " + d;
		}

	}
}
