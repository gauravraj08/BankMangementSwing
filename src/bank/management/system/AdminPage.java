package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage {

    private JFrame frame;
    private JTextField adminTextField;
    private JPasswordField passwordField;

    /**
     * Create the application.
     */
    public AdminPage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin Page");
        frame.getContentPane().setLayout(null);

        JLabel lblAdmin = new JLabel("Admin:");
        lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAdmin.setBounds(50, 50, 60, 20);
        frame.getContentPane().add(lblAdmin);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(50, 100, 80, 20);
        frame.getContentPane().add(lblPassword);

        adminTextField = new JTextField();
        adminTextField.setBounds(140, 50, 200, 25);
        frame.getContentPane().add(adminTextField);
        adminTextField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 100, 200, 25);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String admin = adminTextField.getText();
                String password = new String(passwordField.getPassword());

                // Check if the admin password matches
                if (admin.equals("admin") && password.equals("passthis")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    frame.dispose(); // Close the current frame
                    // Redirect to the menu page
                    new Menu();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Admin or Password!");
                }
            }
        });


        btnLogin.setBounds(180, 150, 80, 30);
        frame.getContentPane().add(btnLogin);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminPage().frame.setVisible(true);
    }
}
