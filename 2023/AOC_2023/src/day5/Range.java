package day5;

public class Range {

    private int destination;
    private int source;
    private int range;

    public Range(int destination, int source, int range) {
        this.destination = destination;
        this.source = source;
        this.range = range;
    }

    public Integer getRangeValueOrDefault(Integer inputValue) {
        int srcStart = source;
        int srcEnd = source + range;
        int destStart = destination;
        if (inputValue >= srcStart && inputValue < srcEnd) {
            int diff = inputValue - srcStart;
            return destStart + diff;
        }
        return inputValue;
    }
}
