package se.osbe.aoc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static se.osbe.aoc.helper.SantasLittleHelper.loadInputFileToMap;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_01 {

    private static String INPUTFILE1 = "input_d01_t01.txt";
    private static String INPUTFILE2 = "input_d01_t02.txt";

    private Map<Integer, List<Integer>> _inputMap;
    private List<Integer> _result;

    public AOC_DAY_01() throws Exception {
        _inputMap = loadInputFileToMap(INPUTFILE1);
        _result = new ArrayList<>();
        resolveTask1();
        resolveTask2();
    }

    private void resolveTask1() throws Exception {
        Map<Integer, List<Integer>> inputMap = loadInputFileToMap(INPUTFILE1);
        IntStream.range(0, inputMap.entrySet().size()).forEach(idx -> {
            _result.add(
                    _inputMap.get(idx).stream().collect(Collectors.summingInt(Integer::intValue))
            );
        });
        int indxToMaxValue = _result.indexOf(_result.stream()
                .filter(Objects::nonNull)
                .max(Integer::compare)
                .orElse(-1));
        printResult("01", "1", _result.get(indxToMaxValue)); // correct answer is 69206
    }

    private void resolveTask2() throws Exception {
        Collections.sort(_result, Comparator.reverseOrder());
        int sum = IntStream.rangeClosed(0, 2)
                .map(idx -> _result.get(idx))
                .sum();
        printResult("01", "2", sum); // correct answer is 197400
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_01();
    }
}
