package filesprocessing;

/**
 * This class represents an exception which is thrown when ORDER line wasn't given correctly or at all.
 */
public class BadSubOrder extends Type2Exception {
    /**
     * constructs a new BadSubOrder object.
     */
    public BadSubOrder(){
        super("Bad ORDER given");
    }
}
