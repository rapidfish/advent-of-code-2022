package se.osbe.aoc.helper;

import se.osbe.aoc.IAoCTask;
import se.osbe.aoc.data.RPC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

/**
 * Santas little helper - a helper class!
 */
public class SantasLittleHelper {

    private static final String EMPTY_ROW = "";

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
        Objects.requireNonNull(fileName);
        if("".equals(fileName)) {
            throw new RuntimeException("File name is empty!");
        }
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        requireNonNull(SantasLittleHelper.class.getClassLoader().getResourceAsStream(fileName))
                )
        );
        return reader;
    }

    public static void printResult(IAoCTask day, String taskNo, int result) {
        printResult(day, taskNo, "" + result);
    }

    public static void printResult(IAoCTask day, String taskNo, String result) {
        final int OFFSET = 2; // step back 2 chars to get day number from class name (reflections)
        int index = day.getClass().getCanonicalName().length() - OFFSET;
        String dayStr = day.getClass().getCanonicalName().substring(index);
        printResult(dayStr, taskNo, result);
    }

    public static void printResult(String day, String taskNo, String result) {
        System.out.println(String.format("AoC2022 - DAY-%s - TASK-%s: %s", "" + day, taskNo, "" + result));
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

    public static boolean isOnlyDigits(String str){
        final String ONLY_DIGITS_AND_SPACES = "[ 0-9]+";
        final Pattern p = Pattern.compile(ONLY_DIGITS_AND_SPACES);
        return p.matcher(str).matches();
    };
}
