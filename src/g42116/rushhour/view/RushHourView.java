package g42116.rushhour.view;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.model.Direction.*;
import static g42116.rushhour.view.Display.displayBoard;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class manages the game visuals.
 * 
 * @author john
 */
public class RushHourView {

    //Class attribute:
    private final RushHourGame game;
    private JsonNode language;

    /**
     * Full constructor.
     * 
     * @param   game    the game to show the user
     */
    public RushHourView(RushHourGame game) {
        this.game = game;

        ObjectMapper mapper = new ObjectMapper();
        //Set default language: English.
        JsonNode langRootNode = null;
        try {
            File langFile = new File("src/g42116/rushhour/lang/TextsEnglish.json");
            langRootNode = mapper.readValue(langFile, JsonNode.class);
        } catch (FileNotFoundException ex) {
            System.out.println("Default language file not found.");
        } catch (IOException ex) {
            System.out.println("Problem opening default language file.");
        }
        this.language = langRootNode;


        //Ask player for his prefered language:
        try {
            langRootNode = mapper.readValue(askLanguage(), JsonNode.class);
        } catch (FileNotFoundException ex) {
            System.out.println("Selected language file not found.");
        } catch (IOException ex) {
            System.out.println("Problem opening selected language file.");
        }
        this.language = langRootNode;
    }
    
    /**
     * Asks user to select the game language amongst the language files, from
     * a list of JSon objects.
     * 
     * @return the .json language configuration file
     */
    private File askLanguage() {
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

            selected = Character.getNumericValue(askChar(language.path("queryLanguage").asText(), "not valid. "));
            selected--;
        } while (selected < 0 || selected > folderContent.size() - 1);

        return folderContent.get(selected);
    }

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
    private char askChar(String query, String error) {
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
                System.out.print(this.language.path("errBlankCharsOnly").asText()
                    + error);
            }

            if (str1.length() > 1) {
                System.out.print(this.language.path("errTooManyChars").asText() 
                                                                       + error);
            }

        } while (str1.length() > 1 || str1.equals(""));

        return str1.charAt(0);
    }

    /**
     * As long as the game is not over, this method prompts the player to key in
     * a car ID and a direction, then moves this car in the desired direction
     * if possible ; then prints the game board to screen.
     */
    public void play() {        
        String query1 = this.language.path("queryCarId").asText();
        String error1A = this.language.path("wasInvalidId").asText();
        String error1B = this.language.path("noSuchCar").asText();
        String query2 = this.language.path("queryDirection").asText();
        String error2 = this.language.path("wasInvalidDir").asText();
        String query3 = this.language.path("queryDifferentMove").asText();

        char carID;
        Direction direction;

        do {
            carID = requestID(query1, error1A, error1B);
            direction = requestDir(query2, error2);

            try {
                this.game.move(carID, direction);

            } catch (RushHourException rhe) {
                System.out.println(rhe.getMessage().replace(
                                "g42116.rushhour.model.RushHourException: ", "")
                        + "\n\n"
                        + query3);
            }

            displayBoard(this.game.getBoard());

        } while (!this.game.isOver());

        System.out.println(this.language.path("endOfGame").asText());
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
     * @return          player's entry
     */
    private char requestID(String query, String error1, String error2) {
        char carID;

        do {
            carID = askChar(query, error1);
            if (!this.game.isValidId(carID)) {
                    System.out.println(error2);
                    carID = askChar(query, error1);
            }
        } while (!this.game.isValidId(carID));

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
    private Direction requestDir(String query, String error) {
        char keyedIn;
        query = query.concat("\n" + this.language.path("moveDirections").asText());
        do {
            keyedIn = askChar(query, error);
            switch (keyedIn) {
                case 'U': return UP;
                case 'D': return DOWN;
                case 'L': return LEFT;
                case 'R': return RIGHT;
                default:
                    System.out.print(keyedIn 
                                            + this.language.path("isNotDir").asText());
                    break;
            }
        } while (true);
    }
}
