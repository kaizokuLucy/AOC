package d02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Part1 {
    // A - rock      X  1
    // B - paper     Y  2
    // C - scissors  Z  3
    // 0 - lost, 3 - draw, 6 - win
    private static final Map<String, Integer> outcomeScore = Map.of(
            "A X", 3,
            "A Y", 6,
            "A Z", 0,
            "B X", 0,
            "B Y", 3,
            "B Z", 6,
            "C X", 6,
            "C Y", 0,
            "C Z", 3
    );

    private static final Map<String, Integer> shapeScore = Map.of(
            "X", 1,
            "Y", 2,
            "Z", 3
    );

    public static void main(String[] args) {
        int totalScore = 0;
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d02\\input.txt"))) {
            while (sc.hasNext()) {
                String round = sc.nextLine();
                totalScore += outcomeScore.get(round) + shapeScore.get(round.split(" ")[1]);
            }
            System.out.println(totalScore);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
