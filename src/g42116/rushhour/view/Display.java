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
        System.out.print("\n");
        
        System.out.println(ColourString.to(
                            createLineOfDashes(3*board.width()), BLUE, WHITE));

        for (int i = 0; i < board.height()  ; i++) {
            printRow(board, i);
        }
        
        System.out.println(ColourString.to(
                            createLineOfDashes(3*board.width()), BLUE, WHITE));

        System.out.print("\n");
    }
    
    /**
     * Creates a  line of dash characters.
     * 
     * @param   number  the number of dashes printed
     */
    private static String createLineOfDashes(int number) {
        String line = "\u0020";
        for (int i = 0; i < number; i++) {
            line += "-";
        }
        line += "\u0020";
//        line += "\n";
        return line;
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
//        String row = "\n";
        String row = "";
        Position boardSquare;
        int exitColumn = board.getExit().getColumn();
        int exitRow = board.getExit().getRow();
        
        for (int boxLine = 0; boxLine < 3; boxLine++) {
            
            if((exitColumn == 0) && (exitRow == currentRow)) {

                row += "X";
            } else {
                row += "t";
                row += "|";
            }

            for (int j = 0; j < board.width(); j++) {
                boardSquare = new Position(currentRow, j);

                if (board.getCarAt(boardSquare) == null) {
                    for (int k = 0; k < 3; k++) {
                        row += ColourString.to("\u0020", WHITE, BLACK);
                    }

                } else {
                    for (int k = 0; k < 3; k++) {
                        row += ColourString.to("" + board.getCarAt(boardSquare).getId(), 
                                setBackColour(board.getCarAt(boardSquare).getId()), 
                                setIdColour(board.getCarAt(boardSquare).getId()));    
                    }
                }
            }

            System.out.print(verticalBorder((exitColumn == board.width()-1) && (exitRow == currentRow)));

            row += "\n";
        }
        
        System.out.print(row);
    }
    
    private static String verticalBorder(boolean condition) {
        String row = "";
        if(condition) {
            row += "X";
        } else {
                row += "|";
        }
        row += "b";
        return row;
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
