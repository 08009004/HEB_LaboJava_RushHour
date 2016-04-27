package g42116.rushhour;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.view.RushHourView;
import g42116.rushhour.view.Display;
import java.io.File;
import g42116.rushhour.JsonIO.GameInitialiser;
import g42116.rushhour.JsonIO.Language;
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

        String langFolderPath = "src/g42116/rushhour/JsonIO/resources/languages";
        String defaultLangPath = langFolderPath+ "/English.json";

        Language language = null;
        try {
            language = new Language(new File(defaultLangPath));
            language = new Language(
                               UserInput.askLanguage(langFolderPath, language));
        } catch (RushHourException ex) {
            System.out.println("Program was unable to "
                                          + "load language configuration file");
        }

        String initBoardFolder = "src/g42116/rushhour/JsonIO/resources/games";
        File initBoard = UserInput.askInitBoard(initBoardFolder, language);

        RushHourGame game = null;
        try {
            game = new GameInitialiser(initBoard).initialise();
        } catch (RushHourException ex) {
            System.out.println("Problem loading game configuration file");
        }

        RushHourView view = new RushHourView(game, language);
        Display.displayBoard(game.getBoard());
        view.play();
    }

}
