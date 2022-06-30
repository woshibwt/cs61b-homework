package gitlet;

import java.io.File;
import java.io.Serializable;


import static gitlet.MyUtils.getObjectFile;
import static gitlet.MyUtils.saveObjectFile;
import static gitlet.Utils.*;
@SuppressWarnings("PrimitiveArrayArgumentToVaraargsMethod")
public class Blob implements Serializable {

    /** The source file from constructor */
    private final File source;

    /** The content of the source file */
    private final byte[] content;

    /** The SHA1 id generated from the source file content */
    private final String id;

    /** The file of this instance with the path generated from SHA1 id */
    private final File file;

    public Blob(File sourceFile) {
        source = sourceFile;
        String filePath = sourceFile.getPath();
        content = readContents(sourceFile);
        id = sha1(filePath, content);
        file = getObjectFile(id);
    }


    public void save() {
        saveObjectFile(file, this);
    }

    /**
     * Get the Blob file.
     *
     * @return SHA1 id.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the blob file.
     * @return File instance.
     */
    public File getFile() {
        return file;
    }
}
