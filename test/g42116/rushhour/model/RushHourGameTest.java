package g42116.rushhour.model;

import static g42116.rushhour.model.Orientation.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g42116
 */
public class RushHourGameTest {
    
    /**
     * Full constructor, exception : red car outside the board.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testGetBoard1() {
        new RushHourGame(6, 6, new Position(2,5), 
                        new Car('a', 3, HORIZONTAL, new Position(2,0)), null);
    }

    /**
     * Full constructor, exception : cars from 'carsList' outside the board.
     */
/*    @Test 
    public void testGetBoard2() {
        Car redCar = new Car('a', 2, HORIZONTAL, new Position(3,0));
        new RushHourGame(6, 6, new Position(2,5), redCar, null);
    }
*/
    @Test
    public void testMove() {
    }

    @Test
    public void testIsOver() {
    }
    
}
