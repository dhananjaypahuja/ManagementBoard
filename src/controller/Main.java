package controller;

import model.*;
import view.*;
import java.util.Date;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // TODO main process implementation
        dumbExample();
    }
    
    public static void dumbExample() {
        JFrame frame = new JFrame("Test Frame");
        TaskModel tModel1a = new TaskModel("Task 1a", "Description 1a", new Date());
        TaskModel tModel1b = new TaskModel("Task 1b", "Description 1b", new Date());
        TaskModel tModel2a = new TaskModel("Task 2a", "Description 2a", new Date());
        TaskModel tModel2b = new TaskModel("Task 2b", "Description 2b", new Date());
        ColumnModel cModel1 = new ColumnModel("Column 1", tModel1a, tModel1b);
        ColumnModel cModel2 = new ColumnModel("Column 2", tModel2a, tModel2b);
        ProjectModel pModel = new ProjectModel("Project", cModel1, cModel2);
        ProjectView pView = new ProjectView(pModel);
        frame.add(pView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 256, 256);
        frame.setVisible(true);
        for (int i = 0; i < 10; i ++) {
            try {
                Thread.sleep(500);
            } catch(Exception e) {
                e.printStackTrace();
            }
            Manager.swapColumns(pView, 0, 1);
        }
    }
}