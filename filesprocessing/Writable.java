package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents a filter which filters which are defined as writable.
 */
public class Writable extends Filter {
    File directory;
    String yesOrNo;

    /**
     * Constructs a new Writable filter object.
     * @param directory the folder from which the files need to be filtered.
     * @param yesOrNo a boolean statement if needs to be sorted normally or by #NOT.
     */
    public Writable(File directory, String yesOrNo){
        this.directory = directory;
        this.yesOrNo = yesOrNo;
    }

    /**
     * @return a linkedList made out of the filtered files.
     */
    public LinkedList<File> filterFiles(){
        LinkedList<File> l = new LinkedList<File>();
        File[] files = directory.listFiles();
        for (int i = 0; i<files.length; i++){
            if (!files[i].isDirectory()){
                if (yesOrNo.equals("YES")) {
                    if (files[i].canWrite()) {
                        l.add(files[i]);
                    }
                }else if (yesOrNo.equals("NO")){
                    if (!files[i].canWrite()){
                        l.add(files[i]);
                    }
                }
            }
        }
        return l;
    }
}
