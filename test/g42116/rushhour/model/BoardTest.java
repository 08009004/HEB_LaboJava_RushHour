package g42116.rushhour.model;

import org.junit.Test;
import static org.junit.Assert.*;
import static g42116.rushhour.model.Orientation.*;
import static g42116.rushhour.model.Direction.*;

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
        Car[][] testGrid1 = {
            {null,    null,    null,    null,    null,    null},
            {null,    testCar, testCar, null,    null,    null},
            {null,    null,    null,    null,    null,    null}
        };
        Board board1 = new Board(testGrid1);
        
        Car testCar2 = new Car('a', 2, HORIZONTAL, new Position(1,1));        
        Car[][] testGrid2 = {
            {null,    null,    null,    null,    null,    null},
            {null,    testCar, testCar, null,    null,    null},
            {null,    null,    null,    null,    null,    null}
        };
        Board board2 = new Board(testGrid2);
        
        assertTrue(board1.equals(board2) && board2.equals(board1));
        assertTrue(board1.hashCode() == board2.hashCode());
    }

    /**
     * equals() 'false' case: different cars on grid.
     */
    @Test
    public void testNotEquals1() { 
        Board board1 = createTestBoard();
        Board board2 = new Board();
        assertFalse(board1.equals(board2) || board2.equals(board1));
    }

    /**
     * equals() 'false' case: different grid exit position.
     */
    @Test
    public void testNotEquals2() {
        Board board1 = new Board(6, 6, new Position(4, 0));
        Board board2 = new Board();
        assertFalse(board1.equals(board2) || board2.equals(board1));
    }

    /**
     * put(car) normal case.
     */
    @Test
    public void testPutCar() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));        
        Board tested = new Board();
        tested.put(testCar);
        
        Board expected = createTestBoard();
        
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
     * getCarAt() normal case.
     */
    @Test
    public void testGetCarAt1() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Board tested = new Board();
        Car expected = testCar;
        
        tested.put(testCar);
        Car result = tested.getCarAt(new Position(1,2));
        
        assertEquals(expected, result);
    }

    /**
     * getCarAt() null case.
     */
    @Test
    public void testGetCarAt2() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Board tested = new Board();
        Car expected = testCar;
        
        tested.put(testCar);
        Car result = tested.getCarAt(new Position(0,0));
        
        assertNull(result);
    }

    /**
     * getCarAt(), exception : passed position is outside the board.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testGetCarAt3() {
        Board tested = new Board();
        tested.getCarAt(new Position(6,4));
    }

    /**
     * removeC(car) normal case.
     */
    @Test
    public void testRemoveCar() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Board tested = createTestBoard();;
        tested.remove(testCar);
        
        Board expected = new Board();
        
        assertEquals(expected, tested);
    }

    /**
     * getCar(id) normal case.
     */
    @Test
    public void testGetCar1() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Board tested = createTestBoard();
        
        Car expected = testCar;
        Car result = tested.getCar('a');
                
        assertEquals(expected, result);
    }

    /**
     * getCar(id) null case.
     */
    @Test
    public void testGetCar2() {
        Board tested = new Board();
        assertNull(tested.getCar('b'));
    }

    /**
     * canMove(car, direction) normal case (move down).
     */
    @Test
    public void testCanMoveCar1() {
        Car testCar = new Car('b', 3, VERTICAL, new Position(2,1));
        Board tested = new Board();
        tested.put(testCar);
        assertTrue(tested.canMove(testCar, DOWN));
    }

    /**
     * canMove(car, direction) normal case (move up).
     */
    @Test
    public void testCanMoveCar2() {
        Car testCar = new Car('b', 3, VERTICAL, new Position(2,1));
        Board tested = new Board();
        tested.put(testCar);
        assertTrue(tested.canMove(testCar, UP));
    }

    /**
     * canMove(car, direction) normal case (move left).
     */
    @Test
    public void testCanMoveCar3() {
        Car testCar = new Car('b', 3, HORIZONTAL, new Position(2,1));
        Board tested = new Board();
        tested.put(testCar);
        assertTrue(tested.canMove(testCar, LEFT));
    }

    /**
     * canMove(car, direction) normal case (move left).
     */
    @Test
    public void testCanMoveCar4() {
        Car testCar = new Car('b', 3, HORIZONTAL, new Position(2,1));
        Board tested = new Board();
        tested.put(testCar);
        assertTrue(tested.canMove(testCar, RIGHT));
    }

    /**
     * canMove(car, direction) 'false' case: car moved over another.
     */
    @Test
    public void testCanMoveCar5() {
        Car testCar = new Car('b', 3, VERTICAL, new Position(2,1));
        Board tested = createTestBoard();
        tested.put(testCar);
        assertFalse(tested.canMove(testCar, UP));
    }

    /**
     * canMove(car, direction) 'false' case: car moved outside the board.
     */
    @Test
    public void testCanMoveCar6() {
        Car testCar = new Car('b', 3, VERTICAL, new Position(3,1));
        Board tested = new Board();
        tested.put(testCar);
        assertFalse(tested.canMove(testCar, DOWN));
    }  

    /**
     * canMove(car, direction), exception: car == null.
     */
    @Test (expected=NullPointerException.class)
    public void TestCanMoveCar7() {
        Board tested = new Board();
        tested.canMove(null, LEFT);
    }

    /**
     * This method declares and instantiates a board for test purpose.
     * 
     * @return  a board board with its exit on position (2,5) and that contains 
     *          only the following car:
     *          new Car('a', 2, HORIZONTAL, new Position(1,1)
     */
    private static Board createTestBoard() {
        Car testCar = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Car[][] testGrid = {
            {null,    null,    null,    null,    null,    null},
            {null,    testCar, testCar, null,    null,    null},
            {null,    null,    null,    null,    null,    null},
            {null,    null,    null,    null,    null,    null},
            {null,    null,    null,    null,    null,    null},
            {null,    null,    null,    null,    null,    null}
        };
        return new Board(testGrid);
    }
}
