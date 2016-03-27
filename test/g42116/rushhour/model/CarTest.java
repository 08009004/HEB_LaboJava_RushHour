/*
Sauf mention explicite du contraire, toutes les méthodes non triviales du modèle 
devront être testées mais aucune méthode de la vue ne devra l’être.

Les accesseurs peuvent être considérés comme triviaux.
-> c'est quand meme mieux de les tester

*/


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
public class CarTest {
 
    /**
     * Constructor, exception: 'size' must be strictly greater than zero.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testConstructor1() {
        new Car('a', 0, HORIZONTAL, new Position(5,5));
    }

    /**
     * equals() and hashCode() 'true' cases.
     */
    @Test
    public void testEqualsAndHashCode() {
        Car car1 = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Car car2 = new Car('a', 2, HORIZONTAL, new Position(1,1));        
        
        assertTrue(car1.equals(car2) && car2.equals(car1));
        assertTrue(car1.hashCode() == car2.hashCode());
    }
    
    /**
     * equals() 'false' case: different 'id' attributes.
     */
    @Test
    public void testNotEquals1() {
        Car car1 = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Car car2 = new Car('b', 2, HORIZONTAL, new Position(1,1));
        
        assertFalse(car1.equals(car2) || car2.equals(car1));
    }    

    /**
     * equals() 'false' case: different 'size' attributes.
     */
    @Test
    public void testNotEquals2() {
        Car car1 = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Car car2 = new Car('a', 3, HORIZONTAL, new Position(1,1));
        
        assertFalse(car1.equals(car2) || car2.equals(car1));
    }
    
    /**
     * equals() 'false' case: different 'orientation' attributes.
     */
    @Test
    public void testNotEquals3() {
        Car car1 = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Car car2 = new Car('a', 2, VERTICAL, new Position(1,1));
        
        assertFalse(car1.equals(car2) || car2.equals(car1));
    }
    
    /**
     * equals() 'false' case: different 'position' attributes.
     */
    @Test
    public void testNotEquals4() {
        Car car1 = new Car('a', 2, HORIZONTAL, new Position(1,1));
        Car car2 = new Car('b', 2, HORIZONTAL, new Position(1,2));
        
        assertFalse(car1.equals(car2) || car2.equals(car1));
    }
    
    /**
     * getId() normal case.
     */
    @Test
    public void testGetId1() {
        Car tested = new Car('r', 1, HORIZONTAL, new Position(7,7));
        char expected = 'r';
        char result = tested.getId();
        assertEquals(expected, result);
    }

    /**
     * getOrientation() normal case.
     */
    @Test
    public void testGetOrientation1() {
        Car tested = new Car('a', 2, VERTICAL, new Position(2,2));
        Orientation expected = VERTICAL;
        Orientation result = tested.getOrientation();
        assertEquals(expected, result);
    }

    /**
     * getPosition() normal case.
     */
    @Test
    public void testGetPosition1() {
        Car tested = new Car('a', 3, VERTICAL, new Position(5,0));
        Position expected = new Position(5,0);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }

    /**
     * move(Direction.DOWN) normal case.
     */
    @Test
    public void testMove1() {
        Car tested = new Car('a', 2, VERTICAL, new Position(6,6));
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
        Car tested = new Car('a', 2, VERTICAL, new Position(6,6));
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
        Car tested = new Car('a', 2, HORIZONTAL, new Position(6,6));
        Position expected = new Position(6,5);
        tested.move(LEFT);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }
    
    /**
     * move(Direction.RIGHT) normal case.
     */
    @Test
    public void testMove4() {
        Car tested = new Car('a', 2, HORIZONTAL, new Position(6,6));
        Position expected = new Position(6,7);
        tested.move(RIGHT);
        Position result = tested.getCurrentPosition();
        assertEquals(expected, result);
    }
    
