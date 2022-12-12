package d07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Part2 {

    private static int MAX_SIZE = 100000;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d07\\input.txt"))) {
            DirNode currentDirectory = new DirNode();
            while (sc.hasNext()) {
                String input = sc.nextLine();
                if (input.contains("$ cd")) {
                    String[] cdData = input.split(" ");
                    if (cdData[2].equals("/")) {
                        currentDirectory.setName("/");
                    } else if (cdData[2].equals("..")) {
                        currentDirectory = currentDirectory.getParent();
                    } else {
                        currentDirectory = currentDirectory.getChildren().stream()
                                .filter(c -> c.getName().equals(cdData[2]))
                                .findFirst()
                                .orElse(new DirNode());
                    }
                } else if (!input.contains("$ ls")) {
                    if (input.startsWith("dir")) {
                        String[] dirData = input.split(" ");
                        currentDirectory.getChildren().add(new DirNode(dirData[1], currentDirectory));
                    } else {
                        String[] fileData = input.split(" ");
                        currentDirectory.getFiles().add(new FileNode(Integer.parseInt(fileData[0]), fileData[1]));
                    }
                }
            }
            while (!currentDirectory.getName().equals("/")) {
                currentDirectory = currentDirectory.getParent();
            }

            int freeSpace = 70000000 - getSize(currentDirectory);
            TreeSet<Integer> sizeMap = new TreeSet<>();
            memoization(currentDirectory, sizeMap);
            for (Integer size : sizeMap) {
                if (freeSpace + size >= 30000000) {
                    System.out.println(size);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    private static void memoization (DirNode currentDir, TreeSet<Integer> sizeMap) {
        if (currentDir.getChildren().isEmpty()) {
            sizeMap.add(getSize(currentDir));
            return;
        }
        sizeMap.add(getSize(currentDir));
        for (DirNode child : currentDir.getChildren()) {
            memoization(child, sizeMap);
        }
    }

    private static int getSize (DirNode currentDir) {
        if (currentDir.getChildren().isEmpty()) {
            return currentDir.getFilesSizeSum();
        }
        int childSum = 0;
        for (DirNode child : currentDir.getChildren()) {
            childSum += getSize(child);
        }
        childSum += currentDir.getFilesSizeSum();
        return childSum;
    }
}
