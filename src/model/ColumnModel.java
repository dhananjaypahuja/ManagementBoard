package model;

import java.util.LinkedList;

/**
 * Represents the model for a column in a project.
 */
public class ColumnModel extends ListModel<TaskModel> {
    private static final long serialVersionUID = 1L;

	@Override
    public ColumnModel() {
        setTitle("New column");
    }
}