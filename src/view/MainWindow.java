package view;

import controller.EditProject;
import controller.FileIO;
import controller.Manager;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow {
    public MainWindow(FileIO.UserInfo projList) {
        JFrame frame = new JFrame("Task Board");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        frame.setBounds(0, 0, 1024, 512);

        JLabel pLabel = new JLabel("Select Project");
        ProjectView projView = new ProjectView();//gridx 0 gridy 1
        JComboBox<String> pScroll = new JComboBox<String>();
        pScroll.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // TODO ask if user wants to save
                    try {
                        projView.setProject(FileIO.read((String) e.getItem()));
                        projView.revalidate();
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        populateProjectList(pScroll, projList);//Project list scroll
        /**FileIO.searchPrivileges(projList.getUserHash())*/
        //TODO FIX TO SHOW PROJECTS LIST

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileIO.loadProject(projView.getProject(), projList, pScroll);
//                JOptionPane.showMessageDialog(null, "Load", "Test Box", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new EditProject(frame, jPanel, projView);
                JFrame editFrame = new JFrame("Edit");
                editFrame.setMinimumSize(new Dimension(1024, 256));

                ProjectModel projModel = projView.getProject();
                // Shallow copy.
                ArrayList<ColumnModel> cols = new ArrayList<ColumnModel>(projModel);
                JPanel columnPanel = new JPanel();
                JTextArea title = new JTextArea(projModel.getTitle());
                columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.X_AXIS));
                for (int i = 0; i < cols.size(); i ++)
                    columnPanel.add(new ColumnPanel(cols, i, columnPanel));
                JButton addColumn = new JButton("+");
                addColumn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        synchronized(this) {
                            cols.add(new ColumnModel(""));
                            columnPanel.add(new ColumnPanel(cols, cols.size() - 1, columnPanel));
                        }
                        columnPanel.revalidate();
                    }
                });
                JButton cancel = new JButton("Cancel");
                cancel.addActionListener(new Windows.CancelListener(editFrame));
                JButton confirm = new JButton("Confirm");
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ColumnModel[] columns = new ColumnModel[cols.size()];
                        Component[] comps = columnPanel.getComponents();
                        for (int i = 0; i < columns.length; i ++) {
                            columns[i] = cols.get(i);
                            columns[i].setTitle(((ColumnPanel) comps[i]).titleField.getText());
                        }
                        projView.setProject(new ProjectModel(title.getText(), columns));
                        projView.revalidate();
                        editFrame.dispose();
                    }
                });

                editFrame.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridheight = c.gridwidth = c.gridx = 1;
                c.ipadx += 5;
                c.ipady += 5;
                c.gridy = 0;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.2;
                c.weighty = 0.4;
                editFrame.add(title, c);
                c.gridx --;
                c.weightx = 0.1;
                editFrame.add(new JLabel("Project Title"), c);
                c.ipadx -= 5;
                c.ipady -= 5;
                c.gridy ++;
                c.weighty = 0.1;
                editFrame.add(new JLabel("Columns"), c);
                c.weightx = 0;
                c.gridy ++;
                c.gridwidth = 4;
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;
                editFrame.add(new JScrollPane(columnPanel), c);
                c.weighty = 0.1;
                c.weightx = 0;
                c.fill = GridBagConstraints.NONE;
                c.gridwidth = 1;
                c.gridx = 4;
                editFrame.add(addColumn, c);
                c.gridy ++;
                editFrame.add(confirm, c);
                c.gridx --;
                editFrame.add(cancel, c);

                editFrame.setVisible(true);
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Save", "Test Box", JOptionPane.INFORMATION_MESSAGE);
                FileIO.saveProject(projView.getProject(), projList, pScroll);

            }
        });

        JButton newProjButton = new JButton("Create New Project");
        newProjButton.addActionListener(new CreateWindowListener(Windows.WindowType.CREATEPROJECT, projView));
        // pScroll.addItem(projView);

        JButton removeProjectButton = new JButton("Remove Project");
        removeProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame rmFrame = new JFrame("Remove project?");
                rmFrame.setLayout(new GridBagLayout());
                rmFrame.setMinimumSize(new Dimension(320, 96));
                rmFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JButton cancel = new JButton("No");
                cancel.addActionListener(new Windows.CancelListener(rmFrame));
                JButton confirm = new JButton("Yes");
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = pScroll.getSelectedIndex();
                        FileIO.removeProject(pScroll, projList);
                        if (index > 0) {
                            pScroll.setSelectedItem(index - 1);
                        } else if (index < pScroll.getItemCount()) {
                            pScroll.setSelectedIndex(index);
                        } else {
                            projView.setProject(new model.ProjectModel());
                        }
                        rmFrame.dispose();
                    }
                });

                GridBagConstraints c = new GridBagConstraints();
                c.gridx = c.gridy = 0;
                c.gridwidth = 2;
                c.gridheight = 1;
                c.fill = GridBagConstraints.HORIZONTAL;
                rmFrame.add(new JLabel("Are you sure you want to remove this project?"), c);
                c.gridwidth = c.gridy = c.gridx = 1;
                rmFrame.add(confirm, c);
                c.gridx --;
                rmFrame.add(cancel, c);

                cancel.setSelected(true);
                rmFrame.setVisible(true);
            }
        });

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
        jPanel.add(removeProjectButton, c);
        c.gridx = 7;
        jPanel.add(logOutButton, c);
        c.gridwidth = 9;
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

    private void populateProjectList(JComboBox<String> pScroll, FileIO.UserInfo projList) {
        for (int i = 0; i < projList.size(); i ++)
            pScroll.addItem(projList.get(i));
        return;
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

    private static class ColumnPanel extends JPanel {
        ArrayList<ColumnModel> columns;
        int index;
        Container container;
        JTextField titleField;

        ColumnPanel(ArrayList<ColumnModel> columns, int index, Container container) {
            this.columns = columns;
            this.index = index;
            this.container = container;
            titleField = new JTextField(columns.get(index).getTitle());
            JButton left = new JButton("◀");
            left.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (container.getComponentCount() == 1)
                        return;
                    if (index > 0)
                        swapConsecutive(index - 1);
                    else
                        swapFirstAndLast();
                    container.revalidate();
                }
            });
            JButton remove = new JButton("−");
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    synchronized(this) {
                        container.remove(index);
                        columns.remove(index);
                    }
                    container.revalidate();
                }
            });
            JButton right = new JButton("▶");
            right.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (container.getComponentCount() == 1)
                        return;
                    if (index >= container.getComponentCount() - 1)
                        swapFirstAndLast();
                    else
                        swapConsecutive(index);
                    container.revalidate();
                }
            });

            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = c.gridy = 0;
            c.gridwidth = 3;
            c.gridheight = 1;
            c.fill = GridBagConstraints.HORIZONTAL;
            add(titleField, c);
            c.gridwidth = 1;
            c.gridy ++;
            add(left, c);
            c.gridx ++;
            add(remove, c);
            c.gridx ++;
            add(right, c);
        }

        private synchronized void swapFirstAndLast() {
            int lastIndex = columns.size() - 1;
            ColumnModel lastCol = columns.remove(lastIndex);
            ColumnModel firstCol = columns.remove(0);
            columns.add(0, lastCol);
            columns.add(lastIndex, firstCol);
            ColumnPanel firstComp = (ColumnPanel) container.getComponent(0);
            ColumnPanel lastComp = (ColumnPanel) container.getComponent(lastIndex);
            container.remove(lastIndex);
            container.remove(0);
            container.add(lastComp, 0);
            container.add(firstComp, lastIndex);
            firstComp.index = lastIndex;
            lastComp.index = 0;
        }
        private synchronized void swapConsecutive(int first) {
            columns.add(first + 1, columns.remove(first));
            ColumnPanel firstComp = (ColumnPanel) container.getComponent(first);
            container.remove(first);
            firstComp.index ++;
            ((ColumnPanel) container.getComponent(first)).index --;
            container.add(firstComp, first + 1);
        }
    }
}
