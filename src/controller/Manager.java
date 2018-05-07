package controller;

import model.*;
import view.*;
import javax.swing.*;

/**
 * Use this class whenever possible to manipulate data.
 */
public class Manager {
    // A bunch of duplicate code here. Probably should be changed, but it works for now.
    public static boolean addColumn(ProjectView pView, ColumnModel cModel, int index) {
        if (index < 0 || index >= pView.getComponentCount())
            return false;
        pView.getProject().add(index, cModel);
        pView.add(new ColumnView(cModel), index);
        return true;
    }
    public static boolean addTask(ColumnView cView, TaskModel tModel, int index) {
        if (index < 0 || index >= pView.getComponentCount())
            return false;
        cView.getColumn().add(index, tModel);
        cView.add(new TaskView(tModel), index);
        return true;
    }
    public static ColumnModel removeColumn(ProjectView pView, int index) {
        ColumnModel col = pView.getProject().remove(index);
        pView.remove(index);
        return col;
    }
    public static TaskModel removeTask(ColumnView cView, int index) {
        TaskModel task = cView.getColumn().remove(index);
        cView.remove(index);
        return task;
    }
    public static boolean swapColumns(ProjectView pView, int index1, int index2) {
        ProjectModel pModel = pView.getProject();
        // We may not have to do anything.
        if (index1 == index2 || index1 < 0 || index2 < 0 || index1 >= pModel.size() || index2 >= pModel.size())
            return false;
        // Order indices for consistent results.
        if (index1 > index2) {
            int hold = index1;
            index1 = index2;
            index2 = hold;
        }
        ColumnModel cModel2 = pModel.get(index2);
        ColumnModel cModel1 = pModel.get(index1);
        ColumnView cView2 = pView.remove(index2);
        ColumnView cView1 = pView.remove(index1);
        pView.add(cView1, index1);
        pView.add(cView2, index2);
        pModel.add(index1, cModel1);
        pModel.add(index2, cModel2);
        return true;
    }
    public static boolean swapTasks(ColumnView cView, int index1, int index2) {
        ColumnModel cModel = cView.getColumn();
        // We may not have to do anything.
        if (index1 == index2 || index1 < 0 || index2 < 0 || index1 >= cModel.size() || index2 >= cModel.size())
            return false;
        // Order indices for consistent results.
        if (index1 > index2) {
            int hold = index1;
            index1 = index2;
            index2 = hold;
        }
        TaskModel tModel2 = cModel.get(index2);
        TaskModel tModel1 = cModel.get(index1);
        TaskView tView2 = cView.remove(index2);
        TaskView tView1 = cView.remove(index1);
        cView.add(tView1, index1);
        cView.add(tView2, index2);
        cModel.add(index1, tModel1);
        cModel.add(index2, tModel2);
        return true;
    }
}