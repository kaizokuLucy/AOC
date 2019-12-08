package day.d13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainTwo {

	public static void main(String[] args) {
		File file = new File("src/day/d13/input.txt");
		List<String> mineMap = new ArrayList<>();
		List<Cart> carts = new ArrayList<>();

		String line = "";
		int y = 0;
		try (Scanner sc = new Scanner(file)) {
			Pattern pattern = Pattern.compile("([<>^v])");
			Matcher matcher;
			while (sc.hasNext()) {
				line = sc.nextLine();

				matcher = pattern.matcher(line);
				while (matcher.find()) {
					// < = 0; > = 1; ^ = 2; v = 3
					String s = matcher.group();
					switch (s) {
					case "<":
						carts.add(new Cart('<', 0, matcher.start(), y));
						break;
					case ">":
						carts.add(new Cart('>', 0, matcher.start(), y));
						break;
					case "^":
						carts.add(new Cart('^', 0, matcher.start(), y));
						break;
					default:
						carts.add(new Cart('v', 0, matcher.start(), y));
						break;
					}
				}

				line = line.replaceAll("[<>]", "-");
				line = line.replaceAll("[v^]", "|");
				mineMap.add(line);
				// System.out.println(line);
				y++;
			}
		} catch (FileNotFoundException e) {
		}

		List<Cart> dead = new ArrayList<>();
		loop: 
		while (true) {
			dead.clear();
			if(carts.size() == 1) {
				System.out.println(carts.get(0).toString());
				break loop;
			}
			
			System.out.println("carts: " + carts.size());
			carts.sort((Cart c1, Cart c2) -> c1.x - c2.x);
			carts.sort((Cart c1, Cart c2) -> c1.y - c2.y);
			
			for (Cart c : carts) {
				c.nextMove(mineMap.get(c.y).charAt(c.x));
				for (Cart c2 : carts) {
					if (c.equals(c2))
						continue;
					else if (c.x == c2.x && c.y == c2.y) {
						System.out.println("SUDAAAAAAAAAAAAR");
						System.out.println("x: " + c.x + " y: " + c.y);
						dead.add(c);
						dead.add(c2);
					}
				}
			}
			
			for(Cart d : dead) {
				carts.remove(d);
			}
		}
	}
}
