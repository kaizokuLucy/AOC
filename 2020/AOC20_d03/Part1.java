package AOC20_d03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d03\\input.txt"))) {
            int count = 0;
            for (int x = 0; sc.hasNext(); x += 3) {
                String line = sc.nextLine();
                if (isTree(line, x % line.length())) {
                    count++;
                }
            }
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean isTree(String nextLine, int x) {
        return nextLine.charAt(x) == '#';
    }
}
