package g42116.rushhour.JsonIO;

import g42116.rushhour.model.RushHourException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class provides the mechanisms to instantiate a Json object from file to
 * other package classes.
 * 
 * @author john
 */
public class JsonLoader {

    private JSONObject jsonObj;

    /**
     * Loads the json language file.
     * 
     * @param   JsonFile    the Json file to load
     * @return              a Json Object generated from the Json file
     * @throws              RushHourException if there is a problem opening or
     *                      parsing the configuration file
     */
    JsonLoader(File JsonFile) throws RushHourException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(JsonFile));
            JSONParser parser = new JSONParser();
            this.jsonObj = (JSONObject) parser.parse(in);
        } catch (IOException e) {
            throw new RushHourException("language config file access failure");
        } catch (ParseException e) {
            throw new RushHourException("language config parsing failure");
        }
    }

    /**
     * Returns the JSONObject.class instance.
     * 
     * @return 
     */
    public JSONObject getJsonObj() {
        return jsonObj;
    }
    
    
}
