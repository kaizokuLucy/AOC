package d10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    private static final String NOOP = "noop";
    private static final String ADDX = "addx";

    private static final Map<String, Integer> cycleLength = new HashMap<>() {{
        put(NOOP, 1);
        put(ADDX, 2);
    }};

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("d10/input.txt"))) {
            int X = 1;
            int count = 0;
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] instruction = input.split("\\s");
                for (int i = 1; i <= cycleLength.get(instruction[0]); i++) {
                    if (count == X - 1 || count == X || count == X + 1) {
                        System.out.print("#");
                    } else {
                        System.out.print(' ');
                    }

                    if (instruction[0].equals(ADDX) && i % 2 == 0) {
                        X += Integer.parseInt(instruction[1]);
                    }
                    if (count == 39) {
                        count = 0;
                        System.out.println();
                        continue;
                    }
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
