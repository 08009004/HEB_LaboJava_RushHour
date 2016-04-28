package g42116.rushhour;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.view.RushHourView;
import g42116.rushhour.view.Display;
import java.io.File;
import g42116.rushhour.JsonIO.GameInitialiser;
import g42116.rushhour.JsonIO.Language;
import static g42116.rushhour.view.Colour.*;
import g42116.rushhour.view.ColourString;
import g42116.rushhour.view.UserInput;

/**
 *
 * @author john
 */
public class RushHour {

    /**
     * Main method: run to play the game.
     * 
     * @param args unused
     */
    public static void main(String[] args) {

        // Query and set language from file (Must be relative, from 
        // JsonLoader.class location for default, and from project root for
        // askLanguage() method):
        String defaultLangPath = "resources/languages/English.json";
        String langFolderPath = "src/g42116/rushhour/JsonIO/resources/languages";

        Language language = null;
        try {
            language = new Language(defaultLangPath);
            language = new Language(
                               UserInput.askLanguage(langFolderPath, language));
        } catch (RushHourException ex) {
            String error = "Program was unable to "
                                          + "load language configuration file";
            System.out.println(ColourString.to(error, RED, WHITE));
        }

        // Query board from file, then initialise RushHourGame object:
        String initBoardFolder = "src/g42116/rushhour/JsonIO/resources/games";
        File initBoard = UserInput.askInitBoard(initBoardFolder, language);

        RushHourGame game = null;
        try {
            game = new GameInitialiser(initBoard).initialise();
        } catch (RushHourException ex) {
            String error = "Problem loading game configuration file";
            System.out.println(ColourString.to(error, RED, WHITE));
        }

        // Play:
        RushHourView view = new RushHourView(game, language);
        Display.displayBoard(game.getBoard());
        view.play();
    }

}
