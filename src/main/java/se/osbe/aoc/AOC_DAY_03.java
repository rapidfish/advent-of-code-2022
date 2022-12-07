package se.osbe.aoc;

import se.osbe.aoc.helper.SantasLittleHelper;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import static se.osbe.aoc.helper.SantasLittleHelper.*;

public class AOC_DAY_03 implements IResolveTasks {

    private static final String INPUTFILE1 = "input_d03_t01.txt";

    private List<String> _inputList;

    public AOC_DAY_03() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        resolveTask1();
        resolveTask2();
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_03();
    }

    @Override
    public void resolveTask1() throws Exception {
        Object result = null;
        _inputList.stream().forEach(r -> System.out.println(AOC_DAY_03.getLeftCompartment(r)));
        SortedSet<String> leftCompartment = ;
        printResult("03", "1", "" + result); // Correct result is 14375
    }

    @Override
    public void resolveTask2() throws Exception {
        Object result = null;
        printResult("03", "2", "" + result); // Correct result is 10274
    }

    private static String getLeftCompartment(String rucksack) {
        return rucksack.substring(0, rucksack.length() / 2);
    }

    private static String getRightCompartment(String rucksack) {
        return rucksack.substring(rucksack.length() / 2);
    }
}