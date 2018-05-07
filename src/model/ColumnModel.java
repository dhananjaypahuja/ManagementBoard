package model;

import java.util.LinkedList;

/**
 * Represents the model for a column in a project.
 */
public class ColumnModel extends ListModel<TaskModel> {
    private static final long serialVersionUID = 1L;

    public ColumnModel() {
        setTitle("New column");
    }
    public ColumnModel(String title, TaskModel... contents) {
        super(title, contents);
    }
}