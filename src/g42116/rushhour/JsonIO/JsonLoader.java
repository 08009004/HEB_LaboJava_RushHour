package g42116.rushhour.JsonIO;

import g42116.rushhour.model.RushHourException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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

/*
    @srv
    Après de longues recherches et de multiples lectures en ligne, je suis
    arrivé à la conclusion que la meilleure manière d'ouvrir les fichiers json
    (idéalement inclus dans le jar) est d'appeler getResourceAsStream comme
    l'explique bien l'article suivant:
    http://www.javaworld.com/article/2077352/java-se/smartly-load-your-properties.html

    J'aurais donc préféré utiliser le code ci-dessous, que j'ai écrit dans un
    autre projet sans rencontrer de problème, pour le constructeur. Mais pour
    une raison qui m'échappe je n'arrive pas à le faire fonctionner dans mon
    projet RushHour.
    Pressé par le temps, j'ai dû écrire un autre code que je sais
    moins bon pour le constructeur.
*/
/*
    public static void main(String[] args) {
//      note:
//      getResourceAsStream(String): "If you don’t have a lead /, you have a
//      relative name and the name of the package will be prepended.
//      http://mindprod.com/jgloss/getresourceasstream.html

        String gameInitPath = "resources/games/Game1Test.json";

        InputStream in = GameInitialiser.class.getResourceAsStream(gameInitPath);
        System.out.println("in = " + in);
//     retourne bien le fichier dans un autre projet (in = java.io.BufferedInputStream@7d4991ad)
//     mais ici 'null'
    }
*/

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
