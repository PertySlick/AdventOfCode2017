/*
Filename:       AdventOFCode.java
Author:         Timothy Roush
Date:           December 8th, 2017
Description:    Advent Of Code 2017 - Day 1: Inverse Captcha
 */

package com.roush.AdventOfCode;

import com.roush.InverseCaptcha.InverseCaptcha;

import java.util.Scanner;

/**
 * A menu driven environment for seeing my responses to the AdventOfCode.com
 * 2017 challenges.
 */
public class AdventOfCode {


// FIELDS AND OBJECTS


    private static boolean exit = false;
    private static Scanner kb = new Scanner(System.in);


// METHODS - PRIMARY


    /**
     * Main driver method for displaying and handling a menu to view all advent
     * responses.
     * @param args Command line input not used
     */
    public static void main(String[] args) {
        while(!exit) {
            menuDisplay();
        }
    }


// METHODS - SUBROUTINES


    /**
     * Displays a menu for choosing which Advent response to view
     */
    private static void menuDisplay() {
        System.out.println("Advent Of Code 2017");
        System.out.println("###################");
        System.out.println("Day 1: Inverse Captcha");
        System.out.println("(1) Match Next Index");
        System.out.println("(2) Match Index At Half Length");
        System.out.println("--------------------");
        System.out.println("(x) Quit");
        System.out.println("####################");
        System.out.print("Your Selection: ");
        String choice = kb.nextLine();
        processChoice(choice);
    }

    /**
     * Process the choice input provided by user and directs them to their
     * chosen module.
     * @param choice Input from user
     */
    private static void processChoice(String choice) {
        switch (choice) {
            case "1":
                new InverseCaptcha().byNextIndex();
                pause();
                break;
            case "2":
                new InverseCaptcha().byHalfLength();
                pause();
                break;
            case "x":
                System.out.println("Be Well!");
                exit = true;
                break;
            default:
                System.out.println("Unrecognized input.  Please try again!");
                pause();
                break;
        }
    }

    /**
     * Utility class to shorthand pausing for user to press "Enter" to continue
     */
    private static void pause() {
        System.out.print("(ENTER) to continue...");
        kb.nextLine();
    }

    /**
     * Utility class for clearing the scanner after certain inputs
     */
    private static void clearScanner() {
        if(kb.hasNextLine()) kb.nextLine();
    }
}
