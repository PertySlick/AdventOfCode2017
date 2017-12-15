package com.roush.SpiralMemory;

/**
 * Performs Spiral Memory function as prescribed by the AdventofCode 2017
 * activity for Day 3.
 *
 * @author Timothy Roush
 * @version 1.0
 */
public class SpiralMemory {


// FIELDS, CONSTANTS and OBJECTS


    private static final int INPUT = 361527;                // Assigned project input
    private static final int INITIAL_VALUE = 1;             // Starting value for matrix data
    private boolean debug = false;                          // Toggles debug output on/off
    private int[][] matrix;                                 // Initialize 2D array for matrix
    private int width;                                      // Width of matrix array (rows and columns)
    private int initialX, initialY;                         // Starting point for plotting data
    private int currentX, currentY;                         // Current X/Y coordinates


// CONSTRUCTOR(S)


    /**
     * Instanciates a SpiralMemory object by initializing the matrix for
     * plotting data.
     */
    public SpiralMemory() {
        initializeMatrix();
    }


// METHODS - PRIMARY


    /**
     * Sets the size of the matrix based on the input provided.  Then sets the
     * starting point for plotting data and sets the current position to match.
     * Initial starting point in matrix is populated with the initial value.
     */
    private void initializeMatrix() {
        width = (int) Math.ceil(Math.sqrt(INPUT));
        matrix = new int[width][width];

        initialX = (int) Math.floor((width - 1) / 2);
        initialY = initialX;

        currentX = initialX;
        currentY = initialY;

        matrix[currentX][currentY] = INITIAL_VALUE;
    }

    public void sequentialData() {
        for (int distance = 1, i = 1; i < INPUT; distance++) {

            for (int x = distance; x > 0 && i < INPUT; x--) {
                matrix[currentX][currentY += 1] = ++i;
            }
            for (int y = distance; y > 0 && i < INPUT; y--) {
                matrix[currentX -= 1][currentY] = ++i;
            }
            distance++;

            for (int x = distance; x > 0 && i < INPUT; x--) {
                matrix[currentX][currentY -= 1] = ++i;
            }
            for (int y = distance; y > 0 && i < INPUT; y--) {
                matrix[currentX += 1][currentY] = ++i;
            }
        }

        // Displays spiral matrix output
        if(debug) {
            for (int[] row : matrix) {
                for (int value : row) { System.out.print(value + "\t"); }
                System.out.println();
            }
        }

        System.out.println(
                "Manhattan Distance to " + INPUT + ": " +
                (Math.abs(initialX - currentX) + Math.abs(initialY - currentY))
        );
    }

}
