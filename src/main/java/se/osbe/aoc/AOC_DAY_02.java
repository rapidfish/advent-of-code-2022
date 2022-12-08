package se.osbe.aoc;

import se.osbe.aoc.data.RPC;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static se.osbe.aoc.helper.SantasLittleHelper.*;

public class AOC_DAY_02 implements IAoCTask {

    private static final String INPUTFILE1 = "input_d02_t01.txt";

    private final List<String> _inputList;
    private static final Pattern ROCK_PAPER_SCISSORS_PATTERN = Pattern.compile("[ABC][ ][XYZ]");

    public AOC_DAY_02() throws Exception {
        _inputList = loadFileToListOfStrings(INPUTFILE1);
        resolveTask1(); // Correct result is 14375
        resolveTask2(); // Correct result is 10274
    }

    public static void main(String[] args) throws Exception {
        new AOC_DAY_02();
    }

    @Override
    public void resolveTask1() throws Exception {
        int result = _inputList.stream()
                .map(AOC_DAY_02::convertRawHandToRPC)
                .mapToInt(AOC_DAY_02::calculateScoreWinLose)
                .sum();
        printResult(this, "1", "" + result);
    }

    @Override
    public void resolveTask2() throws Exception {
        int result = _inputList.stream()
                .map(AOC_DAY_02::convertRawHandToRPC)
                .map(AOC_DAY_02::resolveStrategyGuide)
                .mapToInt(AOC_DAY_02::calculateScoreWinLose)
                .sum();
        printResult(this, "2", "" + result);
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

    private static RPC convertRawHandToRPC(String rawHands) {
        String hands = Objects.requireNonNull(rawHands);
        if (!ROCK_PAPER_SCISSORS_PATTERN.matcher(rawHands).find()) {
            throw new RuntimeException("Wrong pattern for row with 'Rock Paper Scissor' hand: " + rawHands);
        }
        return new RPC(hands.charAt(0), hands.charAt(2));
    }
}