package gitlet;

// TODO: any imports you need here


import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static gitlet.MyUtils.getObjectFile;
import static gitlet.MyUtils.saveObjectFile;
import static gitlet.Utils.readObject;
import static gitlet.Utils.sha1;


/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author biwentao
 */
public class Commit implements Serializable {
    /**
     * DONE: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The created date */
    private final Date date;


    /** The message of this Commit. */
    private String message;

    /** The parent commit SHA1 id*/
    private final List<String> parents;

    /** The tracked files Map with file path as key and SHA1 id as value */
    private final Map<String, String> tracked;

    /** The SHA1 id.*/
    private final String id;

    /** The file of this instance with the path generated from SHA1 id */
    private final File file;

    public Commit(String message, List<String> parents, Map<String, String> trackedFileMap) {
        date = new Date();
        this.message = message;
        this.parents = parents;
        this.tracked = trackedFileMap;
        id = generateId();
        file = getObjectFile(id);
    }

    /**
     * Initial commit
     */
    public Commit() {
        date = new Date(0);
        message = "initial commit";
        parents = new ArrayList<>();
        tracked = new HashMap<>();
        id = generateId();
        file = getObjectFile(id);
    }

    /* TODO: fill in the rest of this class. */

    /**
     * Get a Commit instance from the file with the SHA1 id.
     *
     * @param id SHA1 id
     * @return Commit instance
     */
    public static Commit fromFile(String id) {
        return readObject(getObjectFile(id), Commit.class);
    }

    private String generateId(){

        return sha1(getTimestamp(), message, parents.toString(), tracked.toString());
    }
    /**
     * Save this commit instance to file in objects folder.
     */
    public void save() {

        saveObjectFile(file, this);
    }

    /**
     * Get the Date instance when the commit is created.
     *
     * @return Date instance
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the timestamp.
     *
     * @return Data and time
     */
    public String getTimestamp() {
        // THU JAN 1 00:00:00 1970 +0000
        DateFormat dataFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy z", Locale.ENGLISH);
        return dataFormat.format(date);

    }

    /**
     * Get the commit message.
     *
     * @return Commit message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the parent commit ids.
     *
     * @return Array of parent commit ids.
     */
    public List<String> getParents() {
        return parents;
    }

    /**
     * Get the tracked files Map with file path as key and SHA1 id as value
     *
     * @return Map with file path as key and SHA1 id as value
     */
    public Map<String, String> getTracked() {
        return tracked;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean restoreTracked(String filePath) {
        String blobId = tracked.get(filePath);
        if (blobId == null) {
            return false;
        }
        Blob.fromFile(blobId).writeContentToSource();
        return true;
    }

    /**
     * Restore all tracked files, overwriting the existing ones.
     */
    public void restoreAllTracked() {
        for (String blobId : tracked.values()) {
            Blob.fromFile(blobId).writeContentToSource();
        }
    }

    /**
     * Get the SHA1 id.
     *
     * @return SHA1 id
     */
    public String getId() {
        return id;
    }

    /**
     * Get the commit log.
     * @return Log content
     */
    public String getLog() {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("===").append("\n");
        logBuilder.append("commit").append(" ").append(id).append("\n");
        if (parents.size() > 1) {
            logBuilder.append("Merge:");
            for (String parent: parents) {
                logBuilder.append(" ").append(parent, 0, 7);
            }
            logBuilder.append("\n");
        }
        logBuilder.append("Date:").append(" ").append(getTimestamp()).append("\n");
        logBuilder.append(message).append("\n");
        return logBuilder.toString();
    }
}
