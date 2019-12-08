package day.four;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class MainOne {

	public static void main(String[] args) {
		File file = new File("src/day/four/input.txt");
		List<String> list = new ArrayList<>();
		Map<String, int[]> map = new HashMap<>();
		
		try (Scanner sc = new Scanner(file)){
			while(sc.hasNext()) {
				list.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
		}
		
		Collections.sort(list);
		String id = "";
		
		Map<String, Integer> totalSleepTime = new HashMap<>();
		
		int asleepTime = 0, awakeTime = 0;
		for(String s : list) {
			Pattern pattern = Pattern.compile("#\\d+");
			Matcher m = pattern.matcher(s);
			// find id
			if (m.find()) {
				id = m.group();
				map.putIfAbsent(id, new int[60]);
			} 
			// get sleep period
			else {
				if(s.contains("asleep")) {
					asleepTime = Integer.parseInt(s.substring(s.indexOf(":")+1, s.indexOf(":")+3));					
				} else {
					awakeTime = Integer.parseInt(s.substring(s.indexOf(":")+1, s.indexOf(":")+3));
					int[] pom = map.get(id);
					for(int i = asleepTime; i < awakeTime; i++) {
						pom[i] += 1;
					}
					totalSleepTime.put(id, totalSleepTime.getOrDefault(id, 0) + (awakeTime - asleepTime));
				}
			}
		}
		
		id = Collections.max(totalSleepTime.entrySet(), Map.Entry.comparingByValue()).getKey();
		OptionalInt maxAsleepMinute = IntStream.of(map.get(id)).max();
		
		System.out.println("Sum: " + maxAsleepMinute.getAsInt());
		System.out.println(id);
		int index = 0;
		int[] times = map.get(id);
		for (int i = 0; i < times.length; i++) {
			if(times[i] == maxAsleepMinute.getAsInt()) {
				index = i;
				break;
			}
		}
		id = id.replaceAll("#", "");
		System.out.println("id: #" + id + " minute: " + index);
		System.out.println("res: " + Integer.parseInt(id)*index);
	}
}
