package d05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("D:\\Documents\\workspace\\gits\\AOC\\2022\\d05\\input.txt"))) {
            Map<Integer, LinkedList<String>> stackMap = new HashMap<>();
            Pattern pattern = Pattern.compile("\\d");
            while (sc.hasNext()) {
                String input = sc.nextLine();
                Matcher matcherText = pattern.matcher(input);
                if (matcherText.find()) {
                    break;
                }
                String inputData = input.replaceAll("^\\s{3}", "[-]")
                        .replaceAll("\\s{4}", " [-]");
                String[] data = inputData.split("\\s");
                for (int i = 0; i < data.length; i++) {
                    if (!data[i].equals("[-]")) {
                        stackMap.computeIfAbsent(i + 1, k -> new LinkedList<>()).add(data[i]);
                    }
                }
            }
            for (Map.Entry<Integer, LinkedList<String>> entry : stackMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            sc.nextLine();
            pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            while (sc.hasNext()) {
                String input = sc.nextLine();
                Matcher matcherText = pattern.matcher(input);
                if (matcherText.find()) {
                    moveCargo(
                            Integer.parseInt(matcherText.group(1)),
                            Integer.parseInt(matcherText.group(2)),
                            Integer.parseInt(matcherText.group(3)),
                            stackMap);
                }
            }
            for (Map.Entry<Integer, LinkedList<String>> entry : stackMap.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    System.out.print(entry.getValue().get(0).replaceAll("[\\[\\]]", ""));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void moveCargo(int num, int start, int end, Map<Integer, LinkedList<String>> stackMap) {
        LinkedList<String> startQueue = stackMap.get(start);
        LinkedList<String> endQueue = stackMap.get(end);
        LinkedList<String> tmp = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            tmp.add(startQueue.pop());
        }
        endQueue.addAll(0, tmp);
    }
}
