package g42116.rushhour.JsonIO;

import g42116.rushhour.model.Car;
import g42116.rushhour.model.Orientation;
import g42116.rushhour.model.Position;
import g42116.rushhour.model.RushHourException;
import g42116.rushhour.model.RushHourGame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static java.lang.Math.toIntExact;

/**
 *
 * @author g42116
 */
public class GameInitialiser {

    public final int height;
    public final int width;
    public final Position exit;
    public final Car redCar;
    public final List<Car> otherCars;
    
    
    /**
     * Full constructor.
     * 
     * @param   gameInit    the game configuration file 
     * @throws              RushHourException if there is a problem opening or
     *                      parsing the configuration file
     */
    public GameInitialiser(File gameInit) throws RushHourException {
        JSONObject initialBoard = load(gameInit);

        this.height = toIntExact((long) initialBoard.get("boardHeight"));
        this.width = toIntExact((long) initialBoard.get("boardWidth"));
        
        JSONArray exitData = (JSONArray) initialBoard.get("exit");
        int row = toIntExact((long) exitData.get(0));
        int column = toIntExact((long) exitData.get(1));
        this.exit = new Position(row, column);
        
        JSONArray redCarData = (JSONArray) initialBoard.get("redCar");
        this.redCar = new Car(
                ((String) redCarData.get(0)).charAt(0), 
                toIntExact((long)redCarData.get(1)), 
                Orientation.valueOf((String) redCarData.get(2)), 
                new Position(toIntExact((long)redCarData.get(3)), 
                                          toIntExact((long)redCarData.get(4))));
        
        this.otherCars = theOtherCars((JSONArray) initialBoard.get("otherCars"));
    }

    /**
     * Creates a list of Car objects based on data from a JSONArray.
     * 
     * @param   carsArray   the JSONArray containing the Car objects attributes
     * @return              an ArrayList of the Car objects
     */
    private List<Car> theOtherCars(JSONArray carsData) {
        char id;
        int size;
        Orientation orientation;
        int row;
        int column;
        JSONArray singleCarData;
        List<Car> theCars = new ArrayList<>();
        
        for (int i = 0; i < carsData.size(); i++) {
            singleCarData = (JSONArray) carsData.get(i);

            id = ((String) singleCarData.get(0)).charAt(0);
            size = toIntExact((long)singleCarData.get(1));
            orientation = Orientation.valueOf((String) singleCarData.get(2));
            row = toIntExact((long)singleCarData.get(3));
            column = toIntExact((long)singleCarData.get(4));

            theCars.add(
                     new Car(id, size, orientation, new Position(row, column)));
        }

        return theCars;
    }

    /**
     * Loads the json game file.
     * 
     * @param   gameInit    the game configuration file
     * @return              a Json Object generated from the configuration file
     * @throws              RushHourException if there is a problem opening or
     *                      parsing the configuration file
     */
    private JSONObject load(File gameInit) throws RushHourException {
        JSONObject jsonObj = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(gameInit));
            JSONParser parser = new JSONParser();
            jsonObj = (JSONObject) parser.parse(in);
        } catch (IOException e) {
            throw new RushHourException("game config file access failure");
        } catch (ParseException e) {
            throw new RushHourException("game config parsing failure");
        }
        
        return jsonObj;
    }

    /**
     * Builds a new RushHourGame object from this GameInitialiser's attributes.
     * 
     * @return  a new RushHourGame instance based on Json file content
     * @throws  RushHourException if one of the class attributes is an illegal
     *          argument for RushHourGame instatiation
     */
    public RushHourGame initialise() throws RushHourException {
        return new RushHourGame(height, width, exit, redCar, otherCars);
    }

}