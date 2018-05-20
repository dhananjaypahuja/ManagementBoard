package controller;

import model.*;
import java.io.*;
import java.util.*;

public class FileIO {
    /**
     * Reads a {@link ProjectModel} from a file. The desired object must appear on its own
     * in the file under this implementation.
     * @param filepath The path to the file containing the {@link ProjectModel}.
     * @return The {@link ProjectModel} in the file, if any.
     */
    public static ProjectModel read(String filepath) throws IOException, ClassNotFoundException {
        return read(new File(filepath));
    }
    /**
     * Reads a {@link ProjectModel} from a file. The desired object must appear on its own
     * in the file under this implementation.
     * @param file The file containing the {@link ProjectModel}.
     * @return The {@link ProjectModel} in the file, if any.
     */
    public static ProjectModel read(File file) throws IOException, ClassNotFoundException {
        ProjectModel model = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            model = (ProjectModel) in.readObject();
        } catch(IOException ioe) {
            throw ioe;
        } catch(ClassNotFoundException cfne) {
            throw cfne;
        }
        return model;
    }

    /**
     * Writes a {@link ProjectModel} to a file. Any pre-existing data will be overwritten.
     * @param filepath The path to the file to which to write the {@link ProjectModel}.
     */
    public static void write(String filepath, ProjectModel project) throws IOException {
        write(new File(filepath), project);
    }
    /**
     * Writes a {@link ProjectModel} to a file. Any pre-existing data will be overwritten.
     * @param file The file to which to write the {@link ProjectModel}.
     */
    public static void write(File file, ProjectModel project) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(project);
            out.flush();
        } catch(IOException ioe) {
            throw ioe;
        }
    }

    /**
     * Generates hashcodes for username and password
     */
    public static int hashcode(String username, char[] password) {
        String str = username + "\n" + new String(password);
        return str.hashCode();
    }

    /**
     * Writes a new user to the privileges file if that user already exists.
     * @param userHash The hash of the username and password to add.
     * @return Whether the user was added.
     */
    public static boolean createUser(int userHash) {
        ArrayList<UserInfo> privileges = readPrivileges();
        for (UserInfo info : privileges)
            if (info.getUserHash() == userHash)
                return false;
        privileges.add(new UserInfo(userHash));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("./privileges")))) {
            out.writeObject(privileges);
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }
    public static UserInfo searchPrivileges(int userHash) {
        List<UserInfo> userInfo = readPrivileges();
        for (UserInfo info : userInfo)
            if (info.getUserHash() == userHash)
                return info;
        return null;
    }
    public static ArrayList<UserInfo> readPrivileges() {
        ArrayList<UserInfo> infoList = new ArrayList<UserInfo>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("./privileges")))) {
            infoList = (ArrayList<UserInfo>) in.readObject();
        } catch(EOFException eofe) {
            return new ArrayList<UserInfo>();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return infoList;
    }
    /**
     * Add a filepath to the list of files a user denoted by a hash is authorized to access.
     * @param userHash The user's hash, determined by username and password.
     * @param filepath The filepath to add.
     * @return Whether the filepath was added. If the user was already authorized to access
     *         the file, it is not duplicated in the user's privileges.
     */
    public static boolean addFilePrivilege(int userHash, String filepath) {
        List<UserInfo> userInfo = readPrivileges();
        for (UserInfo info : userInfo)
            if (info.getUserHash() == userHash)
                if (info.contains(filepath))
                    return false;
                else {
                    info.add(filepath);
                    return true;
                }
        throw new RuntimeException("User hash not found.");
    }

    /**
     * Subclass serializable to save valid userhashcodes and project access
     */
    public static class UserInfo extends ArrayList<String> {
        private static final long serialVersionUID = 1L;

        private int userHash;

        public UserInfo(int userHash) {
            this.userHash = userHash;
        }

        public int getUserHash() {
            return userHash;
        }

        public void setUserHash(int userHash) {
            this.userHash = userHash;
        }
    }
}