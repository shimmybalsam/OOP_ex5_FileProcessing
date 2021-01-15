package filesprocessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the main processing class which is in charge of the entire filtering and ordering.
 * In case of Exception this class (in the main) will be the final catcher and caretaker.
 */
public class DirectoryProcessor {
    private static final String SUBSECTION1 = "FILTER";
    private static final String SUBSECTION2 = "ORDER";
    /**
     * chekcs if the given commands file given has illegal FILTER or ORDER lines.
     * @param stringList a List (java) where each cell in the list holds a line from the command file.
     * @throws Type2Exception in case of a bad FILTER or ORDER line.
     */
    private void checkForErrors(List<String> stringList)throws Type2Exception {
        for (int i = 0; i < stringList.size(); i++){
            if (!stringList.get(i).equals(SUBSECTION1)){
                throw new BadSubFilter();
            }else if (stringList.size() < i+1){
                throw new BadSubFilter();
            }else if (stringList.size() <= i+2){
                if (!stringList.get(i+1).equals(SUBSECTION2)) {
                    throw new BadSubOrder();
                }
            }else if (!stringList.get(i+1).equals(SUBSECTION2) && !stringList.get(i+2).equals(SUBSECTION2)){
                throw new BadSubOrder();
            }else if (stringList.size() <= i+3){
                if (stringList.get(i+1).equals(SUBSECTION2) && stringList.get(i+2).equals(SUBSECTION1)) {
                    throw new BadSubFilter();
                }else{
                    i = i + 2;
                }
            }else{
                if (stringList.get(i+3).equals(SUBSECTION1)) {
                    i = i + 2;
                }else if (stringList.size() > i+4){
                    if(stringList.get(i+4).equals(SUBSECTION1)){
                        i = i + 3;
                    }else{
                        throw new BadSubFilter();
                    }
                }else{
                    i = i + 4;
                }
            }
        }
    }

    /**
     * processes the given types of filters and order given in the command file,
     * filters and orders the files accordingly.
     * @param directory the folder from which the files need to be filtered.
     * @param stringList a List (java) where each cell in the list holds a line from the command file.
     * @throws Type1Exception in case bad parameters were given in the types of filters or orders given in
     * the command file.
     */
    private void process(File directory, List<String> stringList) throws Type1Exception {
        LinkedList<File> l;
        for (int i = 0; i < stringList.size(); i++) {
            ArrayList<String> forPrinting = new ArrayList<>();
            if (i + 1 < stringList.size()) {
                try {
                    l = FilterFactory.createFilter(directory, stringList.get(i + 1));
                } catch (BadParameters b) {
                    forPrinting.add(b.getMessage() + Integer.toString(i + 2));
                    l = new FilterAll(directory).filterFiles();
                }
                if (stringList.get(i + 1).equals(SUBSECTION2)) {
                    if (i + 2 < stringList.size()) {
                        try {
                            OrderFactory.createOrder(stringList.get(i + 2), l);
                            printList(l, forPrinting);
                            i = i + 2;
                        } catch (BadParameters b) {
                            forPrinting.add(b.getMessage() + Integer.toString(i + 3));
                            new AbsSort().sort(l, false);
                            printList(l, forPrinting);
                            i = i +2;
                        }
                    }
                } else if (i + 2 < stringList.size() && stringList.get(i + 2).equals(SUBSECTION2)) {
                    if (stringList.size() > i + 3) {
                        if (!stringList.get(i + 3).equals(SUBSECTION1)) {
                            try {
                                OrderFactory.createOrder(stringList.get(i + 3), l);
                                printList(l, forPrinting);
                                i = i + 3;
                            } catch (BadParameters b) {
                                forPrinting.add(b.getMessage() + Integer.toString(i + 4));
                                new AbsSort().sort(l, false);
                                printList(l, forPrinting);
                                i = i + 3;
                            }
                        } else {
                            new AbsSort().sort(l, false);
                            printList(l, forPrinting);
                            i = i + 2;
                        }
                    } else {
                        new AbsSort().sort(l, false);
                        printList(l, forPrinting);
                        i = i + 3;
                    }
                }else {
                    i = i + 2;
                }
            }
        }
    }

    /**
     * prints the warinings at the filtered files (sorted by specific order),
     * for each subsection within the command file.
     * @param l a linkedList containing the filtered and sorted files.
     * @param printTo an ArrayList containg the warnings per subsection, to which the linkedList data will be added
     *                accordingly and then printed.
     */
    private void printList(LinkedList<File> l, ArrayList<String> printTo){
        Iterator<File> myIter1 = l.iterator();
        while (myIter1.hasNext()){
            printTo.add(myIter1.next().getName());
        }
        for (int i = 0; i < printTo.size(); i++){
            System.out.println(printTo.get(i));
        }
    }

    /**
     * the main section, calls uppon the functions above in order to process and filter and order accordingly,
     * also in charge of the final catching at care-taking of exceptions.
     * @param args the given directory and command file.
     * @throws IOException in case legal directory or command file weren't given.
     * @throws Type1Exception in case bad parameters were given to filter/order with.
     * @throws Type2Exception in case bad (or non-existent) FILTER or ORDER lines within the command file.
     */
    public static void main(String[] args) throws IOException, Type1Exception, Type2Exception {
        DirectoryProcessor dp = new DirectoryProcessor();
        String directoryPath = args[0];
        String filePath = args[1];
        File command = new File(filePath);
        File directory = new File(directoryPath);
        if (!directory.isDirectory()){
            throw new NoSuchFileException("ERROR: no directory given");
        }
        try {
            List<String> fullSection = Files.readAllLines(command.toPath());
            dp.checkForErrors(fullSection);
            dp.process(directory, fullSection);
        }catch (IOException e){
            throw new NoSuchFileException("ERROR: no such file");
        }catch (Type2Exception t) {
        }
    }
}
