package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * An abstract class representing a general file Filter.
 */
public abstract class Filter {
    protected static final int KB_TO_BYTES = 1024;
    /**
     * an abstract filter function.
     * @return a LinkedList of filtered of files.
     */
    public abstract LinkedList<File> filterFiles();
}
