package org.example.commons;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public void logList (final List<String> list) {
        int iterations = 0;
        for (String value: list) {
            System.out.println("L" + iterations + ": " + value);
            iterations += 1;
        }
    }

    public List<char[]> stringListToCharList(List<String> fileLines) {
        List<char[]> fileLinesAsCharArrays = new ArrayList<>();
        for(String line: fileLines) {
            fileLinesAsCharArrays.add(line.toCharArray());
        }
        return fileLinesAsCharArrays;
    }

}
