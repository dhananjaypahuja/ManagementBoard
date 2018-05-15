package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows {

    public enum WindowType {
        LOGIN, CREATEPROJECT, CREATETASK
    }

    JFrame popWindow;
    JPanel panel;
    GridBagConstraints c;

    public Windows(WindowType popType) {
        this.popWindow = new JFrame();
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
        JLabel projName = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel Columns = new JLabel("Columns:");
        JButton columnButton = new JButton("+");
        columnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //adds new field asking title of column with a - button to delete colunm
            }
        });

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

        panel.setVisible(true);
        popWindow.add(panel);
        popWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popWindow.setVisible(true);
    }

}
