package view;

import model.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

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
        this(new TaskModel("New Task", "Description", new Date()));
    }
    /**
     * Recommended constructor for TaskView objects.
     * @param task A {@link TaskModel} whose data this TaskView is to represent.
     */
    public TaskView(TaskModel task) {
        super(new GridLayout(3, 1, 2, 1), true);

        this.task = task;
        // Give the task an outline
        setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new LineBorder(Color.BLACK)));
        // Initial label settings
        title = new JLabel(task.getTitle(), JLabel.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setMinimumSize(DEFAULT_MIN_SIZE);
        add(title);
        description = new JLabel(task.getDescription(), JLabel.LEFT);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        description.setMinimumSize(DEFAULT_MIN_SIZE);
        add(description);
        due = new JLabel(task.getDue().toString(), JLabel.CENTER);
        due.setAlignmentX(Component.CENTER_ALIGNMENT);
        due.setMinimumSize(DEFAULT_MIN_SIZE);
        add(due);
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
