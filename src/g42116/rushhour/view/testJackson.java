package g42116.rushhour.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import g42116.rushhour.model.Position;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.JsonParser;

/**
 *
 * @author g42116
 */
public class testJackson {
    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        
        FileReader rdr = new FileReader("src/g42116/rushhour/lang/TextsEnglish.json");
        
        Object p = gson.fromJson(rdr, Object.class);
        System.out.println("p = " + p);
        
        JsonParser parser = 
        
        /*        
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "{\"row\":2,\"column\":5";
        Position p = mapper.readValue(jsonInString, Position.class);
        System.out.println(p);
        */
//JSON from file to Object
//Position p = mapper.readValue(new File("src/g42116/rushhour/games/GameTest.json"), Position.class);

//JSON from String to Object
//Position p = mapper.readValue(mapper.g, User.class);
    }
}
