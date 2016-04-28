package g42116.rushhour.view;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.view.Display.displayBoard;
import static g42116.rushhour.view.Colour.*;

/**
 * This class manages the game visuals.
 * 
 * @author john
 */
public class RushHourView {

    //Class attribute:
    private final RushHourGame game;
//    private final Language language;
    private final UserInput keyboard;

    /**
     * Full constructor.
     * 
     * @param   game        the game to show the user
     * @param   keyboard    the keyboard for player's entries
     */
    public RushHourView(RushHourGame game, UserInput keyboard) {
        this.game = game;
        this.keyboard = keyboard;
    }

    /**
     * As long as the game is not over, this method prompts the player to key in
     * a car ID and a direction, then moves this car in the desired direction
     * if possible ; then prints the game board to screen.
     */
    public void play() {        
        char carID;
        Direction direction;

        do {
            carID = this.keyboard.askId(this.game);
            direction = this.keyboard.askDir();

            try {
                this.game.move(carID, direction);
            } catch (RushHourException rhe) {
                printErrorToScreen(rhe);
                System.out.println(
                               this.keyboard.getLang().getQueryDifferentMove());
            }

            displayBoard(this.game.getBoard());

        } while (!this.game.isOver());

        printEndOfGame();
    }

    /**
     * Translates the error message it in the current language and prints it to
     * screen.
     * 
     * @param   exception   the exception
     */
    private void printErrorToScreen(RushHourException exception) {
        String errorMessage;
        if (exception.getMessage().startsWith("Id")) {
            errorMessage = this.keyboard.getLang().getErrNoSuchCar();
        } else if (exception.getMessage().startsWith("The orientation")) {
            errorMessage = this.keyboard.getLang().getErrWrongOrientation();
        } else {
            errorMessage = this.keyboard.getLang().getErrIllegalMove();
        }

        System.out.println(ColourString.to(errorMessage, null, RED));
    }

    /**
     * Prints the "end of game" message to screen.
     */
    private void printEndOfGame() {
        System.out.println("\n" 
                    + ColourString.to(this.keyboard.getLang().getEndOfGame(), 
                                                                   null, CYAN));
    }
}
