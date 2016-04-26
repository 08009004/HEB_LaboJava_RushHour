package g42116.rushhour;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.view.RushHourView;
import static g42116.rushhour.view.Display.displayBoard;
import java.io.File;
import g42116.rushhour.JsonIO.GameInitialiser;
import g42116.rushhour.view.UserInput;

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
        String initBoardFolder = "src/g42116/rushhour/JsonIO/resources/games";
        File initBoard = UserInput.askInitBoard(initBoardFolder);
        
        RushHourGame game = null;
        try {
            game = new GameInitialiser(initBoard).initialise();
        } catch (RushHourException ex) {
            System.out.println("Problem loading game configuration file");
        }

        RushHourView view = new RushHourView(game);
        displayBoard(game.getBoard());
        view.play();
    }

}
