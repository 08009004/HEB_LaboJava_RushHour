package g42116.rushhour.model;

import static g42116.rushhour.model.Direction.*;
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
    public void testRushHourGame1() throws RushHourException {
        Position exit = new Position(2,5);
        Car redCar = new Car('r', 2, HORIZONTAL, new Position(2,0));        
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3)),
            new Car('c', 4, HORIZONTAL, new Position(5,0))
        );
        
        new RushHourGame(6, 6, exit, redCar, otherCars);
    }

    /**
     * Full constructor, RushHourException: red car not aligned with exit 
     * position.
     */
    @Test (expected=RushHourException.class)
    public void testRushHourGame2() throws RushHourException {
        Position exit = new Position(2,5);
        Car redCar = new Car('r', 2, VERTICAL, new Position(2,0));        
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3)),
            new Car('c', 4, HORIZONTAL, new Position(5,0))
        );
        

        new RushHourGame(6, 6, exit, redCar, otherCars);
    }  
    
    /**
     * Full constructor, exception: red car partly outside the board.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testRushHourGame3() throws RushHourException {
        Position exit = new Position(0,0);
        Car redCar = new Car('r', 2, VERTICAL, new Position(5,0));        
        
        new RushHourGame(6, 6, exit, redCar, null); 
    }

    
    /**
     * Full constructor, exception: car 'b' from 'otherCars' not fully on the
     * game board.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testRushHourGame4() throws RushHourException {
        Position exit = new Position(2,5);
        Car redCar = new Car('r', 2, HORIZONTAL, new Position(2,0));
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(4,1))
        );

        new RushHourGame(6, 6, exit, redCar, otherCars);
    }


     /**
     * Full constructor, exception: car 'c' from 'otherCars' overlaps car 'b'.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testRushHourGame5() throws RushHourException {
        Position exit = new Position(2,5);
        Car redCar = new Car('r', 2, HORIZONTAL, new Position(2,0)); 
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3)),
            new Car('c', 4, HORIZONTAL, new Position(3,2))
        );
        
            new RushHourGame(6, 6, exit, redCar, otherCars);    
    }
    
    /**
     * move(car, direction) normal case.
     */
    @Test
    public void testMove1() throws RushHourException {
        Position exit = new Position(2, 5);
        Car redCar = new Car('r', 2, HORIZONTAL, new Position(2,0));        
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3)),
            new Car('c', 4, VERTICAL, new Position(2,5))
        );
        RushHourGame tested = new RushHourGame(6, 6, exit, redCar, otherCars);
        List<Position> expected = redCar.getTranslated(RIGHT);
        
        tested.move('r', RIGHT);
        List<Position> result = redCar.getPositions();
        
        assertEquals(expected, result);
    }
    
    /**
     * isOver() 'true' case.
     */
    @Test
    public void testIsOver1() throws RushHourException {
        Position exit = new Position(2, 5);
        Car redCar = new Car('r', 2, HORIZONTAL, new Position(2,4));        
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3))
        );
        RushHourGame tested = new RushHourGame(6, 6, exit, redCar, otherCars);
        
        assertTrue(tested.isOver());
    }
    
    /**
     * isOver() 'false' case.
     */
    @Test
    public void testIsOver2() throws RushHourException {
        Position exit = new Position(2,5);
        Car redCar = new Car('r', 2, HORIZONTAL, new Position(2,0));        
        List<Car> otherCars = Arrays.asList(
            new Car('a', 3, HORIZONTAL, new Position(1,2)),
            new Car('b', 3, VERTICAL, new Position(2,3)),
            new Car('c', 4, HORIZONTAL, new Position(5,0))
        );
        RushHourGame tested = new RushHourGame(6, 6, exit, redCar, otherCars);
        
        assertFalse(tested.isOver());
    }
}