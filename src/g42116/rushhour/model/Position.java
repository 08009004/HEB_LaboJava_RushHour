package g42116.rushhour.model;

import static g42116.rushhour.model.Direction.*;

/**
 * This object class defines the Position object type.
 * @author g42116
 */
public class Position {
    
    // Class attributes
    private int row;
    private int column;
    
    // Constructor
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // Getters
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    // Class methods
    @Override
    public String toString() {
        return "(" + row + "," + column + ')';
    }
    
    /**
     * Returns a new position representing the neighboring position to current 
     * position, according to passed direction parameter.
     * @param direction The direction of position translation.
     * @return The position after translation.
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
