package g42116.rushhour.model;

import static g42116.rushhour.model.Direction.*;

/**
 * This class defines the Position object type, which represents the possible 
 * positions on the game board.
 * @author g42116
 */
public class Position {
    
    // Class attributes:
    private int row;
    private int column;
    
    /**
     * Full constructor.
     * @param row the position's row number
     * @param column the position's column number
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Row attribute accessor.
     * @return the position's row number
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Column attribute accessor.
     * @return the position's column number
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Returns a text representation of the position.
     * @return A string formated as (row,column)
     */
    @Override
    public String toString() {
        return "(" + row + "," + column + ')';
    }
    
    /**
     * Returns a new position representing the adjacent position to current 
     * position, according to passed direction parameter.
     * @param direction The direction of position translation
     * @return The position after translation
     */
    public Position getPosition(Direction direction) {
        Position newPosition = null;
        switch (direction) {
            case LEFT:
                newPosition = new Position(this.row, this.column - 1);
                break;
            case RIGHT:
                newPosition = new Position(this.row, this.column + 1);
                break;
            case UP:
                newPosition = new Position(this.row - 1, this.column);
                break;
            case DOWN:
                newPosition = new Position(this.row + 1, this.column);
                break;
        }
        return newPosition;
    }
  
}
