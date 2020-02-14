import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * A utility class for the fox hound program.
 * 
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {

    public static boolean saveGame (String[] players, char figure, Path input) {
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

    }

}
