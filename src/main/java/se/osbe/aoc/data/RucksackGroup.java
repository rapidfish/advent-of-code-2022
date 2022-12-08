package se.osbe.aoc.data;

import java.util.Arrays;
import java.util.List;

public class RucksackGroup {
    private List<Rucksack> rucsacks;

    private RucksackGroup() {
        // unused, locked constructor
        this.rucsacks = null;
    }

    public RucksackGroup(Rucksack r1, Rucksack r2, Rucksack r3) {
        this.rucsacks = Arrays.asList(r1, r2, r3);
    }

    public Rucksack getRucksackByIndex(int index) {
        return rucsacks.get(index);
    }
}
