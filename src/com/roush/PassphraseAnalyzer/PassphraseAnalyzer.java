package com.roush.PassphraseAnalyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Reads a list of passphrases from an external file and analyzes each on to
 * ensure it is valid per the specified requirements for each activity.
 */
public class PassphraseAnalyzer {


// FIELDS, CONSTANTS, and OBJECTS


    private static final String INPUT_FILE = "input/passphrases.txt";       // Path to input file
    private boolean debug = false;                                          // Toggle debug output on/off
    private List<String[]> passphrases = new ArrayList<>();                 // Storage for input passphrases


// CONSTRUCTOR(S)


    /**
     * Initiates PassphraseAnalyzer object by reading input from external file
     * and storing passphrases in an ArrayList.
     */
    public PassphraseAnalyzer() {
        String deliminator = " ";
        Scanner file = null;

        try {
            file = new Scanner(new FileInputStream(INPUT_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (file.hasNextLine()) {
            passphrases.add(file.nextLine().split(deliminator));
        }

        file.close();

    }


// METHODS - PRIMARY


    /**
     * Tests each passphrase in passphrases ArrayList for non-unique words.
     * Passphrases are only valid if each word within is unique.  Outputs
     * number of valid passphrases to screen.
     */
    public void testForUniqueWords() {
        int validCount = 0;

        for (String[] phrase : passphrases) {
            boolean valid = true;
            outterLoop:
            for (int i = 0; i < phrase.length; i++) {
                for (int j = i + 1; j < phrase.length; j++) {
                    if (phrase[i].equals(phrase[j])) {
                        valid = false;
                        break outterLoop;
                    }
                }
            }
            if (valid) validCount++;
        }
        System.out.println("Number of valid passphrases: " + validCount);
    }

    /**
     * Tests each passphrase for unique words as well as anagrams.  Any
     * passphrase containing duplicate words, or anagram words is not valid.
     */
    public void testForAnagrams() {
        int validCount = 0;

        for (String[] phrase : passphrases) {
            boolean valid = true;
            outterLoop:
            for (int i = 0; i < phrase.length; i++) {
                for (int j = i + 1; j < phrase.length; j++) {
                    if (phrase[i].equals(phrase[j]) || isAnagram(phrase[i], phrase[j])) {
                        valid = false;

                        if (debug) {
                            System.out.println("REJECTED (" + phrase[i] + " - " + phrase[j] + ")");
                        }

                        break outterLoop;
                    }
                }
            }
            if (valid) validCount++;
        }
        System.out.println("Number of valid passphrases: " + validCount);
    }


// METHODS - SUBROUTINES


    /**
     * Compares two for to determine if they are anagrams of each other. Each
     * word is parsed into a sorted array of characters.  Then each character
     * is compared to each other.  If word lengths and each character match,
     * the word is determined to be an anagram.
     * @param word1 String first word to compare
     * @param word2 String second word to compare
     * @return boolean true if words are anagrams, false otherwise
     */
    private boolean isAnagram(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        } else {
            String[] letters1 = word1.split("");
            String[] letters2 = word2.split("");
            Arrays.sort(letters1);
            Arrays.sort(letters2);

            for (int i = 0; i < letters1.length; i++) {
                if (!(letters1[i].equals(letters2[i]))) return false;
            }
        }
        return true;
    }

    /**
     * Outputs each passphrase read in from external input file.
     */
    private void outputPassphrases() {
        for (String[] phrase : passphrases) {
            for (String word : phrase) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
    }
}
