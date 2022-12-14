package d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.IntBinaryOperator;

public class Part1 {
    private static final int ROUNDS = 20;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("2022\\d11\\input.txt"))) {
            // parse input
            String input = sc.nextLine();
            if (input.contains("Monkey")) {
                String name = input.split("\\s")[1];
                List<String> startingItems = Arrays.asList(sc.nextLine().split(": ")[1].split(", "));
                String operation = sc.nextLine().split("= ")[1];
            }

            for (int i = 0; i < ROUNDS; i++) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
