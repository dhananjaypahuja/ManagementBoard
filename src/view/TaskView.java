package view;

import model.*;
import java.awt.Component;
import javax.swing.*;

public class TaskView extends JPanel {
    private TaskModel task;
    private JLabel title, description, due;

    public TaskView() {
        super(new GridLayout(3, 1, 2, 1), true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        title = new JLabel("New Task", JLabel.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        description = new JLabel("Description", JLabel.LEFT);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(description);
        due = new JLabel("Date", JLabel.CENTER);
        due.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(due);

    }
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
    public void updateLabels() {
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        due.setText(task.getDue().toString());
    }
}