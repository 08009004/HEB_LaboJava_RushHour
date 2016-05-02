package g42116.rushhour.model;

import java.util.List;

/**
 * This interface represents items that can be present on the board, namely Car
 * objects and Obstacle objects.
 * 
 * @author g42116
 */
public interface BoardItem {
    
    public char getId();
    public List<Position> getPositions();
}
