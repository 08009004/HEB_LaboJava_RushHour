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
     * @param   string  the string to colour
     * @param   back    the background colour (default colour if null)
     * @param   fore    the foreground colour (default colour if null)
     * @return          the ANSI escape code for desired SGR parameters, 
     *                  followed by the parameter string and finally the 
     *                  ANSI escape code for default SGR parameters 
     */
    public static String to(String string, Colour back,Colour fore) {
        /* colouredString initialised with toDefault() to guaranty that 
         * foreground and/or background are set to default if parameter is null.
         */
        String colourCoded = toDefault();

        if (fore == null) {
            if (back == null) {
                colourCoded += string;
            } else {
                colourCoded += "\033[4" + back.ordinal() + "m" + string;
            }
        } else {
            if (back == null) {
                colourCoded += "\033[3" + fore.ordinal() + "m" + string;
            } else {
                colourCoded += "\033[3" + fore.ordinal() 
                                + ";4" + back.ordinal() 
                                + "m" + string;
            }
        }

        return colourCoded + toDefault();
    }
    
    /**
     * Main method to test the class.
     * 
     * @param args unused
     */
    public static void main(String[] args) {
        String test = "test message";
        System.out.println(test);
        String test1 = to(test, CYAN, YELLOW);
        System.out.println(test1);
        String test2 = to(test, null, BLUE);
        System.out.println(test2);
        String test3 = to(test, RED, null);
        System.out.println(test3);
    }
}
