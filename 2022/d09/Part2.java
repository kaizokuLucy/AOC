package d09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static d09.Point.calculateDistance;

public class Part2 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d09\\input.txt"))) {
            Set<Point> positions = new HashSet<>();
            List<Point> rope = new ArrayList<>() {{
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
                add(new Point(0, 0));
            }};

            while (sc.hasNext()) {
                String input = sc.nextLine();
                System.out.println(input);
                String[] inputData = input.split("\\s");
                char direction = inputData[0].charAt(0);
                int distance = Integer.parseInt(inputData[1]);

                for (int d = 1; d <= distance; d++) {
                    Point head = rope.get(0);
                    head.move(direction);

                    for (int i = 0; i < rope.size() - 1; i++) {
                        head = rope.get(i);
                        Point tail = rope.get(i + 1);

                        if (calculateDistance(head, tail) > Math.sqrt(2)) {
                            if (tail.getX() == head.getX() || tail.getY() == head.getY()) {
                                if (head.getX() != tail.getX()) {
                                    tail.move((head.getX() > tail.getX()) ? 'R' : 'L');
                                } else {
                                    tail.move((head.getY() > tail.getY()) ? 'U' : 'D');
                                }
                            } else {
                                tail.move((head.getX() > tail.getX()) ? 'R' : 'L');
                                tail.move((head.getY() > tail.getY()) ? 'U' : 'D');
                            }
                        }
                    }
                    positions.add(new Point(rope.get(9).getX(), rope.get(9).getY()));
                }
            }
            System.out.println(positions.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printRope(List<Point> rope) {
        for (int y = 10; y >= -10; y--) {
            for (int x = -10; x < 20; x++) {
                if (rope.contains(new Point(x, y))) {
                    System.out.print(rope.indexOf(new Point(x, y)));
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
