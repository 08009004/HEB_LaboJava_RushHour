package g42116.rushhour.model;

import java.util.ArrayList;
import java.util.List;
import static g42116.rushhour.model.Orientation.*;
import static g42116.rushhour.model.Direction.*;
import java.util.Objects;

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
     * @param   size            the number of boxes occupied by the car
     * @param   orientation     the car's orientation on the board
     * @param   initialPosition the car's position on the board (leftmost 
     *                          occupied board box for horizontal cars, topmost 
     *                          occupied box for vertical ones)
     * @throws                  IllegalArgumentException if the car size passed 
     *                          is not 1 or more
     */
    public Car(char id, int size, 
                Orientation orientation, Position initialPosition) {
        
        if (size <= 0) {
            throw new IllegalArgumentException("Car size was: " + size 
                    + ". It must be strictly greater than 0.");
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
        return "Car '" + id + "' (size: " + size + ", orientation:" 
                + orientation + ", position:" + currentPosition + ')';
    }

    /**
     * Checks if the board has the same attributes values as the parameter 
     * board. Performs a deep equality check on the board grid content (2 
     * dimensional array).
     * 
     * @param   other   the car against which the current car must be checked
     * @return          true if current board attributes equal those of the 
     *                  parameter board
     */    
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        final Car otherCar = (Car) other;

        return (this.id == otherCar.id) && (this.size == otherCar.size)
                && (this.orientation == otherCar.orientation)
                && currentPosition.equals(otherCar.currentPosition);
    }

    /**
     * Returns a hash code value for the car.
     * 
     * @return  a hash code value for this car
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.size;
        hash = 79 * hash + Objects.hashCode(this.orientation);
        hash = 79 * hash + Objects.hashCode(this.currentPosition);
        return hash;
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
        return (this.orientation == HORIZONTAL
                &&  (direction == DOWN || direction == UP))
            || (this.orientation == VERTICAL
                &&  (direction == LEFT || direction == RIGHT));
    }
    
    /**
     * Modifies current car position by moving it one square further in the 
     * direction received as a parameter.
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
                    + " ; move direction: " + direction + ").");
        }
        
        currentPosition = currentPosition.getPosition(direction);
    }

/*          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    NullPointerException: judicieux? si non aussi retirer le test.
*/
    
    /**
     * Returns a list of all the positions occupied by the car.
     * 
     * @return  the list of the positions occupied
     * @throws  NullPointerException if car size is greater than 1 and its 
     *          orientation of the car is not HORIZONTAL or VERTICAL.
     */
    public List<Position> getPositions() {
        List<Position> occupied = new ArrayList<>();
        occupied.add(currentPosition);
        
        for (int i = 1; i < this.size; i++) {
            if (this.orientation == HORIZONTAL) {
                occupied.add(occupied.get(i-1).getPosition(RIGHT));
            } else if (this.orientation == VERTICAL) {
                occupied.add(occupied.get(i-1).getPosition(DOWN));
            } else {
                throw new NullPointerException("Orientation of the car cannot "
                    + "be null.");
            }
        }
        
        return occupied;
    }
    
    /**
     * Simulates a translation of the car positions in the desired move 
     * direction (the actual car stays unchanged).
     * @param   desired the desired move direction   
     * @return          a list of the candidate positions that the car would
     *                  occupy if Car.move(direction) was applied
     * @throws          IllegalArgumentException if the requested move 
     *                  direction is not compatible with the orientation of the 
     *                  car
     */
    public List<Position> getTranslated(Direction desired) {
        if (isWrongOrientation(desired)) {
            throw new IllegalArgumentException("Incompatible move arguments "
                    + "(car orientation: " + this.orientation
                    + " ; desired direction: " + desired + ").");
        }
        
        List<Position> translation = getPositions();
        
        for (int i = 0; i < translation.size(); i++)
                    translation.set(i, translation.get(i).getPosition(desired));  
        
        return translation;
    }

}
