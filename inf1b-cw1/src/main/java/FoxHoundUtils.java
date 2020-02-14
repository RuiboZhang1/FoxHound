/**
 * A utility class for the fox hound program.
 * <p>
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {

    // ATTENTION: Changing the following given constants can 
    // negatively affect the outcome of the auto grading!

    /**
     * Default dimension of the game board in case none is specified.
     */
    public static final int DEFAULT_DIM = 8;
    /**
     * Minimum possible dimension of the game board.
     */
    public static final int MIN_DIM = 4;
    /**
     * Maximum possible dimension of the game board.
     */
    public static final int MAX_DIM = 26;

    /**
     * Symbol to represent a hound figure.
     */
    public static final char HOUND_FIELD = 'H';
    /**
     * Symbol to represent the fox figure.
     */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...

    public static String[] initialisePositions(int dimension) {
        if (dimension < MIN_DIM || dimension > MAX_DIM) {
            throw new IllegalArgumentException();
        }


        int middle = dimension / 2;
        String[] position = new String[middle + 1];
        for (int i = 0; i < middle; i++) {
            position[i] = (char) ('B' + i * 2) + "1";  // add position of hound in to the array
        }

        if (dimension % 2 == 0) {    // if the dimension is even
            if (middle % 2 == 0) {
                position[middle] = (char) (65 + middle) + Integer.toString(dimension); // if the middle position is even
                // the fox will add the right of the middle
            } else {
                position[middle] = (char) (64 + middle) + Integer.toString(dimension); // if the middle is odd
                // add the position into the middle
            }
        } else {
            if (((dimension + 1) / 2) % 2 == 0) {           // same procedure for the odd dimension
                position[middle] = (char) (65 + middle) + Integer.toString(dimension);
            } else {
                position[middle] = (char) (66 + middle) + Integer.toString(dimension);
            }
        }
        return position;
    }


    /**
     * Check if the move is valid
     */
    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String dest) {
        boolean isValid = false;

        // Check if the figure is valid

        // check if the figure is in the origin
        if (figure == 'H') {
            for (int i = 0; i < players.length - 1; i++) {
                if (players[i].equals(origin)) {
                    isValid = true;
                    break;
                }
            }
            if (isValid == false) {
                return isValid;
            }
        } else if (figure == 'F') {
            if (players[players.length - 1].equals(origin)) {
                isValid = true;
            } else {
                return false;
            }
        }


        // hound can't go back
        if (figure == 'H') {
            if (FoxHoundUI.getTail(origin) < FoxHoundUI.getTail(dest)) {
                isValid = true;
            } else {
                return false;
            }
        }


        // check if the dest is out of dim
        if (FoxHoundUI.getTail(dest) <= dim && FoxHoundUI.getTail(dest) > 0) {
            isValid = true;
        } else {
            return false;
        }

        boolean firstBool = (origin.charAt(0) + 1 == dest.charAt(0) || origin.charAt(0) - 1 == dest.charAt(0));

        // check if the dest can be reach by origin
        if (figure == 'F') {
            boolean secondBool = ((FoxHoundUI.getTail(origin)) + 1 == FoxHoundUI.getTail(dest) || FoxHoundUI.getTail(origin) - 1 == FoxHoundUI.getTail(dest));
            if (firstBool && secondBool) {
                isValid = true;
            } else {
                return false;
            }
        } else {
            boolean secondBool = (FoxHoundUI.getTail(origin)) + 1 == FoxHoundUI.getTail(dest);
            if (firstBool && secondBool) {
                isValid = true;
            } else {
                return false;
            }
        }


        // check if the dest occupied by other figures
        for (int i = 0; i < players.length; i++) {
            if (players[i].equals(dest)) {
                isValid = false;
                return isValid;
            } else {
                isValid = true;
            }

        }

        return isValid;
    }


    public static boolean isHoundWin(String[] players, int dimension) {
        boolean win = true;
        String foxPosition = players[players.length - 1];

        char foxLetter = foxPosition.charAt(0);
        int foxNum = FoxHoundUI.getTail(foxPosition);

        String[] testFox =
                {(Character.toString(foxLetter + 1) + Integer.toString(foxNum + 1))
                ,(Character.toString(foxLetter + 1) + Integer.toString(foxNum - 1))
                ,(Character.toString(foxLetter - 1) + Integer.toString(foxNum + 1))
                ,(Character.toString(foxLetter - 1) + Integer.toString(foxNum - 1))};

        for (String test : testFox) {
            if (isValidMove(dimension,players,'F',foxPosition,test) == true) {
                return false;
            }
        }

        return  win;
    }


    public static boolean isFoxWin(String foxPosition) {
        if (FoxHoundUI.getTail(foxPosition) == 1) {
            return true;
        }
        return false;
    }
}
