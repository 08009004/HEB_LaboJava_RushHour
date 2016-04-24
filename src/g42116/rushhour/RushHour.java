package g42116.rushhour;

import g42116.rushhour.model.Car;
import g42116.rushhour.model.Position;
import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.view.RushHourView;
import static g42116.rushhour.view.Display.*;
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
        ObjectMapper mapper = new ObjectMapper();
        File gameInitFile = new File("src/g42116/rushhour/games/GameTest3.json");
        RushHourGame game = null;
        JsonNode initNode;
        int boardHeight;
        int boardWidth;
        try {
            initNode = mapper.readValue(gameInitFile, JsonNode.class);           
            boardHeight = initNode.path("boardHeight").asInt();
            boardWidth = initNode.path("boardWidth").asInt();
            Position exit = mapper.readValue(initNode.path("exit").toString(), Position.class);
            Car redCar = mapper.readValue(initNode.path("redCar").toString(), Car.class);

            List<Car> otherCars = mapper.readValue(initNode.path("otherCars").toString(), 
                mapper.getTypeFactory().constructCollectionType(List.class, Car.class));
            System.out.println("6");
            game = new RushHourGame(boardHeight, boardWidth, exit, redCar, otherCars);
        } catch (IOException ioe) {
            System.out.println("Impossible to start the game. " 
                    + ioe.getMessage().replace(
                            "g42116.rushhour.model.RushHourException: ", ""));
        } catch (RushHourException rhe) {
            System.out.println("Impossible to start the game. " 
                    + rhe.getMessage().replace(
                            "g42116.rushhour.model.RushHourException: ", ""));
        }

        
        RushHourView view = new RushHourView(game);

        displayBoard(game.getBoard());
        view.play();
        
/*        Position exit = new Position(2, 5);
        Car redCar = new Car('R', 2, HORIZONTAL, new Position(2,0));
        List<Car> otherCars = Arrays.asList(
            new Car('1', 3, HORIZONTAL, new Position(1,2)),
            new Car('2', 2, VERTICAL, new Position(2,3)),
            new Car('3', 4, HORIZONTAL, new Position(5,0))
        );

        RushHourGame game;

        try {
            game = new RushHourGame(6, 6, exit, redCar, otherCars);
            RushHourView view = new RushHourView(game);
            clearScreen();
            displayBoard(game.getBoard());
            view.play();

        } catch (RushHourException rhe) {
            System.out.println("Impossible to start the game. " 
                    + rhe.getMessage().replace(
                            "g42116.rushhour.model.RushHourException: ", ""));
        }
*/
    }

}
