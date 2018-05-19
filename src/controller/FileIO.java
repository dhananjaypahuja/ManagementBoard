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

    private static ArrayList<String> searchPrivileges(int userHash) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("privileges")))) {
            for (UserInfo info = (UserInfo) in.read(); info != null; info = (UserInfo) in.read())
                if (info.getHash() == userHash)
                    return info;
        } catch(IOException ioe) {
            throw ioe;
        } catch(ClassNotFoundException cnfe) {
            throw cnfe;
        }
        return null;
    }
}