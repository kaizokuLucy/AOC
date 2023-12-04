package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day4/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNextLine()) {
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}