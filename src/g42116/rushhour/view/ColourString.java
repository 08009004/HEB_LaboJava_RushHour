package g42116.rushhour.view;

import static g42116.rushhour.view.Colour.*;

/**
 * Utility class using Select Graphic Rendition parameters ANSI escape codes to 
 * embedded colour information into strings.
 * 
 * @author john
 */
public class ColourString {
    
    /**
     * Returns terminal default colour settings.
     * 
     * @return  the ANSI escape code for default SGR parameters
     */
    private static String toDefault() {
        return "\033[0m";
    }
    
    /**
     * Returns the desired message with embedded colour information, following
     * the ANSI escape code SGR specification. (some terminals might render 
     * this information differently.)
     * 
     * More info: https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
     * 
     * @param   string      the string to colour
     * @param   background  the background colour
     * @param   foreground  the foreground colour
     * @return              the ANSI escape code for desired SGR parameters, 
     *                      followed by the parameter string and finally the 
     *                      ANSI escape code for default SGR parameters 
     * @throws              NullPointerException if either of the colour fields
     *                      is left at null
     */
    public static String to(String string, Colour background, Colour foreground) {
        if (background == null || foreground == null) {
            throw new NullPointerException("Background and foreground " 
                    + "parameters can't be null.");
        }
        return "\033[3" + foreground.ordinal() 
                + ";4" + background.ordinal() 
                + "m" + string + toDefault();
    }
    
    /**
     * Main method to test the class.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        String test = "hyia";
        test = to(test, GREEN, RED);
        System.out.println(test);
    }
}
