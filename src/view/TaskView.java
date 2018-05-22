package view;

import controller.Manager;
import model.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Visually represents a task in a project
 */
public class TaskView extends JPanel {
    // Reference to task data
    private TaskModel task;
    // Text to display
    private JTextArea title, description, due;

    // Give task text labels a minimum size
    private static final Dimension DEFAULT_MIN_SIZE = new Dimension(128, 18);

    public TaskView() {
        this(new TaskModel("New Task", "Description", new Date()));
    }
    /**
     * Recommended constructor for TaskView objects.
     * @param task A {@link TaskModel} whose data this TaskView is to represent.
     */
    public TaskView(TaskModel task) {
        super(new GridLayout(3, 1, 2, 1), true);

        this.task = task;
        // Give the task an outline and background
        setBorder(new CompoundBorder(new EmptyBorder(2, 2, 2, 2), new LineBorder(Color.BLACK)));
        setBackground(task.getColor());
        // Initial label settings
        MouseListener listener = new EditListener(this);
        title = makeTextArea(task.getTitle(), listener);
        title.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        description = makeTextArea(task.getDescription(), listener);
        description.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        due = makeTextArea(task.getDue().toString(), listener);
        due.setFont(new Font("Sans-Serif", Font.ITALIC, 12));
        add(title);
        add(description);
        add(due);
        setTask(task);
        this.addMouseListener(listener);
    }

    private static JTextArea makeTextArea(String text, MouseListener listener) {
        JTextArea area = new JTextArea(text);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setMinimumSize(DEFAULT_MIN_SIZE);
        area.setPreferredSize(DEFAULT_MIN_SIZE);
        area.setAlignmentX(Component.CENTER_ALIGNMENT);
        area.setBorder(new EmptyBorder(2, 2, 2, 2));
        area.setBackground(new Color(255, 255, 255, 63));
        area.addMouseListener(listener);
        return area;
    }

    public void setTask(TaskModel task) {
        this.task = task;
        updateLabels();
    }
    public TaskModel getTask() {
        return task;
    }

    /**
     * Ensures that the text displayed by this TaskView object is up to date.
     */
    public void updateLabels() {
        title.setText(task.getTitle());
        description.setText(task.getDescription());
        due.setText(java.text.DateFormat.getDateInstance().format(task.getDue()));
    }

    @Override
    public void revalidate() {
        try {
            updateLabels();
        } catch(NullPointerException npe) {}
        super.revalidate();
    }

    private class EditListener implements MouseListener {
        TaskView tView;
        EditListener(TaskView tView) {
            this.tView = tView;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            ProjectView pView = getParentProject();

            JComboBox<String> statusPanel = new JComboBox<String>(Manager.columnTitles(pView.getProject()));
            TaskModel tModel = tView.getTask();

            JTextArea title = new JTextArea(tModel.getTitle());
            JTextArea description = new JTextArea(tModel.getDescription());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -100);
            Date earliest = calendar.getTime();
            calendar.add(Calendar.YEAR, 200);
            Date latest = calendar.getTime();
            Date due = tModel.getDue();
            SpinnerDateModel dateModel = new SpinnerDateModel(due, earliest, latest, Calendar.YEAR);
            JSpinner dateSpinner = new JSpinner(dateModel);
            dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));

            JFrame frame = new JFrame("Edit Task");
            frame.setLayout(new GridBagLayout());
            frame.setMinimumSize(new Dimension(256, 160));

            JButton confirm = new JButton("Confirm");
            confirm.addActionListener(new ConfirmEditListener(frame, tView, title, description, dateModel, statusPanel, pView));
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new Windows.CancelListener(frame));

            JButton delete = new JButton("Delete");
            delete.setForeground(Color.RED);
            delete.addActionListener(new DeleteListener(frame, tView, pView));

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = c.gridy = 0;
            c.gridwidth = c.gridheight = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(1, 0, 1, 0);
            c.ipady = 20;
            frame.add(new JLabel("Title"), c);
            c.gridx ++;
            frame.add(title, c);
            c.gridy ++;
            frame.add(description, c);
            c.gridx --;
            frame.add(new JLabel("Description"), c);
            c.ipady = 0;
            c.gridy ++;
            frame.add(new JLabel("Due date"), c);
            c.gridx ++;
            frame.add(dateSpinner, c);
            c.gridy ++;
            frame.add(new JLabel("Status"), c);
            c.gridx --;
            frame.add(statusPanel, c);
            c.gridy ++;
            frame.add(cancel, c);
            c.gridx ++;
            frame.add(confirm, c);
            c.gridy++;
            c.gridx++;
            frame.add(delete, c);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        }
        @Override public void mousePressed(MouseEvent e) {}
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}

        private ProjectView getParentProject() {
            Container c;
            for (c = tView.getParent(); !(c instanceof ProjectView); c = c.getParent());
            return (ProjectView) c;
        }
    }
    private class ConfirmEditListener implements ActionListener {
        private JFrame frame;
        private TaskView tView;
        private JTextArea title, description;
        private SpinnerDateModel date;
        JComboBox statusPanel;
        private ProjectView pView;
        ConfirmEditListener(JFrame frame, TaskView tView, JTextArea title, JTextArea description, SpinnerDateModel date, JComboBox statusPanel, ProjectView pView) {
            this.frame = frame;
            this.tView = tView;
            this.title = title;
            this.description = description;
            this.date = date;
            this.statusPanel = statusPanel;
            this.pView = pView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            TaskModel tModel = tView.getTask();
            tModel.setTitle(title.getText());
            tModel.setDescription(description.getText());
            tModel.setDue(date.getDate());
            int indexFrom = Manager.indexOfColumnContainingTask(pView.getProject(), tModel);
            int indexTo = statusPanel.getSelectedIndex();
            if (indexFrom != indexTo) {
                Component[] columns = pView.getComponents();
                ColumnView columnFrom = (ColumnView) columns[indexFrom];
                ColumnView columnTo = (ColumnView) columns[indexTo];
                Manager.addTask(columnTo, Manager.removeTask(columnFrom, tModel), 0);
            }
            tView.revalidate();
            frame.dispose();
        }

//        public void selectColumn(JComboBox statusPanel, ProjectView projectView){
//
//            if(statusPanel.getSelectedIndex()== Manager.indexOfColumnContainingTask(projectView.getProject(), tView.getTask())){
//                System.out.println("Status unchanged");
//            } else {
//                //tView.getTask().add
//                //then remove from revius column
//                        //doessomething have to be done with updateLabels and putting this method in there
//            }
//        }
    }

    private class DeleteListener implements ActionListener {
        private JFrame frame;
        private TaskView tView;
        private ProjectView pView;

        DeleteListener(JFrame frame, TaskView tView, ProjectView pView) {
            this.frame = frame;
            this.tView = tView;
            this.pView = pView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Manager.removeTask(getParentProject(), tView.getTask());
            tView.revalidate();
            frame.dispose();
        }

        private ColumnView getParentProject() {
            Container c;
            for (c = tView.getParent(); !(c instanceof ColumnView); c = c.getParent());
            return (ColumnView) c;
        }
    }
}
