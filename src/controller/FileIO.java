package controller;

import model.*;
import java.io.*;
import java.util.ArrayList;

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

    public static ArrayList<String> searchPrivileges(int userHash) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("privileges")))) {
            for (UserInfo info = (UserInfo) in.readObject(); info != null; info = (UserInfo) in.readObject())
                if (info.getUserHash() == userHash)
                    return info;
        } catch(IOException ioe) {
            System.out.println("Error: FIle not found or access denied");
        } catch(ClassNotFoundException cnfe) {
            System.out.println("Data file corrupted: Privileges file not readable");
        }
        return null;
    }

    /**
     * Writes a new user to the privileges file if that user already exists.
     * @param userHash The hash of the username and password to add.
     * @return Whether the user was added.
     */
    public static boolean createUser(int userHash) {
        if (searchPrivileges(hash) == null) {
            UserInfo newUser = new UserInfo(hash);
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("privileges"))) {
                @Override protected void writeStreamHeader() throws IOException { reset(); }
            }) {
                out.writeObject(newUser);
                return true;
            } catch(IOException ioe) {
                label.setText("Could not create new user.");
            }
        }
        return false;
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