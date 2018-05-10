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
    private JPanel subpanel;

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
        if (getComponentCount() > 0 && getComponent(0) instanceof JLabel)
            remove(0);
        JLabel title = new JLabel(column.getTitle());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title, 0);
        if (getComponentCount() == 1)
            add(new JScrollPane());
        subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.Y_AXIS));
        for (TaskModel task : column)
            subpanel.add(new TaskView(task));
        ((JScrollPane) getComponent(1)).setViewportView(subpanel);
        subcomponents(this, 1);
    }
    public ColumnModel getColumn() {
        return column;
    }

    public JPanel getSubpanel() {
        return subpanel;
    }

    private static void subcomponents(Container c, int spaces) {
        for (Component d : c.getComponents()) {
            for (int i = 0; i < spaces; i ++)
                System.out.print("  ");
            System.out.println(d.toString());
            if (d instanceof Container)
                subcomponents((Container) d, spaces + 1);
        }
    }
}
