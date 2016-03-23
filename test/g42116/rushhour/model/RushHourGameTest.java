package g42116.rushhour.model;

import static g42116.rushhour.model.Orientation.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g42116
 */
public class RushHourGameTest {
    
    /**
     * Full constructor normal case.
     */
    @Test 
    public void testRushHourGame1() {
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3)),
            new Car('c', 4, HORIZONTAL, new Position(5,0))
        );
        
        new RushHourGame(6, 6, new Position(2,5), 
                    new Car('a', 2, HORIZONTAL, new Position(2,0)), otherCars);
    }

    /**
     * Full constructor, exception : red car partly outside the board.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testRushHourGame2() {
        new RushHourGame(6, 6, new Position(2,5), 
                    new Car('a', 2, VERTICAL, new Position(5,0)), null);
    }

    
    /**
     * Full constructor, exception : cars from 'otherCars' outside the board.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testRushHourGame3() {
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(4,1))
        );
        
        new RushHourGame(6, 6, new Position(2,5), 
                    new Car('a', 2, HORIZONTAL, new Position(2,0)), otherCars);
    }


     /**
     * Full constructor, exception : some cars from 'otherCars' overlap.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testRushHourGame4() {
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3)),
            new Car('c', 4, HORIZONTAL, new Position(3,2))
        );
        
        new RushHourGame(6, 6, new Position(2,5), 
                    new Car('a', 2, HORIZONTAL, new Position(2,0)), otherCars);
    }
    
}