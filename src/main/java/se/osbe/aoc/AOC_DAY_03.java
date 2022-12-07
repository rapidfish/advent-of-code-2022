package se.osbe.aoc;

import se.osbe.aoc.helper.Rucksack;
import se.osbe.aoc.helper.SantasLittleHelper;

import java.util.List;
import java.util.stream.Collectors;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_03 implements IAoCTask {

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
        List<Rucksack> rucksacks = _inputList.stream().map(Rucksack::new).collect(Collectors.toList());
        List<Integer> prioList = rucksacks.stream()
                .map(SantasLittleHelper::resolveDuplicateItem)
                .map(SantasLittleHelper::resolveItemPriority)
                .collect(Collectors.toList());
        int result = prioList.stream().collect(Collectors.summingInt(Integer::intValue));
        printResult("03", "1", "" + result); // Correct result is 8493
    }

    @Override
    public void resolveTask2() throws Exception {
        Object result = null;
        printResult("03", "2", "" + result); // Correct result is nnn
    }
}