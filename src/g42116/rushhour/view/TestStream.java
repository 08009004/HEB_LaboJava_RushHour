package g42116.rushhour.view;

import g42116.rushhour.jsonIO.Language;
import g42116.rushhour.model.RushHourException;
import java.io.IOException;

/**
 * temp class
 * @author john
 */
public class TestStream {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, RushHourException {
//        CreateTempFile t = new CreateTempFile("/g42116/rushhour/jsonIO/resources/languages");
        
        String defaultLangPath = "resources/languages/English.json";
        Language lang = new Language(defaultLangPath);
        UserInput i = new UserInput(null);
        i.CreateTempFile("/g42116/rushhour/jsonIO/resources/languages");
    }
    
}
