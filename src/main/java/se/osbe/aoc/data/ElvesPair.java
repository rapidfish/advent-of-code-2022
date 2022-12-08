package se.osbe.aoc.data;

public class ElvesPair {
    private final int sectionsRange1Low;
    private final int sectionsRange1High;
    private final int sectionsRange2Low;
    private final int sectionsRange2High;

    public ElvesPair(String rawPairs) {
        String[] pairs = rawPairs.split(",");
        String[] range1 = pairs[0].split("-");
        String[] range2 = pairs[1].split("-");
        this.sectionsRange1Low = Integer.valueOf(range1[0]);
        this.sectionsRange1High = Integer.valueOf(range1[1]);
        this.sectionsRange2Low = Integer.valueOf(range2[0]);
        this.sectionsRange2High = Integer.valueOf(range2[1]);
    }

    public int getRange1low() {
        return sectionsRange1Low;
    }

    public int getRange1high() {
        return sectionsRange1High;
    }

    public int getRange2low() {
        return sectionsRange2Low;
    }

    public int getRange2high() {
        return sectionsRange2High;
    }
}
