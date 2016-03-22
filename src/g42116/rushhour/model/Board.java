package g42116.rushhour.model;

import java.util.List;

/**
 * This class defines the game board object.
 * 
 * @author john
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
     * DO NOT USE - method for testing purpose only.
     */
    public Board(Car testCar) {
        Position tempPos = new  Position(0,0);
//        testCar = new Car('a', 2, Orientation.HORIZONTAL, tempPos);
        Car[][] temp = {
            {testCar, testCar, null},
            {null,    null,    null},
            {null,    null,    null}
        };
        this.grid = temp;
        this.exit = tempPos;
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
     */
    public boolean canPut(Car car) {
        List<Position> desired = car.getPositions();
        Car boardSquare = null;
        
        for (Position element : desired) {
            if ( !isOntheBoard(element) ) return false;
            
            boardSquare = this.grid[element.getRow()][element.getColumn()];
            
            if ((boardSquare != null) || (boardSquare != car)) return false;
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
            && (position.getRow() > 0)
            && (position.getColumn() < width() ) 
            && (position.getColumn() > 0);
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
}
