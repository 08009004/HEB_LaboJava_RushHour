package g42116.rushhour;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import g42116.rushhour.model.Car;
import g42116.rushhour.model.Position;
import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import static g42116.rushhour.model.Orientation.*;
import g42116.rushhour.view.RushHourView;
import static g42116.rushhour.view.Display.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        File file = new File("src/g42116/rushhour/games/GameTest.json");
        RushHourGame game = null;
        try {
            game = mapper.readValue(file, RushHourGame.class);
        } catch (IOException ex) {
            Logger.getLogger(RushHour.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        RushHourView view = new RushHourView(game);
//            clearScreen();
            displayBoard(game.getBoard());
            view.play();
        
/*        Position exit = new Position(2, 5);
        Car redCar = new Car('R', 2, HORIZONTAL, new Position(2,0));
        List<Car> otherCars = Arrays.asList(
            new Car('1', 3, HORIZONTAL, new Position(1,2)),
            new Car('2', 2, VERTICAL, new Position(2,3)),
            new Car('3', 4, HORIZONTAL, new Position(5,0))
        );

    //    RushHourGame game;

        try {
//            game = new RushHourGame(6, 6, exit, redCar, otherCars);
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
