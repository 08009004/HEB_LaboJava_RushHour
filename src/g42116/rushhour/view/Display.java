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
                                  lineOfDashes(3*board.width()), BLACK, WHITE));

        for (int i = 0; i < board.height()  ; i++) {
            printRow(board, i);
        }
        
        System.out.println(ColourString.to(
                                  lineOfDashes(3*board.width()), BLACK, WHITE));

        System.out.print("\n");
    }
    
    /**
     * Creates a  line of dash characters.
     * 
     * @param   number  the number of dashes printed
     */
    private static String lineOfDashes(int number) {
        String line = "\u0020";
        for (int i = 0; i < number; i++) {
            line += "-";
        }
        line += "\u0020";
//        line += "\n";
        return line;
    }
    
    /**
     * Prints three lines in the terminal, representing a board row as follows:
     * <br> 
     * 1 - first 3 pipe characters on top of each other (or a space character on
     *     top of an X on top of another space if row's first box is the exit);<br> 
     * 2 - followed by space characters filling empty boxes or the car's id in 
     *     the center of the box if the it is occupied;<br> 
     * 3 - then 3 pipe characters on top of each other (or a space character on
     *    top of an X on top of another space if row's first box is the exit);
     * 4 - finally a carriage return character.<br> 
     * 
     * @param   board       the board being printed
     * @param   currentRow  the row being printed
     */
    private static void printRow(Board board, int currentRow) {
        String boardRow = "";
        int exitColumn = board.getExit().getColumn();
        int exitRow = board.getExit().getRow();
        Car carOnBox;
        boolean isExit;
        
        for (int boxLine = 0; boxLine < 3; boxLine++) {
            
            isExit = (exitColumn == 0) && (exitRow == currentRow);
            boardRow += ColourString.to(
                                     sideBorder(isExit, boxLine), BLACK, WHITE);

            for (int box = 0; box < board.width(); box++) {
                
                carOnBox = board.getCarAt(new Position(currentRow, box));

                for (int boxColumn = 0; boxColumn < 3; boxColumn++) { 

                    if((carOnBox != null) && (boxLine==1) && (boxColumn==1)) {
                            boardRow += ColourString.to("" + carOnBox.getId(), 
                                                    setCarColour(carOnBox), 
                                                         setIdColour(carOnBox)); 
                    } else {
                            boardRow += ColourString.to("\u0020", 
                                                    setCarColour(carOnBox), 
                                                         setIdColour(carOnBox));
                    }
                }
            }

            isExit = (exitColumn == board.width()-1) && (exitRow == currentRow);
            boardRow += ColourString.to(
                                     sideBorder(isExit, boxLine), BLACK, WHITE);

            boardRow += "\n";
        }
        
        System.out.print(boardRow);
    }
    
    /**
     * Formats the vertical border boxes (for end and start of board rows).
     * 
     * @param   isExit  controls method behaviour
     * @param   counter itration counter
     * @return          'X' (if counter equals 1) or a space character when 
     *                  isExit is true, otherwise a pipe character
     */
    private static String sideBorder(boolean isExit, int counter) {
        String row = "";
        if(isExit) {
            if (counter == 1) {
                row += "X";
            } else {
                row += "\u0020";
            }
        } else {
            row += "|";
        }
        return row;
    }
    
    /**
     * Sets the background colour of a car character on the board displayed
     * according to its 'id' attribute.
     * 
     * @param   Car the car
     * @return      a colour based on the 'id' UTF code number (red only if car 
     *              id is 'R', and white only if car equals null)
     */
    private static Colour setCarColour(Car car) {
        int colourIndex;
        
        if (car == null) {
            colourIndex = 7;
        } else {
            colourIndex = car.getId() % 6;
            if (colourIndex == 1) colourIndex = 6;
            if (car.getId() == 'R') colourIndex = 1;
        }
        return Colour.values()[colourIndex];
    }
    
    /**
     * Sets a car's 'id' font display colour, based on the car's colour by 
     * setCarColour(Car car).
     * 
     * @param   carID   the car
     * @return          BLACK if the background should be of a light colour,
     *                  otherwise WHITE
     */
    private static Colour setIdColour(Car car) {
        switch (setCarColour(car)) {
            case BLACK:
            case RED:
            case BLUE:
            case MAGENTA:
                return WHITE;
        }
        return BLACK;
    }
    
    /**
     *  Clears the terminal screen by printing 100 empty lines.
     */
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }
}
