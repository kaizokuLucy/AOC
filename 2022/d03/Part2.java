package d03;

import javax.naming.ldap.HasControls;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {

    // a-z : 1-26
    // A-Z : 27-52
    private static final int A_score = 27;
    private static final int a_score = 1;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d03\\input.txt"))) {
            int prioritySum = 0;
            List<String> group = new ArrayList<>();
            while (sc.hasNext()) {
                group.add(sc.nextLine());
                if (group.size() == 3) {
                    prioritySum += calculateSum(group);
                    group.clear();
                }
            }
            System.out.println(prioritySum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int calculateSum(List<String> group) {
        List<Character> first =
                group.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> second =
                group.get(1).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> third =
                group.get(2).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        second.retainAll(first);
        third.retainAll(second);
        Character duplicateItem = third.get(0);
        return Character.isUpperCase(duplicateItem) ? duplicateItem - 'A' + A_score : duplicateItem - 'a' + a_score;
    }
}
