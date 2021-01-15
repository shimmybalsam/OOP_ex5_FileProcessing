package filesprocessing;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * This class represents a sorting by the abs order extending from the Order class.
 * Also used as the default sort.
 */
public class AbsSort extends Order {

    /**
     * Sorts a filtered list of files by the abs order.
     * @param list a filtered list of files to be sorted.
     * @param reverse a boolean statement if needs to be sorted normally or reversed.
     */
    public void sort(LinkedList<File> list, boolean reverse) {
        if (!reverse){
        list.sort(new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
            }
        });
        }
        else{
            list.sort(new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    return -f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
                }
            });
        }
    }
}