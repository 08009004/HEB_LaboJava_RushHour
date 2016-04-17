/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g42116.rushhour.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
FileReader is = new FileReader("src/g42116/rushhour/view/TextsEnglish.json");
JsonReader rdr = Json.createReader(is);
JsonObject obj = rdr.readObject();
rdr.close();
obj.getString("query1");
System.out.println("obj.getString(query1) = " + obj.getString("query1"));
        

    }
}
