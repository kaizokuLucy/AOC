package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day1/input.txt");
        int res = 0;
        try (Scanner scanner = new Scanner(input)) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                res += getDigits(line);
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static int getDigits(String line) {
        char first = 0;
        char last = 0;
        for (int i = 0; i < line.length(); i++) {
            char chrs = line.charAt(i);
            if (Character.isDigit(chrs)) {
                first = chrs;
                break;
            }
        }

        for (int i = line.length() - 1; i >= 0; i--) {
            char chrs = line.charAt(i);
            if (Character.isDigit(chrs)) {
                last = chrs;
                break;
            }
        }
        return Integer.parseInt("" + first + last);
    }
}