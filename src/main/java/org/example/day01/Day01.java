package org.example.day01;

import org.example.commons.CommonUtils;
import org.example.commons.OpenFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day01 {

    private static final String sampleInputPath = "C:\\Users\\nerv\\Documents\\avalon\\adventOfCode23\\src\\main\\java\\org\\example\\day01\\Day01SampleInput.txt";
    private static final String inputPath = "C:\\Users\\nerv\\Documents\\avalon\\adventOfCode23\\src\\main\\java\\org\\example\\day01\\Day01Input.txt";

    final static OpenFile openFile = new OpenFile();

    final static CommonUtils commonUtils = new CommonUtils();

    public static void main(String[] args) throws IOException {

        partOneResult();

    }

    private static void partOneResult() throws IOException {
        final List<String> fileLines = openFile.readAllFile(inputPath);
        commonUtils.logList(fileLines);

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

    private static void partTwoResult() {

    }

}
