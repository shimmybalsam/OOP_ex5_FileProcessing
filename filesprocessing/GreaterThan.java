package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters all files who's size are greater than a given amount.
 */
public class GreaterThan extends Filter {
    File directory;
    double minSize;
    boolean isOrNot;

    /**
     * Constructs a new GreaterThan filter object.
     * @param directory the folder from which the files need to be filtered.
     * @param minSize file size must be strictly greater than this number.
     * @param isOrNot a boolean statement if needs to be sorted normally or by #NOT.
     */
    public GreaterThan(File directory, double minSize, boolean isOrNot){
        this.directory = directory;
        this.minSize = minSize;
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
                if (!files[i].isDirectory() && (double)files[i].length()/KB_TO_BYTES > minSize) {
                    l.add(files[i]);
                }
            }else if(!files[i].isDirectory() && (double)files[i].length()/KB_TO_BYTES <= minSize){
                l.add(files[i]);
            }
        }
        return l;
    }
}
