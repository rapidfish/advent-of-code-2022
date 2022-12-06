package se.osbe.aoc;

/**
 * Opponent hand ('A' for Rock, 'B' for Paper, and 'C' for Scissors)
 * Player hand ('X' for Rock, 'Y' for Paper, and 'Z' for Scissors)
 */
public class RPC {
    private final char opponent;
    private final char player;

    public RPC(char opponent, char player) {
        this.opponent = opponent;
        this.player = player;
    }

    public char getOpponentHand() {
        return opponent;
    }

    public char getPlayerHand() {
        return player;
    }
}
