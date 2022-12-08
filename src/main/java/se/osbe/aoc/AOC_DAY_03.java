package se.osbe.aoc;

import se.osbe.aoc.data.Rucksack;
import se.osbe.aoc.data.RucksackGroup;

import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_03 implements IAoCTask {

    private static final String INPUTFILE1 = "input_d03_t01.txt";
    private final List<String> _inputList;


    public AOC_DAY_03() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        resolveTask1(); // Correct result is 8493
        resolveTask2(); // Correct result is 2552
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_03();
    }

    @Override
    public void resolveTask1() throws Exception {
        List<Rucksack> rucksacks = _inputList.stream().map(Rucksack::new).collect(Collectors.toList());
        List<Integer> prioList = rucksacks.stream()
                .map(AOC_DAY_03::resolveDuplicateItem)
                .map(AOC_DAY_03::resolveItemPriority)
                .collect(Collectors.toList());
        int result = prioList.stream().collect(Collectors.summingInt(Integer::intValue));
        printResult("03", "1", "" + result);
    }

    @Override
    public void resolveTask2() throws Exception {
        List<Rucksack> rucksacks = _inputList.stream().map(Rucksack::new).collect(Collectors.toList());
        List<Integer> prioList = IntStream.range(0, rucksacks.size() / 3)
                .mapToObj(i -> new RucksackGroup(rucksacks.get(0 + i * 3), rucksacks.get(1 + i * 3), rucksacks.get(2 + i * 3)))
                .map(AOC_DAY_03::resolveGroupBadge)
                .map(AOC_DAY_03::resolveItemPriority)
                .collect(Collectors.toList());
        int result = prioList.stream().collect(Collectors.summingInt(Integer::intValue));
        printResult("03", "2", "" + result);
    }

    private static Character resolveDuplicateItem(Rucksack rucksack) {
        char[] left = rucksack.getLeftCompartment().toCharArray();
        List<Character> rightCompartmentList = rucksack.getRightCompartment().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        OptionalInt optInt = IntStream.range(0, left.length).map(i -> {
            if (rightCompartmentList.contains(left[i])) {
                return i;
            } else {
                return -1;
            }
        }).filter(i -> i != -1).findFirst();
        return new Character(left[optInt.orElseThrow(RuntimeException::new)]);
    }

    private static int resolveItemPriority(char c) {
        /* priority:
         * Lowercase item types a through z have priorities 1 through 26.
         * Uppercase item types A through Z have priorities 27 through 52.
         */
        // 65=A
        // 97=a
        if (c >= 65 && c < (65 + 26)) {
            // A-Z
            return (c - 65) + 27;
        }
        if (c > (65 + 25)) {
            // a-z
            return (c - 97) + 1;
        }
        throw new RuntimeException("Priority error!");
    }

    private static Character resolveGroupBadge(RucksackGroup rucksackGroup) {
        Objects.requireNonNull(rucksackGroup);
        char[] rucksack0 = rucksackGroup.getRucksackByIndex(0).toString().toCharArray();
        char[] rucksack1 = rucksackGroup.getRucksackByIndex(1).toString().toCharArray();
        char[] rucksack2 = rucksackGroup.getRucksackByIndex(2).toString().toCharArray();
        for (char c0 : rucksack0) {
            for (char c1 : rucksack1) {
                if (c0 == c1) {
                    for (char c2 : rucksack2) {
                        if (c0 == c2) {
                            return c0; // bingo!
                        }
                    }
                }
            }
        }
        throw new RuntimeException("resolveGroupBadge error");
    }
}