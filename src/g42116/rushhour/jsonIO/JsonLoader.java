package g42116.rushhour.jsonIO;

import g42116.rushhour.model.RushHourException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class provides the methods required to instantiate a Json object from 
 * file, to the attention of other methods of this package.
 * 
 * @author g42116
 */
public class JsonLoader {

    private JSONObject jsonObj;

    /**
     * Loads the Json file.
     * 
     * @param   jsonFilePath    the filepath to the Json file to load
     * @return                  a Json Object generated from the Json file
     * @throws                  RushHourException if there is a problem opening
     *                          or parsing the configuration file
     */
    
    JsonLoader(String jsonFilePath) throws RushHourException {
/*      Best way to open resource files is to call getResourceAsStream as
        explained in the following atricle:
        http://www.javaworld.com/article/2077352/java-se/smartly-load-your-properties.html

        More details on filepath available here: "If you don’t have a lead /,
        you have a relative name and the name of the package will be prepended. 
        If you use a /, you must include the name of the package yourself, or 
        whatever name the resource is filed under in the jar."
        http://mindprod.com/jgloss/getresourceasstream.html                   */

        JSONParser parser = new JSONParser();
        InputStream stream = this.getClass().getResourceAsStream(jsonFilePath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        try {
            this.jsonObj = (JSONObject) parser.parse(reader);
//            stream.close();
        } catch (IOException e) {
            throw new RushHourException("json config file access failure");
        } catch (ParseException e) {
            throw new RushHourException("json config file parsing failure");
        }
    }

    /**
     * Returns a JSONObject, instantiated from the file at path passed as 
     * constructor parameter.
     * 
     * @return  the JSONObject created
     */
    public JSONObject getJsonObj() {
        return jsonObj;
    }

}
