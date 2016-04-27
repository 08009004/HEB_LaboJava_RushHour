package g42116.rushhour.view;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.view.Display.displayBoard;
import g42116.rushhour.JsonIO.Language;
import static g42116.rushhour.view.Colour.*;

/**
 * This class manages the game visuals.
 * 
 * @author john
 */
public class RushHourView {

    //Class attribute:
    private final RushHourGame game;
    private final Language language;

    /**
     * Full constructor.
     * 
     * @param   game        the game to show the user
     * @param   language    the language for the game display
     */
    public RushHourView(RushHourGame game, Language language) {
        this.game = game;
        this.language = language;
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
            carID = UserInput.askId(this.game, this.language);
            direction = UserInput.askDir(this.language);

            try {
                this.game.move(carID, direction);
            } catch (RushHourException rhe) {
                printErrorToScreen(rhe);
                System.out.println(this.language.queryDifferentMove);
            }

            displayBoard(this.game.getBoard());

        } while (!this.game.isOver());

        System.out.println("\n" 
                        + ColourString.to(this.language.endOfGame, null, CYAN));
    }

    /**
     * Prints the error message to screen, based on exception cause, after
     * translating it in the current language.
     * 
     * @param   exception   the message of the exception recieved
     */
    private void printErrorToScreen(RushHourException exception) {
        String errorMessage;
        if (exception.getMessage().startsWith("Id")) {
            errorMessage = this.language.errNoSuchCar;
        } else if (exception.getMessage().startsWith("The orientation")) {
            errorMessage = this.language.errWrongOrientation;
        } else {
            errorMessage = this.language.errIllegalMove;
        }

        System.out.println(ColourString.to(errorMessage, null, RED));
    }
}
