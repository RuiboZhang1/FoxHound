import java.util.Scanner;
import java.util.Objects;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 2;
    /** Main menu display string. */
    private static final String MAIN_MENU =
        "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 2;

    /** Method to print the main board. */
    private static void displayMainBoard(String[] players, int dimension, int i) {

        // when dimension > 10, we need to find the y coordinate of the fox.
        String tail = "";
        for (int j = 1; j < 3; j++ ) {
            tail += players[players.length -1].charAt(j);
        }

        for (int j = 0; j < dimension; j++) {
            boolean foundHound = false;
            boolean foundFox = false;

            for (int k = 0; k < players.length - 1; k++) {
                if (((players[k].charAt(0)) - 'A') == j && (players[k].charAt(1) - '0' == i)) {
                    foundHound = true;
                    break;
                } else foundHound = false;
            }

            if (((players[players.length - 1].charAt(0)) - 'A') == j && (Integer.parseInt(tail) == i)) {
                foundFox = true;
            }

            if (foundHound) {
                System.out.print(FoxHoundUtils.HOUND_FIELD);
            } else if (foundFox) {
                System.out.print(FoxHoundUtils.FOX_FIELD);
            } else {
                System.out.print('.');
            }
        }
    }


    /** Receive two arguments and display the board. */
    public static void displayBoard(String[] players, int dimension) {
        // initialise a switcher to check if the dimension is smaller than 10.
        int switcher;
        if (dimension < 10) {
            switcher = 0;
        } else {
            switcher = 1;
        }


        // Print the letters line
        System.out.print(" ".repeat(switcher+2));

        for (int i = 0; i < dimension; i++) {
            System.out.print((char) ('A' + i));
        }


        // Print the empty line
        System.out.print("\n");


        // Print the main graph
        // 1. if the dimension < 10, print only the number
        if (dimension < 10) {
            for (int i = 1; i < dimension + 1; i++) {
                System.out.print(i + " ");

                displayMainBoard(players, dimension, i);

                System.out.print(" " + i);

                System.out.print("\n");
            }
            // 2. if the dimension > 10, first 9 number print with 0
            // after only print with the number
        } else {
            for (int i = 1; i < 10; i++) {
                System.out.print("0" + i + " ");

                displayMainBoard(players,dimension,i);

                System.out.print(" 0" + i );

                System.out.print("\n");
               }
            for (int i = 10; i < dimension+1; i++) {
                System.out.print(i + " ");

                displayMainBoard(players, dimension, i);

                System.out.print(" " + i);

                System.out.print("\n");
           }
        }

        System.out.print("\n");

        System.out.print(" ".repeat(switcher+2));

        for (int i = 0; i < dimension; i++) {
            System.out.print((char) ('A' + i));
        }

        System.out.print("\n");

    }

    /**
     * Print the main menu and query the user for an entry selection.
     * 
     * @param figureToMove the figure type that has the next move
     * @param stdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException if the given Scanner is null
     */
    public static int mainMenuQuery(char figureToMove, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD 
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure = 
            figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

        int input = -1;
        while (input == -1) {
            System.out.println(nextFigure + " to move");
            System.out.println(MAIN_MENU);

            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES;
            }

            if (!validInput) {
                System.out.println("Please enter valid number.");
                input = -1; // reset input variable
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }
}







