package se.osbe.aoc;

import se.osbe.aoc.helper.SantasLittleHelper;

import java.util.List;
import java.util.Map;

import static se.osbe.aoc.helper.SantasLittleHelper.*;

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
        int result = inputList.stream()
                .map(SantasLittleHelper::convertRawHandToRPC)
                .mapToInt(AOC_DAY_02::calculateScoreWinLose)
                .sum();
        printResult("02", "1", "" + result); // Correct result is 14375
    }

    @Override
    public void resolveTask2() throws Exception {
        List<String> inputList = loadFileToListOfStrings(INPUTFILE1);
        int result = inputList.stream()
                .map(SantasLittleHelper::convertRawHandToRPC)
                .map(AOC_DAY_02::resolveStrategyGuide)
                .mapToInt(AOC_DAY_02::calculateScoreWinLose)
                .sum();
        printResult("02", "2", "" + result); // Correct result is 10274
    }

    private static RPC resolveStrategyGuide(RPC hand) {
        // Players hand says how the round needs to end
        char playersHand = ' ';
        switch (hand.getPlayerHand()) { // X means you need to lose
            case 'X':
                switch (hand.getOpponentHand()) {
                    case 'A':
                        playersHand = 'Z'; // Scissors (Z) loose to rock (A)
                        break;
                    case 'B':
                        playersHand = 'X'; // Rock (X) loose to paper (B)
                        break;
                    case 'C':
                        playersHand = 'Y'; // Paper (Y) loose to scissors (C)
                        break;
                }
                break;
            case 'Y': // Y means you need to end the round in a draw,
                // by setting playershand to same as opponent hand.
                switch (hand.getOpponentHand()) {
                    case 'A':
                        playersHand = 'X';
                        break;
                    case 'B':
                        playersHand = 'Y';
                        break;
                    case 'C':
                        playersHand = 'Z';
                        break;
                }
                break;
            case 'Z': // Z means you need to win
                switch (hand.getOpponentHand()) {
                    case 'A':
                        playersHand = 'Y'; // Paper win over rock
                        break;
                    case 'B':
                        playersHand = 'Z'; // Scissors win over paper
                        break;
                    case 'C':
                        playersHand = 'X'; // Rock win over scissors
                        break;
                }
                break;
        }
        return new RPC(hand.getOpponentHand(), playersHand);
    }

    private static int calculateScoreWinLose(RPC hand) {
        // score for draw/win/lose
        if (hand.getPlayerHand() == 'X') {
            return 1 + (compareHands(hand) > 0 ? 6 : (compareHands(hand) == 0 ? 3 : 0));
        }
        if (hand.getPlayerHand() == 'Y') {
            return 2 + (compareHands(hand) > 0 ? 6 : (compareHands(hand) == 0 ? 3 : 0));
        }
        if (hand.getPlayerHand() == 'Z') {
            return 3 + (compareHands(hand) > 0 ? 6 : (compareHands(hand) == 0 ? 3 : 0));
        }
        throw new RuntimeException("Crazy error");
    }
}