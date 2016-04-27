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
public class Language {
    
    // Display messages:
    public final String listLangFiles;
    public final String listGameInitFiles;
    public final String listDirChoices;
    public final String endOfGame;

    // Query messages:
    public final String queryLang;
    public final String reQueryLang;
    public final String queryGameInit;
    public final String reQueryGameInit;
    public final String queryCarId;
    public final String queryDir;
    public final String queryDifferentMove;

    // Error messages:
    public final String errCharsOnlyBlank;
    public final String errCharsSeveral;
    public final String errIsNotDir;
    public final String errNotAnId;
    public final String errNoSuchCar;
    public final String errInvalidDir;
    public final String errWrongOrientation;
    public final String errIllegalMove;

    /**
     * Full constructor.
     * 
     * @param   langInit    the language configuration file
     * @throws              RushHourException if there is a problem opening or
     *                      parsing the language file
     */
    public Language(File langInit) throws RushHourException {
        JSONObject lang = load(langInit);

        // Display messages:
        this.listLangFiles = (String) lang.get("listLangFiles");
        this.listGameInitFiles = (String) lang.get("listGameInitFiles");
        this.listDirChoices = (String) lang.get("listDirChoices");
        this.endOfGame = (String) lang.get("endOfGame");

        // Query messages:
        this.queryLang = (String) lang.get("queryLang");
        this.reQueryLang = (String) lang.get("reQueryLang");
        this.queryGameInit = (String) lang.get("queryGameInit");
        this.reQueryGameInit = (String) lang.get("reQueryGameInit");
        this.queryCarId = (String) lang.get("queryCarId");
        this.queryDir = (String) lang.get("queryDir");
        this.queryDifferentMove = (String) lang.get("queryDifferentMove");

        // Error messages:
        this.errCharsOnlyBlank = (String) lang.get("errCharsOnlyBlank");
        this.errCharsSeveral = (String) lang.get("errCharsSeveral");
        this.errIsNotDir = (String) lang.get("errIsNotDir");
        this.errNoSuchCar = (String) lang.get("errNoSuchCar");
        this.errNotAnId = (String) lang.get("errNotAnId");
        this.errInvalidDir = (String) lang.get("errInvalidDir");
        this.errWrongOrientation = (String) lang.get("errWrongOrientation");
        this.errIllegalMove = (String) lang.get("errIllegalMove");
    }

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
