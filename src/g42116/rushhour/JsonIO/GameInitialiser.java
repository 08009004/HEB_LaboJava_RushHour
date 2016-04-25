package g42116.rushhour.JsonIO;

import g42116.rushhour.model.Car;
import g42116.rushhour.model.Position;
import g42116.rushhour.model.RushHourGame;
import g42116.rushhour.view.Display;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author g42116
 */
public class GameInitialiser {

//    public final int width;
//    public final int height;
//    public final Position exit;
//    public final Car redCar;
//    public final List<Car> otherCars;
    
    public static JSONObject GameInitialiser(File gameInit) {
        JSONObject jsonObj = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(gameInit));
            JSONParser parser = new JSONParser();
            jsonObj = (JSONObject) parser.parse(in);
        } catch (FileNotFoundException ex) {
            System.out.println("buffer reader failure");
        } catch (IOException ex) {
            Logger.getLogger(GameInitialiser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GameInitialiser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return jsonObj;
    }

    /**
     * Builds a new RushHourGame object from GameInitialiser's attributes.
     * 
     * @return  a new RushHourGame instance based on Json file content
     */
    public static RushHourGame boardInitialiser() {
//        return new RushHourGame(height, width, exit, redCar, otherCars);
return null;
    }
    
    public static void main(String[] args) {
        File file = new File("src/g42116/rushhour/games/Game1Test.json");
        GameInitialiser(file);
        System.out.println("GameInitialiser(file) = " + GameInitialiser(file));
        RushHourGame test = boardInitialiser();
        Display.displayBoard(test.getBoard());
    }
}
