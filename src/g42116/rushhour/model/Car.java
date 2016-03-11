package g42116.rushhour.model;

/**
 * This class defines the Car object type.
 * @author g42116
 */
public class Car {
    
    // Class attributes:
    protected char id;
    protected int size;
    protected Orientation orientation;
    protected Position currentPosition;

    /**
     * Full constructor.
     * @param id the car's identifier
     * @param size the number of boxes occupied by the car
     * @param orientation the car's orientation on the board
     * @param initialPosition the car's position on the board (leftmost occupied
     * board box for horizontal cars, topmost occupied box for vertical ones)
     * @throws IllegalArgumentException if the car sized passed is not equal
     * to 1 or more.
     */
    public Car(char id, int size, Orientation orientation, Position initialPosition) {
        if (size <= 0) {
            throw new IllegalArgumentException(
                    "Car size was: " + size + ". It must be 1 or more.");
        }
        this.id = id;
        this.size = size;
        this.orientation = orientation;
        this.currentPosition = initialPosition;
    }
    
    /**
     * Id attribute accessor.
     * @return the car's id
     */
    public char getId() {
        return id;
    }

    /**
     * Orientation attribute accessor.
     * @return the car's orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Current Position attribute accessor.
     * @return the car's current position.
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }
    
    /**
     * Returns a text representation of the car.
     * @return A string formated as follow: Car id (size: size, orientation:
     * orientation, position: currentPosition)
     */
    @Override
    public String toString() {
        return "Car" + id + " (size: " + size + ", orientation:" + orientation 
                + ", position:" + currentPosition + ')';
    }
    
    /**
     * Modifies current car position by moving it one box in the direction 
     * recieved as a parameter.
     * @param direction the desired car move direction
     * @throws IllegalArgumentException if the requested move direction is not
     * compatible with the car's orientation.
     */
    public void move(Direction direction) {
        if (isWrongOrientation(direction)) {
            throw new IllegalArgumentException("Incompatible move arguments "
                    + "(car orientation: " + this.orientation
                    + " ; car direction: " + direction + ").");
        }
        
        currentPosition = currentPosition.getPosition(direction);
    }
    
    /**
     * Checks the validity of a given move direction against the car's
     * orientation.
     * @param direction the desired car move direction
     * @return False if the direction is compatible with the car's orientation,
     * else true.
     */
    private boolean isWrongOrientation(Direction direction) {
        return (this.orientation == Orientation.HORIZONTAL
                    && (direction == Direction.DOWN || direction == Direction.UP))
            || (this.orientation == Orientation.VERTICAL
                    && (direction == Direction.LEFT || direction == Direction.RIGHT));
    }
    
}
