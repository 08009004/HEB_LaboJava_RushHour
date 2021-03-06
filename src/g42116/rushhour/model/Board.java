package g42116.rushhour.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class defines the game board object.
 * 
 * @author g42116
 */
public class Board {

    //Class attributes:
    private BoardItem[][] grid;
    private final Position exit;

    /**
     * Minimal constructor: instantiates a 6 x 6 boxes game board, with the exit
     * on position (2,5).
     */
    public Board() {
        this(6, 6, new Position(2,5));
    }

    /**
     * Full constructor.
     * 
     * @param   width   the length of the game board
     * @param   height  the height of the game board
     * @param   exit    the exit position
     * @throws          IllegalArgumentException if length or height are 
     *                  strictly lesser than 1, or if exit is not on one of the 
     *                  board sides
     */
    public Board(int height, int width, Position exit) {
        if (height < 1) {
            throw new IllegalArgumentException("Board height was: " + height
                    + ". It must be strictly greater than 0.");
        } else if (width < 1) {
            throw new IllegalArgumentException("Board length was: " + width
                    + ". It must be strictly greater than 0.");
        }

        /* this.grid must be instantiated prior to calling isOnBoardSide(exit)
        because that method calls height() and width() which both refer to 
        this.grid                                                             */

        this.grid = new BoardItem[height][width];

        if (!isOnBoardSide(exit)) {
            throw new IllegalArgumentException("Exit position was: " + exit
                    + "It must be on one of the board sides.");
        }

        this.exit = exit;
    }

    /**
     * DO NOT USE - method for testing purpose only.
     * 
     * @param   testGrid the board grid
     */
    Board(Car[][] testGrid) {
        this.grid = testGrid;
        this.exit = new  Position(2,5);
    }

    /**
     * Checks the validity of passed position as the exit position.
     * 
     * @param   candidate   the desired exit position
     * @return              true if the candidate position is on one of the
     *                      board sides, false otherwise
     */
    private boolean isOnBoardSide(Position candidate){
        return (candidate.getRow() == 0)
            || (candidate.getRow() == (height() - 1) )
            || (candidate.getColumn() == 0)
            || (candidate.getColumn() == (width() - 1) );
    }

    /**
     * Returns the height of the game board.
     * 
     * @return the height of the board
     */
    public int height() {
        return grid.length;
    }

    /**
     * Returns the width of the game board.
     *
     * @return  the width of the board
     */
    public int  width() {
        return grid[0].length;
    }

    /**
     * Returns the exit position on the game board.
     * 
     * @return  the exit position
     */
    public Position getExit() {
        return this.exit;
    }

    /**
     * USE ONLY TO DEBUG - Returns a textual representation of the board object.
     *
     * @return  a textual representation of the board grid content, line 
     *          by line, where an empty board square is represented by an em 
     *          dash
     */
    @Override
    public String toString() {
        Car temp;
        String textualGrid = "\n GAME BOARD\n";
        for (BoardItem[] row : grid) {
            for (BoardItem column : row) {
                
                if (column == null) {
                    textualGrid = textualGrid.concat("—\u0009");
                } else {
                    temp = (Car) column;
                    textualGrid = textualGrid.concat(temp.getId() + "\u0009");
                }
            }
            textualGrid = textualGrid.concat("\n");
        }
        return textualGrid;
    }

    /**
     * Checks if the board has the same attributes values as the parameter
     * board. Performs a deep equality check on the board grid content (2
     * dimensional array).
     *
     * @param   other   the board against which the current board must be
     *                  checked
     * @return          true if current board attributes equal those of the 
     *                  parameter board
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final Board otherBoard = (Board) other;

        return Arrays.deepEquals(this.grid, otherBoard.grid)
                && this.exit.equals(otherBoard.exit);
    }

    /**
     * Returns a hash code value for the board.
     *
     * @return  a hash code value for this board
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Arrays.deepHashCode(this.grid);
        hash = 43 * hash + Objects.hashCode(this.exit);
        return hash;
    }

    /**
     * Returns the car occupying the position passed as a parameter.
     *
     * @param   position    the position to check
     * @return              the car occupying the position, or the obstacle, 
     *                      or null if the position is empty
     * @throws              IllegalArgumentException if position is outside the
     *                      board
     */
    public BoardItem getItemAt(Position position) {
        if (!isOntheBoard(position)) {
            throw new IllegalArgumentException("Position " + position
                    + " is outside the board. Passed position must be on the "
                    + "board.");
        }
        return this.grid[position.getRow()][position.getColumn()];
    }

