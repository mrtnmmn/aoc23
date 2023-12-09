package org.example.day02;

import org.example.commons.CommonUtils;
import org.example.commons.OpenFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Day02 {

    public static final String sampleInputPath = "/Users/martin/Documents/avalon/aoc23/src/main/java/org/example/day02/Day02SampleInput.txt";

    public static final String inputPath = "/Users/martin/Documents/avalon/aoc23/src/main/java/org/example/day02/Day02Input.txt";

    public static final HashMap<String, Integer> maxValues = new HashMap<>();

    public static final HashMap<Integer, Boolean> lineMaxValues = new HashMap<>();

    final static OpenFile openFile = new OpenFile();

    final static CommonUtils commonUtils = new CommonUtils();

    public static void main(String[] args) throws IOException {

        fillMap();
        partTwoResult(openFile.readAllFile(inputPath));

    }

    private static void partOneResult(List<String> fileLines) {
        AtomicReference<Integer> result = new AtomicReference<>(0);

        for(String line: fileLines) {
           separateLine(line);
        }

        lineMaxValues.forEach((key, value) -> {
            if (value) {
                result.updateAndGet(v -> v + key);
            }
        });

        System.out.println("Result of the operation: " + result);
    }

    private static void partTwoResult(List<String> fileLines) {
        Integer result = 0;
        for(String line: fileLines) {
            result += processLineV2(line);
        }

        System.out.println("Result of the operation: " + result);
    }

    public static void separateLine(String line) {
        String[] auxArray = line.split(":");
        Integer gameNumber = Integer.valueOf(auxArray[0].split(" ")[1]);
        List<String> resultList = mergeAllCubesFromGame(auxArray[1]);
        boolean isValidGame = true;
        for(String color: resultList) {
            String[] splittedBySpace = color.trim().split(" ");
            if (Integer.parseInt(splittedBySpace[0]) > maxValues.get(splittedBySpace[1])) {
                isValidGame = false;
            }
        }
        lineMaxValues.put(gameNumber, isValidGame);
    }

    public static List<String> mergeAllCubesFromGame(String line ) {
        String[] gameArray = line.split(";");
        List<String> aux = Arrays.stream(gameArray).toList();
        return aux.stream().flatMap(s -> Arrays.stream(s.split(","))).toList();
    }

    public static Integer processLineV2(String line ) {
        final HashMap<String, Integer> maxValuePerColor = initializeMapForPartTwo();
        List<String> mergedLine = mergeAllCubesFromGame(line.split(":")[1]);

        for (String color: mergedLine) {
            String[] splittedColor = color.trim().split(" ");
            int colorValue = Integer.parseInt(splittedColor[0]);
            String colorName = splittedColor[1];
            if (colorValue > maxValuePerColor.get(colorName)) {
                maxValuePerColor.put(colorName, colorValue);
            }
        }
        AtomicInteger aux = new AtomicInteger(1);
        maxValuePerColor.forEach((k, v) -> {
            aux.updateAndGet(v1 -> v1 * v);
        });

        return aux.get();
    }

    public static void fillMap() {
        maxValues.put("red", 12);
        maxValues.put("green", 13);
        maxValues.put("blue", 14);
    }

    public static HashMap<String, Integer> initializeMapForPartTwo() {
        HashMap<String, Integer> returnMap = new HashMap<>();
        returnMap.put("red", 0);
        returnMap.put("green", 0);
        returnMap.put("blue", 0);
        return returnMap;
    }
}
