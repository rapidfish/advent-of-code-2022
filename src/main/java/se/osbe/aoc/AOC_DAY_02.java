package se.osbe.aoc;

import se.osbe.aoc.helper.SantasLittleHelper;

import java.util.List;
import java.util.Map;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_02 implements IResolveTasks {

    private static final String INPUTFILE1 = "input_d02_t01.txt";
    // private static String INPUTFILE2 = "input_d02_t02.txt";

    private Map<Integer, List<Integer>> _inputMap;
    private List<Integer> _result;

    public AOC_DAY_02() throws Exception {
        // _inputMap = loadInputFileToMap(INPUTFILE1);
        // _result = new ArrayList<>();
        resolveTask1();
        resolveTask2();
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_02();
    }

    @Override
    public void resolveTask1() throws Exception {
        List<String> inputList = loadFileToListOfStrings(INPUTFILE1);
        int result = inputList.stream().mapToInt(SantasLittleHelper::rockPaperScissors).sum();
        printResult("01", "2", "" + result); // Correct result is 14375
    }

//    @Override
//    public void resolveTask2() throws Exception {
//
//    }
}