    /**
     * Checks that all of the positions of a given car are not off the board, 
     * nor occupied by another car.
     *
     * @param   car the car to check
     * @return      true if all of the positions required for the car are free,
     *              otherwise false
     * @throws      NullPointerException if 'car' is null.
     */
    public boolean canPutCar(Car car) {
        List<Position> carPositions = car.getPositions();

        for (Position element : carPositions) {
            if ( !isOntheBoard(element) || (getItemAt(element) != null) )
                return false;
        }

        return true;
    }
    
    /**
     * Checks that all of the positions of a given car are not off the board, 
     * nor occupied by another car.
     *
     * @param   item the car to check
     * @return      true if all of the positions required for the car are free,
     *              otherwise false
     * @throws      NullPointerException if 'car' is null.
     */
    public boolean canPutItem(BoardItem item) {
        List<Position> itemPositions = item.getPositions();

        for (Position element : itemPositions) {
            if ( !isOntheBoard(element) || (getItemAt(element) != null) )
                return false;
        }

        return true;
    }

    /**
     * Checks if a car can be moved in a given direction, that is:<br> 
     *  - the car does not exit the board ;<br> 
     *  - it is not blocked by another car in the given direction.
     * 
     * @param   car         the car to move
     * @param   direction   the desired direction
     * @return              true if the move is possible, otherwise false
     * @throws              NullPointerException if the car parameter is null
     */
    public boolean canMove(Car car, Direction direction) {
        List<Position> candidate = car.getTranslated(direction);

        for (Position element : candidate) {
            if ( !isOntheBoard(element) || (containsOther(element, car)) )
                return false;
        }

        return true;
    }

    /**
     * Checks if a given position is on the game board.
     * 
     * @param   candidate    the position to check
     * @return              true if the position is on the game board, otherwise
     *                      false
     */
    private boolean isOntheBoard(Position candidate) {
        return (candidate.getRow() < height() )
            && (candidate.getRow() >= 0)
            && (candidate.getColumn() < width() )
            && (candidate.getColumn() >= 0);
    }

    /**
     * Checks if a given board square is occupied by another car than the one
     * passed as a parameter.
     *
     * @param position  the board square to check
     * @param car       the car checked
     * @return          true if another car occupies the board square
     */
    private boolean containsOther(Position position, Car car) {
        return (getItemAt(position) != null && getItemAt(position) != car);
    }

    /**
     * Adds a car on the board.
     * 
     * @param   item the car to add
     */
    public void put(BoardItem item) {
        List<Position> occupy = item.getPositions();
        for (Position element : occupy) {
            this.grid[element.getRow()][element.getColumn()] = item;
        }
    }

    /**
     * Deletes a car from the board: fills all the grid squares occupied by null
     * pointers.
     * 
     * @param   car the car to delete
     */
    public void remove(Car car) {
        List<Position> positions = car.getPositions();
        for (Position position : positions) {
            this.grid[position.getRow()][position.getColumn()] = null;
        }
    }

    /**
     * Scans the board grid to find a car whose id matches the parameter 'id'.
     * 
     * @param   id  the id of the car searched
     * @return      the car if found, otherwise null
     */
    public Car getCar(char id) {

        for (BoardItem[] row : grid) {
            for (BoardItem car : row) {
                if(car != null && car.getId()==id)
                    return (Car) car;
            }
        }

        return null; 
    }

}
