package g42116.rushhour.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g42116
 */
public class CarTest {
 
    /**
     * Argument 'size' must be strictly greater than zero.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testConstructor1() {
        new Car('a', 0, Orientation.HORIZONTAL, new Position(5,5));
    }

    /**
     * getId() normal case.
     */
    @Test
    public void testGetId1() {
        Car tested = new Car('r', 1, Orientation.HORIZONTAL, new Position(7,7));
        char expected = 'r';
        char result = tested.getId();
        assertEquals(expected, result);
    }

    /**
     * getOrientation() normal case.
     */
    @Test
    public void testGetOrientation1() {
        Car tested = new Car('a', 2, Orientation.VERTICAL, new Position(2,2));
        Orientation expected = Orientation.VERTICAL;
        Orientation result = tested.getOrientation();
        assertEquals(expected, result);
    }

    /**
     * getPosition() normal case.
     */
    @Test
    public void testGetPosition1() {
        Car tested = new Car('a', 3, Orientation.VERTICAL, new Position(5,0));
        Position expected = new Position(5,0);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }

    /**
     * move(Direction.DOWN) normal case.
     */
    @Test
    public void testMove1() {
        Car tested = new Car('a', 2, Orientation.VERTICAL, new Position(6,6));
        Position expected = new Position(7,6);
        tested.move(Direction.DOWN);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }
    
    /**
     * move(Direction.UP) normal case.
     */
    @Test
    public void testMove2() {
        Car tested = new Car('a', 2, Orientation.VERTICAL, new Position(6,6));
        Position expected = new Position(5,6);
        tested.move(Direction.UP);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }
    
    /**
     * move(Direction.LEFT) normal case.
     */
    @Test
    public void testMove3() {
        Car tested = new Car('a', 2, Orientation.HORIZONTAL, new Position(6,6));
        Position expected = new Position(6,5);
        tested.move(Direction.LEFT);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }
    
    /**
     * move(Direction.RIGHT) normal case.
     */
    @Test
    public void testMove4() {
        Car tested = new Car('a', 2, Orientation.HORIZONTAL, new Position(6,6));
        Position expected = new Position(6,7);
        tested.move(Direction.RIGHT);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }
    
    /**
     * move(Direction.LEFT), exception : incompatible car orientation
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove5() {
        Car tested = new Car('a', 2, Orientation.VERTICAL, new Position(6,6));
        tested.move(Direction.LEFT);
    }
    
    /**
     * move(Direction.RIGHT), exception : incompatible car orientation
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove6() {
        Car tested = new Car('a', 2, Orientation.VERTICAL, new Position(6,6));
        tested.move(Direction.RIGHT);
    }
    
    /**
     * move(Direction.DOWN), exception : incompatible car orientation
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove7() {
        Car tested = new Car('a', 2, Orientation.HORIZONTAL, new Position(6,6));
        tested.move(Direction.DOWN);
    }
    
    /**
     * move(Direction.UP), exception : incompatible car orientation
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove8() {
        Car tested = new Car('a', 2, Orientation.HORIZONTAL, new Position(6,6));
        tested.move(Direction.UP);
    }
    
}
