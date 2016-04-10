package g42116.rushhour.view;

import g42116.rushhour.model.Board;
import g42116.rushhour.model.Car;
import g42116.rushhour.model.Position;
import static g42116.rushhour.model.Orientation.*;
import static g42116.rushhour.view.Colour.*;

/**
 * This class serves the purpose of displaying the game board to screen.
 * 
 * @author john
 */
public class Display {
    
    /**
     * Main method to test the display.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        Board board = new Board();
        Car redCar = new Car('R', 2, HORIZONTAL, new Position(2,0));
        Car car1 = new Car('3', 3, VERTICAL, new Position(2,2));
        Car car2 = new Car('6', 3, VERTICAL, new Position(2,4));
        
        board.put(redCar);
        if (board.canPut(car1)) board.put(car1);
        if (board.canPut(car2)) board.put(car2);
        
//System.out.println("setBackColour(car1.getId()) = " + setBackColour(car1.getId()));
//System.out.println("setIdColour(car1.getId()) = " + setIdColour(car1.getId()));
//System.out.println("setBackColour(car2.getId()) = " + setBackColour(car2.getId()));
//System.out.println("setIdColour(car1.getId()) = " + setIdColour(car1.getId()));

        displayBoard(board);
    }
    
    /**
     * Displays lines of characters representing the game board in the terminal.
     * 
     * @param   board   the board to display
     */
    public static void displayBoard(Board board) {
        printLineOfDashes(board.width());
        
        for (int i = 0; i < board.height()  ; i++) {
            printRow(board, i);
        }
        printLineOfDashes(board.width());
        System.out.print("\n");
    }
    
    /**
     * Prints a carriage return followed by a line of dashes.
     * 
     * @param   number  the number of dashes printed
     */
    private static void printLineOfDashes(int number) {
        String line = "\n\u0020";
        for (int i = 0; i < number; i++) {
            line += "-";
        }
        line += "\u0020";
        System.out.print(ColourString.to(line, GREEN, WHITE));
    }
    
    /**
     * Prints a carriage return followed by a game board row in the terminal as 
     * follows:
     *  - first a pipe character (or a cross if row's first box is the exit) ;
     *  - followed by dots for empty boxes or the car's id if the box is
     *    occupied ;
     *  - finally a pipe character (or a cross if row's last box  the exit).
     * 
     * @param   board       the board being printed
     * @param   currentRow  the row being printed
     */
    private static void printRow(Board board, int currentRow) {
        String row = "\n";
        Position boardSquare;
        int exitColumn = board.getExit().getColumn();
        int exitRow = board.getExit().getRow();
        
        if((exitColumn == 0) && (exitRow == currentRow)) {
            row += ColourString.to("X", WHITE, BLUE);
        } else {
            row += ColourString.to("|", GREEN, WHITE);
        }

        for (int i = 0; i < board.width(); i++) {
            boardSquare = new Position(currentRow, i);
            
            if (board.getCarAt(boardSquare) == null) {
                row += ColourString.to("\u0020", WHITE, BLACK);
            } else {
                row += ColourString.to("" + board.getCarAt(boardSquare).getId(), 
                        setBackColour(board.getCarAt(boardSquare).getId()), 
                        setIdColour(board.getCarAt(boardSquare).getId()));
            }
        }
        
        if((exitColumn == board.width()-1) && (exitRow == currentRow)) {
            row += ColourString.to("X", WHITE, BLUE);
        } else {
            row += ColourString.to("|", GREEN, WHITE);
        }
        
        System.out.print(row);
    }
    
    /**
     * Sets the background colour of a car character on the board displayed
     * according to its 'id' attribute.
     * 
     * @param   carID   the car ID
     * @return          a colour based on the 'id' UTF code number (red only if
     *                  car id is 'R', never white)
     */
    private static Colour setBackColour(char carID) {
        int col;
        col = carID % 6;
        if (col == 1) col = 6;
        if (carID == 'R') col = 1;
        return Colour.values()[col];
    }
    
    /**
     * Sets the 'id' character's font colour when displayed.
     * 
     * @param   carID   the 'id' of the car
     * @return          BLACK if the background should be of a light colour,
     *                  otherwise WHITE
     */
    private static Colour setIdColour(char carID) {
        switch (setBackColour(carID)) {
            case BLACK:
            case RED:
            case BLUE:
            case MAGENTA:
                return WHITE;
        }
        return BLACK;
    }
}
