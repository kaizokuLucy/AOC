package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day1/input.txt");
        int res = 0;
        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                res += getDigits(line);
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static int getDigits(String line) {
        Map<String, String> numbers = Map.of(
          "one", "1",
          "two", "2",
          "three", "3",
          "four", "4",
          "five", "5",
          "six", "6",
          "seven", "7",
          "eight", "8",
          "nine", "9"
        );

        Map<Integer, String> matches = new HashMap<>();
        numbers.forEach((key, value) -> getIndices(line, matches, key, value));

        int minIndex = line.length();
        int maxIndex = -1;
        if (!matches.isEmpty()) {
            minIndex = Collections.min(matches.keySet());
            maxIndex = Collections.max(matches.keySet());
        }

        String first = matches.get(minIndex);
        for (int i = 0; i < line.length(); i++) {
            char chrs = line.charAt(i);
            if (Character.isDigit(chrs)) {
                if (i < minIndex) {
                    first = String.valueOf(chrs);
                }
                break;
            }
        }

        String last = matches.get(maxIndex);
        for (int i = line.length() - 1; i >= 0; i--) {
            char chrs = line.charAt(i);
            if (Character.isDigit(chrs)) {
                if (i > maxIndex) {
                    last = String.valueOf(chrs);
                }
                break;
            }
        }

        return Integer.parseInt(first + last);
    }

    private static void getIndices(String line, Map<Integer, String> matches, String key, String value) {
        int index = 0;
        while(index != -1){
            index = line.indexOf(key, index);
            if (index != -1) {
                matches.put(index, value);
                index++;
            }
        }
    }
}