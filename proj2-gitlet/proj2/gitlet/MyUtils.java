package gitlet;

import static gitlet.Utils.*;

public class MyUtils {

    /**
     *
     * Print a message and exit with status code 0
     * @param message String to print
     * @param args Arguments to referenced by the format specifiers in the format string
     */
    public static  void exit(String message, Object... args) {
        message(message, args);
        System.exit(0);
    }

}
