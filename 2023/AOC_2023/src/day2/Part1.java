package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day2/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            int id = 0;
            int sum = 0;
            int maxRed = 12;
            int maxGreen = 13;
            int maxBlue = 14;

            game:
            while(scanner.hasNextLine()) {
                id++;
                String line = scanner.nextLine();
                line = line.replaceAll("Game \\d+: ", "");
                String[] subsets = line.split(";");
                for (String subset : subsets) {
                    if (!(parseColor(subset, "red", maxRed)
                            && parseColor(subset, "green", maxGreen)
                            && parseColor(subset, "blue", maxBlue))) {
                        continue game;
                    }
                }
                sum += id;
            }

            System.out.println(sum);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean parseColor(String subset, String color, Integer maxColor) {
        Pattern pattern = Pattern.compile("(\\d+) " + color);
        Matcher matcher = pattern.matcher(subset);
        if (matcher.find()) {
            int num = Integer.parseInt(matcher.group(1));
            return num <= maxColor;
        }

        return true;
    }
}