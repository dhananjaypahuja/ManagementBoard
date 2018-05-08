package controller;

import model.*;
import view.*;
import javax.swing.*;

/**
 * Use this class whenever possible to manipulate data.
 */
public class Manager {
    // A bunch of duplicate code here. Probably should be changed, but it works for now.

    /**
     * Adds a column to a project view and model.
     * @param pView The {@link ProjectView} to which the column should be added.
     * @param cModel The {@link ColumnModel} representing the column to add.
     * @param index The position in the project at which to add the column.
     */
    public static void addColumn(ProjectView pView, ColumnModel cModel, int index) {
        pView.getProject().add(index, cModel);
        pView.add(new ColumnView(cModel), index);
    }
    /**
     * Adds a task to a column view and model.
     * @param cView The {@link ColumnView} to which the task should be added.
     * @param tModel The {@link TaskModel} representing the task to add.
     * @param index The position in the column at which to add the task.
     */
    public static void addTask(ColumnView cView, TaskModel tModel, int index) {
        cView.getColumn().add(index, tModel);
        cView.add(new TaskView(tModel), index);
    }
    /**
     * Removes a column from a project view and model.
     * @param pView The {@link ProjectView} from which the column should be removed.
     * @param index The position of the column to remove.
     * @return The removed column.
     */
    public static ColumnModel removeColumn(ProjectView pView, int index) {
        ColumnModel col = pView.getProject().remove(index);
        pView.remove(index);
        return col;
    }
    /**
     * Removes a task from a column view and model.
     * @param cView The {@link ColumnView} from which the task should be removed.
     * @param index The position of the task to remove.
     * @return The removed task.
     */
    public static TaskModel removeTask(ColumnView cView, int index) {
        TaskModel task = cView.getColumn().remove(index);
        cView.remove(index);
        return task;
    }
    /**
     * Swaps two columns in a project view and model.
     * @param pView The {@link ProjectView} of the project whose columns should be swapped.
     * @param index1 The position of one column to swap.
     * @param index2 The position of the other column to swap.
     * @return Whether a change occurred.
     */
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
        // Swap models and views
        ColumnModel cModel2 = pModel.remove(index2);
        ColumnModel cModel1 = pModel.remove(index1);
        ColumnView cView2 = (ColumnView) pView.getComponent(index2);
        pView.remove(index2);
        ColumnView cView1 = (ColumnView) pView.getComponent(index1);
        pView.remove(index1);
        pView.add(cView2, index1);
        pView.add(cView1, index2);
        pModel.add(index1, cModel2);
        pModel.add(index2, cModel1);
        // Inform the project view that a change has occurred
        pView.revalidate();
        return true;
    }
    /**
     * Swaps two tasks in a column view and model.
     * @param pView The {@link ColumnView} of the column whose tasks should be swapped.
     * @param index1 The position of one task to swap.
     * @param index2 The position of the other task to swap.
     * @return Whether a change occurred.
     */
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
        // Swap models and views
        TaskModel tModel2 = cModel.remove(index2);
        TaskModel tModel1 = cModel.remove(index1);
        TaskView tView2 = (TaskView) cView.getComponent(index2);
        cView.remove(index2);
        TaskView tView1 = (TaskView) cView.getComponent(index1);
        cView.remove(index1);
        cView.add(tView2, index1);
        cView.add(tView1, index2);
        cModel.add(index1, tModel2);
        cModel.add(index2, tModel1);
        // Inform the column view that a change has occurred
        cView.revalidate();
        return true;
    }
}