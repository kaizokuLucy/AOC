package d11;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {
    private static final int ROUNDS = 1000;

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("2022\\d11\\input.txt"))) {
            // parse input
            List<Monkey> monkeys = new ArrayList<>();
            while (sc.hasNext()) {
                String input = sc.nextLine();
                if (input.contains("Monkey")) {
                    String name = input.split("\\s")[1];
                    List<BigInteger> startingItems = Arrays.stream(
                            sc.nextLine().split(": ")[1].split(", "))
                            .map(BigInteger::new)
                            .collect(Collectors.toList());
                    String[] operationData = sc.nextLine().split("= ")[1].split("\\s");
                    String operation = operationData[1];
                    String arg1 = operationData[0];
                    String arg2 = operationData[2];
                    BigInteger divisor = new BigInteger(sc.nextLine().replaceAll("[^0-9]", ""));
                    Integer trueMonkey = Integer.parseInt(sc.nextLine().replaceAll("[^0-9]", ""));
                    Integer falseMonkey = Integer.parseInt(sc.nextLine().replaceAll("[^0-9]", ""));
                    monkeys.add(new Monkey(name, startingItems, operation, arg1, arg2, divisor, trueMonkey, falseMonkey, 0));
                }
            }
            int i = 0;
            BigInteger worryLevel;
            while (i < ROUNDS) {
                System.out.println(i);
                for (Monkey currentMonkey : monkeys) {
                    for (BigInteger item : currentMonkey.getStartingItems()) {
                        // calculate worry level
                        worryLevel = item;
                        BigInteger arg1;
                        BigInteger arg2;
                        if (currentMonkey.getArg1().equals("old")) {
                            arg1 = worryLevel;
                        } else {
                            arg1 = new BigInteger(currentMonkey.getArg1());
                        }
                        if (currentMonkey.getArg2().equals("old")) {
                            arg2 = worryLevel;
                        } else {
                            arg2 = new BigInteger(currentMonkey.getArg2());
                        }
                        switch (currentMonkey.getOperation()) {
                            case "*":
                                worryLevel = arg1.multiply(arg2);
                                break;
                            case "+":
                                worryLevel = arg1.add(arg2);
                                break;
                            default:
                                break;
                        }
                        // check divisibility
                        if (worryLevel.remainder(currentMonkey.getDivisor()).equals(BigInteger.ZERO)) {
                            monkeys.get(currentMonkey.getDivisorTrue()).getStartingItems().add(worryLevel);
                        } else {
                            monkeys.get(currentMonkey.getDivisorFalse()).getStartingItems().add(worryLevel);
                        }
                    }
                    currentMonkey.setItemsChecked(currentMonkey.getItemsChecked()
                            + currentMonkey.getStartingItems().size());
                    currentMonkey.getStartingItems().clear();
                }
                i++;
            }
            List<Integer> sortedCheckedItems = monkeys.stream()
                    .map(Monkey::getItemsChecked)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            System.out.println(sortedCheckedItems.get(0)*sortedCheckedItems.get(1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
