package view;

import model.*;
import javax.swing.*;

public class ProjectView extends JPanel {
    private ProjectModel project;

    public ProjectView() {
        super(true);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setProject(null);
    }
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