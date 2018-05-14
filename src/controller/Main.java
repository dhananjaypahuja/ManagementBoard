package controller;

import model.*;
import view.*;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // TODO main process implementation
//        dumbExample();
        JFrame frame = new JFrame("Task Board");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        frame.setBounds(0, 0, 500, 500);

        JLabel pLabel = new JLabel("Select Project");
        pLabel.setVerticalTextPosition(JLabel.TOP);
        pLabel.setHorizontalTextPosition(JLabel.LEFT);
        frame.getContentPane().add(pLabel);

        //Project list scroll
        JScrollPane pScroll = new JScrollPane();
        frame.getContentPane().add(pScroll);

        JButton loadButton = new JButton("Load");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton saveButton = new JButton("Save");
        JButton newPButton = new JButton("Create New");
        JButton logOutButton = new JButton("Log Out");

        jPanel.add(loadButton);
        jPanel.add(editButton);
        jPanel.add(deleteButton);
        jPanel.add(saveButton);
        jPanel.add(newPButton);
        jPanel.add(logOutButton);


        jPanel.setVisible(true);
        frame.add(jPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void dumbExample() {
        // Set up components
        JFrame frame = new JFrame("Test Frame");
        TaskModel tModel1a = new TaskModel("Task 1a", "Description 1a", new Date());
        TaskModel tModel1b = new TaskModel("Task 1b", "Description 1b", new Date());
        TaskModel tModel2a = new TaskModel("Task 2a", "Description 2a", new Date());
        TaskModel tModel2b = new TaskModel("Task 2b", "Description 2b", new Date());
        ColumnModel cModel1 = new ColumnModel("Column 1", tModel1a, tModel1b);
        ColumnModel cModel2 = new ColumnModel("Column 2", tModel2a, tModel2b);
        ProjectModel pModel = new ProjectModel("Project", cModel1, cModel2);
        ProjectView pView = new ProjectView(pModel);

        // Put everything in the frame
        frame.add(pView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 384, 256);
        frame.setVisible(true);

        for (int i = 0; i < 10; i ++) {
            try {
                Thread.sleep(500);
            } catch(Exception e) {
                e.printStackTrace();
            }
            // Demonstrate column swapping
            Manager.swapColumns(pView, 0, 1);
            try {
                Thread.sleep(250);
            } catch(Exception e) {
                e.printStackTrace();
            }
            // Demonstrate task swapping
            Manager.swapTasks((ColumnView) pView.getComponent(0), 0, 1);
        }

        try {
            FileIO.write("./example.proj", pModel);
            System.out.println(FileIO.read("./example.proj").toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}