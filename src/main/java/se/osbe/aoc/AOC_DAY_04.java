package se.osbe.aoc;//package se.osbe.aoc;
//
//import java.util.List;
//
//import static se.osbe.aoc.helper.SantasLittleHelper.loadFileToListOfStrings;
//import static se.osbe.aoc.helper.SantasLittleHelper.printResult;
//
//public class AOC_DAY_NN implements IResolveTasks {
//
//    private static final String INPUTFILE1 = "input_d03_t01.txt";
//
//    private List<String> _inputList;
//
//    public AOC_DAY_NN() throws Exception {
//        _inputList = loadFileToListOfStrings(INPUTFILE1);
//        resolveTask1();
//        resolveTask2();
//    }
//
//    public static void main(String[] args) throws Exception {
//        new AOC_DAY_NN();
//    }
//
//    @Override
//    public void resolveTask1() throws Exception {
//        Object result = null;
//        printResult("03", "1", "" + result); // Correct result is 14375
//    }
//
//    @Override
//    public void resolveTask2() throws Exception {
//        Object result = null;
//        printResult("03", "2", "" + result); // Correct result is 10274
//    }
//}