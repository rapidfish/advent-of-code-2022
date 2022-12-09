package se.osbe.aoc.helper;

import java.util.Deque;
import java.util.LinkedList;

public class CraterStack {
    private Deque<Character> craterStack;

    public CraterStack() {
        this.craterStack = new LinkedList<>();
    }

    public void pushCrater(Character c) {
        this.craterStack.push(c);
    }

    public Character popCrater() {
        return this.craterStack.pop();
    }

    public Character peekTopCrater() {
        return this.craterStack.peekLast();
    }

}
