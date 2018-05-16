package view;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows {

    public enum WindowType {
        LOGIN, CREATEPROJECT, CREATETASK
    }

    JFrame popWindow;
    JPanel panel, callback;
    GridBagConstraints c;

    public Windows(WindowType popType) { this(popType, null); }
    public Windows(WindowType popType, JPanel callback) {
        this.popWindow = new JFrame();
        this.callback = callback;
        popWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.panel = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        run(popType);
    }

    private void run(WindowType popType) {
        switch(popType){
            case LOGIN:
                System.out.println("Login");
                loginView();
                break;
            case CREATETASK:
                System.out.println("Create new Task");
                newTask();
                break;
            case CREATEPROJECT:
                System.out.println("Create new Project");
                newProj();
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    private void newTask() {
        JLabel taskName = new JLabel("Task Name:");
        JTextField nameField = new JTextField();
        JLabel taskDescription = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        descriptionField.setBounds(0,0,200,200);
        JLabel taskStatus = new JLabel("Status:");
        JScrollPane statusPanel = new JScrollPane();
        JLabel taskDate = new JLabel("Due Date:");
        JTextField dateField = new JTextField();

        c.gridx = 0;
        c.gridy = 0;
        panel.add(taskName, c);
        c.gridx = 1;
        panel.add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(taskDescription, c);
        c.gridx = 1;
        panel.add(descriptionField, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(taskStatus, c);
        c.gridx = 1;
        panel.add(statusPanel, c);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(taskDate, c);
        c.gridx = 1;
        panel.add(dateField, c);

        panel.setVisible(true);
        popWindow.add(panel);
        popWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popWindow.setVisible(true);
    }

    private void loginView() {
        JLabel username = new JLabel("Username:");
        JTextField nameField = new JTextField();
        JLabel password = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("LOGIN");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        c.gridx = 0;
        c.gridy = 0;
        panel.add(username, c);
        c.gridx = 1;
        panel.add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(password, c);
        c.gridx = 1;
        panel.add(passwordField, c);

        panel.setVisible(true);
        popWindow.add(panel);
        popWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popWindow.setVisible(true);
    }

    private void newProj() {
        if (!(callback instanceof ProjectView))
            throw new RuntimeException("Requires reference to a ProjectView");
        JLabel projName = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel Columns = new JLabel("Columns:");
        JButton columnButton = new JButton("+");
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");

        JPanel columnsPanel = new JPanel();
        columnsPanel.setLayout(new BoxLayout(columnsPanel, BoxLayout.X_AXIS));

        columnButton.addActionListener(new AddColumnListener(columnsPanel, panel));
        confirm.addActionListener(new CreateProjectListener(nameField, (ProjectView) callback, columnsPanel, popWindow));
        cancel.addActionListener(new CancelListener(popWindow));

        c.gridx = 0;
        c.gridy = 0;
        panel.add(projName, c);
        c.gridx = 1;
        panel.add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(Columns, c);
        c.gridx = 1;
        panel.add(columnButton, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.NONE;
        panel.add(columnsPanel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(cancel, c);
        c.gridx = 1;
        panel.add(confirm, c);

        panel.setVisible(true);
        popWindow.add(panel);
        popWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popWindow.setVisible(true);
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
    }
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
    private static class CancelListener implements ActionListener {
        private JFrame frame;
        public CancelListener(JFrame frame) { this.frame = frame; }
        @Override public void actionPerformed(ActionEvent e) { frame.dispose(); }
    }
    private static class CreateProjectListener implements ActionListener {
        JTextField name;
        ProjectView pView;
        JPanel columns;
        JFrame frame;
        public CreateProjectListener(JTextField name, ProjectView pView, JPanel columns, JFrame frame) {
            this.name = name;
            this.pView = pView;
            this.columns = columns;
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ProjectModel proj = new ProjectModel(name.getText());
            Component[] components = columns.getComponents();
            for (Component c : components)
                proj.add(new ColumnModel(((JTextField) ((Container) c).getComponent(0)).getText()));
            pView.setProject(proj);
            pView.revalidate();
            frame.dispose();
        }
    }
}
