package filesprocessing;

/**
 * This class represents an exception which is thrown when bad arguments are given, printing a warning.
 */
public class Type1Exception extends Exception {
    /**
     * prints warning.
     */
    public void printWarning(){
        System.err.println("type1 warning");
    }
}
