package day.d12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		File file = new File("2019/day/d12/input.txt");

		try (Scanner sc = new Scanner(file)) {

			List<Moon> moons = new ArrayList<>();
			while (sc.hasNext()) {
				moons.add(createMoon(sc.nextLine()));
			}

			for (int cnt = 0; cnt < 1000; cnt++) {
				applyGravity(moons);
				applyVelocity(moons);
			}

			System.out.println(calculateTotalEnergy(moons));

		} catch (FileNotFoundException e) {
			System.out.println("file no existy");
		}
	}

	private static Moon createMoon(String data) {
		data = data.replaceAll("[^-0-9]+", " ");
		return new Moon(Arrays.stream(data.trim().split(" ")).mapToInt(Integer::parseInt).toArray());
	}

	private static void applyGravity(List<Moon> moons) {

		for (Moon m1 : moons) {
			for (Moon m2 : moons) {
				setVelocity(m1, m2);
			}
		}
	}

	private static void setVelocity(Moon moon1, Moon moon2) {
		if (moon1.getPositionX() < moon2.getPositionX()) {
			moon1.setVelocityX(moon1.getVelocityX() + 1);
			moon2.setVelocityX(moon2.getVelocityX() - 1);
		}

		if (moon1.getPositionY() < moon2.getPositionY()) {
			moon1.setVelocityY(moon1.getVelocityY() + 1);
			moon2.setVelocityY(moon2.getVelocityY() - 1);
		}

		if (moon1.getPositionZ() < moon2.getPositionZ()) {
			moon1.setVelocityZ(moon1.getVelocityZ() + 1);
			moon2.setVelocityZ(moon2.getVelocityZ() - 1);
		}
	}

	private static void applyVelocity(List<Moon> moons) {

		for (Moon m : moons) {
			m.setPositionX(m.getPositionX() + m.getVelocityX());
			m.setPositionY(m.getPositionY() + m.getVelocityY());
			m.setPositionZ(m.getPositionZ() + m.getVelocityZ());
		}
	}

	private static int calculateTotalEnergy(List<Moon> moons) {

		int totalEnergy = 0;
		for (Moon m : moons) {
			totalEnergy += (getPotEnergy(m) * getKinEnergy(m));
		}
		return totalEnergy;
	}

	private static int getPotEnergy(Moon m) {
		return Math.abs(m.getPositionX()) + Math.abs(m.getPositionY()) + Math.abs(m.getPositionZ());
	}

	private static int getKinEnergy(Moon m) {
		return Math.abs(m.getVelocityX()) + Math.abs(m.getVelocityY()) + Math.abs(m.getVelocityZ());
	}
}
