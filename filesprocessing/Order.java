package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * An abstract class representing a general files ORDER.
 */
public abstract class Order {

    /**
     * an abstract sort by order function.
     * @param list the list of files to be sorted.
     * @param b a boolean statement implying if to sort by order or by reversed order.
     */
    public abstract void sort(LinkedList<File> list, boolean b);

}
