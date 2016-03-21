package g42116.rushhour.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author john
 */
public class BoardTest {

    /**
     * Constructor normal case.
     */
    public void testBoard() {
        new Board(6, 6, new Position(2,5));
    }
    
    /**
     * getHeight() normal case.
     */
    @Test
    public void testGetHeight() {
        Board tested = new Board(3, 4, new Position(0, 0));
        int expected = 3;
        int result = tested.height();
        assertEquals(expected, result);
    }
    
    /**
     * getWidth() normal case.
     */
    @Test
    public void testGetWidth() {
        Board tested = new Board(3, 4, new Position(0, 0));
        int expected = 4;
        int result = tested.width();
        assertEquals(expected, result);
    }

    /**
     * Constructor, exception: width must be strictly greater than 0.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testBoard1() {
        new Board(0, 6, null);
    }
    
    /**
     * Constructor, exception: height must be strictly greater than 0.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testBoard2() {
        new Board(6, 0, null);
    }
    
    /**
     * Constructor, exception: exit must be on one of the board sides.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testBoard3() {
        new Board(6, 6, new Position(2,4));
    }
    
}
