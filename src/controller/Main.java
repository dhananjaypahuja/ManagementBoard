package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // TODO main process implementation
//        dumbExample();
        JFrame frame = new JFrame("Task Board");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        frame.setBounds(0, 0, 500, 500);

        JLabel pLabel = new JLabel("Select Project");
        JScrollPane pScroll = new JScrollPane(); //Project list scroll
        ProjectView projView = new ProjectView();//gridx 0 gridy 1

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Load", "Test Box", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Edit", "Test Box", JOptionPane.INFORMATION_MESSAGE);
                Windows taskWindow = new Windows(Windows.WindowType.CREATETASK);
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Delete", "Test Box", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Save", "Test Box", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton newProjButton = new JButton("Create New Project");
        newProjButton.addActionListener(new CreateWindowListener(Windows.WindowType.CREATEPROJECT, projView));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Log Out", "Test Box", JOptionPane.INFORMATION_MESSAGE);
                Windows taskWindow = new Windows(Windows.WindowType.LOGIN);
            }
        });

        TaskModel tModel1a = new TaskModel("Task 1a", "Description 1a", new Date());
        TaskModel tModel1b = new TaskModel("Task 1b", "Description 1b", new Date());
        TaskModel tModel2a = new TaskModel("Task 2a", "Description 2a", new Date());
        TaskModel tModel2b = new TaskModel("Task 2b", "Description 2b", new Date());
        ColumnModel cModel1 = new ColumnModel("Column 1", tModel1a, tModel1b);
        ColumnModel cModel2 = new ColumnModel("Column 2", tModel2a, tModel2b);
        ProjectModel pModel = new ProjectModel("Project", cModel1, cModel2);
        projView.setProject(pModel);

        //specify diff grid x (changes) 0-6 grid y is 0
        c.gridx = 0;
        c.gridy = 0;
        jPanel.add(pLabel, c);
        c.gridx = 1;
        jPanel.add(pScroll, c);
        c.gridx = 2;
        jPanel.add(newProjButton, c);
        c.gridx = 3;
        jPanel.add(saveButton, c);
        c.gridx = 4;
        jPanel.add(editButton, c);
        c.gridx = 5;
        jPanel.add(loadButton, c);
        c.gridx = 6;
        jPanel.add(deleteButton, c);
        c.gridx = 7;
        jPanel.add(logOutButton, c);
        c.gridwidth = 8;
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        jPanel.add(projView, c);


        jPanel.setVisible(true);
        frame.add(jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    static class CreateWindowListener implements ActionListener {
        private JPanel callback;
        private Windows.WindowType type;
        public CreateWindowListener(Windows.WindowType type, JPanel callback) {
            this.callback = callback;
            this.type = type;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            new Windows(type, callback);
        }
    }
}