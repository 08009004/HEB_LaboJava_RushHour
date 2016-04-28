package g42116.rushhour.view;

import g42116.rushhour.JsonIO.Language;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.model.Direction.*;
import g42116.rushhour.model.RushHourGame;
import static g42116.rushhour.view.Colour.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This static class interfaces the game for interaction with the player.
 * 
 * @author g42116
 */
public class UserInput {

    /**
     * Asks user to key in a character, until only a single character is keyed 
     * in (blank characters are ignored).
     * 
     * @param   query   message printed to screen to prompt the user to key in a 
     *                  character
     * @param   error   message printed to screen to prompt for a new input if 
     *                  user didn't key in a single non-blank character 
     *                  (coloured in red)
     * @return          user's entry (upper case)
     */
    private static char askChar(String query, String error, Language language) {
        Scanner keyboard = new Scanner(System.in);
        String str1;
        System.out.print("\n" + query);
/* API: "A Scanner breaks its input into tokens using a delimiter pattern, which 
        by default matches whitespace."
        nextLine() "advances this scanner past the current line and returns the 
        input that was skipped."
*/
        do {
            str1 = keyboard.nextLine();
            str1 = str1.replace(" ", "").replace("\t", "");  // \t == tabulation
            str1 = str1.toUpperCase();

            if (str1.equals("")) {
                System.out.println(
                        ColourString.to(
                                language.errCharsOnlyBlank + error, null, RED));
            }

            if (str1.length() > 1) {
                System.out.println(
                        ColourString.to(
                                language.errCharsSeveral + error, null, RED));
            }

        } while (str1.length() > 1 || str1.equals(""));

        return str1.charAt(0);
    }

    /**
     * Asks user to select the game language amongst the language init files,
     * from a folder content list.
     * 
     * @param   folderPath  the path to the folder where the language files are
     *                      stored
     * @param   language    the current language
     * @return              the selected language configuration relative file 
     *                      path (from project root package, included)
     */
    public static String askLang(String folderPath, Language language) {
        System.out.println(language.listLangFiles);

        List<File> folderContent = Arrays.asList(
                                              new File(folderPath).listFiles());

        String printListItem;
        int index;
        int selected;

        do {
            index = 1;

            for (File file : folderContent) {
                printListItem = index + " - " + file.getName();
                printListItem = printListItem.replace(".json", "");
                System.out.println(printListItem);
                index++;
            }

            selected = Character.getNumericValue(
                  askChar(language.queryLang, language.reQueryLang, language));
            selected--;
     
        } while (selected < 0 || selected > folderContent.size() - 1);

        return folderContent.get(selected).getPath().replace("src", "");
    }

    /**
     * Asks user to select the game he wants to play amongst the initial board
     * files (a list of JSon objects which is the content of a folder).
     * 
     * @param   folderPath  the path to the folder containing the initial board
     *                      files
     * @param   language    the current language
     * @return              the selected initial board configuration relative  
     *                      file path (from project root package, included)
     */             
    public static String askBoard(String folderPath, Language language) {
        System.out.println(language.listGameInitFiles);
        List<File> folderContent = Arrays.asList(
                                              new File(folderPath).listFiles());

        String printListItem = "";
        int index;
        int selected;

        do {
            index = 1;

            for (File file : folderContent) {
                printListItem = index + " - " + file.getName();
                printListItem = printListItem.replace(".json", "");
                System.out.println(printListItem);
                index++;
            }

            selected = Character.getNumericValue(askChar(
                   language.queryGameInit, language.reQueryGameInit, language));
            selected--;
        } while (selected < 0 || selected > folderContent.size() - 1);

        return folderContent.get(selected).getPath().replace("src", "");
    }

    /**
     * Asks player to key in the ID of the car that he wants to move.
     * 
     * @param   game        the current game
     * @param   language    the current language
     * @return              player's entry (in upper case)
     */
    public static char askId(RushHourGame game, Language language) {
        char carID;

        do {
            carID = askChar(language.queryCarId, language.errNotAnId, language);
            if (!game.isValidId(carID)) {
                    System.out.println(
                            ColourString.to(language.errNoSuchCar, null, RED));
                    carID = askChar(language.queryCarId, 
                                                 language.errNotAnId, language);
            }
        } while (!game.isValidId(carID));

        return carID;
    }

    /**
     * Asks user to key in a direction, until a valid key is pressed: U for Up, 
     * D for Down, L for Left or R for Right. This method is not case sensitive.
     * 
     * @param   language    the current language               
     * @return              character representative of the user's choice (upper
     *                      case)
     */
    public static Direction askDir(Language language) {

        char keyedIn;
        String query = language.queryDir.concat("\n" + language.listDirChoices);

        do {
            keyedIn = askChar(query, language.errInvalidDir, language);
            switch (keyedIn) {
                case 'U': return UP;
                case 'D': return DOWN;
                case 'L': return LEFT;
                case 'R': return RIGHT;
                default:
                    System.out.print(keyedIn + 
                              ColourString.to(language.errIsNotDir, null, RED));
                    break;
            }
        } while (true);
    }
}
