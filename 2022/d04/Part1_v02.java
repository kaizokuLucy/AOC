package d04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1_v02 {

    public static void main(String[] args) {
        int numOfPairs = 0;
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d04\\input.txt"))) {
            while (sc.hasNext()) {
                var limits = Arrays.stream(sc.nextLine().split("[-,]"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                int firstStart = limits.get(0);
                int firstEnd = limits.get(1);
                int secondStart = limits.get(2);
                int secondEnd = limits.get(3);

                if ((firstStart >= secondStart && firstEnd <= secondEnd)
                        || (secondStart >= firstStart && secondEnd <= firstEnd)) {
                    numOfPairs++;
                }
            }
            System.out.println(numOfPairs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
