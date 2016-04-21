/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g42116.rushhour.view;

import g42116.rushhour.model.Car;
import static g42116.rushhour.model.Orientation.HORIZONTAL;
import static g42116.rushhour.model.Orientation.VERTICAL;
import g42116.rushhour.model.Position;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author john
 */
public class tempTest {
    public static void main(String[] args) throws FileNotFoundException {
//        String s = File.separator;
//        File langPath = new File("blablabla");
//        Path path = FileSystems.getDefault().getPath("logs", "TextsEnglish");

//FileReader is = new FileReader("/home/NetBeansProjects/RushHour-Ruiz/src/g42116/rushhour/view/TextsEnglish.json");
// FileReader is = ;
JsonReader gameReader = Json.createReader(new FileReader("src/g42116/rushhour/view/Game1.json"));
JsonObject gameInit = gameReader.readObject();
gameReader.close();
//System.out.println(gameInit.getJsonObject("test"));
//Position test = (Position) gameInit.getJsonObject("test");
//System.out.println("test = " + test);

JsonObject innerObject = gameInit.getJsonObject("test");
Position pos = new Position(
        innerObject.getInt("row"), 
        innerObject.getInt("column"));
System.out.println("pos = " + pos);

        
/*      Position exit = new Position(2, 5);
        Car redCar = new Car('R', 2, HORIZONTAL, new Position(2,0));
        List<Car> otherCars = Arrays.asList(
            new Car('1', 3, HORIZONTAL, new Position(1,2)),
            new Car('2', 2, VERTICAL, new Position(2,3)),
            new Car('3', 4, HORIZONTAL, new Position(5,0))
        );
*/

    }
}
