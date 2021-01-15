package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters files containing a specific string within the file name.
 */
public class Contains extends Filter {
    File directory;
    String value;
    boolean isOrNot;

    /**
     * Constructs a new Contains filter object.
     * @param directory the folder from which the files need to be filtered.
     * @param value the value to be checked if is contained within the file name.
     * @param isOrNot a boolean statement if needs to be sorted normally or by #NOT.
     */
    public Contains(File directory, String value, boolean isOrNot){
        this.directory = directory;
        this.value = value;
        this.isOrNot = isOrNot;
    }

    /**
     * @return a linkedList made out of the filtered files.
     */
    public LinkedList<File> filterFiles(){
        LinkedList<File> l = new LinkedList<File>();
        File[] files = directory.listFiles();
        for (int i = 0; i<files.length; i++){
            if (isOrNot) {
                if (files[i].isDirectory() && files[i].getName().contains(value)) {
                    l.add(files[i]);
                }
            } else if (!files[i].isDirectory() && files[i].getName().contains(value)) {
                l.add(files[i]);
            }
        }
        return l;
    }
}
