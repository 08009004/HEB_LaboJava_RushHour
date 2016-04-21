package g42116.rushhour.view;

import java.io.File;
import java.io.FilenameFilter;

/**
 * This implementation of the interface FilenameFilter.class helps the 
 * programmer to select the files he wants from a folder.
 * 
 * @author g42116
 */
public class FileExtensionTrimmer implements FilenameFilter {
    
    // Attribute
    String fileExtension;

    /**
     * Full constructor.
     * 
     * @param fileExtension the extension of the files to select
     */
    public FileExtensionTrimmer(String fileExtension) {
        this.fileExtension = ("." + fileExtension).toLowerCase();
    }

    /**
     * Tests if a specified file should be included in a file list.
     * 
     * @param   directory   the directory in which the file was found
     * @param   fileName    the name of the file
     * @return              true if the name should be included in the file 
     *                      list, otherwise false
     */
    @Override
    public boolean accept(File directory, String fileName) {
        return fileName.toLowerCase().endsWith(this.fileExtension);
    }
}

