package controller;

import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProject {

    JFrame frame;
    JPanel panel; //main panel
    JPanel columnPanel; // panel displaying existing columns with 2 navigation buttons and a "-" button on opposite side
    GridBagConstraints c;

    public EditProject(JFrame frame, JPanel panel, ProjectView columnPanel) {
        frame = new JFrame("Edit Project");
        panel = new JPanel();
        this.columnPanel = columnPanel;
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        frame.setBounds(0, 0, 1024, 512);

        JLabel rename = new JLabel("Rename Project:");
        JTextField newNameField = new JTextField();

        JButton addColumn = new JButton("+");

        addColumn.addActionListener(new AddColumnListener( columnPanel ,panel));

        c.gridx = 0;
        c.gridy = 0;
        panel.add(rename,c);
        c.gridx++;
        panel.add(newNameField, c);
        c.gridx--;
        c.gridy++;
        panel.add(addColumn, c);
    }
    private static class AddColumnListener implements ActionListener {
        JPanel columns, mainPanel;

        public AddColumnListener(JPanel columns, JPanel mainPanel) {
            this.columns = columns;
            this.mainPanel = mainPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //adds new field asking title of column with a - button to delete colunm
            JPanel column = new JPanel();
            column.setLayout(new BoxLayout(column, BoxLayout.Y_AXIS));
            column.add(new JTextField());
            JButton remove = new JButton("−");
            remove.addActionListener(new RemoveColumnListener(columns, column, mainPanel));
            column.add(remove);
            columns.add(column);
            mainPanel.revalidate();
        }

        // Connect this to a button under a column in a project editor. See above.
        private static class RemoveColumnListener implements ActionListener {
            JPanel columns, column, mainPanel;

            public RemoveColumnListener(JPanel columns, JPanel column, JPanel mainPanel) {
                this.columns = columns;
                this.column = column;
                this.mainPanel = mainPanel;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                columns.remove(column);
                mainPanel.revalidate();
            }
        }
    }
}