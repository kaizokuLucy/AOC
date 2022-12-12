package d02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
    // A - rock
    // B - paper
    // C - scissors
    // 0 - lost, 3 - draw, 6 - win
    // X lose, Y draw, Z win
    private static final Map<String, String> outcomeShape = Map.of(
            "A X", "C",
            "A Y", "A",
            "A Z", "B",
            "B X", "A",
            "B Y", "B",
            "B Z", "C",
            "C X", "B",
            "C Y", "C",
            "C Z", "A"
    );

    private static final Map<String, Integer> outcomeScore = Map.of(
            "X", 0,
            "Y", 3,
            "Z", 6
    );

    private static final Map<String, Integer> shapeScore = Map.of(
            "A", 1,
            "B", 2,
            "C", 3
    );

    public static void main(String[] args) {
        int totalScore = 0;
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d02\\input.txt"))) {
            while (sc.hasNext()) {
                String round = sc.nextLine();
                String playersShape = outcomeShape.get(round);
                totalScore += shapeScore.get(playersShape) + outcomeScore.get(round.split(" ")[1]);
            }
            System.out.println(totalScore);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
