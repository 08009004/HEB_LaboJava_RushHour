package g42116.rushhour.view;

import g42116.rushhour.jsonIO.Language;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.model.Direction.*;
import g42116.rushhour.model.RushHourGame;
import static g42116.rushhour.view.Colour.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This static class interfaces the game for player keyboard entries.
 * 
 * @author g42116
 */
public class UserInput {
    
    private Language lang;

    /**
     * Class constructor.
     * 
     * @param   lang    the current game language.
     */
    public UserInput(Language lang) {
        this.lang = lang;
    }

    /**
     * Language attribute setter.
     * 
     * @return 
     */
    public Language getLang() {
        return lang;
    }

    /**
     * Language attribute setter.
     * 
     * @param   lang    the new language
     */
    public void setLang(Language lang) {
        this.lang = lang;
    }

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
    private char askChar(String query, String error) {
        Scanner keyboard = new Scanner(System.in);
        String str1;
        System.out.println(query);
/*      API: "A Scanner breaks its input into tokens using a delimiter pattern, 
        which by default matches whitespace."
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
                                lang.getErrCharsOnlyBlank() + error, null, RED));
            }

            if (str1.length() > 1) {
                System.out.println(
                        ColourString.to(
                                lang.getErrCharsSeveral() + error, null, RED));
            }

        } while (str1.length() > 1 || str1.equals(""));

        return str1.charAt(0);
    }

    /**
     * Scans and displays a folder content (list of files), and asks player to 
     * chose a file amongst those.
     * 
     * @param   fldrPath    the path of the folder
     * @param   msg1        the message displayed to query user to select one of
     *                      the files
     * @param msg2          the message displayed to query for a new entry if
     *                      previous one was not valid
     * @return 
     */
    private String askFile(String fldrPath, String msg1, String msg2) {
        System.out.println("IN ASK_FILE: fldrPath = " + fldrPath);
        File folder = new File(fldrPath);
        System.out.println("IN ASK_FILE: folder = " + folder);
        System.out.println("folder.getAbsolutePath() = " + folder.getAbsolutePath());
        String[] arr0 = folder.list();
        File[] arr = folder.listFiles();
        List<File> folderContent = Arrays.asList(
                                              folder.listFiles());

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

            selected = Character.getNumericValue(askChar(msg1, msg2));
            selected--;
     
        } while (selected < 0 || selected > folderContent.size() - 1);

        return folderContent.get(selected).getPath().replace("build/classes", "");
    }
    /**
     * Asks user to select the game language amongst the language init files,
     * from a folder content list.
     * 
     * @return              the selected language configuration relative file 
     *                      path (from project root package, included)
     */
    public String askLang() {
        /* Path to the folder where the language files are stored (relative from
         * project root folder, no leading slash):
         */
        String folderPath = "build/classes/g42116/rushhour/jsonIO/"
                                                       + "resources/languages/";

        System.out.println(lang.getListLangFiles());
        String query = lang.getQueryLang();
        String reQuery = lang.getReQueryLang();    // Upon incorrect user entry.

        return askFile(folderPath, query, reQuery);
        
    }

    /**
     * Asks user to select the game he wants to play amongst the initial board
     * files (a list of JSon objects which is the content of a folder).
     * 
     * @return              the selected initial board configuration relative  
     *                      file path (from project root package, included)
     */             
    public String askBoard() {
        /* Path to the folder where the game files are stored (relative from
         * project root folder, no leading slash):
         */
        String folderPath = "build/classes/g42116/rushhour/jsonIO/"
                                                           + "resources/games/";

        System.out.println(lang.getListGameInitFiles());
        String query = lang.getQueryGameInit();
        String reQuery = lang.getReQueryGameInit();// Upon incorrect user entry.

        return askFile(folderPath, query, reQuery);
    }

    /**
     * Asks player to key in the ID of the car that he wants to move.
     * 
     * @param   game    the current game
     * @return          player's entry (in upper case)
     */
    public char askId(RushHourGame game) {
        char carID;

        do {
            carID = askChar(lang.getQueryCarId(), lang.getErrNotAnId());
            if (!game.isValidId(carID)) {
                    System.out.println(
                            ColourString.to(lang.getErrNoSuchCar(), null, RED));
                    carID = askChar(lang.getQueryCarId(), lang.getErrNotAnId());
            }
        } while (!game.isValidId(carID));

        return carID;
    }

    /**
     * Asks user to key in a direction, until a valid key is pressed: U for Up, 
     * D for Down, L for Left or R for Right. This method is not case sensitive.
     *             
     * @return  character representative of the user's choice (upper case)
     */
    public Direction askDir() {

        char keyedIn;
        String query = lang.getQueryDir().concat("\n" + lang.getListDirChoices());

        do {
            keyedIn = askChar(query, lang.getErrInvalidDir());
            switch (keyedIn) {
                case 'U': return UP;
                case 'D': return DOWN;
                case 'L': return LEFT;
                case 'R': return RIGHT;
                default:
                    System.out.print(keyedIn + 
                             ColourString.to(lang.getErrIsNotDir(), null, RED));
                    break;
            }
        } while (true);
    }
}
