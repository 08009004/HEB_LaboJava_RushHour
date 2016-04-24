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
import java.io.IOException;

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
        File initialBoard = new File("src/g42116/rushhour/games/Game3.json");
        RushHourGame game = null;
        try {
            game = initGame(initialBoard);
        } catch (IOException | RushHourException ioe) {
            System.out.println("Impossible to start the game. " 
                    + ioe.getMessage().replace(
                              "g42116.rushhour.model.RushHourException: ", ""));
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
    public static RushHourGame initGame(File initialBoard) 
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

}
