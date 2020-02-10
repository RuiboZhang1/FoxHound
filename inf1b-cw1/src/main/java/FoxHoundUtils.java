/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {

    // ATTENTION: Changing the following given constants can 
    // negatively affect the outcome of the auto grading!

    /** Default dimension of the game board in case none is specified. */
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possible dimension of the game board. */
    public static final int MAX_DIM = 26;

    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...

    public static String[] initialisePositions(int dimension) {
        int middle = dimension / 2;
        String[] position = new String[middle+1];
        for (int i = 0; i < middle; i++) {
            position[i] = (char)('B' + i * 2) + "1";  // add position of hound in to the array
        }

        if (dimension % 2 == 0) {    // if the dimension is even
            if (middle % 2 == 0) {
                position[middle] = (char) (65+middle) + Integer.toString(dimension); // if the middle position is even
                                                                                     // the fox will add the right of the middle
            } else {
                position[middle] = (char) (64+middle) + Integer.toString(dimension); // if the middle is odd
                                                                                     // add the position into the middle
            }
        } else {
            if (((dimension + 1) / 2) % 2 == 0) {           // same procedure for the odd dimension
                position[middle] = (char) (65+middle) + Integer.toString(dimension);
            } else {
                position[middle] = (char) (66+middle) + Integer.toString(dimension);
            }
        }
        return  position;
    }


    /** Check if the move is valid */
    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String dest) {
        // check if the figure is in the origin

        // check if the dest is out of dim

        // check if the dest can be reach by origin

        // check if the dest occupied by other figures


        return true;
    }



    public static boolean isHoundWin(String[] players, int dimension) {
        return true;
    }


    public static void isFoxWin(Object o) {
    }
}
