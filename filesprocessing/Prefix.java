package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters files who's name starts with a given value.
 */
public class Prefix extends Filter {
    File directory;
    String prefix;
    boolean isOrNot;

    /**
     * Constructs a new Prefix filter object.
     * @param directory the folder from which the files need to be filtered.
     * @param prefix the value to be checked if it is equal to the beginning of file name.
     * @param isOrNot a boolean statement if needs to be sorted normally or by #NOT.
     */
    public Prefix(File directory, String prefix, boolean isOrNot){
        this.directory = directory;
        this.prefix = prefix;
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
                if (!files[i].isDirectory() && files[i].getName().startsWith(prefix)) {
                    l.add(files[i]);
                }
            } else if (!files[i].isDirectory() && !files[i].getName().startsWith(prefix)) {
                    l.add(files[i]);
            }
        }
        return l;
    }
}
