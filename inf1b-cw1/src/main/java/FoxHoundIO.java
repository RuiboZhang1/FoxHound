import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {

    public static boolean saveGame (String[] players, char figure, Path input) {
        if (input == null) {
            throw new NullPointerException();
        }

        if (players.length != 5) {
            throw new IllegalArgumentException();
        }


        FileWriter writer;
        try {
            writer = new FileWriter(String.valueOf(input));
            writer.write(figure + " ");

            for (int i = 0; i < players.length; i++) {
                writer.write(players[i] + " ");
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public static char loadGame(String[] players, Path input) {
        if (players.length != 5) {
            throw new IllegalArgumentException();
        }

        if (input == null) {
            throw new NullPointerException();
        }

        String record = null;
        try {
            record = Files.readString(input);
        } catch (Exception e) {
            return '#';
        }


        String[] playersRecord = record.split(" ");

        if (playersRecord[0].length() != 1) {
            return '#';
        }

        for (int i = 1; i < players.length; i++) {
            if (Character.isDigit(playersRecord[i].charAt(0))) {
                return '#';
            }
        }

        if ((!playersRecord[0].equals("H")) && (!playersRecord[0].equals("F"))) {
            return '#';
        }

        for (int i = 1; i < players.length; i++) {
            if (FoxHoundUI.getTail(playersRecord[i]) > 8) {
                return '#';
            }
        }


        for (int i = 0; i < players.length; i++) {
            players[i] = playersRecord[i + 1];
        }



        return (playersRecord[0].charAt(0));
    }

}
