package g42116.rushhour.model;

/**
 * This object class defines the Car object type.
 * @author g42116
 */
public class Car {
    
    // Class attributes:
    protected char id;
    protected int size;
    protected Orientation orientation;
    protected Position currentPosition;

    // Constructor
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
    
    // Getters
    public char getId() {
        return id;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
    
    // Class Methods
    @Override
    public String toString() {
        return "Car" + id + " (size: " + size + ", orientation:" + orientation 
                + ", currentPosition:" + currentPosition + ')';
    }
    
    /**
     * Modifies current car position by moving it one box in the direction 
     * recieved as a parameter.
     * @param direction 
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
     * @param direction
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
