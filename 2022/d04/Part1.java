package d04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args) {
        int numOfPairs = 0;
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d04\\input.txt"))) {
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String firstElfAssignments = input.split(",")[0];
                String secondElfAssignments = input.split(",")[1];
                int firstStart = Integer.parseInt(firstElfAssignments.split("-")[0]);
                int firstEnd = Integer.parseInt(firstElfAssignments.split("-")[1]);
                int secondStart = Integer.parseInt(secondElfAssignments.split("-")[0]);
                int secondEnd = Integer.parseInt(secondElfAssignments.split("-")[1]);
                List<Integer> first = new ArrayList<>();
                List<Integer> second = new ArrayList<>();
                for (int i = firstStart; i <= firstEnd; i++) {
                    first.add(i);
                }
                for (int i = secondStart; i <= secondEnd; i++) {
                    second.add(i);
                }
                if (first.containsAll(second) || second.containsAll(first)) {
                    numOfPairs++;
                }
            }
            System.out.println(numOfPairs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
