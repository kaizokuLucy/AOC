package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day3/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            List<List<Character>> schematic = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                schematic.add(line.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            }

            List<EngineNumber> engineNumbers = new ArrayList<>();
            for (int i = 0; i < schematic.size(); i++) {
                String num = "";
                Coordinate starCoordinate = null;
                for (int j = 0; j < schematic.get(i).size(); j++) {
                    Character character = schematic.get(i).get(j);
                    if (Character.isDigit(character)) {
                        num += character;
                        Coordinate adjacentCoordinate = getAdjacentStar(i, j, schematic);
                        starCoordinate =  adjacentCoordinate != null ? adjacentCoordinate : starCoordinate;
                    } else {
                        if (!num.isEmpty()
                                && Objects.nonNull(starCoordinate)) {
                            engineNumbers.add(new EngineNumber(Integer.parseInt(num), starCoordinate));
                        }
                        num = "";
                        starCoordinate = null;
                    }
                }
                if (!num.isEmpty()
                        && Objects.nonNull(starCoordinate)) {
                    engineNumbers.add(new EngineNumber(Integer.parseInt(num), starCoordinate));
                }
            }
            System.out.println(getAdjacentStarSum(engineNumbers));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static int getAdjacentStarSum(List<EngineNumber> engineNumbers) {
        int sum = 0;
        for (int i = 0; i < engineNumbers.size(); i++) {
            for (int j = i + 1; j < engineNumbers.size(); j++) {
                EngineNumber current = engineNumbers.get(i);
                EngineNumber next = engineNumbers.get(j);
                if (areEqualCoordinates(current, next)) {
                    sum += current.getNum() * next.getNum();
                }
            }
        }

        return sum;
    }

    private static boolean areEqualCoordinates(EngineNumber current, EngineNumber next) {
        Coordinate currentStarCoordinates = current.starCoordinate;
        Coordinate nextStarCoordinates = next.starCoordinate;
        return currentStarCoordinates.getX() == nextStarCoordinates.getX()
                && currentStarCoordinates.getY() == nextStarCoordinates.getY();
    }

    private static Coordinate getAdjacentStar(int row, int col, List<List<Character>> schematic) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i < 0 || i >= schematic.size() || j < 0 || j >= schematic.get(row).size()) {
                    continue;
                }
                Character adjacentSymbol = schematic.get(i).get(j);
                if (adjacentSymbol == '*') {
                    return new Coordinate(j, i);
                }
            }
        }
        return  null;
    }
}