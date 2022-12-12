package d09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static d09.Point.calculateDistance;

public class Part1 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d09\\input.txt"))) {
            Set<Point> positions = new HashSet<>();
            Point head = new Point(0, 0);
            Point tail = new Point(0, 0);
            positions.add(new Point(tail.getX(), tail.getY()));

            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] inputData = input.split("\\s");
                char direction = inputData[0].charAt(0);
                int distance = Integer.parseInt(inputData[1]);
                for (int i = 1; i <= distance; i++) {
                    head.move(direction);
                    if (calculateDistance(head, tail) > Math.sqrt(2)) {
                        if (tail.getX() == head.getX() || tail.getY() == head.getY()) {
                            tail.move(direction);
                        } else {
                            tail.move((head.getX() > tail.getX()) ? 'R' : 'L');
                            tail.move((head.getY() > tail.getY()) ? 'U' : 'D');
                        }
                        positions.add(new Point(tail.getX(), tail.getY()));
                    }
                }
            }

            System.out.println(positions.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
