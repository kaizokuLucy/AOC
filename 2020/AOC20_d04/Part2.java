package AOC20_d04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
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
            return areValidFields(passport);
        }
        return false;
    }

    private static boolean areValidFields(List<String> passport) {
        return passport.stream().allMatch(p -> {
            String[] field = p.split(":");
            boolean returnValue = false;
            switch (field[0]) {
                case "byr":
                    returnValue = isValidRange(field[1], 1920, 2002);
                    break;
                case "iyr":
                    returnValue = isValidRange(field[1], 2010, 2020);
                    break;
                case "eyr":
                    returnValue = isValidRange(field[1], 2020, 2030);
                    break;
                case "hgt":
                    returnValue = isValidHgt(field[1]);
                    break;
                case "hcl":
                    returnValue = isValidHcl(field[1]);
                    break;
                case "ecl":
                    returnValue = isValidEcl(field[1]);
                    break;
                case "pid":
                    returnValue = isValidPid(field[1]);
                    break;
                default:
                    returnValue = true;
                    break;
            }
            return returnValue;
        });
    }

    private static boolean isValidRange(String value, int start, int finish) {
        int year = Integer.parseInt(value);
        return start <= year && year <= finish;
    }

    private static boolean isValidHgt(String value) {
        if (value.endsWith("cm")) {
            return isValidRange(value.substring(0, value.length() - 2), 150, 193);
        } else if (value.endsWith("in")) {
            return isValidRange(value.substring(0, value.length() - 2), 59, 76);
        }
        return false;
    }

    private static boolean isValidHcl(String value) {
        return value.matches("^#[0-9a-f]{6}$");
    }

    private static boolean isValidPid(String value) {
        return value.matches("[0-9]{9}");
    }

    private static boolean isValidEcl(String value) {
        return value.matches("amb|blu|brn|gry|grn|hzl|oth");
    }
}
