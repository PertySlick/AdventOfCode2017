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
    private int currentTotal;                               // Current total for sum of neighbors


// CONSTRUCTOR(S)


    /**
     * Instantiates a SpiralMemory object by initializing the matrix for
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

    /**
     * Populates the spiral matrix by making each new value the next value
     * incrementing from 1.  Matrix is populated in a counter-clockwise spiral
     * pattern starting in the middle vertex which has been pre-populated with
     * a value.
     */
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
        if(debug) { displayMatrix(); }

        System.out.println(
                "Manhattan Distance to " + INPUT + ": " +
                        (Math.abs(initialX - currentX) + Math.abs(initialY - currentY))
        );
    }

    /**
     * Populates the spiral matrix by making each new value the sum of all
     * neighboring vertexes.  Matrix is populated in a counter-clockwise spiral
     * pattern starting in the middle vertex which has been pre-populated with
     * a value.
     */
    public void neighborSum() {
        currentTotal = 0;

        for (int distance = 1; currentTotal < INPUT; distance++) {

            for (int x = distance; x > 0 && currentTotal < INPUT; x--) {
                matrix[currentX][currentY += 1] = sumOfNeighbors(currentX, currentY);
            }
            for (int y = distance; y > 0 && currentTotal < INPUT; y--) {
                matrix[currentX -= 1][currentY] = sumOfNeighbors(currentX, currentY);
            }
            distance++;

            for (int x = distance; x > 0 && currentTotal < INPUT; x--) {
                matrix[currentX][currentY -= 1] = sumOfNeighbors(currentX, currentY);
            }
            for (int y = distance; y > 0 && currentTotal < INPUT; y--) {
                matrix[currentX += 1][currentY] = sumOfNeighbors(currentX, currentY);
            }
        }

        // Displays spiral matrix output
        if(debug) { displayMatrix(); }

        System.out.println(
                "Largest value after " + INPUT + ": " + currentTotal
        );
    }


// METHODS - SUBROUTINES


    /**
     * Sums up all neighboring values of the 2D array vertex supplied.
     * @param xCoord int x coordinate of current matrix vertex
     * @param yCoord int y coordinate of current matrix vertex
     * @return total int sum of all neighboring values
     */
    private int sumOfNeighbors(int xCoord, int yCoord) {
        int total = 0;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;

                int newX = xCoord + x;
                int newY = yCoord + y;

                if (newX >= 0 && newX < width && newY >= 0 && newY < width) {
                    total += matrix[newX][newY];
                    currentTotal = total;
                }
            }
        }
        return total;
    }

    /**
     * Reusable code block for outputting the entire spiral matrix.
     */
    private void displayMatrix() {
        for (int[] row : matrix) {
            for (int value : row) { System.out.print(value + "\t"); }
            System.out.println();
        }
    }

}
