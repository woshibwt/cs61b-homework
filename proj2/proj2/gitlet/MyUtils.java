package gitlet;
import java.io.File;
import java.io.Serializable;
import java.util.function.Supplier;

import static gitlet.Utils.*;

public class MyUtils {

    public static <T> Lazy<T> lazy(Supplier<T> delegate) {
        return new Lazy<>(delegate);
    }

    /**
     * Print a message and exit with status code 0
     *
     * @param message String to print
     * @param args Arguments referenced by the format specifiers in the format string
     */
    public static void exit(String message, Object... args) {
        message(message, args);
        System.exit(0);
    }

    /**
     * Create a directory from the File Object
     *
     * @param dir Directory File instance
     */
    public static  void mkdir(File dir) {
        if (!dir.mkdir()) {
            throw new IllegalArgumentException(String.format("mkdir: %s: Failed to create.", dir.getPath()));
        }

    }

    /**
     * Get a file instance with the path generated from SHA1 id in the object folder.
     *
     * @param id SHA1 id
     * @return File instance
     */
    public static File getObjectFile(String id) {
        String dirName = getObjectDirName(id);
        String fileName = getObjectFileName(id);
        return join(Repository.OBJECT_DIR, dirName, fileName);

    }

    /**
     * Get directory name from SHA1 id in the objects folder.
     *
     * @param id SHA1 id
     * @return Name of the directory
     */
    public static String getObjectDirName(String id) {
        return id.substring(0, 2);
    }

    /**
     * Get file name from SHA1 id .
     *
     * @param id SHA1 id
     * @return Name of the file
     */
    public static String getObjectFileName(String id) {
        return id.substring(2);
    }

    /**
     * Save the serializable object to the file path.
     * Create a parent directory if not exists.
     * @param file File instance
     * @param obj Serializable object
     */
    public static void saveObjectFile(File file, Serializable obj) {
        File dir = file.getParentFile();
        if (!dir.exists()) {
            mkdir(dir);
        }
        writeObject(file, obj);
    }
}