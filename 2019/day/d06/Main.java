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

		try(Scanner sc = new Scanner(file)) {
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
			
		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	public static int getDistance(Planet p) {
		if (p.parent == null) {
			return 0;
		}
		return getDistance(p.parent) + 1;
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
	}
}
