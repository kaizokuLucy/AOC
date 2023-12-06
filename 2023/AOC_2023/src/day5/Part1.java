package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day5/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            List<Integer> seeds = Arrays.stream(scanner.nextLine().replace("seeds: ", "").split("\\s+"))
                    .map(Integer::parseInt).collect(Collectors.toList());
            // read empty line
            scanner.nextLine();
            List<Range> seedToSoil = getAlmanacMap(scanner);
            List<Range> soilToFertilizer = getAlmanacMap(scanner);
            List<Range> fertilizerToWater = getAlmanacMap(scanner);
            List<Range> waterToLight = getAlmanacMap(scanner);
            List<Range> lightToTemperature = getAlmanacMap(scanner);
            List<Range> temperatureToHumidity = getAlmanacMap(scanner);
            List<Range> humidityToLocation = getAlmanacMap(scanner);

            seeds = mapSrcToDest(seeds, seedToSoil);
            seeds = mapSrcToDest(seeds, soilToFertilizer);
            seeds = mapSrcToDest(seeds, fertilizerToWater);
            seeds = mapSrcToDest(seeds, waterToLight);
            seeds = mapSrcToDest(seeds, lightToTemperature);
            seeds = mapSrcToDest(seeds, temperatureToHumidity);
            seeds = mapSrcToDest(seeds, humidityToLocation);
            System.out.println(seeds.stream().mapToInt(v -> v).min().getAsInt());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Integer> mapSrcToDest(List<Integer> inputValues, List<Range> mapping) {
        List<Integer> outputValues = new ArrayList<>();
        for (Integer inputValue : inputValues) {
            int outputValue = inputValue;
            for (Range range : mapping) {
                outputValue = range.getRangeValueOrDefault(inputValue);
            }
            outputValues.add(outputValue);
        }

        return outputValues;
    }

    private static List<Range> getAlmanacMap(Scanner scanner) {
        List<Range> almanacMap = new ArrayList<>();
        // read mapping string
        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                List<Integer> data = Arrays.stream(input.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
                almanacMap.add(new Range(data.get(0), data.get(1), data.get(2)));
            } else {
                break;
            }
        }
        return almanacMap;
    }
}