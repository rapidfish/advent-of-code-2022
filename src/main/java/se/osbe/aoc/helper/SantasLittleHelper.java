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

    public static RPC convertRawHandToRPC(String rawHands) {
        String hands = Objects.requireNonNull(rawHands);
        if (!ROCK_PAPER_SCISSORS_PATTERN.matcher(rawHands).find()) {
            throw new RuntimeException("Wrong pattern for row with 'Rock Paper Scissor' hand: " + rawHands);
        }
        return new RPC(hands.charAt(0), hands.charAt(2));
    }

    /**
     * Determine winning hand, opponent wins (-1), player wins (1), or if its a draw (0)
     *
     * @param rpc a Rock Paper Scissors object
     * @return greater than zero player wins, less than zero opponent wins, zero its a draw!
     */
    public static int compareHands(RPC rpc) {
        return compareHands(rpc.getOpponentHand(), rpc.getPlayerHand());
    }

    /**
     * @param opponent opponent hand ('A' for Rock, 'B' for Paper, and 'C' for Scissors)
     * @param player   player hand ('X' for Rock, 'Y' for Paper, and 'Z' for Scissors)
     * @return greater than zero player wins, less than zero opponent wins, zero its a draw!
     */
    private static int compareHands(char opponent, char player) {
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
        }
        throw new RuntimeException("Invalid input for winner( leftPlayer, rightPlayer ) : " + opponent + ", " + player);
    }

    public static int resolvePriority(char c) {
        /* priority:
         * Lowercase item types a through z have priorities 1 through 26.
         * Uppercase item types A through Z have priorities 27 through 52.
         */
        // 65=A
        // 97=a
        if(c >= 65 && c < (65+26)) {
            // A-Z
            return (c - 65) + 27;
        }
        if(c > (65+25) && c <= 97) {
            // a-z
            return (c - 97) + 1;
        }
        throw new RuntimeException("Priority error!");
    }
}
