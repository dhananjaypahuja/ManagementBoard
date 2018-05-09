package view;

import model.*;
import javax.swing.*;

/**
 * Visually represents a column in a project.
 */
public class ColumnView extends JPanel {
    // Reference to column data
    private ColumnModel column;

    public ColumnView() {
        this(new ColumnModel());
    }
    /**
     * Recommended constructor for ColumnView objects.
     * @param column A {@link ColumnModel} object whose data this ColumnView is to represent.
     */
    public ColumnView(ColumnModel column) {
        super(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setColumn(column);
    }

    public void setColumn(ColumnModel column) {
        this.column = column;
        this.removeAll();
        for (TaskModel task : column)
            add(new TaskView(task));
    }
    public ColumnModel getColumn() {
        return column;
    }
}
