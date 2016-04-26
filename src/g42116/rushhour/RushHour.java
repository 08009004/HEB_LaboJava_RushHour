package g42116.rushhour;

import g42116.rushhour.model.Car;
import g42116.rushhour.model.Position;
import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.view.RushHourView;
import static g42116.rushhour.view.Display.displayBoard;
import java.io.File;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import g42116.rushhour.JsonIO.GameInitialiser;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class RushHour {
    
    /**
     * Main method: run to start playing.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
//        File initialBoard = askInitBoard();
        File initialBoard = new File("src/g42116/rushhour/JsonIO/resources/Game1Test.json");
        
        RushHourGame game = null;
        try {
            game = new GameInitialiser(initialBoard).initialise();
        } catch (RushHourException ex) {
            System.out.println("Problem loading game configuration file");
        }

        RushHourView view = new RushHourView(game);
        displayBoard(game.getBoard());
        view.play();
    }

    /**
     * Initialises the game object with parameters recovered from a Json file.
     * 
     * @param   initialBoard    the Json file
     * @return                  the game
     * @throws                  java.io.IOException if 'initialBoard' refers to
     *                          an invalid file or filepath.
     * @throws                  g42116.rushhour.model.RushHourException if the
     *                          game arguments are incorrect or illegal.
     */
    private static RushHourGame initGame(File initialBoard) 
                                         throws IOException, RushHourException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(initialBoard, JsonNode.class);

        int boardHeight = rootNode.path("boardHeight").asInt();
        int boardWidth = rootNode.path("boardWidth").asInt();

        Position exit = mapper.readValue(
                              rootNode.path("exit").toString(), Position.class);
        Car redCar = mapper.readValue(
                                 rootNode.path("redCar").toString(), Car.class);

    /* NOTES:
    http://www.leveluplunch.com/java/examples/convert-json-array-to-arraylist-of-objects-jackson/
    https://github.com/FasterXML/jackson-databind
    */
        List<Car> otherCars = mapper.readValue(
                rootNode.path("otherCars").toString(), 
                        mapper.getTypeFactory().constructCollectionType(
                                                        List.class, Car.class));

        return new RushHourGame(
                              boardHeight, boardWidth, exit, redCar, otherCars);
    }

    /**
     * Asks user to select the game he wants to play amongst the initial board
     * files (a list of JSon objects).
     * 
     * @return the .json initial board absolute file path
     */
    private static File askInitBoard() {
        System.out.println("Games available: ");
        File folderPath = new File("src/g42116/rushhour/games/");
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

            selected = Character.getNumericValue(
                           askChar("Select one of the above: ", "not valid. "));
            selected--;
        } while (selected < 0 || selected > folderContent.size() - 1);

        return folderContent.get(selected).getAbsoluteFile();
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
                System.out.print(error);
            }

            if (str1.length() > 1) {
                System.out.print(error);
            }

        } while (str1.length() > 1 || str1.equals(""));

        return str1.charAt(0);
    }
}
