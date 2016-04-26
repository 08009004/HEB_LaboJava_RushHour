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
 *
 * @author g42116
 */
public class LanguageInitialiser {
    
        
    
    /**
     * Loads the json language file.
     * 
     * @param   language    the language configuration file
     * @return              a Json Object generated from the configuration file
     * @throws              RushHourException if there is a problem opening or
     *                      parsing the configuration file
     */
    private JSONObject load(File language) throws RushHourException {
        JSONObject jsonObj = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(language));
            JSONParser parser = new JSONParser();
            jsonObj = (JSONObject) parser.parse(in);
        } catch (IOException e) {
            throw new RushHourException("language config file access failure");
        } catch (ParseException e) {
            throw new RushHourException("language config parsing failure");
        }
        
        return jsonObj;
    }
}
