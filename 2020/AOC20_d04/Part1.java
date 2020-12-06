package AOC20_d04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {

        try {
            String path = "D:\\Documents\\workspace\\gits\\AOC\\2020\\AOC20_d04\\input.txt";
            List<String> passengers = new ArrayList<>(Files.readAllLines(Paths.get(path)));

            List<String> passport = new ArrayList<>();
            int count = 0;
            for (String line : passengers){
                if (line.isEmpty()) {
                    if (isValid(passport)) {
                        count++;
                    }
                    passport.clear();
                    continue;
                }
                passport.addAll(Arrays.asList(line.split(" ")));
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValid(List<String> passport) {
        if (passport.size() == 8 ||
            passport.size() == 7 && !passport.stream().anyMatch(p -> p.startsWith("cid:"))) {
            return true;
        }
        return false;
    }
}
