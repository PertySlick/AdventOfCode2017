package com.roush.Checksum;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Checksum {


// FIELDS, CONSTANTS, and OBJECTS


    private final String INPUT = "input/checksum.txt";              // URI for input file
    private int result = 0;                                         // Incrementing results storage
    private boolean debug = false;                                  // Set debug output on/off


// CONSTRUCTOR(S)


    /**
     * Instantiates a Checksum object to initiate the Checksum procedure
     */
    public Checksum() {
        processChecksum();
    }


// METHODS - PRIMARY


    /**
     * Opens the input file and processes each line at a time by finding the
     * highest and lowest values, determining their difference, and adding the
     * result to the result value.
     */
    private void processChecksum() {
        Scanner file = null;

        try {
            file = new Scanner(new FileInputStream(INPUT));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(file.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(file.nextLine());
            int largeNum = 0;
            int smallNum = -1;

            while (st.hasMoreTokens()) {
                int currentNum = Integer.parseInt(st.nextToken());
                if (currentNum > largeNum) largeNum = currentNum;
                if (smallNum == -1 || currentNum < smallNum) smallNum = currentNum;
                if (debug) System.out.println(
                        "=" + currentNum + " +" + largeNum + " -" + smallNum);
            }
            result += (largeNum - smallNum);
        }
        System.out.println("RESULTS: " + result);
    }

    /**
     * Toggles debug output on or off.
     * @param debug boolean value for debug status
     */
    private void setDebug(boolean debug) {
        this.debug = debug;
    }


// METHODS- SUBROUTINES

}
