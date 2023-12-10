package org.example.day01;

import org.example.commons.CommonUtils;
import org.example.commons.OpenFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 {

    private static final String sampleInputPath = "C:\\Users\\nerv\\Documents\\avalon\\adventOfCode23\\src\\main\\java\\org\\example\\day01\\Day01SampleInput.txt";
    private static final String inputPath = "/Users/martin/Documents/avalon/aoc23/src/main/java/org/example/day01/Day01Input.txt";
    private static final String sampleInputPathV2 = "src/main/java/org/example/day01/Day01SampleInputPart2.txt";

    final static OpenFile openFile = new OpenFile();

    final static CommonUtils commonUtils = new CommonUtils();

    static Map<String, Integer> numbersMap;

    public static void main(String[] args) throws IOException {

        partTwoResult();

    }

    private static void partOneResult() throws IOException {
        final List<String> fileLines = openFile.readAllFile(inputPath);

        List<char[]> fileLinesAsCharArrays = new ArrayList<>();
        for(String line: fileLines) {
            fileLinesAsCharArrays.add(line.toCharArray());
        }

        int finalResult = 0;
        for(char[] charLine: fileLinesAsCharArrays){
            finalResult += Integer.parseInt(firstNumberInLine(charLine) + Character.toString(lastNumberInLine(charLine)));
        }

        System.out.println("Final result: " + finalResult);
    }

    private static char firstNumberInLine(final char[] charLine) {
        int iterations = 0;
        char currentChar;
        do {
            currentChar = charLine[iterations];
            iterations += 1;
        } while (!Character.isDigit(currentChar));
        return currentChar;
    }

    private static char lastNumberInLine(final char[] charLine) {
        int iterations = charLine.length - 1;
        char currentChar;
        do {
            currentChar = charLine[iterations];
            iterations -= 1;
        } while (!Character.isDigit(currentChar));
        return currentChar;
    }

    private static void partTwoResult() throws IOException {

        int result = 0;
        numbersMap = createStringIntegerMap();

        final List<String> fileLines = openFile.readAllFile(inputPath);
        commonUtils.logList(fileLines);

        for(String line: fileLines) {
            System.out.println("LINE: " + line);
            result += firstNumberInLinePart2(line) * 10 + lastNumberInLinePart2(line);
            System.out.println(" ");
        }

        System.out.println("RESULT: " + result);

    }

    private static Integer firstNumberInLinePart2(String line) {
        boolean doContinue = true;
        int iterations = 0;
        int returnValue = 0;
        do {
            char currentChar = line.charAt(iterations);
            if (Character.isDigit(currentChar)) {
                doContinue = false;
                returnValue = Integer.parseInt(String.valueOf(currentChar));
            } else {
                String auxString = line.substring(iterations, iterations + 6 > line.length() ? line.length() - 1 : iterations + 6);
                for(String numberAsString: numbersMap.keySet()) {
                    if(auxString.contains(numberAsString) && firstPosition(numberAsString, auxString)) {
                        returnValue = numbersMap.get(numberAsString);
                        doContinue = false;
                    }
                }
            }
            iterations += 1;
        } while (doContinue);

        System.out.println("First number of the line: " + returnValue);
        return returnValue;
    }

    private static boolean firstPosition(String numberAsString, String stringToCompare) {
        boolean result = true;
        for (int i = 0; i < numberAsString.length(); i++) {
            if (numberAsString.charAt(i) != stringToCompare.charAt(i)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static Integer lastNumberInLinePart2(String line) {
        boolean doContinue = true;
        int iterations = line.length() -1;
        int returnValue = 0;
        do {
            char currentChar = line.charAt(iterations);
            if (Character.isDigit(currentChar)) {
                doContinue = false;
                returnValue = Integer.parseInt(String.valueOf(currentChar));
            } else {
                String auxString = iterations < line.length() - 5 ? line.substring(iterations, iterations+ 5) : line.substring(iterations);
                for(String numberAsString: numbersMap.keySet()) {
                    if(auxString.contains(numberAsString)  && firstPosition(numberAsString, auxString)) {
                        returnValue = numbersMap.get(numberAsString);
                        doContinue = false;
                    }
                }
            }
            iterations -= 1;
        } while (doContinue);
        return returnValue;
    }

    private static Map<String, Integer> createStringIntegerMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        return map;
    }



}
