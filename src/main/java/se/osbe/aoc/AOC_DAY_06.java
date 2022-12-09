package se.osbe.aoc;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public class AOC_DAY_06 implements IAoCTask {

    private static final String INPUTFILE1 = "input_d06_t01.txt";

    private List<String> _inputList;
    private String _inputSignals;

    public AOC_DAY_06() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        _inputSignals = _inputList.get(0);
        Objects.requireNonNull(_inputList);
        resolveTask1(); // Correct result is 1929
        resolveTask2(); // Correct result is 3298
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_06();
    }

    @Override
    public void resolveTask1() throws Exception {
        final int NO_OF_DISTINCT_CHARS_BEFORE_START_OF_PACKET = 4;
        int result = findIndexToSequenceStart(NO_OF_DISTINCT_CHARS_BEFORE_START_OF_PACKET);
        printResult(this, "1", "" + result);
    }

    @Override
    public void resolveTask2() throws Exception {
        final int NO_OF_DISTINCT_CHARS_BEFORE_MESSAGE_START = 14;
        int result = findIndexToSequenceStart(NO_OF_DISTINCT_CHARS_BEFORE_MESSAGE_START);
        printResult(this, "2", "" + result);
    }

    private int findIndexToSequenceStart(int noOfUniqeChars) {
        return IntStream.range(noOfUniqeChars, _inputSignals.length()).filter(i -> {
            char[] sequenceCandidate = _inputSignals.substring((i - noOfUniqeChars), i).toCharArray();
            int counter = 0;
            for(char c1 : sequenceCandidate) {
                for(char c2 : sequenceCandidate) {
                    counter += (c1 == c2) ? 1 : 0;
                }
            }
            return (counter == noOfUniqeChars); // 4 == a score of four when all chars are unique
        }).findFirst().getAsInt();
    }
}