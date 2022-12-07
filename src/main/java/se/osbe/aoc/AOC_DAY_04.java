package se.osbe.aoc;

import se.osbe.aoc.helper.ElvesPair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_04 implements IAoCTask {

    private static final String INPUTFILE1 = "input_d04_t01.txt";

    private final List<String> _inputList;

    public AOC_DAY_04() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        resolveTask1(); // Correct result is 526
        resolveTask2(); // Correct result is 886
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_04();
    }

    @Override
    public void resolveTask1() throws Exception {
        List<ElvesPair> elvesPairList = _inputList.stream().map(ElvesPair::new).collect(Collectors.toList());
        List<ElvesPair> resultList = elvesPairList.stream()
                .filter(AOC_DAY_04::isCompleteOverlappingRange)
                .collect(Collectors.toList());
        int result = resultList.size();
        printResult("04", "1", "" + result);
    }

    @Override
    public void resolveTask2() throws Exception {
        List<ElvesPair> elvesPairList = _inputList.stream().map(ElvesPair::new).collect(Collectors.toList());
        List<ElvesPair> resultList = elvesPairList.stream()
                .filter(AOC_DAY_04::isPartlyOverlappingRange)
                .collect(Collectors.toList());
        int result = resultList.size();
        printResult("04", "2", "" + result);
    }

    private static boolean isCompleteOverlappingRange(ElvesPair pair) {
        boolean isRange1LoCover = pair.getRange1low() <= pair.getRange2low();
        boolean isRange1HiCover = pair.getRange1high() >= pair.getRange2high();
        boolean isRange2LoCover = pair.getRange2low() <= pair.getRange1low();
        boolean isRange2HiCover = pair.getRange2high() >= pair.getRange1high();
        return (isRange1LoCover && isRange1HiCover) || (isRange2LoCover && isRange2HiCover);
    }

    private static boolean isPartlyOverlappingRange(ElvesPair pair) {
        List<Integer> range1 = IntStream.rangeClosed(pair.getRange1low(), pair.getRange1high())
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
        List<Integer> range2 = IntStream.rangeClosed(pair.getRange2low(), pair.getRange2high())
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
        return range1.stream().filter(n -> range2.contains(n)).findAny().isPresent() ||
                range2.stream().filter(n -> range1.contains(n)).findAny().isPresent();
    }
}