package se.osbe.aoc;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToMapWithLists;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_01 implements IAoCTask {

    private static String INPUTFILE1 = "input_d01_t01.txt";

    private Map<Integer, List<Integer>> _inputMap;
    private List<Integer> _result;

    public AOC_DAY_01() throws Exception {
        _inputMap = loadFileToMapWithLists(INPUTFILE1);
        _result = new ArrayList<>();
        resolveTask1(); // correct answer is 69206
        resolveTask2(); // correct answer is 197400
    }

    public void resolveTask1() throws Exception {
        Map<Integer, List<Integer>> inputMap = loadFileToMapWithLists(INPUTFILE1);
        IntStream.range(0, inputMap.entrySet().size()).forEach(idx -> {
            _result.add(
                    _inputMap.get(idx).stream().collect(Collectors.summingInt(Integer::intValue))
            );
        });
        int indxToMaxValue = _result.indexOf(_result.stream()
                .filter(Objects::nonNull)
                .max(Integer::compare)
                .orElse(-1));
        printResult(this, "1", _result.get(indxToMaxValue));
    }

    public void resolveTask2() throws Exception {
        Collections.sort(_result, Comparator.reverseOrder());
        int result = IntStream.rangeClosed(0, 2)
                .map(idx -> _result.get(idx))
                .sum();
        printResult(this, "2", result);
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_01();
    }
}
