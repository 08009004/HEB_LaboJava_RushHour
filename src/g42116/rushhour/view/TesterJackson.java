package g42116.rushhour.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import g42116.rushhour.RushHour;
import g42116.rushhour.model.Car;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author john
 */
public class TesterJackson {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        File file = new File("src/g42116/rushhour/games/GameTest.json");
        Car p = mapper.readValue(file, Car.class);
        System.out.println(p);
        
        
        System.out.println("\n -------2------- \n \n");
        
        File file2 = new File("src/g42116/rushhour/games/GameTest2.json");
        /* NOTE:
        http://www.leveluplunch.com/java/examples/convert-json-array-to-arraylist-of-objects-jackson/
        https://github.com/FasterXML/jackson-databind
        */
        List<Car> otherCars = mapper.readValue(file2, 
                mapper.getTypeFactory().constructCollectionType(List.class, Car.class));

        for (Car car : otherCars) {
            System.out.println(car);
        }
        
        System.out.println("\n -------3------- \n \n");
        File file3 = new File("src/g42116/rushhour/games/GameTest3.json");
        JsonNode gameInit = mapper.readValue(file3, JsonNode.class);
        String otherCarsAsString = gameInit.path("otherCars").toString();

        List<Car> otherCars2 = mapper.readValue(otherCarsAsString, 
                mapper.getTypeFactory().constructCollectionType(List.class, Car.class));

        for (Car car : otherCars2) {
            System.out.println(car);
        }

    }
}