    /**
     * move(Direction.LEFT), exception: incompatible car orientation.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove5() {
        Car tested = new Car('a', 2, VERTICAL, new Position(6,6));
        tested.move(LEFT);
    }
    
    /**
     * move(Direction.RIGHT), exception: incompatible car orientation.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove6() {
        Car tested = new Car('a', 2, VERTICAL, new Position(6,6));
        tested.move(RIGHT);
    }
    
    /**
     * move(Direction.DOWN), exception: incompatible car orientation.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove7() {
        Car tested = new Car('a', 2, HORIZONTAL, new Position(6,6));
        tested.move(DOWN);
    }
    
    /**
     * move(Direction.UP), exception : incompatible car orientation.
     */
    @Test (expected=IllegalArgumentException.class)
    public void testMove8() {
        Car tested = new Car('a', 2, HORIZONTAL, new Position(6,6));
        tested.move(UP);
    }
    
    /**
     * getPositions() normal case, with Orientation.HORIZONTAL.
     */
    @Test
    public void testGetPositions1() {
        Car tested = new Car('a', 2, HORIZONTAL, new Position(1,2));
        
        List<Position> expected = Arrays.asList(
            new Position(1,3),
            new Position(1,2)
        );
        
        List<Position> result = tested.getPositions();
        
        assertTrue(
                expected.containsAll(result) && result.containsAll(expected));
    }
    
    /**
     * getPositions() normal case, with Orientation.VERTICAL.
     */
    @Test
    public void testGetPositions2() {
        Car tested = new Car('a', 3, VERTICAL, new Position(1,2));
        
        List<Position> expected = Arrays.asList(
            new Position(3,2),
            new Position(1,2),
            new Position(2,2)
        );
        
        List<Position> result = tested.getPositions();
        
        assertTrue(
                expected.containsAll(result) && result.containsAll(expected));
    }
    
    /**
     * getPositions(), exception: orientation attribute = null.
     */
    @Test (expected=NullPointerException.class) 
    public void testGetPositions3() {
        Car tested = new Car('a', 4, HORIZONTAL, null);
        tested.getPositions();
    }
    
    /**
     * getTranslated(RIGHT) normal case, with Orientation.HORIZONTAL.
     */
    @Test
    public void testGetTranslated1() {
        Car tested = new Car('a', 2, HORIZONTAL, new Position(1,2));
        
        List<Position> expected = Arrays.asList(
            new Position(1,4),
            new Position(1,3)
        );
        
        List<Position> result = tested.getTranslated(RIGHT);
        
        assertTrue(
                expected.containsAll(result) && result.containsAll(expected));
    }
    
    /**
     * getTranslated(LEFT) normal case, with Orientation.HORIZONTAL.
     */
    @Test
    public void testGetTranslated2() {
        Car tested = new Car('a', 2, HORIZONTAL, new Position(1,2));
        
        List<Position> expected = Arrays.asList(
            new Position(1,2),
            new Position(1,1)
        );
        
        List<Position> result = tested.getTranslated(LEFT);
        
        assertTrue(
                expected.containsAll(result) && result.containsAll(expected));
    }

    /**
     * getTranslated(UP) normal case, with Orientation.HORIZONTAL.
     */
    @Test
    public void testGetTranslated3() {
        Car tested = new Car('a', 2, VERTICAL, new Position(1,2));
        
        List<Position> expected = Arrays.asList(
            new Position(0,2),
            new Position(1,2)
        );
        
        List<Position> result = tested.getTranslated(UP);
        
        assertTrue(
                expected.containsAll(result) && result.containsAll(expected));
    }
    
    /**
     * getTranslated(DOWN) normal case, with Orientation.UP.
     */
    @Test
    public void testGetTranslated4() {
        Car tested = new Car('a', 2, VERTICAL, new Position(1,2));
        
        List<Position> expected = Arrays.asList(
            new Position(2,2),
            new Position(3,2)
        );
        
        List<Position> result = tested.getTranslated(DOWN);
        
        assertTrue(
                expected.containsAll(result) && result.containsAll(expected));
    } 
    
    /**
     * getPositions(), exception: orientation.HORIZONTAL and direction.DOWN.
     */
    @Test (expected=IllegalArgumentException.class) 
    public void testGetTranslated5() {
        Car tested = new Car('a', 4, HORIZONTAL, null);
        tested.getTranslated(DOWN);
    }
    
    
    /**
     * getPositions(), exception: orientation.VERTICAL and direction.LEFT.
     */
    @Test (expected=IllegalArgumentException.class) 
    public void testGetTranslated6() {
        Car tested = new Car('a', 4, VERTICAL, null);
        tested.getTranslated(LEFT);
    }
    
}
