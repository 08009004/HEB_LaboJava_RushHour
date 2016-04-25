package g42116.rushhour.view;

import g42116.rushhour.model.Direction;
import static g42116.rushhour.model.Direction.*;
import g42116.rushhour.model.RushHourGame;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This static class interfaces the game for interaction with the player.
 * 
 * @author john
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
     * @return          user's entry (upper case)
     */
    private static char askChar(String query, String error) {
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
            str1 = str1.replace(" ", "").replace("\t", "");
            str1 = str1.toUpperCase();

            if (str1.equals("")) {
//                System.out.print(this.language.path("errBlankCharsOnly").asText()
//                    + error);
                System.out.println("blank chars only"+ error);
            }

            if (str1.length() > 1) {
//                System.out.print(this.language.path("errTooManyChars").asText() 
//                                                                       + error);
                System.out.println("several chars" + error);
            }

        } while (str1.length() > 1 || str1.equals(""));

        return str1.charAt(0);
    }

    /**
     * Asks user to select the game language amongst the language files, from
     * a folder content list.
     * 
     * @return the .json language configuration absolute file path
     */
    public static File askLanguage() {
        System.out.println("Language choices: ");
        File folderPath = new File("src/g42116/rushhour/lang/");
        List<File> folderContent = Arrays.asList(folderPath.listFiles());

        String printList = "";
        int index;
        int selected;

        do {
            index = 1;

            for (File file : folderContent) {
                printList = index + " - " + file.getName();
                printList = printList.replace("Texts", "");
                printList = printList.replace(".json", "");
                System.out.println(printList);
                index++;
            }

//            selected = Character.getNumericValue(askChar(language.path("queryLanguage").asText(), "not valid. "));
            selected = Character.getNumericValue(askChar("queryLanguage", "not valid. "));
            selected--;
        } while (selected < 0 || selected > folderContent.size() - 1);

        return folderContent.get(selected).getAbsoluteFile();
    }
    
    /**
     * Asks player to key in the ID of the car that he wants to move.
     * 
     * @param   query   message printed to screen to prompt the user to key in 
     *                  the ID of the car that he wants to move
     * @param   error1  message printed to screen to prompt for a new input if 
     *                  user didn't key in a single non-blank character 
     * @param   error2  message printed to screen to prompt for a new input if 
     *                  the ID entered doesn't match that of one of the cars 
     *                  present on the board
     * @param   game    the current game
     * @return          player's entry
     */
    public static char requestID(String query, String error1, String error2, RushHourGame game) {
        char carID;

        do {
            carID = askChar(query, error1);
            if (!game.isValidId(carID)) {
                    System.out.println(error2);
                    carID = askChar(query, error1);
            }
        } while (!game.isValidId(carID));

        return carID;
    }

    /**
     * Asks user to key in a direction, until a valid key is pressed: U for Up, 
     * D for Down, L for Left or R for Right. This method is not case sensitive.
     * 
     * @param   query   message printed to screen to prompt the user to key in a 
     *                  direction, followed by a listing of the four choices 
     *                  available
     * @param   error   message printed to screen to prompt for a new input if 
     *                  user didn't key in a valid entry
     * @return          character representative of the user's choice (upper
     *                  case)
     */
    public static Direction requestDir(String query, String error) {
        char keyedIn;
//        query = query.concat("\n" + this.language.path("moveDirections").asText());
        query = query.concat("\n" + "u, l, d, or r");
        do {
            keyedIn = askChar(query, error);
            switch (keyedIn) {
                case 'U': return UP;
                case 'D': return DOWN;
                case 'L': return LEFT;
                case 'R': return RIGHT;
                default:
                    System.out.print(keyedIn 
//                                            + this.language.path("isNotDir").asText());
                                            + "is Not Dir");
                    break;
            }
        } while (true);
    }
}
