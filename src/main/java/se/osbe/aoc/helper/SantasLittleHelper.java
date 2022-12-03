package se.osbe.aoc.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

/**
 * Santas little helper - a helper class!
 */
public class SantasLittleHelper {

    private static final String EMPTY_ROW = "";
    private static final Pattern ROCK_PAPER_SCISSORS_PATTERN = Pattern.compile("[ABC][ ][XYZ]");

    public static Map<Integer, List<Integer>> loadFileToMapWithLists(String fileName) throws Exception {
        Integer index = 0;
        Map<Integer, List<Integer>> result = new HashMap<>();
        BufferedReader reader = readFileByFilename(fileName);
        String line;
        while ((line = reader.readLine()) != null) {
            if (EMPTY_ROW.equals(line)) {
                index++;
                continue;
            }
            if (!result.containsKey(index)) {
                result.put(index, new ArrayList<>());
            }
            result.get(index).add(Integer.valueOf(line));
        }
        return result;
    }

    public static List<String> loadFileToListOfStrings(String fileName) throws Exception {
        Integer index = 0;
        List<String> result = new ArrayList<>();
        BufferedReader reader = readFileByFilename(fileName);
        String line;
        while ((line = reader.readLine()) != null) {
            if (EMPTY_ROW.equals(line)) {
                index++;
                continue;
            }
            result.add(line);
        }
        return result;
    }

    private static BufferedReader readFileByFilename(String fileName) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        requireNonNull(SantasLittleHelper.class.getClassLoader().getResourceAsStream(fileName))
                )
        );
        return reader;
    }

    public static void printResult(String dayNo, String taskNo, Integer result) {
        printResult(dayNo, taskNo, String.valueOf(result));
    }

    public static void printResult(String dayNo, String taskNo, String result) {
        System.out.println(String.format("AoC2022 - DAY-%s - TASK-%s: %s", dayNo, taskNo, result));
    }

    public static int rockPaperScissors(String rawHands) {
        String hands = Objects.requireNonNull(rawHands);
        if (!ROCK_PAPER_SCISSORS_PATTERN.matcher(rawHands).find()) {
            throw new RuntimeException("Wrong pattern for row with 'Rock Paper Scissor' hand: " + rawHands);
        }
        char opponent = hands.charAt(0);
        char player = hands.charAt(2);
        return resolveScoreForPlayer(winner(opponent, player), player);
    }

    private static int winner(char opponent, char player) {
        // 'A' for Rock, 'B' for Paper, and 'C' for Scissors
        // 'X' for Rock, 'Y' for Paper, and 'Z' for Scissors
        switch (opponent) {
            case 'A':
                switch (player) {
                    case 'X':
                        return 0;
                    case 'Y':
                        return 1;
                    case 'Z':
                        return -1;
                }
                break;
            case 'B':
                switch (player) {
                    case 'X':
                        return -1;
                    case 'Y':
                        return 0;
                    case 'Z':
                        return 1;
                }
                break;
            case 'C':
                switch (player) {
                    case 'X':
                        return 1;
                    case 'Y':
                        return -1;
                    case 'Z':
                        return 0;
                }
                break;
        }
        throw new RuntimeException("Invalid input for winner( leftPlayer, rightPlayer ) : " + opponent + ", " + player);
    }

    private static int resolveScoreForPlayer(int winnerResult, char player) {
        int result = winnerResult == 0 ? 3 : (winnerResult > 0 ? 6 : 0);
        result += (player == 'X' ? 1 : (player == 'Y' ? 2 : 3)); // add score for using rock=1, paper=2, scissors=3
        return result;
    }

    public List<String> splitSpace(String str) {
        final String space = " ";
        return Arrays.asList(str.split(space));
    }
}
