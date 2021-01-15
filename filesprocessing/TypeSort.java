package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * This class represents a sorting by the type order extending from the Order class.
 */
public class TypeSort extends Order {
    private String[] s1, s2;

    /**
     * Sorts a filtered list of files by their type.
     * @param list the list of files to be sorted.
     * @param reverse a boolean statement if needs to be sorted normally or reversed.
     */
    public void sort(LinkedList<File> list, boolean reverse) {
        list.sort((f1, f2) -> {
            s1 = f1.getAbsolutePath().split(Pattern.quote("."));
            s2 = f2.getAbsolutePath().split(Pattern.quote("."));
            int compared = s1[s1.length - 1].compareTo(s2[s2.length - 1]);
            if (!reverse) {
                if (compared != 0) {
                    return compared;
                } else {
                    return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
                }
            }else {
                if (compared != 0) {
                    return -compared;
                } else {
                    return -f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
                }
            }
        });
    }
}
