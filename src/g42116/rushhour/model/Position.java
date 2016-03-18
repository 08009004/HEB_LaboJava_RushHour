package g42116.rushhour.model;

/**
 * This class defines the Position object type, which represents the positions 
 * on the game board.
 * 
 * @author g42116
 */
public class Position {
    
    // Class attributes:
    private final int row;
    private final int column;
    
    /**
     * Full constructor.
     * 
     * @param   row     the row number for the new position
     * @param   column  the column number for the new position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row of the position.
     * 
     * @return  the row number of the position
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Returns the column of the position.
     * 
     * @return  the column number of the position
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Returns a textual representation of the position object.
     * 
     * @return  a string formated as (row,column)
     */
    @Override
    public String toString() {
        return "(" + row + "," + column + ')';
    }
    
    /**
     * Checks if the current position has the same attributes values as the 
     * parameter position.
     * 
     * @param   other   the position against which the current position must be
     *                  checked
     * @return          true if current position attributes equal parameter
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final Position otherPosition = (Position) other;
        return this.row == otherPosition.row 
            && this.column == otherPosition.column;
    }

    /**
     * Returns a hash code value for the position.
     * 
     * @return  a hash code value for this position
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.row;
        hash = 47 * hash + this.column;
        return hash;
    }
    
    /**
     * Returns a new position representing the adjacent position to current 
     * position, according to passed direction parameter.
     * 
     * @param   direction   The direction of position translation
     * @return              The position after translation
     * @throws              NullPointerException if the direction passed is not
     *                      LEFT, RIGHT, UP or DOWN
     */
    public Position getPosition(Direction direction) {
        Position newPosition;
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
            default:
                throw new NullPointerException("Direction cannot be null");      
        }
        return newPosition;
    }
  
}
