package g42116.rushhour.view;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.view.Display.displayBoard;
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
            langRootNode = mapper.readValue(UserInput.askLanguage(), JsonNode.class);
        } catch (FileNotFoundException ex) {
            System.out.println("Selected language file not found.");
        } catch (IOException ex) {
            System.out.println("Problem opening selected language file.");
        }
        this.language = langRootNode;
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
            carID = UserInput.requestID(query1, error1A, error1B, this.game);
            direction = UserInput.requestDir(query2, error2);

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

}
