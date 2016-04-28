package g42116.rushhour;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.view.RushHourView;
import g42116.rushhour.view.Display;
import g42116.rushhour.JsonIO.GameInitialiser;
import g42116.rushhour.JsonIO.Language;
import static g42116.rushhour.view.Colour.*;
import g42116.rushhour.view.ColourString;
import g42116.rushhour.view.UserInput;

/**
 * Main class of the projects, containing the 'main' method.
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

        // Query and set language from file (folder path must be relative, from 
        // JsonLoader.class location for default, and from project root for
        // askLanguage() method):
        String defaultLangPath = "resources/languages/English.json";
        String langFolderPath = "src/g42116/rushhour/JsonIO/resources/languages";

        UserInput keyboard = null;
        Language lang;
        try {
            lang = new Language(defaultLangPath);
            keyboard = new UserInput(lang);
            lang = new Language(keyboard.askLang(langFolderPath));
            keyboard.setLang(lang);
        } catch (RushHourException ex) {
            String error = "Program was unable to "
                                          + "load language configuration file";
            System.out.println(ColourString.to(error, RED, WHITE));
        }

        // Query board from file, then initialise RushHourGame object (folder
        // path must be relative, from project root):
        String boardsFolder = "src/g42116/rushhour/JsonIO/resources/games";

        RushHourGame game = null;
        try {
            game = new GameInitialiser(keyboard.askBoard(
                                                    boardsFolder)).initialise();
        } catch (RushHourException ex) {
            String error = "Problem loading game configuration file";
            System.out.println(ColourString.to(error, RED, WHITE));
        }

        // Play:
        RushHourView view = new RushHourView(game, keyboard);
        Display.displayBoard(game.getBoard());
        view.play();
    }

}
