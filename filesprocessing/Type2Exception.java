package filesprocessing;

/**
 * This class represents an exception which is thrown when bad subsection was given.
 */
public class Type2Exception extends Exception {

    /**
     * Constructs a new exception with acquiescent ERROR message.
     * @param message ERROR message.
     */
    public Type2Exception(String message){ System.err.print("ERROR: " + message + "\n");}
}
