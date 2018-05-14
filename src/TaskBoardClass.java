//import javax.swing.*;
//import java.awt.event.*;
//import java.awt.*;
//
//public class TaskBoardClass extends JPanel
//{
//
//    private static final long serialVersionUID = 1L;
//
//    public void paintComponent(Graphics g2) {
//        Graphics2D g = (Graphics2D) g2;
//        g.setColor(Color.GRAY);
//        g.setStroke(new BasicStroke(3));
//
//        g.drawLine(10,120, 1900 , 120);
//        g.drawLine(160,120,160,1030);
//        g.drawLine(620, 120, 620, 1030);
//        g.drawLine(1080,120,1080,1030);
//        g.drawLine(1540,120,1540,1030);
//
//
//        g.drawLine(10,5, 1900, 5);
//        g.drawLine(10,5, 10, 1030);
//        g.drawLine(10,1030,1900,1030);
//        g.drawLine(1900,5,1900,1030);
//
//    }
//
//    public static void main(String[] args)
//    {
//        final int size = 100;
//
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        final JFrame f = new JFrame("Task Board");
//
//        final TaskBoard l = new TaskBoard(size, Color.RED);
//        final JLabel label = new JLabel(l);
//        label.setBounds(150,500,size,size);
//
//        JButton create = new JButton("Create Project");
//        create.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                l.changeColor(Color.RED);
//                label.repaint();
//
//            }
//        });
//
//        JButton edit = new JButton("Edit");
//        edit.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                l.changeColor(Color.GREEN);
//                label.repaint();
//
//            }
//        });
//
//        JButton save = new JButton("Save");
//        save.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                l.changeColor(Color.BLUE);
//                label.repaint();
//
//            }
//        });
//
//        JButton delete = new JButton("Delete");
//        delete.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//
//            }
//        });
//
//        JButton load = new JButton("Load...");
//        load.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//
//            }
//        });
//
//        JButton logout = new JButton("Log Out");
//        logout.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//
//            }
//        });
//
//		/*JButton todoButton = new JButton("+");
//		logout.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//
//			}
//        });
//
//		JButton inProgressButton = new JButton("+");
//		logout.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//
//			}
//        });
//
//		JButton reviewButton = new JButton("+");
//		logout.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//
//			}
//        });
//
//		JButton doneButton = new JButton("+");
//		logout.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//
//			}
//		}); */
//
//
//
//
//
//        JLabel selectText = new JLabel("Select Project: ");
//		/*JLabel todoText = new JLabel("TODO");
//		todoText.setFont(new Font("Serif", Font.BOLD, 30));
//		JLabel inProgressText = new JLabel("In Progress");
//		inProgressText.setFont(new Font("Serif", Font.BOLD, 30));
//		JLabel reviewText = new JLabel("Review");
//		reviewText.setFont(new Font("Serif", Font.BOLD, 30));
//		JLabel doneText = new JLabel("DONE");
//		doneText.setFont(new Font("Serif", Font.BOLD, 30));
//*/
//
//        create.setBounds(30,150,100,40);
//        edit.setBounds(30,220,100,40);
//        save.setBounds(30,290,100,40);
//        delete.setBounds(30,360,100,40);
//        load.setBounds(30,430,100,40);
//        logout.setBounds(30,500,100,40);
//
//        selectText.setBounds(30, 50, 100, 30);
//        //todoText.setBounds(330,130,400,40);
//        //inProgressText.setBounds(790,130,400,40);
//        //reviewText.setBounds(1250,130,400,40);
//        //doneText.setBounds(1710,130,400,40);
//
//        //todoButton.setBounds(330,180, 100,40);
//        //inProgressButton.setBounds(810,180,100,40);
//        //reviewButton.setBounds(1250,180,100,40);
//        //doneButton.setBounds(1710,180,100,40);
//
//        f.add(label);
//
//        f.add(create);
//        f.add(edit);
//        f.add(save);
//        f.add(delete);
//        f.add(load);
//        f.add(logout);
//
//        f.add(selectText);
//        //f.add(todoText);
//        //f.add(inProgressText);
//        //f.add(reviewText);
//        //f.add(doneText);
//
//        //f.add(todoButton);
//        //f.add(inProgressButton);
//        //f.add(reviewButton);
//        //f.add(doneButton);
//
//        TaskBoardClass panel = new TaskBoardClass();
//
//        f.add(panel);
//
//        //f.setSize(400,500);
//        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        //f.setLayout(null);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);
//
//
//    }
//}
//
