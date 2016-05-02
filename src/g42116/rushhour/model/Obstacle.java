package g42116.rushhour.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents obstacles obstructing Car objects movements.
 * 
 * @author g42116
 */
public class Obstacle implements BoardItem {

    private final char id;
    private final Position position;

    /**
     * Constructor.
     * 
     * @param   id          the obstacle's ID
     * @param   position    the position of the obstacle
     */
    public Obstacle(char id, Position position) {
        this.id = id;
        this.position = position;
    }

    /**
     * Returns the obstacle identifier.
     * 
     * @return  the car's id
     */
    public char getId() {
        return id;
    }

    public List<Position> getPositions() {
        List<Position> thePos = new ArrayList<>();
        thePos.add(position);
        return thePos;
    }
    
    

}
