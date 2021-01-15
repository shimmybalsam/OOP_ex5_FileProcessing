package filesprocessing;

/**
 * This class represents an exception which is thrown when FILTER line wasn't given correctly or at all.
 */
public class BadSubFilter extends Type2Exception {

    /**
     * constructs a new BadSubFiler object.
     */
    public BadSubFilter(){
        super("Bad FILTER given");
    }

}
