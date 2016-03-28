package g42116.rushhour.view;

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
        
        char currentCar;
        
        while (!this.game.isOver()) {            
            currentCar = askCarID("Select the car that you want to move: ",
                "Please enter a valid car identifier: ");
        }
    }
    
    /**
     * Asks user to key in character until only a single character is keyed in,
     * blank characters are ignored.
     * 
     * @param   initialMessage  message printed to screen to prompt the user to
     *                          key in a character
     * @param   newRequest      message printed to screen to prompt for a new 
     *                          input if user didn't key in a single non-blank 
     *                          character
     * @return                  the character keyed in by the user
     */
    private char askCarID(String initialMessage, String newRequest) {
        Scanner keyboard = new Scanner(System.in);
        String str1;
        System.out.print("\n" + initialMessage);
/* API: "A Scanner breaks its input into tokens using a delimiter pattern, which 
        by default matches whitespace."
        "nextLine() advances this scanner past the current line and returns the 
        input that was skipped."
*/
        do {
            str1 = keyboard.nextLine();
            str1 = str1.replace(" ", "");
            str1 = str1.replace("\t", "");
            
            if (str1.equals("")) {
                System.out.print("You have keyed in a blank character. "
                    + newRequest);
            }

            if (str1.length() > 1) {
                System.out.print("You have keyed in several characters. "
                    + newRequest);
            }
    
        } while (str1.length() > 1 || str1.equals(""));

        return str1.charAt(0);
    }
    
}
