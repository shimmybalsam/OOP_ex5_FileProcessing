package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters only files who's size are between two given sizes.
 */
public class Between extends Filter {
    File directory;
    double minSize, maxSize;
    boolean isOrNot;
    /**
     * Construcs a new Between filter object.
     * @param directory the folder from which the files need to be filtered.
     * @param minSize the minimum size (inclusive) of a filtered file.
     * @param maxSize the maximum size (inclusive) of a filtered file.
     * @param isOrNot a boolean statement if needs to be sorted normally or by #NOT.
     */
    public Between(File directory, double minSize, double maxSize, boolean isOrNot){
        this.directory = directory;
        this.minSize = minSize;
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
            if (!files[i].isDirectory()){
                if (isOrNot) {
                    if ((double)files[i].length()/KB_TO_BYTES >= minSize &&
                            (double)files[i].length()/KB_TO_BYTES <= maxSize) {
                        l.add(files[i]);
                    }
                }else if ((double)files[i].length()/KB_TO_BYTES < minSize ||
                        (double)files[i].length()/KB_TO_BYTES > maxSize){
                    l.add(files[i]);
                }
            }
        }
        return l;
    }
}
