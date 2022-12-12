package d06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Part2 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d06\\input.txt"))) {
            String[] input = sc.nextLine().split("");
            Set<String> startPacket = new HashSet<>();
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < 14; j++) {
                    startPacket.add(input[i + j]);
                }
               if (startPacket.size() == 14) {
                   System.out.println(i + 14);
                   break;
               }
               startPacket.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
