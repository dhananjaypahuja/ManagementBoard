package view;

import model.*;
import java.awt.*;
import javax.swing.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

/**
 * Visually represents a task in a project
 */
public class TaskView extends JPanel {
    // Reference to task data
    private TaskModel task;
    // Text to display
    private JLabel title, description, due;

    // Give task text labels a minimum size
    private static final Dimension DEFAULT_MIN_SIZE = new Dimension(128, 18);

    public TaskView() {
        super(new GridLayout(3, 1, 2, 1), true);
        // Give the task an outline
        setBorder(BorderFactory.createLineBorder(Color.black));
        // Initial label settings
        title = new JLabel("New Task", JLabel.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setMinimumSize(DEFAULT_MIN_SIZE);
        add(title);
        description = new JLabel("Description", JLabel.LEFT);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        description.setMinimumSize(DEFAULT_MIN_SIZE);
        add(description);
        due = new JLabel("Date", JLabel.CENTER);
        due.setAlignmentX(Component.CENTER_ALIGNMENT);
        due.setMinimumSize(DEFAULT_MIN_SIZE);
        add(due);
    }
    /**
     * Recommended constructor for TaskView objects.
     * @param task A {@link TaskModel} whose data this TaskView is to represent.
     */
    public TaskView(TaskModel task) {
        this();
        setTask(task);
    }

    public void setTask(TaskModel task) {
        this.task = task;
        updateLabels();
    }
    public TaskModel getTask() {
        return task;
    }

    /**
     * Ensures that the text displayed by this TaskView object is up to date.
     */
    public void updateLabels() {
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        due.setText(task.getDue().toString());
    }
}