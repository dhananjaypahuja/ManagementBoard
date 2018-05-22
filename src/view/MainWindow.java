package view;

import controller.FileIO;
import controller.Main;
import controller.Manager;
import model.ColumnModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    public MainWindow(FileIO.UserInfo projList) {
        JFrame frame = new JFrame("Task Board");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        frame.setBounds(0, 0, 700, 500);

        JLabel pLabel = new JLabel("Select Project");
        ProjectView projView = new ProjectView();//gridx 0 gridy 1
        JComboBox pScroll = new JComboBox(Manager.columnTitles(projView.getProject())); //Project list scroll
        //TODO FIX TO SHOW PROJECTS LIST

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Load", "Test Box", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Edit", "Test Box", JOptionPane.INFORMATION_MESSAGE);
//                Windows taskWindow = new Windows(Windows.WindowType.CREATETASK);
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Delete", "Test Box", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Save", "Test Box", JOptionPane.INFORMATION_MESSAGE);
                FileIO.saveProject(projView.getProject());

            }
        });

        JButton newProjButton = new JButton("Create New Project");
        newProjButton.addActionListener(new CreateWindowListener(Windows.WindowType.CREATEPROJECT, projView));

        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Log Out", "Test Box", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                Windows taskWindow = new Windows(Windows.WindowType.LOGIN);

            }
        });

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
