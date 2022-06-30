package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// import static gitlet.MyUtils.rm;
import static gitlet.Utils.readObject;
import static gitlet.Utils.writeObject;

/**
 * The staging area representation
 */
public class StagingArea implements Serializable {

    /** The added files Map with file path as key and SHA1 id as value */
    private final Map<String, String> added = new HashMap<>();

    /** The removed files Set with file path as key */
    private final Set<String> removed = new HashSet<>();

    /** The tracked files Map with file path as key and SHA1 id as value */
    private transient Map<String, String> tracked;

    /**
     * Get a StagingArea instance from the file INDEX
     *
     * @return StagingArea instance
     */
    public static StagingArea fromFile() {
        return readObject(Repository.INDEX, StagingArea.class);
    }

    /**
     * Set tracked files
     *
     * @param filesMap Map with file path as key and SHA1 id as value
     */
    public void setTracked(Map<String, String> filesMap) {
        tracked = filesMap;
    }

    public boolean add(File file) {
        String filePath = file.getPath();

        Blob blob = new Blob(file);
        String blobId = blob.getId();
    }



}
