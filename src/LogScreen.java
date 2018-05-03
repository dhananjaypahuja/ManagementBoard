import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LogScreen{

    JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogScreen window = new LogScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public LogScreen() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(400, 400, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblNewLabel = new JLabel("Task Board Login");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel.setBounds(124, 32, 124, 16);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.RED);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 15));
        lblUsername.setBounds(83, 120, 88, 16);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.RED);
        lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
        lblPassword.setBounds(83, 177, 81, 16);

        textField = new JTextField();
        textField.setBounds(203, 117, 116, 22);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(203, 174, 116, 22);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(lblNewLabel);
        frame.getContentPane().add(lblUsername);
        frame.getContentPane().add(lblPassword);
        frame.getContentPane().add(textField);
        frame.getContentPane().add(passwordField);

        JButton btnLoginButton = new JButton("LOGIN");
        btnLoginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = textField.getText();
                String password = passwordField.getText();

                // read password and username from file and then continue with the code below

                if(username.contains("CS151") && password.contains("123456"))
                {
                    JOptionPane.showMessageDialog(null, "Login Successful", "Please Try Again.", JOptionPane.ERROR_MESSAGE);
                    textField.setText(null);
                    passwordField.setText(null);

                    // connect this class to TaskBoard class using the video you have seen over the weekend
                    TaskBoardClass board = new TaskBoardClass();
                    board.main(null);
                }

                else
                {
                    JOptionPane.showMessageDialog(null, "Login Incorrect. Please try again. ", "", JOptionPane.ERROR_MESSAGE);
                    textField.setText(null);
                    passwordField.setText(null);
                }
            }
        });
        btnLoginButton.setBounds(139, 262, 97, 25);
        frame.getContentPane().add(btnLoginButton);

        JSeparator separator = new JSeparator();
        separator.setBounds(54, 238, 292, 2);
        frame.getContentPane().add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(54, 86, 292, 2);
        frame.getContentPane().add(separator_1);
    }
}