package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters all files who are not a directory.
 * Also used as the default filter.
 */
public class FilterAll extends Filter {
    File directory;

    /**
     * Constructs a new FilterAll filter object.
     * @param directory the folder from which the files need to be filtered.
     */
    public FilterAll(File directory){
        this.directory = directory;
    }

    /**
     * @return a linkedList made out of the filtered files.
     */
    public LinkedList<File> filterFiles(){
        LinkedList<File> l = new LinkedList<File>();
        File[] files = this.directory.listFiles();
        for (int i = 0; i<files.length; i++){
            if (!files[i].isDirectory()){
                l.add(files[i]);
            }
        }
        return l;
    }
}
