package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day4/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            int sum = 0;
            while(scanner.hasNextLine()) {
                int point = 0;
                String card = scanner.nextLine().replaceAll("Card\\s+\\d+:\\s+", "");
                String[] cardNumbers = card.split(" \\| ");
                List<Integer> winningNumbers = Arrays.stream(cardNumbers[0].trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
                List<Integer> playedNumbers = Arrays.stream(cardNumbers[1].trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
                for (Integer number : playedNumbers) {
                    if (winningNumbers.contains(number)) {
                        if (point == 0) {
                            point = 1;
                        } else {
                            point *= 2;
                        }
                    }
                }
                sum += point;
            }

            System.out.println(sum);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}