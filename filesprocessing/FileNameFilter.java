package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters files who's name are equal to a given value.
 */
public class FileNameFilter extends Filter {
    File directory;
    String fileName;
    boolean isOrNot;

    /**
     * Constructs a new FileNameFilter object.
     * @param directory the folder from which the files need to be filtered.
     * @param fileName the value to be checked if it is equal to the file name.
     * @param isOrNot a boolean statement if needs to be sorted normally or by #NOT.
     */
    public FileNameFilter(File directory, String fileName, boolean isOrNot){
        this.directory = directory;
        this.fileName = fileName;
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
                    if (files[i].getName().equals(fileName)) {
                        l.add(files[i]);
                    }
                }else if (!files[i].getName().equals(fileName)){
                    l.add(files[i]);
                }
            }
        }
        return l;
    }
}
