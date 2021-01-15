package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * A class representing a factory of orders. Taking a given command and associating the correct order.
 */
public class OrderFactory {
    /**
     * Taking a given string and associating (and calling upon) the order acquainted with said string.
     * @param s the String telling which order, with which arguments must be called upon.
     * @param l this filteres list which must be ordered by correct order.
     * @throws BadParameters in case but parameter/non existent order type was given.
     */
    public static void createOrder(String s, LinkedList<File> l) throws BadParameters {
        if (s.contains("#")){
            String[] splitted = s.split("#");
            if (splitted.length == 2 && splitted[1].equals("REVERSE")){
                if (splitted[0].equals("abs")){
                    new AbsSort().sort(l, true);
                }else if (splitted[0].equals("type")){
                    new TypeSort().sort(l, true);
                }else if (splitted[0].equals("size")){
                    new SizeSort().sort(l, true);
                }else{
                    throw new BadParameters();
                }
            }else{
                throw new BadParameters();
            }
        }else if (s.equals("abs")){
            new AbsSort().sort(l, false);
        }else if (s.equals("type")){
            new TypeSort().sort(l, false);
        }else if (s.equals("size")){
            new SizeSort().sort(l, false);
        }else{
            throw new BadParameters();
        }

    }
}
