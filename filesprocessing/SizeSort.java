package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a sorting by the size order extending from the Order class.
 */
public class SizeSort extends Order {
    private static final int FIRST = 1;
    private static final int SECOND = -1;

    /**
     * Sorts a filtered list of files by their size.
     * @param list the list of files to be sorted.
     * @param reverse a boolean statement if needs to be sorted normally or reversed.
     */
    public void sort(LinkedList<File> list, boolean reverse)
    {
        list.sort((File f1, File f2) -> {
            long compared =  f1.length() - f2.length();
            if (!reverse) {
                if (compared == 0) {
                    return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
                } else if (compared > 0) {
                    return FIRST;
                } else {
                    return SECOND;
                }
            }else{
                if (compared == 0) {
                    return -f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
                } else if (compared > 0) {
                    return SECOND;
                } else {
                    return FIRST;
                }
            }
        });
    }
}
