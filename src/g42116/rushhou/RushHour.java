package g42116.rushhou;

import g42116.rushhour.model.Car;
import g42116.rushhour.model.Position;
import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import static g42116.rushhour.model.Orientation.*;
import g42116.rushhour.view.RushHourView;
import g42116.rushhour.view.Display;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author john
 */
public class RushHour {

/*
Lancer le programme
Placez la méthode principale du jeu dans une nouvelle classe RushHour qui se trouve
dans le (nouveau) package g12345.rushhour. 
    -> NAME PROBLEM
*/
    
    /**
     * Main method: run to start playing.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        Position exit = new Position(2, 5);
        Car redCar = new Car('R', 2, HORIZONTAL, new Position(2,0));
        List<Car> otherCars = Arrays.asList(
            new Car('A', 3, HORIZONTAL, new Position(1,2)),
            new Car('B', 2, VERTICAL, new Position(2,3)),
            new Car('C', 4, HORIZONTAL, new Position(5,0))
        );
        RushHourGame game;

        try {
            game = new RushHourGame(6, 6, exit, redCar, otherCars);
            RushHourView view = new RushHourView(game);
            Display.displayBoard(game.getBoard());
            view.play();

        } catch (RushHourException rhe) {
            System.out.println("Impossible to start the game. " + rhe);
        }
    }
    
}
