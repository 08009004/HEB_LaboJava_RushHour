package g42116.rushhour.model;

/**
 * This class defines the game board oject.
 * 
 * @author john
 */
public class Board {
    
    //Class attributes:
    private Car[][] grid;
    private Position exit;
    
    /**
     * Minimal constructor: instantiates a 6 x 6 boxes game board, with the exit
     * on position (2,5).
     */
    public Board() {
        this.grid = new Car[6][6];
        this.exit = new Position(2,5);
    }
    
    /**
     * Full constructor.
     * 
     * @param   width  the length of the game board
     * @param   height  the height of the game board
     * @param   exit    the exit position
     * @throws          IllegalArgumentException if length or height are 
     *                  strictly lesser than 1, or if exit is not on a board 
     *                  side
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
                    + "It must be on a board side.");
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
            || (candidate.getRow() == (getHeight() - 1) )
            || (candidate.getColumn() == 0) 
            || (candidate.getColumn() == (getWidth() - 1) );
    }
    
    /**
     * Returns the height of the game board.
     * @return the height of the board
     */
    public int getHeight() {
        return grid.length;
    }
    
    /**
     * Returns the width of the game board.
     * @return  the width of the board
     */
    public int  getWidth() {
        return grid[0].length;
    }
    
    /**
     * Returns the exit position on the game board.
     * @return  the exit position
     */
    public Position getExit() {
        return this.exit;
    }
}
