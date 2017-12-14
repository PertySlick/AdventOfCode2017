package com.roush.Checksum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Provides a Checksum object for performing checksum operations according to
 * the Advent of Code 2017 assignment parameters.
 */
public class Checksum {


// FIELDS, CONSTANTS, and OBJECTS


    private final String INPUT = "input/checksum.txt";              // URI for input file
    private List<Integer[]> inputLines = new ArrayList<>();         // Storage for each line of input
    private boolean debug = false;                                  // Set debug output on/off


// CONSTRUCTOR(S)


    /**
     * Instantiates a Checksum object to initiate the Checksum procedure
     */
    public Checksum() {
        readInput();
    }


// METHODS - PRIMARY


    /**
     * Opens the input file and parses it into usable arrays for further use.
     */
    private void readInput() {
        String deliminator = "\t";
        Scanner file = null;

        try {
            file = new Scanner(new FileInputStream(INPUT));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(file.hasNextLine()) {
            String[] stringValues = file.nextLine().split(deliminator);
            Integer[] intValues = new Integer[stringValues.length];

            for (int i = 0; i < stringValues.length; i++) {
                intValues[i] = Integer.parseInt(stringValues[i]);
            }

            inputLines.add(intValues);
        }

    }

    /**
     * Processes each line of the supplied input by finding the highest and
     * lowest values, determining their difference, and adding the results to
     * the result value.
     */
    public void minMaxChecksum() {
        int result = 0;
        int largeNum = 0;
        int smallNum = -1;

        for (Integer[] line : inputLines) {
            for (int currentNum : line) {
                if (currentNum > largeNum) largeNum = currentNum;
                if (smallNum == -1 || currentNum < smallNum) smallNum = currentNum;
                if (debug) System.out.println(
                        "=" + currentNum + " +" + largeNum + " -" + smallNum);
            }
            result += (largeNum - smallNum);
        }
        System.out.println("Checksum: " + result);
    }

    /**
     * Performs a checksum operation on supplied input by finding the first
     * (and assumed only) value evenly divisible by another in each row, and
     * adding their quotients together to form a final result.
     */
    public void divisibleChecksum() {
        int result = 0;

        for (Integer[] line : inputLines) {
            if (debug) {
                for (int value : line) { System.out.print(value + " "); }
                System.out.println("");
            }

            // Check each value against every value in the row until target is found
            // Then move to the next row
            outerloop:
            for (int i = 0; i < line.length; i++) {
                int currentNum = line[i];

                for (int j = 0; j < line.length; j++) {
                    if (debug) {
                        System.out.println(" I: " + line[i] + " J: " + line[j]);
                    }

                    if (i == j || line[i] < line[j]) {
                        continue;
                    } else if (line[i] % line[j] == 0) {
                        result += line[i] / line[j];
                        if (debug) { System.out.println("RESULT: " + result); }
                        break outerloop;
                    }
                }
            }
        }
        System.out.println("Checksum: " + result);
    }

    /**
     * Toggles debug output on or off.
     * @param debug boolean value for debug status
     */
    private void setDebug(boolean debug) {
        this.debug = debug;
    }
}
