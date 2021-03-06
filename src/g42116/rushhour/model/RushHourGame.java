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
     * @param   obstacles   the obstacles
     * @param   redCar      the red car that the player tries to drive out of
     *                      the board
     * @throws              RushHourException if the red car is not in alignment
     *                      with the exit position
     * @throws              IllegalArgumentException if the red car is not 
     *                      completely on the game board
     */
    public  RushHourGame(int boardHeight, int boardWidth, 
                 Position exit, Car redCar, List<BoardItem> obstacles, 
                           List<BoardItem> otherCars) throws RushHourException {

        if ( !matchingExit(redCar, exit) ) throw new RushHourException(
                        "The red car must be aligned with the exit position.");

        this.board = new Board(boardHeight, boardWidth, exit);

        if (this.board.canPutCar(redCar)) {
            this.board.put(redCar);
            this.redCar = redCar;
        } else {
            throw new IllegalArgumentException("Red " + redCar + " was at least"
                + " partly outside of the board.");
        }

        put(obstacles);
        put(otherCars);
    }

    /**
     * Controls if a given red car is on the same line as a given exit position.
     * 
     * @param   redCar  the red car candidate car
     * @param   exit    the exit candidate position
     * @return          true if the red car is aligned with the exit, 
     *                  otherwise false
     */
    private boolean matchingExit(Car redCar, Position exit) {
        return (redCar.getOrientation() == HORIZONTAL)
            && (redCar.getCurrentPosition().getRow() == exit.getRow() )
            || (redCar.getOrientation() == VERTICAL)
            && (redCar.getCurrentPosition().getColumn() == exit.getColumn() );
    }

    /**
     * Adds a list of car objects on the game board.
     * 
     * @param   items    the list of cars to add
     * @throws          IllegalArgumentException if one of the cars from list
     *                  cannot be added to the board
     */
    private void put(List<BoardItem> items) {
        for (BoardItem element : items) {
            if (this.board.canPutItem(element)) {
                this.board.put(element);
            } else {
                throw new IllegalArgumentException("" + element + " could not "
                        + "be added to the board.");
            }
        }
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
     * Moves a given car in a given direction if such a move is possible.
     * 
     * @param   id          the 'id' attribute of the car to move
     * @param   direction   the direction of the move
     * @throws              RushHourException if there is no car matching the id
     *                      parameter, or if the desired move is impossible
     */
    public void move(char id, Direction direction) throws RushHourException {
        Car car = this.board.getCar(id);

        if (car == null) {
            throw new RushHourException("Id was: " + id
                                      + ". There is no such car on the board.");
        }

        if (car.isWrongOrientation(direction)) {
            throw new RushHourException("The orientation of the car "
                                              + "doesn't allow for this move.");
        }

        if (this.board.canMove(car, direction)) {
            this.board.remove(car);
            car.move(direction);
            this.board.put(car);
        } else {
            throw new RushHourException("This move is not possible: the car is "
                    + "either being moved outside the board limits, or there is"
                    + " another car blocking it.");
        }
    }

    /**
     * Checks if the game is over (i.e. if the red car occupies the exit
     * position).
     * 
     * @return  true if the game is over, otherwise false
     */
    public boolean isOver() {
        return ( this.board.getItemAt(this.board.getExit()) == redCar );
    }

    /**
     * Verifies if parameter character matches the 'id' attribute of a car 
     * present on this game's board.
     * 
     * @param   carID   the 'id' attribute of the car being searched for
     * @return          true if there is a car with this ID on the board, 
     *                  otherwise false
     */
    public boolean isValidId(char carID) {
        return this.board.getCar(carID) != null;
    }

}