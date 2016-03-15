package g42116.rushhour.model;

/**
 * This class defines the Car object type.
 * 
 * @author g42116
 */
public class Car {
    
    // Class attributes:
    private final char id;
    private final int size;
    private final Orientation orientation;
    private Position currentPosition;

    /**
     * Full constructor.
     * 
     * @param   id              the car's identifier
     * @param   size            the number (between 1 and 4, included) of boxes 
     *                          occupied by the car
     * @param   orientation     the car's orientation on the board
     * @param   initialPosition the car's position on the board (leftmost 
     *                          occupied board box for horizontal cars, topmost 
     *                          occupied box for vertical ones)
     * @throws                  IllegalArgumentException if the car size passed 
     *                          is not between 1 and 4 included
     */
    public Car(char id, int size, 
                Orientation orientation, Position initialPosition) {
        
        if (size <= 0 || size > 4) {
            throw new IllegalArgumentException(
                    "Car size was: " + size + ". It must be between 1 and 4.");
        }
        this.id = id;
        this.size = size;
        this.orientation = orientation;
        this.currentPosition = initialPosition;
    }
    
    /**
     * Returns the car identifier.
     * 
     * @return  the car's id
     */
    public char getId() {
        return id;
    }

    /**
     * Returns the orientation of the car.
     * 
     * @return  the orientation of the car
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Returns the current position of the car.
     * 
     * @return  the current position of the car
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }
    
    /**
     * Returns a textual representation of the car object.
     * 
     * @return  A string formated as follow: Car id (size: size, orientation:
     *          orientation, position: currentPosition)
     */
    @Override
    public String toString() {
        return "Car" + id + " (size: " + size + ", orientation:" + orientation 
                + ", position:" + currentPosition + ')';
    }
    
    /**
     * Modifies current car position by moving it one box in the direction 
     * received as a parameter.
     * 
     * @param   direction   the desired car move direction
     * @throws              IllegalArgumentException if the requested move 
     *                      direction is not compatible with the orientation of 
     *                      the car
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
     * Checks the validity of a given move direction against the orientation of
     * the car.
     * 
     * @param   direction   the desired car move direction
     * @return              true if the direction is incompatible with the 
     *                      orientation of the car, false otherwise
     */
    private boolean isWrongOrientation(Direction direction) {
        return (this.orientation == Orientation.HORIZONTAL
                &&  (direction == Direction.DOWN 
                        || direction == Direction.UP))
            || (this.orientation == Orientation.VERTICAL
                &&  (direction == Direction.LEFT 
                        || direction == Direction.RIGHT));
    }
    
};
