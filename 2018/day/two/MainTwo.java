package day.two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTwo {
	static int two = 0;
	static int three = 0;
	
	public static void main(String[] args) {
		File file = new File("src/day/two/input.txt");
		List<String> list = new ArrayList<>();

		try (Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				list.add(sc.next());
			}
		} catch (FileNotFoundException e) {
		}
		
		int razlika = 0;
		int index = 0;
		for (String prvi : list) {
			char[] pomJedan = prvi.toCharArray();
			for (String drugi : list) {
				char[] pomDva = drugi.toCharArray();
				for (int i = 0; i < pomDva.length; i++) {
					if(pomJedan[i] != pomDva[i]) {
						razlika++;
						index = i;
					}					
				}
				if(razlika == 1) {
					System.out.println(prvi + " " + drugi);
					String newstr = prvi.substring(0, index) + prvi.substring(index + 1);
					System.out.println("rez: " + index + "\t" + newstr);
					System.exit(0);
				}
				razlika = 0;
			}
		}
		
		
	}
}
