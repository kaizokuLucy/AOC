package d09;

import java.util.Objects;

public class Point {

    private int x;
    private int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void move(char direction) {
        switch (direction) {
            case 'L':
                this.x -= 1;
                break;
            case 'R':
                this.x += 1;
                break;
            case 'U':
                this.y += 1;
                break;
            case 'D':
                this.y -= 1;
                break;
            default:
                break;
        }
    }

    public static double calculateDistance(Point head, Point tail) {
        int dx = tail.x - head.x;
        int dy = tail.y - head.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" +
                x +
                ", " + y +
                ')';
    }
}
