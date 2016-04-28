package g42116.rushhour.JsonIO;

import g42116.rushhour.model.RushHourException;
import org.json.simple.JSONObject;

/**
 *
 * @author g42116
 */
public class Language {

    // relative path to folder containing language json files (from 
    public final String folderPath = "resources/languages/";
    
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
    public Language(String langInit) throws RushHourException {
        JSONObject lang = (new JsonLoader(langInit)).getJsonObj();

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

}
