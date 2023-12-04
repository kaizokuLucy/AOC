package day3;

public class EngineNumber {

    int num;
    Coordinate starCoordinate;

    public EngineNumber(int num, Coordinate starCoordinate) {
        this.num = num;
        this.starCoordinate = starCoordinate;
    }

    public int getNum() {
        return num;
    }

    public Coordinate getStarCoordinate() {
        return starCoordinate;
    }

    @Override
    public String toString() {
        return "num=" + num +
                ", starCoordinate=" + starCoordinate;
    }
}
