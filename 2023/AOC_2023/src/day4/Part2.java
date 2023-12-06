package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {
    public static void main(String[] args) {
        File input = new File("/home/lucy/Documents/workspace/gits/AOC/2023/AOC_2023/src/day4/input.txt");
        try (Scanner scanner = new Scanner(input)) {
            int cardId = 1;
            Map<Integer, Integer> cardPoints = new TreeMap<>();
            while(scanner.hasNextLine()) {
                String card = scanner.nextLine().replaceAll("Card\\s+\\d+:\\s+", "");
                String[] cardNumbers = card.split(" \\| ");
                List<Integer> winningNumbers = Arrays.stream(cardNumbers[0].trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
                List<Integer> playedNumbers = Arrays.stream(cardNumbers[1].trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
                cardPoints.put(cardId, getCardPoints(winningNumbers, playedNumbers));
                cardId++;
            }

            System.out.println(getCardSum(cardPoints));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getCardPoints(List<Integer> winningNumbers, List<Integer> playedNumbers) {
        winningNumbers.retainAll(playedNumbers);
        return winningNumbers.size();
    }

    private static int getCardSum(Map<Integer, Integer> cardPoints) {
        Set<Integer> originalCards = cardPoints.keySet();
        List<Integer> newCards = new ArrayList<>();
        List<Integer> copyCards = new ArrayList<>();
        for (Integer cardId : originalCards) {
            for (int i = cardId + 1; i <= cardId + cardPoints.get(cardId); i++) {
                copyCards.add(i);
            }
            int count = 0;
            for (Integer newCard : newCards) {
                if (newCard.equals(cardId)) {
                    count ++;
                }
            }

            for (int i = 0; i < count; i++) {
                newCards.addAll(copyCards);
            }

            newCards.addAll(copyCards);
            copyCards.clear();
        }

        return newCards.size() + originalCards.size();
    }
}