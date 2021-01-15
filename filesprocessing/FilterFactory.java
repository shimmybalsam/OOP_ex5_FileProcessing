package filesprocessing;

import java.io.File;
import java.util.LinkedList;

/**
 * A class representing a factory of filters. Taking a given command and associating the correct filter.
 */
public class FilterFactory {
    /**
     * Taking a given string and associating (and calling upon) the filter acquainted with said string.
     * @param directory the folder from which the files need to be filtered.
     * @param s the String telling which filter, with which arguments must be called upon.
     * @return a LinkedList of the filtered files, based on the correct filter as such.
     * @throws BadParameters in case bad arguments/non existent filter type was given.
     */
    public static LinkedList<File> createFilter(File directory, String s) throws BadParameters {
        if (s.equals("all")) {
            return new FilterAll(directory).filterFiles();
        } else {
            String[] splitted = s.split("#");
            if (splitted[0].equals("greater_than")) {
                if (Double.parseDouble(splitted[1]) < 0) {
                    throw new BadParameters();
                } else if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    return new GreaterThan(directory, Double.parseDouble(splitted[1]), false).filterFiles();
                } else if (splitted.length == 2) {
                    return new GreaterThan(directory, Double.parseDouble(splitted[1]), true).filterFiles();
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("smaller_than")) {
                if (Double.parseDouble(splitted[1]) < 0) {
                    throw new BadParameters();
                } else if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    return new SmallerThan(directory, Double.parseDouble(splitted[1]), false).filterFiles();
                } else if (splitted.length == 2) {
                    return new SmallerThan(directory, Double.parseDouble(splitted[1]), true).filterFiles();
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("between")) {
                double n1 = Double.parseDouble(splitted[1]);
                double n2 = Double.parseDouble(splitted[2]);
                if (n2 < n1 || n1 < 0 || n2 < 0) {
                    throw new BadParameters();
                } else if (splitted.length == 4 && splitted[3].equals("NOT")) {
                    return new Between(directory, n1, n2, false).filterFiles();
                } else if (splitted.length == 3) {
                    return new Between(directory, n1, n2, true).filterFiles();
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("file")) {
                if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    return new FileNameFilter(directory, splitted[1], false).filterFiles();
                } else if (splitted.length == 2) {
                    return new FileNameFilter(directory, splitted[1], true).filterFiles();
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("contains")) {
                if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    return new Contains(directory, splitted[1], false).filterFiles();
                } else if (splitted.length == 2) {
                    return new Contains(directory, splitted[1], true).filterFiles();
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("prefix")) {
                if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    return new Prefix(directory, splitted[1], false).filterFiles();
                } else if (splitted.length == 2) {
                    return new Prefix(directory, splitted[1], true).filterFiles();
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("suffix")) {
                if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    return new Suffix(directory, splitted[1], false).filterFiles();
                } else if (splitted.length == 2) {
                    return new Suffix(directory, splitted[1], true).filterFiles();
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("writable")) {
                if (splitted.length == 2 && (splitted[1].equals("YES") || splitted[1].equals("NO"))) {
                    return new Writable(directory, splitted[1]).filterFiles();
                } else if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    if (splitted[1].equals("YES")) {
                        return new Writable(directory, "NO").filterFiles();
                    } else if (splitted[1].equals("NO")) {
                        return new Writable(directory, "YES").filterFiles();
                    } else {
                        throw new BadParameters();
                    }
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("executable")) {
                if (splitted.length == 2 && (splitted[1].equals("YES") || splitted[1].equals("NO"))) {
                    return new Executable(directory, splitted[1]).filterFiles();
                } else if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    if (splitted[1].equals("YES")) {
                        return new Executable(directory, "NO").filterFiles();
                    } else if (splitted[1].equals("NO")) {
                        return new Executable(directory, "YES").filterFiles();
                    } else {
                        throw new BadParameters();
                    }
                } else {
                    throw new BadParameters();
                }
            } else if (splitted[0].equals("hidden")) {
                if (splitted.length == 2 && (splitted[1].equals("YES") || splitted[1].equals("NO"))) {
                    return new Hidden(directory, splitted[1]).filterFiles();
                } else if (splitted.length == 3 && splitted[2].equals("NOT")) {
                    if (splitted[1].equals("YES")) {
                        return new Hidden(directory, "NO").filterFiles();
                    } else if (splitted[1].equals("NO")) {
                        return new Hidden(directory, "YES").filterFiles();
                    } else {
                        throw new BadParameters();
                    }
                } else {
                    throw new BadParameters();
                }
            } else {
                throw new BadParameters();
            }
        }
    }
}
