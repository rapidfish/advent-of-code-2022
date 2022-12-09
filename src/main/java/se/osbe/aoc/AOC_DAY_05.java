package se.osbe.aoc;

import se.osbe.aoc.helper.CraterStack;
import se.osbe.aoc.helper.SantasLittleHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_05 implements IAoCTask {

    private static final String INPUTFILE1 = "input_d05_t01.txt";

    private List<String> _inputList;
    private List<CraterStack> _stackColumns = null;
    private final static String NON_DIGITS_PATTERN = "\\D";
    private int _cutPointIndex;

    public AOC_DAY_05() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        // Find cutpoint row index to the row with column labels between crater chart diagram and rearrangement procedure list (for the giant crane operator)
        _cutPointIndex = IntStream.range(0, _inputList.size()).filter(i -> SantasLittleHelper.isOnlyDigits(_inputList.get(i))).findFirst().orElseThrow(RuntimeException::new);
        resolveTask1(); // Correct result is FCVRLMVQP
        resolveTask2(); // Correct result is RWLWGJGFD
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_05();
    }

    @Override
    public void resolveTask1() throws Exception {
        initCraterStacks();
        resolveMovementsForGiantCrateMover9000();
        printResult(this, "1", getResult());
    }

    @Override
    public void resolveTask2() throws Exception {
        initCraterStacks();
        resolveMovementsForGiantCrateMover9001();
        printResult(this, "2", "" + getResult());
    }

    private void initCraterStacks() {
        // Extract crater info
        _stackColumns = new ArrayList<>();
        List<String> rawCraterRowStrings = new ArrayList<>(_inputList.subList(0, _cutPointIndex));
        String columnLabelRow = _inputList.get(_cutPointIndex);
        Integer maxColumnIndex = Collections.max(Arrays.stream(
                        columnLabelRow.split(NON_DIGITS_PATTERN))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList())
        );
        // Init columns to have crate stacks
        IntStream.range(0, maxColumnIndex).forEach(i -> {
            _stackColumns.add(new CraterStack());
        });
        // Prepare to initialize start state for crates in the columns
        Collections.reverse(rawCraterRowStrings); // Flip order to preserve order when using push to a stack
        // Columns
        IntStream.range(0, _cutPointIndex).forEach(row -> {
            IntStream.range(0, maxColumnIndex).forEach(col -> {
                int pos = (col * 4 + 1);
                Character c = rawCraterRowStrings.get(row).charAt(pos);
                if (c != ' ') {
                    _stackColumns.get(col).pushCrater(c);
                }
            });
        });
    }

    private void resolveMovementsForGiantCrateMover9000() {
        // CrateMover 9000
        final int OFFSET = -1;
        List<String> giantCraneMovements = _inputList.subList(_cutPointIndex, _inputList.size());
        giantCraneMovements.stream().filter(row -> row.startsWith("move")).forEach(row -> {
            List<Integer> cmd = Arrays.asList(row.split(" "))
                    .stream()
                    .filter(s -> !s.matches("move|from|to"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int thisMany = cmd.get(0);
            int source = cmd.get(1) + OFFSET;
            int dest = cmd.get(2) + OFFSET;
            IntStream.rangeClosed(0, thisMany + OFFSET).forEach(n -> {
                _stackColumns.get(dest).pushCrater(_stackColumns.get(source).popCrater());
            });
        });
    }

    private void resolveMovementsForGiantCrateMover9001() {
        // CrateMover 9001
        final int OFFSET = -1;
        List<String> giantCraneMovements = _inputList.subList(_cutPointIndex, _inputList.size());
        giantCraneMovements.stream().filter(row -> row.startsWith("move")).forEach(row -> {
            List<Integer> cmd = Arrays.asList(row.split(" "))
                    .stream()
                    .filter(s -> !s.matches("move|from|to"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int thisMany = cmd.get(0);
            int source = cmd.get(1) + OFFSET;
            int dest = cmd.get(2) + OFFSET;
            CraterStack tempCraterStack = new CraterStack();
            IntStream.rangeClosed(0, thisMany + OFFSET).forEach(n -> {
                tempCraterStack.pushCrater(_stackColumns.get(source).popCrater());
            });
            IntStream.rangeClosed(0, thisMany + OFFSET).forEach(n -> {
                _stackColumns.get(dest).pushCrater(tempCraterStack.popCrater());
            });
        });
    }

    private String getResult() {
        String result = _stackColumns.stream().map(col -> col.popCrater()).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.joining());
        return result;
    }

    private Character readCraterFromReversedRaw(int col, int row) {
        return ' ';
    }
}