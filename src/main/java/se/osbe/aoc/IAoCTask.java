package se.osbe.aoc;

import static se.osbe.aoc.helper.SantasLittleHelper.printResult;

public interface IAoCTask {

    default void resolveTask1() throws Exception {
        printResult("--", "1", "(OBS! - Ej impl. ännu...)");
    }

    default void resolveTask2() throws Exception {
        printResult("--", "2", "(OBS! - Ej impl. ännu...)");
    }

}
