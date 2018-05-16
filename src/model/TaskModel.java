package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents the model for a task in a project.
 */
public class TaskModel implements Serializable, Comparable<TaskModel> {
    private static final long serialVersionUID = 2L;

    private String title, description;
    private Date due;
    private Color color;

    public TaskModel(String title, String description, Date due) {
        this(title, description, due, Color.WHITE);
    }
    public TaskModel(String title, String description, Date due, Color color) {
        this.title = title;
        this.description = description;
        this.due = due;
        this.color = color;
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
    public Color getColor() {
        return color;
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
    public void setColor(Color color) {
        this.color = color;
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
