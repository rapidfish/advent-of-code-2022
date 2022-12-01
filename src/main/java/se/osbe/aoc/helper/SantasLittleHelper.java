package se.osbe.aoc.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Santas little helper - a helper class!
 */
public class SantasLittleHelper {

    private static String EMPTY_ROW = "";

    public static Map<Integer, List<Integer>> loadInputFileToMap(String fileName) throws Exception {
        Integer index = 0;
        Map<Integer, List<Integer>> result = new HashMap<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        requireNonNull(SantasLittleHelper.class.getClassLoader().getResourceAsStream(fileName))
                )
        );
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

    public static void printResult(String dayNo, String taskNo, Integer result) {
        printResult(dayNo, taskNo, String.valueOf(result));
    }

    public static void printResult(String dayNo, String taskNo, String result) {
        System.out.println(String.format("AoC2022 - DAY-%s - TASK-%s: %s", dayNo, taskNo, result));
    }
}
