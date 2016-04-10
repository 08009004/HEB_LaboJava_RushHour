package g42116.rushhour.view;

import java.util.Scanner;
import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.model.Direction;
import static g42116.rushhour.model.Direction.*;
import static g42116.rushhour.view.Display.displayBoard;

/**
 * This class manages the game visuals.
 * 
 * @author john
 */
public class RushHourView {
    
    //Class attribute:
    private final RushHourGame game;
    
    public RushHourView(RushHourGame game) {
        this.game = game;
    }

    /**
     * As long as the game is not over, this method prompts the player to key in
     * a car ID and a direction, then moves this car in the desired direction
     * if possible ; then prints the game board to screen.
     */
    public void play() {
        String query1 = "Which car would you like to move: ";
        String error1A = "Not a car ID. Please enter a valid ID: ";
        String error1B = "No such car on the board. Please select a valid car.";
        String query2 = "Which way would you like to move it? ";
        String error2 = "Please enter a valid direction: ";
        String query3 = "Please try another move: ";
        
        char carID;
        Direction direction;
        
        do {
            carID = requestID(query1, error1A, error1B);
            direction = requestDir(query2, error2, carID);
            
            try {
                this.game.move(carID, direction);

            } catch (RushHourException rhe) {
                System.out.println(rhe.getMessage().replace(
                                "g42116.rushhour.model.RushHourException: ", "")
                        + "\n\n"
                        + query3);
            }
            
            displayBoard(this.game.getBoard());
            
        } while (!this.game.isOver());
        
        System.out.println("\nGAME COMPLETED, CONGRATULATIONS!");
        
    }

    /**
     * Asks player to key in the ID of the car that he wants to move.
     * 
     * @param   query   message printed to screen to prompt the user to key in 
     *                  the ID of the car that he wants to move
     * @param   error1  message printed to screen to prompt for a new input if 
     *                  user didn't key in a single non-blank character 
     * @param   error2  message printed to screen to prompt for a new input if 
     *                  the ID entered doesn't match that of one of the cars 
     *                  present on the board
     * @return          player's entry
     */
    private char requestID(String query, String error1, String error2) {
        char carID;
        
        do {
            carID = askChar(query, error1);
            if (!isValidID(carID)) {
                    System.out.println(error2);
                    carID = askChar(query, error1);
            }
        } while (!isValidID(carID));
        
        return carID;
    }
    
    /**
     * Checks that a car is present on the board.
     * 
     * @param   carID   the 'id' attribute of the car being searched for
     * @return          true if that car is found on the board, otherwise false
     */
    private boolean isValidID(char carID) {
        return this.game.getBoard().getCar(carID) != null;
    }
    
    /**
     * Asks user to key in a character, until only a single character is keyed 
     * in (blank characters are ignored).
     * 
     * @param   query   message printed to screen to prompt the user to key in a 
     *                  character
     * @param   error   message printed to screen to prompt for a new input if 
     *                  user didn't key in a single non-blank character
     * @return          user's entry (upper case)
     */
    private char askChar(String query, String error) {
        Scanner keyboard = new Scanner(System.in);
        String str1;
        System.out.print("\n" + query);
/* API: "A Scanner breaks its input into tokens using a delimiter pattern, which 
        by default matches whitespace."
        nextLine() "advances this scanner past the current line and returns the 
        input that was skipped."
*/
        do {
            str1 = keyboard.nextLine();
            str1 = str1.replace(" ", "").replace("\t", "");
            str1 = str1.toUpperCase();
            
            if (str1.equals("")) {
                System.out.print("You only keyed in blank characters. "
                    + error);
            }

            if (str1.length() > 1) {
                System.out.print("You have keyed in several characters. "
                    + error);
            }
    
        } while (str1.length() > 1 || str1.equals(""));

        return str1.charAt(0);
    }

    /**
     * Asks player to key in the direction which he wants to move a car.
     * 
     * @param   query   message printed to screen to prompt the user to key in 
     *                  the direction
     * @param   error   message printed to screen to prompt for a new input if 
     *                  the direction selected is mismatched match with the
     *                  orientation of the car
     * @param   carID   the car that the player wants to move
     * @return          player's entry
     */
    private Direction requestDir(String query, String error, char carID) {
        Direction direction;
        
        do {
            direction = askDir(query, error);
            if (areMismatched(carID, direction)) {
                    System.out.println("Car " + carID + " can't be moved in the"
                        + " selected direction. ");
                    direction = askDir(query, error);
                }
        } while (!isValidID(carID));
        
        return direction;
    }
    
    /**
     * Checks if a car can be moved in a given direction or not.
     * 
     * @param   carID       the car being checked
     * @param   direction   the desired move direction
     * @return              true if the car and the direction are incompatible,
     *                      otherwise false
     */
    private boolean areMismatched(char carID, Direction direction) {
        return this.game.getBoard().getCar(carID).isWrongOrientation(direction);
    }
    
    /**
     * Asks user to key in a direction, until a valid key is pressed: U for Up, 
     * D for Down, L for Left or R for Right. This method is not case sensitive.
     * 
     * @param   query   message printed to screen to prompt the user to key in a 
     *                  direction, followed by a listing of the four choices 
     *                  available
     * @param   error   message printed to screen to prompt for a new input if 
     *                  user didn't key in a valid entry
     * @return          character representative of the user's choice (upper
     *                  case)
     */
    private Direction askDir(String query, String error) {
        char keyedIn;
        query = query.concat("\npress U for Up, D for Down, L for Left "
                + "or R for Right: ");
        do {                
            keyedIn = askChar(query, error);
            switch (keyedIn) {
                case 'U': return UP;
                case 'D': return DOWN;
                case 'L': return LEFT;
                case 'R': return RIGHT;
                default:
                    System.out.print(keyedIn + " is not a valid direction.");
                    break;
            }   
        } while (true);
    }
}
