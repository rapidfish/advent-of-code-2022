package se.osbe.aoc.helper;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Objects.requireNonNull;

public class Rucksack {
    private final char[] leftCompartment;
    private final char[] rightCompartment;

    public Rucksack(String str) {
        requireNonNull(str);
        int offset = str.length() / 2;
        // Analyze
        leftCompartment = str.substring(0, offset).toCharArray(); // left compartment
        rightCompartment = str.substring(offset).toCharArray(); // right compartment
    }

    public String getLeftCompartment() { return new String(leftCompartment); }

    public String getRightCompartment() {
        return new String(rightCompartment);
    }

    @Override
    public String toString() {
        return getLeftCompartment() + getRightCompartment();
    }

//    public String getLeftCompartmentSorted() {
//        char[] temp = new String(leftCompartment).toCharArray(); // deep copy char array!
//        Arrays.sort(temp);
//        return new String(temp);
//    }
//
//    public String getRightCompartmentSorted() {
//        char[] temp = new String(rightCompartment).toCharArray(); // deep copy char array!
//        Arrays.sort(temp);
//        return new String(temp);
//    }
}
