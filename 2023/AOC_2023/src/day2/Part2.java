package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day2/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            int sum = 0;
            Map<String, Integer> colorNum = new HashMap<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replaceAll("Game \\d+: ", "");
                String[] subsets = line.split("; ");
                for (String subset : subsets) {
                    String[] sets = subset.split(", ");
                    for (String set : sets) {
                        String[] pair = set.split(" ");
                        colorNum.put(pair[1], Math.max(Integer.parseInt(pair[0]), colorNum.getOrDefault(pair[1], 0)));
                    }
                }

                sum += colorNum.values().stream().reduce(1, (a, b) -> a * b);
                colorNum.clear();
            }

            System.out.println(sum);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}