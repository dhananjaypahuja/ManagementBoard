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
    private JTextArea title, description, due;

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
        title = makeTextArea(task.getTitle());
        title.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        description = makeTextArea(task.getDescription());
        description.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        due = makeTextArea(task.getDue().toString());
        due.setFont(new Font("Sans-Serif", Font.ITALIC, 12));
        add(title);
        add(description);
        add(due);
        setTask(task);
    }

    private static JTextArea makeTextArea(String text) {
        JTextArea area = new JTextArea(text);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setMinimumSize(DEFAULT_MIN_SIZE);
        area.setPreferredSize(DEFAULT_MIN_SIZE);
        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        area.setBorder(new EmptyBorder(2, 2, 2, 2));
        return area;
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
