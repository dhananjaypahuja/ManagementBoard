package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the model for a task in a project.
 */
public class TaskModel implements Serializable, Comparable<TaskModel> {
    private static final long serialVersionUID = 1L;

    private String title, description;
    private Date due;

    public TaskModel(String title, String description, Date due) {
        this.title = title;
        this.description = description;
        this.due = due;
    }

    // Accessors
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public Date getDue() {
        return due;
    }
    // Mutators
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDue(Date due) {
        this.due = due;
    }

    /**
     * Checks whether the task's due date has passed.
     * @return `true` if and only if the due date is before now.
     */
    public boolean isDue() {
        return due.after(new Date());
    }

    @Override
    public int compareTo(TaskModel t) {
        return due.compareTo(t.getDue());
    }
}
