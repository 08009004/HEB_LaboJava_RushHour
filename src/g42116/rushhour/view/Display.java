package g42116.rushhour.view;

import g42116.rushhour.model.Board;
import g42116.rushhour.model.Car;

/**
 *
 * @author john
 */
public class Display {
    
    public static void main(String[] args) {
        Board board = new Board();
        displayBoard(board);
    }
    
    static void displayBoard(Board board) {
        printLineOfDashes(3);
        
        printLineOfDashes(7);
    }
    
    private static void printLineOfDashes(int number) {
        for (int i = 0; i < number; i++) {
            System.out.print("-");
        }
        System.out.println("\n");
    }
}
