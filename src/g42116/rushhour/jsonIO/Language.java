package g42116.rushhour.jsonIO;

import g42116.rushhour.model.RushHourException;
import org.json.simple.JSONObject;

/**
 * This class loads all textual output for the game from a language Json file
 * chosen by the player.
 * 
 * @author g42116
 */
public class Language {

    // relative path to folder containing language json files (from 
    private final String folderPath = "resources/languages/";
    
    // Display messages:
    private final String listLangFiles;
    private final String listGameInitFiles;
    private final String listDirChoices;
    private final String endOfGame;

    // Query messages:
    private final String queryLang;
    private final String reQueryLang;
    private final String queryGameInit;
    private final String reQueryGameInit;
    private final String queryCarId;
    private final String queryDir;
    private final String queryDifferentMove;

    // Error messages:
    private final String errCharsOnlyBlank;
    private final String errCharsSeveral;
    private final String errIsNotDir;
    private final String errNotAnId;
    private final String errNoSuchCar;
    private final String errInvalidDir;
    private final String errWrongOrientation;
    private final String errIllegalMove;

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

    public String getFolderPath() {
        return folderPath;
    }

    public String getListLangFiles() {
        return listLangFiles;
    }

    public String getListGameInitFiles() {
        return listGameInitFiles;
    }

    public String getListDirChoices() {
        return listDirChoices;
    }

    public String getEndOfGame() {
        return endOfGame;
    }

    public String getQueryLang() {
        return queryLang;
    }

    public String getReQueryLang() {
        return reQueryLang;
    }

    public String getQueryGameInit() {
        return queryGameInit;
    }

    public String getReQueryGameInit() {
        return reQueryGameInit;
    }

    public String getQueryCarId() {
        return queryCarId;
    }

    public String getQueryDir() {
        return queryDir;
    }

    public String getQueryDifferentMove() {
        return queryDifferentMove;
    }

    public String getErrCharsOnlyBlank() {
        return errCharsOnlyBlank;
    }

    public String getErrCharsSeveral() {
        return errCharsSeveral;
    }

    public String getErrIsNotDir() {
        return errIsNotDir;
    }

    public String getErrNotAnId() {
        return errNotAnId;
    }

    public String getErrNoSuchCar() {
        return errNoSuchCar;
    }

    public String getErrInvalidDir() {
        return errInvalidDir;
    }

    public String getErrWrongOrientation() {
        return errWrongOrientation;
    }

    public String getErrIllegalMove() {
        return errIllegalMove;
    }

    
}
