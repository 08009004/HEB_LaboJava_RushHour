package g42116.rushhour.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author g42116
 */
public class PositionTest {
        
    /**
     * equals() and hashCode() normal cases.
     */
    @Test
    public void testEqualsAndHashCode() {
        Position x = new Position(3,11);
        Position y = new Position(3,11);
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }
    
    /**
     * case: equals() == false.
     */
    @Test
    public void testNotEquals() {
        Position x = new Position(11,9);
        Position y = new Position(3,11);
        assertFalse(x.equals(y) || y.equals(x));
    }
  
    /**
     * getRow() normal case.
     */
    @Test
    public void testGetRow1() {
        Position tested = new Position(0,12);
        int expected = 0;
        assertEquals(expected, tested.getRow());
    }

    /**
     * getColumn() normal case.
     */
    @Test
    public void testGetColumn1() {
        Position tested = new Position(9,0);
        int expected = 0;
        assertEquals(expected, tested.getColumn());
    }

    /**
     * getPosition(Direction.LEFT) normal case.
     */
    @Test
    public void testGetPositionLeft() {
        Position tested = new Position(1,1);
        Position expected = new Position(1,0);
        Position result = tested.getPosition(Direction.LEFT);
        assertEquals(expected, result);
    }
    
    /**
     * getPosition(Direction.RIGHT) normal case.
     */
    @Test
    public void testGetPositionRight() {
        Position tested = new Position(1,1);
        Position expected = new Position(1,2);
        Position result = tested.getPosition(Direction.RIGHT);
        assertEquals(expected, result);
    }

    /**
     * getPosition(Direction.UP) normal case.
     */
    @Test
    public void testGetPositionUp() {
        Position tested = new Position(2,3);
        Position expected = new Position(1,3);
        Position result = tested.getPosition(Direction.UP);
        assertEquals(expected, result);
    }
    
    /**
     * getPosition(Direction.DOWN) normal case.
     */
    @Test
    public void testGetPositionDown() {
        Position tested = new Position(1,1);
        Position expected = new Position(2,1);
        Position result = tested.getPosition(Direction.DOWN);
        assertEquals(expected, result);
    }
    
    /**
     * Argument 'direction' can't be null.
     */
    @Test (expected = NullPointerException.class)
    public void testGetPositionNull() {
        Position tested = new Position(9,4);
        tested.getPosition(null);
    }

}
