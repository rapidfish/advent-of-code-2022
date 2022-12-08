package se.osbe.aoc;

import java.util.List;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_08 implements IAoCTask {

    private static final String INPUTFILE1 = "input_d05_t01.txt";

    private List<String> _inputList;

    public AOC_DAY_08() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        resolveTask1(); // Correct result is nnn
        resolveTask2(); // Correct result is nnn
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_08();
    }

    @Override
    public void resolveTask1() throws Exception {
        Object result = null;
        printResult(this, "1", "" + result);
    }

    @Override
    public void resolveTask2() throws Exception {
        Object result = null;
        printResult(this, "2", "" + result);
    }
}