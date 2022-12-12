package d06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d06\\input.txt"))) {
            String[] input = sc.nextLine().split("");
            Set<String> startPacket = new HashSet<>();
            for (int i = 0; i < input.length; i++) {
               startPacket.add(input[i]);
               startPacket.add(input[i + 1]);
               startPacket.add(input[i + 2]);
               startPacket.add(input[i + 3]);
               if (startPacket.size() == 4) {
                   System.out.println(i + 4);
                   break;
               }
               startPacket.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
