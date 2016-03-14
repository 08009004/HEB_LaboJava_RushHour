package g42116.rushhour.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g42116
 */
public class PositionTest {

    // Tests for Position.getRow:
        
    @Test
    public void testGetRow1() {
        Position tested = new Position(0,12);
        int expected = 0;
        assertEquals(expected, tested.getRow());
    }
    
    @Test
    public void testGetRow2() {
        Position tested = new Position(75,-9);
        int expected = 75;
        assertEquals(expected, tested.getRow());
    }
    
    @Test
    public void testGetRow3() {
        Position tested = new Position(-8,0);
        int expected = -8;
        assertEquals(expected, tested.getRow());
    }

    // Tests for Position.getColumn:
    @Test
    public void testGetColumn1() {
        Position tested = new Position(9,0);
        int expected = 0;
        assertEquals(expected, tested.getColumn());
    }

    @Test
    public void testGetColumn2() {
        Position tested = new Position(-5,58);
        int expected = 58;
        assertEquals(expected, tested.getColumn());
    }

    @Test
    public void testGetColumn3() {
        Position tested = new Position(0,-3);
        int expected = -3;
        assertEquals(expected, tested.getColumn());
    }

    // Tests for Position.getPosition()
    
    @Test
    public void testGetPositionLeft() {
        Position tested = new Position(1,1);
        Position expected = new Position(1,0);
        Position result = tested.getPosition(Direction.LEFT);
        assertEquals(expected, result);
    }
        
    @Test
    public void testGetPositionRight() {
        Position tested = new Position(1,1);
        Position expected = new Position(1,2);
        Position result = tested.getPosition(Direction.RIGHT);
        assertEquals(expected, result);
    }
    
        
    @Test
    public void testGetPositionUp() {
        Position tested = new Position(2,3);
        Position expected = new Position(1,3);
        Position result = tested.getPosition(Direction.UP);
        assertEquals(expected, result);
    }
    
        
    @Test
    public void testGetPositionDown() {
        Position tested = new Position(1,1);
        Position expected = new Position(2,1);
        Position result = tested.getPosition(Direction.DOWN);
        assertEquals(expected, result);
    }
    
        
    @Test(expected = NullPointerException.class)
    public void testGetPositionNull() {
        Position tested = new Position(9,4);
        Direction nullDirection = null;
        tested.getPosition(nullDirection);
    }
    
/*        @Test
    public void testToString() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testGetPosition() {
    }
*/    
    
}
