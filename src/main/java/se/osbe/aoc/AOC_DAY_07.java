package se.osbe.aoc;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_07 implements IAoCTask {

    private static final String INPUTFILE1 = "input_d07_t01.txt";

    private List<String> _inputList;

    public AOC_DAY_07() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        resolveTask1(); // Correct result is nnn
        resolveTask2(); // Correct result is nnn
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_07();
    }

    @Override
    public void resolveTask1() throws Exception {
        // TODO:
        final Pattern p = Pattern.compile("\\d+");
        Integer result = null;
        Integer totalSizeOfEveryFile = // 42143088
                _inputList.stream()
                .filter(row -> p.matcher(row).find())
                .map(row -> row.replaceAll("\\D+", ""))
                .map(Integer::parseInt)
                .collect(Collectors.summingInt(Integer::intValue));

        printResult(this, "1", "" + result);
    }

    @Override
    public void resolveTask2() throws Exception {
        Object result = null;
        printResult(this, "2", "" + result);
    }


}