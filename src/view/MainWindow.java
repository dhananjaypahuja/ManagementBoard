package view;

import controller.EditProject;
import controller.FileIO;
import controller.Manager;

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
//                JOptionPane.showMessageDialog(null, "Edit", "Test Box", JOptionPane.INFORMATION_MESSAGE);
//                Windows taskWindow = new Windows(Windows.WindowType.CREATETASK);
                new EditProject(frame, jPanel, projView.getProject());
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
}
