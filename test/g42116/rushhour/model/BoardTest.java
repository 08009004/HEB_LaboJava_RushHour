package g42116.rushhour.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author john
 */
public class BoardTest {

    /**
     * Minimal constructor normal case.
     */
    @Test
    public void testBoard1() {
        new Board();
    }
    
    /**
     * Full constructor normal case.
     */
    @Test
    public void testBoard2() {
        new Board(6, 6, new Position(2,5));
    }
    
    /**
     * height() normal case.
     */
    @Test
    public void testHeight() {
        Board tested = new Board(3, 4, new Position(0, 0));
        int expected = 3;
        int result = tested.height();
        assertEquals(expected, result);
    }
    
    /**
     * width() normal case.
     */
    @Test
    public void testWidth() {
        Board tested = new Board(3, 4, new Position(0, 0));
        int expected = 4;
        int result = tested.width();
        assertEquals(expected, result);
    }

    /**
     * Full constructor, exception: width must be strictly greater than 0.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testBoard3() {
        new Board(0, 6, null);
    }
    
    /**
     * Full constructor, exception: height must be strictly greater than 0.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testBoard4() {
        new Board(6, 0, null);
    }
    
    /**
     * Full constructor, exception: exit must be on one of the board sides.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testBoard5() {
        new Board(6, 6, new Position(2,4));
    }
    
    /**
     * getExit() normal case.
     */
    @Test
    public void testGetExit() {
        Board tested = new Board();
        Position expected = new Position(2,5);
        Position result = tested.getExit();
        assertEquals(expected, result);
    }
    
    /**
     * getCarAt() normal case.
     */
    @Test
    public void  testGetCarAt() {
        Car testCar = new Car('a', 2, Orientation.HORIZONTAL, new Position(0,0));
        Board tested = new Board(testCar);
        Car expected = testCar;
        Car result1 = tested.getCarAt(new Position(0,0));
        Car result2 = tested.getCarAt(new Position(1,0));
        assertEquals(expected, result1);
        assertEquals(null, result2);
    }
    
    
}
