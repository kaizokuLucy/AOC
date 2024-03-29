package d10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    private static final String NOOP = "noop";
    private static final String ADDX = "addx";

    private static final Map<String, Integer> cycleLength = new HashMap<>() {{
        put(NOOP, 1);
        put(ADDX, 2);
    }};

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("2022\\d10\\input.txt"))) {
            int X = 1;
            int cycles = 1;
            int signalStrengthSum = 0;
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] instruction = input.split("\\s");
                for (int i = 1; i <= cycleLength.get(instruction[0]); i++) {
                    if (cycles % 40 == 20) {
                        signalStrengthSum += cycles * X;
                    }
                    if (instruction[0].equals(ADDX) && i % 2 == 0) {
                        X += Integer.parseInt(instruction[1]);
                    }
                    cycles++;
                }
            }
            System.out.println(signalStrengthSum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
