package d08;

import d07.DirNode;
import d07.FileNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d08\\input.txt"))) {
            List<List<Integer>> trees = new ArrayList<>();
            while (sc.hasNext()) {
                String input = sc.nextLine();
                trees.add(Arrays.stream(input.split("")).map(Integer::parseInt).collect(Collectors.toList()));
            }

            int visibleTrees = 2 * trees.size() + 2 * trees.get(0).size() - 4;
            int size = trees.size();
            for (int i = 1; i < size - 1; i++) {
                for (int j = 1; j < size - 1; j++) {
                    boolean visible = true;
                    for (int count = i - 1; count >= 0; count--) {
                        if (trees.get(i).get(j) <= trees.get(count).get(j)) {
                            visible = false;
                            break;
                        }
                    }
                    if (visible) {
                        visibleTrees++;
                        continue;
                    }
                    visible = true;
                    for (int count = i + 1; count < size; count++) {
                        if (trees.get(i).get(j) <= trees.get(count).get(j)) {
                            visible = false;
                            break;
                        }
                    }
                    if (visible) {
                        visibleTrees++;
                        continue;
                    }
                    visible = true;
                    for (int count = j - 1; count >= 0 ; count--) {
                        if (trees.get(i).get(j) <= trees.get(i).get(count)) {
                            visible = false;
                            break;
                        }
                    }
                    if (visible) {
                        visibleTrees++;
                        continue;
                    }
                    visible = true;
                    for (int count = j + 1; count < size; count++) {
                        if (trees.get(i).get(j) <= trees.get(i).get(count)) {
                            visible = false;
                            break;
                        }
                    }
                    if (visible) {
                        visibleTrees++;
                    }
                }
            }
            System.out.println(visibleTrees);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
