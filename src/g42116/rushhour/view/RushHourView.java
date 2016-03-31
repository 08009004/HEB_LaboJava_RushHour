package g42116.rushhour.view;

import g42116.rushhour.model.Direction;
import static g42116.rushhour.model.Direction.*;
import g42116.rushhour.model.RushHourGame;
import java.util.Scanner;

/**
 *
 * @author john
 */
public class RushHourView {
    
    //Class attribute:
    private final RushHourGame game;
    
    public RushHourView(RushHourGame game) {
        this.game = game;
    }
    
//tant que le jeu n’est pas terminé (le joueur n’a pas gagné) le programme
//demande à l’utilisateur un id et une direction et déplace la voiture correspondante
//dans la direction demandée, puis affiche le plateau.
//Votre programme doit être robuste, vous devez gérer les erreurs éventuelles de l’uti-
//lisateur.
    public void play() {
        String query1 = "Select the car that you want to move: ";
        String error1 = "Please enter a valid car identifier: ";
        String query2 = "Which way would you like to move it? ";
        String error2 = "Please enter a valid direction: ";
        
        char carID = askChar(query1, error1);
        Direction direction = askDir(query2, error2);
        
        
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
    private static char askChar(String query, String error) {
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
    private static Direction askDir(String query, String error) {
        char desiredDir;
        query = query.concat("\npress U for Up, D for Down, L for Left "
                + "or R for Right: ");
        do {                
            desiredDir = askChar(query, error);
            switch (desiredDir) {
                case 'U': return UP;
                case 'D': return DOWN;
                case 'L': return LEFT;
                case 'R': return RIGHT;
                default:
                    System.out.print(desiredDir + " is not a valid "
                            + "direction.");
                    break;
            }   
        } while (true);
    }
}
