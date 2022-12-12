package d08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d08\\input.txt"))) {
            List<List<Integer>> trees = new ArrayList<>();
            while (sc.hasNext()) {
                String input = sc.nextLine();
                trees.add(Arrays.stream(input.split("")).map(Integer::parseInt).collect(Collectors.toList()));
            }

            int visibleTrees = 2 * trees.size() + 2 * trees.get(0).size() - 4;
            int size = trees.size();
            int max = 0;
            for (int i = 1; i < size - 1; i++) {
                for (int j = 1; j < size - 1; j++) {
                    int downCount = 0;
                    for (int index = i - 1; index >= 0; index--) {
                        if (trees.get(i).get(j) > trees.get(index).get(j)) {
                            downCount++;
                        } else {
                            downCount++;
                            break;
                        }
                    }
                    int upCount = 0;
                    for (int index = i + 1; index < size; index++) {
                        if (trees.get(i).get(j) > trees.get(index).get(j)) {
                            upCount++;
                        } else {
                            upCount++;
                            break;
                        }
                    }
                    int leftCount = 0;
                    for (int index = j - 1; index >= 0 ; index--) {
                        if (trees.get(i).get(j) > trees.get(i).get(index)) {
                            leftCount++;
                        } else {
                            leftCount++;
                            break;
                        }
                    }
                    int rightCount = 0;
                    for (int index = j + 1; index < size; index++) {
                        if (trees.get(i).get(j) > trees.get(i).get(index)) {
                            rightCount++;
                        } else {
                            rightCount++;
                            break;
                        }
                    }
                    int scenicScore = downCount * upCount * leftCount * rightCount;
                    if (scenicScore > max) {
                        max = scenicScore;
                    }
                }
            }
            System.out.println(max);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
