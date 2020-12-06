package AOC20_d03;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        try {
            String path = "D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d03\\input.txt";
            List<String> field = new ArrayList<>(Files.readAllLines(Paths.get(path)));
            BigInteger result =
                    BigInteger.valueOf(getCount(field, 1, 1)).multiply(
                    BigInteger.valueOf(getCount(field, 3, 1))).multiply(
                    BigInteger.valueOf(getCount(field, 5, 1))).multiply(
                    BigInteger.valueOf(getCount(field, 7, 1))).multiply(
                    BigInteger.valueOf(getCount(field, 1, 2)));
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getCount(List<String> field, int xStep, int yStep) {
        int count = 0;
        int x = 0;
        for (int y = 0; y < field.size(); y += yStep) {
            String line = field.get(y);
            if (isTree(line, x % line.length())) {
                count++;
            }
            x = (x + xStep) % line.length();
        }
        return count;
    }

    private static boolean isTree(String nextLine, int x) {
        return nextLine.charAt(x) == '#';
    }
}
