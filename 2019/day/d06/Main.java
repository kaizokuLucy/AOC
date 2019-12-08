package day.d06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		File file = new File("2019/day/d06/input.txt");

		try (Scanner sc = new Scanner(file)) {
			List<Planet> planets = new ArrayList<>();

			while (sc.hasNext()) {
				String[] data = sc.nextLine().split("\\)");
				String planetName = data[0];
				String satelliteName = data[1];

				Planet parent = new Planet(planetName, null);
				Optional<Planet> pom = planets.stream().filter(p -> p.name.equals(planetName)).findFirst();
				if (pom.isPresent()) {
					parent = pom.get();
				} else {
					planets.add(parent);
				}

				Planet satellite = new Planet(satelliteName, parent);
				pom = planets.stream().filter(p -> p.name.equals(satelliteName)).findFirst();
				if (pom.isPresent()) {
					pom.get().setParent(parent);
				} else {
					planets.add(satellite);
				}
			}

			System.out.println(planets.stream().mapToInt(Main::getDistance).sum());

			// PART TWO

			Planet youOrbit = planets.stream().filter(p -> p.name.equals("YOU")).findFirst().get().getParent();
			Planet sanOrbit = planets.stream().filter(p -> p.name.equals("SAN")).findFirst().get().getParent();

			List<Planet> pathYOU = new ArrayList<>();
			getPathToCenter(youOrbit, pathYOU);

			List<Planet> pathSAN = new ArrayList<>();
			getPathToCenter(sanOrbit, pathSAN);

			List<Planet> crossPath = new ArrayList<>(pathYOU);
			crossPath.retainAll(pathSAN);

			System.out.println(pathYOU.size() + pathSAN.size() - 2 * crossPath.size());

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	public static void getPathToCenter(Planet from, List<Planet> path) {
		if (from.getParent() == null) {
			path.add(from);
			return;
		}
		getPathToCenter(from.getParent(), path);
		path.add(from);
	}

	public static List<Planet> getPathToCenter2(Planet from) {
		List<Planet> path = new ArrayList<>();
		path.add(from);
		if (from.getParent() != null) {
			path.addAll(getPathToCenter2(from.getParent()));
		}
		return path;
	}

	public static int getDistance(Planet p) {
		if (p.getParent() == null) {
			return 0;
		}
		return getDistance(p.getParent()) + 1;
	}

	public static class Planet {
		private Planet parent;
		private String name;

		public Planet(String name, Planet parent) {
			this.name = name;
			this.parent = parent;
		}

		public Planet getParent() {
			return parent;
		}

		public String getName() {
			return name;
		}

		public void setParent(Planet parent) {
			this.parent = parent;
		}

		@Override
		public String toString() {
			return "name: " + name;
		}
	}
}
