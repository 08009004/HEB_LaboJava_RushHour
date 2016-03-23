package g42116.rushhour.model;

import java.util.ArrayList;
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
    private Car[][] grid;
    private final Position exit;
    
    /**
     * Minimal constructor: instantiates a 6 x 6 boxes game board, with the exit
     * on position (2,5).
     */
    public Board() {
        this.grid = new Car[6][6];
        this.exit = new Position(2,5);
    }
    
    /**
     * DO NOT USE - method for equality testing purpose only.
     * @param   testCar new Car('a', 2, HORIZONTAL, new Position(1,1))
     */
    public Board(Car testCar) {
        Car[][] testGrid = {
            {null,    null,    null,    null,    null,    null},
            {null,    testCar, testCar, null,    null,    null},
            {null,    null,    null,    null,    null,    null},
            {null,    null,    null,    null,    null,    null},
            {null,    null,    null,    null,    null,    null},
            {null,    null,    null,    null,    null,    null}
        };
        this.grid = testGrid;
        this.exit = new  Position(2,5);
    }
  
    /**
     * Full constructor.
     * 
     * @param   width  the length of the game board
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
        
        this.grid = new Car[height][width];
        
        if (!isOnBoardSide(exit)) {
            throw new IllegalArgumentException("Exit position was: " + exit
                    + "It must be on one of the board sides.");
        }

        this.exit = exit;
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
     * Returns a textual representation of the board object.
     * 
     * @return  a textual representation of the board grid content, line 
     *          by line, where an empty board square is represented by an em 
     *          dash
     */
    @Override
    public String toString() {
        String textualGrid = "\n GAME BOARD\n";
        for (Car[] row : grid) {
            for (Car column : row) {
                if (column == null) {
                    textualGrid = textualGrid.concat("—\u0009");
                } else {
                    textualGrid = textualGrid.concat(column.getId() + "\u0009");
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
     * @return              the car occupying the position, or null if the 
     *                      position is empty
     */
    public Car getCarAt(Position position) {
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
    public boolean canPut(Car car) { 
        if (car == null) throw new NullPointerException("Car cannot be null.");
        
        List<Position> carPositions = car.getPositions();
        
        return appropriateName(car, carPositions);
    }
    
    /**
     * Checks if a car can be moved in a given direction, that is:
     *  - the car does not exit the board ;
     *  - it is not blocked by another car in the given direction.
     * 
     * @param   car         the car to move
     * @param   direction   the desired direction
     * @return              true if the move is possible, otherwise false
     */
    public boolean canMove(Car car, Direction direction) {
        if (car == null) throw new NullPointerException("Car cannot be null.");
        
        List<Position> candidate = car.getTranslated(direction);
        
        return appropriateName(car, candidate);
    }
    
    /**
     * Checks if a given 
     * 
     * @param car
     * @param carPositions
     * @return 
     */
    private boolean appropriateName(Car car,List<Position> carPositions) {
        for (Position element : carPositions) {
            if ( !isOntheBoard(element) ) return false;
            if (containsOther(element, car)) return false;
        }
        
        return true;
    }
        
    /**
     * Checks if a given position is on the game board.
     * 
     * @param   position    the position to check
     * @return              true if the position is on the game board, otherwise
     *                      false
     */
    private boolean isOntheBoard(Position position) {
        return (position.getRow() < height() ) 
            && (position.getRow() >= 0)
            && (position.getColumn() < width() ) 
            && (position.getColumn() >= 0);
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
        return (getCarAt(position) != null && getCarAt(position) != car);
    }
    
    /**
     * Adds a car on the board.
     * 
     * @param   car the car to add
     */
    public void put(Car car) {
        List<Position> occupy = car.getPositions();
        for (Position element : occupy) {
            this.grid[element.getRow()][element.getColumn()] = car;
        }
    }
    
    /**
     * Deletes a car from the board: fills all the grid squares occupied by null
     * pointers.
     * 
     * @param   car the car to delete
     */
    public void remove(Car car) {
        List<Position> remove = car.getPositions();
        for (Position element : remove) {
            this.grid[element.getRow()][element.getColumn()] = null;
        }
    }

    /**
     * Scans the board grid to find a car whose id matches the parameter 'id'.
     * 
     * @param   id  the id of the car searched
     * @return      the car if found, otherwise null
     */
    public Car getCar(char id) {
        Car searched = null;
        Car currentCar;
        boolean found = false;
        int line = 0;
        int column;
        
        while ((line < width()) && (!found)) {
            column = 0;
            while ((column < height() && (!found))) {
                currentCar = this.grid[line][column];
                
                if (currentCar.getId() == id) {
                    searched = currentCar;
                    found = true;
                }
                column++;
            }
            line++;
        }
        
        return searched;
    }

}
