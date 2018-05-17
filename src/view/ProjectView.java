package view;

import model.*;
import javax.swing.*;

/**
 * Visually represents a project.
 */
public class ProjectView extends JPanel {
    // Reference to project data
    private ProjectModel project;

    public ProjectView() {
        this(new ProjectModel());
    }
    /**
     * Recommended constructor for ProjectView objects.
     * @param project A {@link ProjectModel} object whose data this ProjectView is to represent.
     */
    public ProjectView(ProjectModel project) {
        super(true);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setProject(project);
    }

    public void setProject(ProjectModel project) {
        this.project = project;
        this.removeAll();
        for (ColumnModel col : project)
            add(new ColumnView(col));
    }
    public ProjectModel getProject() {
        return project;
    }
}