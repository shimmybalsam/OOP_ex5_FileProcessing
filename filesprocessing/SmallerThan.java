package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters all files who's size are smaller than a given amount.
 */
public class SmallerThan extends Filter {
    File directory;
    double maxSize;
    boolean isOrNot;

    /**
     * Constructs a new SmallerThan filter object.
     * @param directory the folder from which the files need to be filtered.
     * @param maxSize file size must be strictly smaller than this number.
     * @param isOrNot a boolean statement if needs to be sorted normally or by #NOT.
     */
    public SmallerThan(File directory, double maxSize, boolean isOrNot){
        this.directory = directory;
        this.maxSize = maxSize;
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
                if (!files[i].isDirectory() && (double)files[i].length()/KB_TO_BYTES < maxSize) {
                    l.add(files[i]);
                }
            }else if(!files[i].isDirectory() && (double)files[i].length()/KB_TO_BYTES >= maxSize){
                l.add(files[i]);
            }
        }
        return l;
    }
}
