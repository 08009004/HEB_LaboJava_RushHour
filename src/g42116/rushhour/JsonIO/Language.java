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
     */
    public Language(File langInit) {

        // Display messages:
        this.listLangFiles = (String) "Languages available: ";
        this.listGameInitFiles = (String) "Games available: ";
        this.listDirChoices = (String) "u, l, d, or r ";
        this.endOfGame = (String) "endOfGame";

        // Query messages:
        this.queryLang = (String) "Which language do you prefer: ";
        this.reQueryLang = (String) "reQueryLang ";
        this.queryGameInit = (String) "Select the game that you want to launch: ";
        this.reQueryGameInit = (String) "reQueryGameInit ";
        this.queryCarId = (String) "queryCarId ";
        this.queryDir = (String) "queryDir ";
        this.queryDifferentMove = (String) "queryDifferentMove ";

        // Error messages:
        this.errCharsOnlyBlank = (String) "errOnlyBlankChars ";
        this.errCharsSeveral = (String) "errSeveralChars ";
        this.errIsNotDir = (String) "is Not Dir ";
        this.errNoSuchCar = (String) "errNoSuchCar ";
        this.errNotAnId = (String) "errNotAnId ";
        this.errInvalidDir = (String) "errInvalidDir ";
        this.errWrongOrientation = (String) "errWrongOrientation ";
        this.errIllegalMove = (String) "errIllegalMove ";
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
