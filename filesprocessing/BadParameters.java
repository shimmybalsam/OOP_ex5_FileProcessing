package filesprocessing;

/**
 * This class represents an exception which is thrown when bad parameters are given per filter or order.
 */

public class BadParameters extends Type1Exception {
    String msg;

    /**
     * constructs a new BadParameters object.
     */
    public BadParameters(){
        this.msg = "Warning in line ";
    }

    /**
     * @return the bad parameter's message.
     */
    public String getMessage(){
        return this.msg;
    }
}
