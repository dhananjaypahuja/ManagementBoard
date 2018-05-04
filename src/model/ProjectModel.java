package model;

import java.util.LinkedList;

/**
 * Represents the model for a project.
 */
public class ProjectModel extends ListModel<ColumnModel> {
    private static final long serialVersionUID = 1L;

	@Override
    public ProjectModel() {
        setTitle("New Project");
    }
}