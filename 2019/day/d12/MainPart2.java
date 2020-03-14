package day.d12;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainPart2 {
	/* 
	 * moons travel in a patter
	 * the result is the least common multiple of X, Y, Z coordinates for all the moons
	 * if LCM is 0, it means that the step wasn't big enough
	 * X = number how long the pattern is for a moon's x coordinates
	 * Y = number how long the pattern is for a moon's y coordinates
	 * Z = number how long the pattern is for a moon's z coordinates
	 * */

	public static void main(String[] args) {
		File file = new File("2019/day/d12/input.txt");

		try (Scanner sc = new Scanner(file)) {

			List<Moon> moons = new ArrayList<>();
			while (sc.hasNext()) {
				moons.add(createMoon(sc.nextLine()));
			}

			List<Moon> moon1 = new ArrayList<>();
			List<Moon> moon2 = new ArrayList<>();
			List<Moon> moon3 = new ArrayList<>();
			List<Moon> moon4 = new ArrayList<>();

			for (int step = 0; step < 1000000; step++) {

				applyGravity(moons);
				applyVelocity(moons);

				moon1.add(new Moon(moons.get(0)));
				moon2.add(new Moon(moons.get(1)));
				moon3.add(new Moon(moons.get(2)));
				moon4.add(new Moon(moons.get(3)));
			}

			try {
				System.out.println("moon1: ");
				calculateLCM(moon1);
				System.out.println("moon2: ");
				calculateLCM(moon2);
				System.out.println("moon3: ");
				calculateLCM(moon3);
				System.out.println("moon4: ");
				calculateLCM(moon4);
				
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
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

	private static void calculateLCM(List<Moon> moon) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		int moonXLCM = patternSize(moon, Moon.class.getMethod("getPositionX"));
		int moonYLCM = patternSize(moon, Moon.class.getMethod("getPositionY"));
		int moonZLCM = patternSize(moon, Moon.class.getMethod("getPositionZ"));

		System.out.println("X: " + moonXLCM);
		System.out.println("Y: " + moonYLCM);
		System.out.println("Z: " + moonZLCM);
		
		System.out.println("LCM: " + lcm(lcm(moonXLCM, moonYLCM), moonZLCM));
	}

	private static int patternSize(List<Moon> moon, Method method)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int start = 0;
		for (int j = 1; j < moon.size(); j++) {
			if ((int) method.invoke(moon.get(start)) == (int) method.invoke(moon.get(j))) {
				start++;
			} else {
				start = 0;
			}
			if (start == 10) {
				return j - start + 1;
			}
		}
		return 0;
	}
	public static long lcm(long number1, long number2) {
	    if (number1 == 0 || number2 == 0) {
	        return 0;
	    }
	    long max = Math.max(number1, number2);
	    long min = Math.min(number1, number2);
	    long lcm = max;
	    while (lcm % min != 0) {
	        lcm += max;
	    }
	    return lcm;
	}
}