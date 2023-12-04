package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day3/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            List<List<Character>> schematic = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                schematic.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            }

            int sum = 0;
            for (int i = 0; i < schematic.size(); i++) {
                String num = "";
                boolean isPartNumber = false;
                for (int j = 0; j < schematic.get(i).size(); j++) {
                    Character character = schematic.get(i).get(j);
                    if (Character.isDigit(character)) {
                        num += character;
                        isPartNumber |= hasAdjacentSymbol(i, j, schematic);
                    } else if (!Character.isDigit(character)
                            || j == schematic.get(i).size()){
                        if (isPartNumber) {
                            sum += Integer.parseInt(num);
                        }
                        num = "";
                        isPartNumber = false;
                    }
                }
                System.out.println(i + " " + sum);
            }
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean hasAdjacentSymbol(int x, int y, List<List<Character>> schematic) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= schematic.size() || j < 0 || j >= schematic.get(x).size()) {
                    continue;
                }
                Character adjacentSymbol = schematic.get(i).get(j);
                if (!Character.isDigit(adjacentSymbol) && adjacentSymbol != '.') {
                    return true;
                }
            }
        }
        return  false;
    }
}