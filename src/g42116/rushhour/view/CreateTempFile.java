package g42116.rushhour.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class, creates a .txt copy at project root of any given file. Also
 * works with files nested inside of a jar archive, hence  allowing access 
 * without needing to extract it.
 * 
 * @author g42116
 */
public class CreateTempFile {

    public CreateTempFile(String path) throws IOException {
        File f=new File("temp.txt");
        InputStream input = this.getClass().getResourceAsStream(path);
        System.out.println("IN CREATE: input = " + input);
        OutputStream output=new FileOutputStream(f);
        System.out.println("IN CREATE: output = " + output);
        byte buf[]=new byte[1024];
        int len;
        while((len=input.read(buf))>0) output.write(buf,0,len);
        output.close();
        input.close();
        System.out.println("\nFile is created.....");
    }
    
}
