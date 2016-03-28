package g42116.rushhour.model;

import static g42116.rushhour.model.Orientation.*;
import java.util.List;

/**
 * This class defines the Rush Hour parlour game object.
 * 
 * @author john
 */
public class RushHourGame {
    
    // Class attributes:
    private Board board;
    private Car redCar;

    /**
     * Full constructor: initialises a new game board, placing all the cars on
     * it (red car included).
     * 
     * @param   boardHeight the height dimension for the game board
     * @param   boardWidth  the width dimension for the game board
     * @param   exit        the exit position on the game board
     * @param   otherCars   a list of the cars to install on the board before 
     *                      letting the game start
     * @param   redCar      the red car that the player tries to drive out of
     *                      the board
     * @throws              RushHourException if the red car is not in alignment
     *                      with the exit position
     * @throws              IllegalArgumentException if the red car is not 
     *                      completely on the game board, or if one of the cars 
     *                      from argument 'carsList' cannot be added to it
     */
    public  RushHourGame(int boardHeight, int boardWidth, Position exit,
                    Car redCar, List<Car> otherCars) throws RushHourException {
        
        if (exitMismatch(redCar, exit)) throw new RushHourException("The red "
            + "car must be aligned with the exit position.");
        
        this.board = new Board(boardHeight, boardWidth, exit);
        
        if (this.board.canPut(redCar)) {
            this.board.put(redCar);
            this.redCar = redCar;
        } else {
            throw new IllegalArgumentException("Red " + redCar + " was at least"
                + " partly outside of the board.");
        }
        
        for (Car element : otherCars) {
            if (this.board.canPut(element)) {
                this.board.put(element);
            } else {
                throw new IllegalArgumentException("" + element + " could not "
                        + "be added to the board.");
            }
        }
    }
    
    /**
     * Checks if a given red car is in alignment with a given exit position.
     * @param   redCar  the red car candidate car
     * @param   exit    the exit candidate position
     * @return          true if the red car is mismatched with the exit, 
     *                  otherwise false
     */
    private boolean exitMismatch(Car redCar, Position exit) {
        return (redCar.getOrientation() == HORIZONTAL)
            && (redCar.getCurrentPosition().getRow() != exit.getRow() )
            || (redCar.getOrientation() == VERTICAL)
            && (redCar.getCurrentPosition().getColumn() != exit.getColumn() );
    }

    /**
     * Returns the game board.
     * 
     * @return  the game board
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Moves a given car in a given direction if it is possible.
     * 
     * @param   id          the 'id' attribute of the car to move
     * @param   direction   the direction of the move
     */
    public void move(char id, Direction direction) {
/* 
pour cela la méthode:
        
— vérifie qu’il y a bien une voiture avec cet id et la récupère, grâce à la 
méthode getCar de Board ;
— vérifie que la voiture peut se déplacer dans cette direction grâce à la 
méthode canMove de Board ;
— retire la voiture du plateau ;
— déplace la voiture dans la direction donnée ;
— replace la voiture sur le plateau.
*/

    }
    
    /**
     * Checks if the game is over (i.e. if the red car has been driven off the
     * board).
     * 
     * @return  true if the game is over, otherwise false
     */
    public boolean isOver() {
        return true;
    }
    
}
