package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters files who's path ends with a given value.
 */
public class Suffix extends Filter {
    File directory;
    String suffix;
    boolean isOrNot;

    /**
     * Constructs a new Prefix filter object.
     * @param directory the folder from which the files need to be filtered.
     * @param suffix the value to be checked if it is equal to the end of the file path.
     * @param isOrNot a boolean statement if needs to be sorted normally or by #NOT.
     */
    public Suffix(File directory, String suffix, boolean isOrNot){
        this.directory = directory;
        this.suffix = suffix;
        this.isOrNot = isOrNot;
    }

    /**
     * @return a linkedList made out of the filtered files.
     */
    public LinkedList<File> filterFiles(){
        LinkedList<File> l = new LinkedList<File>();
        File[] files = directory.listFiles();
        for (int i = 0; i<files.length; i++){
            if (!files[i].isDirectory()) {
                String path = files[i].getAbsolutePath();
                if (isOrNot) {
                    if (path.substring(path.length()-suffix.length()).equals(suffix)) {
                        l.add(files[i]);
                    }
                } else if (!path.substring(path.length()-suffix.length()).equals(suffix)) {
                    l.add(files[i]);
                }
            }
        }
        return l;
    }
}
