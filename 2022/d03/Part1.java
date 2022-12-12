package d03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {

    // a-z : 1-26
    // A-Z : 27-52
    private static final int A_score = 27;
    private static final int a_score = 1;

    public static void main(String[] args) {
        int prioritySum = 0;
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d03\\input.txt"))) {
            while (sc.hasNext()) {
                String rucksackItems = sc.nextLine();
                int l = rucksackItems.length();
                int r = l/2;
                List<Character> firstCompartment =
                        rucksackItems.substring(0, r).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
                List<Character> secondCompartment =
                        rucksackItems.substring(r, l).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

                secondCompartment.retainAll(firstCompartment);
                Character duplicateItem = secondCompartment.get(0);
                int sum = Character.isUpperCase(duplicateItem) ? duplicateItem - 'A' + A_score : duplicateItem - 'a' + a_score;
                prioritySum += sum;
            }
            System.out.println(prioritySum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
