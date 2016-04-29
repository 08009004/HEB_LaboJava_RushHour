package g42116.rushhour.view;

import g42116.rushhour.jsonIO.Language;
import g42116.rushhour.model.RushHourException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
        /*
        String defaultLangPath = "resources/languages/English.json";
        Language lang = new Language(defaultLangPath);
        UserInput i = new UserInput(null);
        i.CreateTempFile("/g42116/rushhour/jsonIO/resources/languages");
        */      
        int count = 0;
        File temp = new File("temp.txt");
        System.out.println("temp = " + temp);
        Scanner inputFile = new Scanner(temp);
        int i = 0;
        while(inputFile.hasNextLine())
        {
            count++;
            inputFile.nextLine();
        }
        inputFile.close();
        
        String [] fileLines = new String[count];
        
        inputFile = new Scanner(temp);
        while(inputFile.hasNext())
        {
            fileLines[i] = inputFile.nextLine();
            System.out.println(fileLines[i]);
            i++;
        }
        inputFile.close();
        
        
        for (String elem : fileLines) {
            System.out.println("elem = " + elem);
        }
        
        
        
    }
    
}
