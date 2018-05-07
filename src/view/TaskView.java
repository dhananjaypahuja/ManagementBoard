package view;

import model.*;
import javax.swing.*;

public class TaskView extends JPanel {
    private TaskModel task;
    private JLabel title, description, due;

    public TaskView(TaskModel task) {
        super(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.task = task;
        title = new JLabel(task.getTitle(), JLabel.CENTER);
        description = new JLabel(task.getDescription(), JLabel.LEFT);
        due = new JLabel(task.getDue().toString(), JLabel.CENTER);
        add(title);
        add(description);
        add(due);
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