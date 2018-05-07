package view;

import model.*;
import javax.swing.*;

public class ColumnView extends JPanel {
    private ColumnModel column;

    public ColumnView() {
        super(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setColumn(null);
    }
    /**
     * Recommended constructor for ColumnView objects.
     * @param column A ColumnModel object whose data this ColumnView is to represent.
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