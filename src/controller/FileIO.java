package controller;

import model.*;
import java.io.*;
import java.lang.reflect.Array;
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
    public static int hashcode(String username, String password){
        String str = username + "\n" + password;
        return str.hashCode();
    }

    /**
     * Subclass serializable to save valid userhashcodes and project access
     */
    class UserInfo extends ArrayList<String> {
        private static final long serialVersionUID = 1L;

        private int userHash;
        UserInfo(int userHash){
            this.userHash = userHash;
        }

        public int getUserHash() {
            return userHash;
        }

        public void setUserHash(int userHash) {
            this.userHash = userHash;
        }
    }

    private static ArrayList<String> searchPrivileges(int userHash) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("privileges")))) {
            for (UserInfo info = (UserInfo) in.readObject(); info != null; info = (UserInfo) in.readObject())
                if (info.getUserHash() == userHash)
                    return info;
        } catch(IOException ioe) {
            throw ioe;
        } catch(ClassNotFoundException cnfe) {
            throw cnfe;
        }
        return null;
    }
}