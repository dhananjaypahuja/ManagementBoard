package view;

import model.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

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
        setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(2, 2, 2, 2)));
    }

    public void setColumn(ColumnModel column) {
        this.column = column;
        this.removeAll();
        JLabel title = new JLabel(column.getTitle());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        for (TaskModel task : column)
            add(new TaskView(task));
    }
    public ColumnModel getColumn() {
        return column;
    }
}
