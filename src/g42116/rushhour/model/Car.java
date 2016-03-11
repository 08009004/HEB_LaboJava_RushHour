package g42116.rushhour.model;

/**
 * This object class defines the Car object type.
 * @author g42116
 */
public class Car {
    
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
    
    /*
déplace (modifie la positioncourante de) la voiture d’une case dans la direction 
reçue en paramètre. Cette
méthode lancera une IllegalArgumentException avec un message adéquat si la
direction n’est pas compatible avec l’orientation de la voiture. Par exemple, si la
voiture est placée horizontalement et que la direction est UP la méthode lance une
exception.
    */
    public void move(Direction direction) {
        
    }
    
}
