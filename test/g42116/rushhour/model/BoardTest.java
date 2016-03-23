package g42116.rushhour.model;

import org.junit.Test;
import static org.junit.Assert.*;
import static g42116.rushhour.model.Orientation.*;

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
     * equals() and hashCode() normal cases.
     */
    @Test
    public void testEqualsAndHashCode() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Board board1 = new Board(testCar);
        Board board2 = new Board(testCar);
        assertTrue(board1.equals(board2) && board2.equals(board1));
        assertTrue(board1.hashCode() == board2.hashCode());
    }
    
    /**
     * case: equals() == false.
     */
//    @Test
    public void testNotEquals() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Board board1 = new Board(testCar);
        Board board2 = new Board();
        assertFalse(board1.equals(board2) && board2.equals(board1));
    }

    /**
     * put(car) normal case.
     */
    @Test
    public void testPutCar() {
        Board tested = new Board();
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Board expected = new Board(testCar);
        tested.put(testCar);
        assertEquals(expected, tested);
    }
  
    /**
     * canPut(car) normal case.
     */
    @Test
    public void testCanPutCar1() {
    Board tested = new Board();
    assertTrue(tested.canPut(new Car('a', 2, HORIZONTAL, new Position(2,0))));
    }

    /**
     * canPut(car) returns false: car outside the board.
     */
    @Test
    public void testCanPutCar2() {
    Board tested = new Board();
    assertFalse(tested.canPut(new Car('a', 3, VERTICAL, new Position(4,4))));
    }

    /**
     * canPut(car), exception: car can't be null.
     */
    @Test (expected=NullPointerException.class)
    public void testCanPutCar3() {
        assertTrue(new Board().canPut(null));
    }   
    
    /**
     * canPut(car) returns false: car overlaps another.
     * uses Board.put(car)
     */
    @Test
    public void testCanPutCar4() {
        Board tested = new Board();
        //testing that a car can initially be added on the board:
        assertTrue(tested.canPut(new Car('b', 3, VERTICAL, new Position(1,3))));
        //adding an overlaping car:
        tested.put(new Car('a', 4, HORIZONTAL, new Position(2,1)));
        //testing that the initial car can't be added anymore:
        assertFalse(tested.canPut(new Car('b', 3, VERTICAL, new Position(1,3))));
    }
    
    /**
     * getCarAt() normal case (2 tests).
     */
//    @Test
//    public void  testGetCarAt() {
//        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
//        Board tested = new Board(testCar);
//        Car expected = testCar;
//        Car result1 = tested.getCarAt(new Position(0,0));
//        Car result2 = tested.getCarAt(new Position(1,2));
//        assertEquals(null, result1);
//        assertEquals(expected, result2);
//    }

}
