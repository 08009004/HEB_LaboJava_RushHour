package g42116.rushhour.view;

import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.view.Display.displayBoard;
import g42116.rushhour.JsonIO.Language;

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
     * @param   game    the game to show the user
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
            carID = UserInput.askId(
                            this.language.queryCarId, 
                                    this.language.errNotAnId, 
                                            this.language.errNoSuchCar, 
                                                      this.game, this.language);

            direction = UserInput.askDir(this.language.queryDir, 
                                                this.language.errInvalidDir, 
                                                                 this.language);

            try {
                this.game.move(carID, direction);
            } catch (RushHourException rhe) {
                System.out.println(rhe.getMessage().replace(
                        "g42116.rushhour.model.RushHourException: ", "")
                                   + "\n\n" + this.language.queryDifferentMove);
            }

            displayBoard(this.game.getBoard());

        } while (!this.game.isOver());

        System.out.println(this.language.endOfGame);
    }

}
