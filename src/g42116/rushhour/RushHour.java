package g42116.rushhour;

import g42116.rushhour.model.Car;
import g42116.rushhour.model.Position;
import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import static g42116.rushhour.model.Orientation.*;
import g42116.rushhour.view.RushHourView;
import g42116.rushhour.view.Display;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;

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
        Position exit = new Position(2, 5);
        Car redCar = new Car('R', 2, HORIZONTAL, new Position(2,0));
        List<Car> otherCars = Arrays.asList(
            new Car('1', 3, HORIZONTAL, new Position(1,2)),
            new Car('2', 2, VERTICAL, new Position(2,3)),
            new Car('3', 4, HORIZONTAL, new Position(5,0))
        );
        
        JsonParser parser = new JsonParser();
        Object language = parser.parse(new FileReader("src.g42116.rushhour.view.TextsEnglish.json"));
        
        RushHourGame game;

        try {
            game = new RushHourGame(6, 6, exit, redCar, otherCars);
            RushHourView view = new RushHourView(game, (JsonObject) language);
            Display.displayBoard(game.getBoard());
            view.play();

        } catch (RushHourException rhe) {
            System.out.println("Impossible to start the game. " 
                    + rhe.getMessage().replace(
                            "g42116.rushhour.model.RushHourException: ", ""));
        }
    }
    
}
